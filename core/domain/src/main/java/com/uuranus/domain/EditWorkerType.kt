package com.uuranus.domain

import com.uuranus.data.repository.workermanage.WorkerManageRepository
import javax.inject.Inject

class EditWorkerType @Inject constructor(
    private val repository: WorkerManageRepository,
) {
    suspend operator fun invoke(
        storeId: Int,
        memberId: Int,
        workerType: String,
    ): Boolean {
        return repository.changeWorkerType(storeId, memberId, workerType)
    }
}