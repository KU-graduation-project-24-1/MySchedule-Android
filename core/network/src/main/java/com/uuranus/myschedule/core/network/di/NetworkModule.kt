package com.uuranus.myschedule.core.network.di

import com.uuranus.myschedule.core.network.service.MyScheduleService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS) // 연결 시간 초과 설정
            .readTimeout(10, TimeUnit.SECONDS) // 읽기 시간 초과 설정
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val request = chain.request()

                try {
                    val response = chain.proceed(request)
                    val bodyString = response.body!!.string()
                    return@addInterceptor response.newBuilder()
                        .body(bodyString.toResponseBody(response.body?.contentType()))
                        .build()
                } catch (e: Exception) {
                    when (e) {
                        is SocketTimeoutException -> {
                            throw SocketTimeoutException()
                        }

                        else -> throw e
                        // Add additional errors... //

                    }
                }
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://3.39.59.95:9000/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMyScheduleService(retrofit: Retrofit): MyScheduleService {
        return retrofit.create(MyScheduleService::class.java)
    }

}
