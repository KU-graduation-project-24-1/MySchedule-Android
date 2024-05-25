package com.uuranus.myschedule.core.network.datasource

import com.uuranus.model.Store


interface StoreDataSource {
    suspend fun fetchStoreList(authorization: String): List<Store>
}