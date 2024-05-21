package com.uuranus.myschedule.core.network.datasource

import com.uuranus.model.StoreSalesInformation
import com.uuranus.model.TimeRange
import javax.inject.Inject

class MyPageDataSourceImpl @Inject constructor(
//    private val service: MyScheduleService,
) : MyPageDataSource {
    override suspend fun getFixedPossibleTimes(): List<List<TimeRange>> {
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

    override suspend fun addFixedPossibleTime(): String {
        return "고정 시간 추가 성공"
    }

    override suspend fun getStoreSalesInformation(): List<StoreSalesInformation> {
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

    override suspend fun addStoreOpeningHourTime(
        storeId: Int,
    ): Boolean {
        return true
    }
}