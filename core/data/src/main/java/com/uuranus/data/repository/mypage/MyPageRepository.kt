package com.uuranus.data.repository.mypage

import com.uuranus.model.StoreSalesInformation
import com.uuranus.model.TimeRange

interface MyPageRepository {

    suspend fun getFixedPossibleTimes(
        accessToken: String,
    ): List<List<TimeRange>>

    suspend fun addFixedPossibleTime(
        accessToken: String,
    ): String

    suspend fun getStoreSalesInformation(
        accessToken: String,
    ): List<StoreSalesInformation>

    suspend fun addStoreOpeningHourTime(
        accessToken: String,
        storeId: Int,
    ): Boolean

    suspend fun deleteStore(
        accessToken: String,
        storeId: Int,
    ): Boolean
}