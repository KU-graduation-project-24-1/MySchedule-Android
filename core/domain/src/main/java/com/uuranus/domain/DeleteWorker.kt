package com.uuranus.domain

import com.uuranus.data.repository.home.HomeRepository
import com.uuranus.data.repository.workermanage.WorkerManageRepository
import javax.inject.Inject

class DeleteWorker @Inject constructor(
    private val repository: WorkerManageRepository,
) {
    suspend operator fun invoke(
        storeId: Int,
        memberId: Int,
    ): Boolean {
        return repository.deleteWorker(storeId, memberId)
    }
}