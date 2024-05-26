package com.uuranus.myschedule.core.network.datasource

import com.uuranus.model.Store
import com.uuranus.myschedule.core.network.service.MyScheduleService
import javax.inject.Inject

class StoreDataSourceImpl @Inject constructor(
    private val service: MyScheduleService
) : StoreDataSource {
    override suspend fun fetchStoreList(authorization: String): List<Store> {
        val response = service.getStoreList(authorization)
        return response.result.myStores
    }
}