package com.uuranus.domain

import com.uuranus.data.repository.workermanage.WorkerManageRepository
import javax.inject.Inject

class EditWorkerType @Inject constructor(
    private val repository: WorkerManageRepository,
) {
    suspend operator fun invoke(
        accessToken: String,
        storeId: Int,
        memberId: Int,
        workerType: String,
    ): Boolean {
        return repository.changeWorkerType(accessToken, storeId, memberId, workerType)
    }
}