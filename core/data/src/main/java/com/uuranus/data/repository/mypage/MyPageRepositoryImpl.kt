package com.uuranus.data.repository.mypage

import com.uuranus.model.StoreSalesInformation
import com.uuranus.model.TimeRange
import com.uuranus.myschedule.core.network.datasource.MyPageDataSource
import javax.inject.Inject

class MyPageRepositoryImpl @Inject constructor(
    private val dataSource: MyPageDataSource,
) : MyPageRepository {
    override suspend fun getFixedPossibleTimes(
        accessToken: String,
    ): List<List<TimeRange>> {
        return dataSource.getFixedPossibleTimes(accessToken)
    }

    override suspend fun addFixedPossibleTime(
        accessToken: String,
    ): String {
        return dataSource.addFixedPossibleTime(accessToken)
    }

    override suspend fun getStoreSalesInformation(
        accessToken: String,
    ): List<StoreSalesInformation> {
        return dataSource.getStoreSalesInformation(accessToken)
    }

    override suspend fun addStoreOpeningHourTime(
        accessToken: String,
        storeId: Int,
    ): Boolean {
        return dataSource.addStoreOpeningHourTime(accessToken, storeId)
    }

    override suspend fun deleteStore(accessToken: String, storeId: Int): Boolean {
        return dataSource.deleteStore(accessToken, storeId)
    }
}