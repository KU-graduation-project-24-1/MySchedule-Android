package com.uuranus.myschedule.core.network.service

import com.uuranus.myschedule.core.network.model.MonthlyScheduleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface MyScheduleService {
    @GET("/store/{storeId}/schedule/{dateYM}")
    suspend fun getMonthlySchedules(
        @Header("Authorization") authorization: String,
        @Path("storeId") storeId: Int,
        @Path("dateYM") dateYM: String,
    ): Response<MonthlyScheduleResponse>


}
