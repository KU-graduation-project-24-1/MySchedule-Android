package com.uuranus.myschedule.core.network.service

import com.uuranus.myschedule.core.network.model.MonthlyScheduleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MyScheduleService {
    @GET("/store/schedule/{storeId}/{dateYM}")
    suspend fun getMonthlySchedules(
        @Path("storeId") storeId: String,
        @Path("dateYM") date: String,
    ): Response<MonthlyScheduleResponse>


}
