package com.uuranus.domain

import com.uuranus.data.repository.home.HomeRepository
import com.uuranus.data.repository.store.StoreRepository
import com.uuranus.model.Store
import javax.inject.Inject


class GetStoreListUseCase @Inject constructor(
    private val repository: StoreRepository
) {
    suspend operator fun invoke(authorization: String): List<Store> {
        return repository.getStoreList(authorization)
    }
}