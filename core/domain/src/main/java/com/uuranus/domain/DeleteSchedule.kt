package com.uuranus.domain

import com.uuranus.data.repository.home.HomeRepository
import com.uuranus.model.WorkerInfo
import javax.inject.Inject

class DeleteSchedule @Inject constructor(
    private val repository: HomeRepository,
) {
    suspend operator fun invoke(
        accessToken: String,
        storeId: Int,
    ): Boolean {
        return repository.deleteSchedule(accessToken, storeId)
    }
}