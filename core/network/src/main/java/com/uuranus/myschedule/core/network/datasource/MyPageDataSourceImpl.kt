package com.uuranus.myschedule.core.network.datasource

import com.uuranus.model.StoreSalesInformation
import com.uuranus.model.TimeRange
import com.uuranus.myschedule.core.network.service.MyScheduleService
import javax.inject.Inject

class MyPageDataSourceImpl @Inject constructor(
    private val service: MyScheduleService,
) : MyPageDataSource {
    override suspend fun getFixedPossibleTimes(accessToken: String): List<List<TimeRange>> {
        return listOf(
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
        )
    }

    override suspend fun addFixedPossibleTime(accessToken: String): String {
        return "고정 시간 추가 성공"
    }

    override suspend fun getStoreSalesInformation(accessToken: String): List<StoreSalesInformation> {
        return listOf(
            StoreSalesInformation(1, emptyList()),
            StoreSalesInformation(2, emptyList()),
            StoreSalesInformation(2, emptyList()),
            StoreSalesInformation(3, emptyList()),
            StoreSalesInformation(4, emptyList()),
            StoreSalesInformation(0, emptyList()),
            StoreSalesInformation(0, emptyList()),
        )
    }

    override suspend fun addStoreOpeningHourTime(accessToken: String, storeId: Int): Boolean {
        return true
    }

    override suspend fun deleteStore(accessToken: String, storeId: Int): Boolean {
        return true
    }
}