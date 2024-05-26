package com.uuranus.data.repository.store

import com.uuranus.model.Store
import com.uuranus.model.StoreSalesInformation
import com.uuranus.model.TimeRange

interface StoreRepository {
    suspend fun getStoreList(authorization: String): List<Store>
}