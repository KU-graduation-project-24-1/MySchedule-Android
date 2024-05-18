package com.uuranus.data.repository.mypage

import com.uuranus.model.StoreSalesInformation
import com.uuranus.model.TimeRange

interface MyPageRepository {

    suspend fun getFixedPossibleTimes(): List<List<TimeRange>>

    suspend fun addFixedPossibleTime(): String

    suspend fun getStoreSalesInformation(): List<StoreSalesInformation>

    suspend fun addStoreOpeningHourTime(
        storeId: Int,
    ): Boolean
}