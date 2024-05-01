package com.uuranus.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    private const val LOGIN_DATASTORE_NAME = "LOGIN_PREFERENCES"
    private val Context.loginDataStore by preferencesDataStore(LOGIN_DATASTORE_NAME)

    @Provides
    @Singleton
    @Named("user")
    fun provideLoginDataStore(
        @ApplicationContext context: Context,
    ): DataStore<Preferences> = context.loginDataStore


}