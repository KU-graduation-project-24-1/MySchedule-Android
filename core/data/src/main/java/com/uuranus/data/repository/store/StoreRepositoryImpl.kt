package com.uuranus.data.repository.store

import com.uuranus.model.Store
import com.uuranus.myschedule.core.network.datasource.StoreDataSource
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val dataSource: StoreDataSource
) : StoreRepository {
    override suspend fun getStoreList(authorization: String): List<Store> {
        return dataSource.fetchStoreList(authorization)
    }
}