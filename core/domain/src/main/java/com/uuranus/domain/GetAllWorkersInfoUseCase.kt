package com.uuranus.domain

import com.uuranus.data.repository.workermanage.WorkerManageRepository
import com.uuranus.model.WorkerInfo
import javax.inject.Inject

class GetAllWorkersInfoUseCase @Inject constructor(
    private val repository: WorkerManageRepository,
) {
    suspend operator fun invoke(
        accessToken: String,
        storeId: Int,
    ): List<WorkerInfo> {
        return repository.getAllWorkers(accessToken, storeId)
    }
}