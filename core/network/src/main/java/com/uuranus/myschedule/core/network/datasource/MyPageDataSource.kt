package com.uuranus.myschedule.core.network.datasource

import com.uuranus.model.StoreSalesInformation
import com.uuranus.model.TimeRange

interface MyPageDataSource {

    suspend fun getFixedPossibleTimes(): List<List<TimeRange>>

    suspend fun addFixedPossibleTime(): String
     suspend fun getStoreSalesInformation(): List<StoreSalesInformation>

     suspend fun addStoreOpeningHourTime(
        storeId: Int,
    ): Boolean
}