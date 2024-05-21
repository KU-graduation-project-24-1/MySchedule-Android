package com.uuranus.myschedule.core.network.datasource

import com.uuranus.model.StoreSalesInformation
import com.uuranus.model.TimeRange

interface MyPageDataSource {

    suspend fun getFixedPossibleTimes(
        accessToken:String,): List<List<TimeRange>>

    suspend fun addFixedPossibleTime(
        accessToken:String,): String
     suspend fun getStoreSalesInformation(
         accessToken:String,): List<StoreSalesInformation>

     suspend fun addStoreOpeningHourTime(
         accessToken:String,
        storeId: Int,
    ): Boolean
}