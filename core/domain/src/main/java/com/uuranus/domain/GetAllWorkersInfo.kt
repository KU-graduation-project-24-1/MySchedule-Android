package com.uuranus.domain

import com.uuranus.data.repository.home.HomeRepository
import com.uuranus.data.repository.workermanage.WorkerManageRepository
import com.uuranus.model.MyPossibleTimeInfo
import com.uuranus.model.WorkerInfo
import javax.inject.Inject

class GetAllWorkersInfo @Inject constructor(
    private val repository: WorkerManageRepository,
) {
    suspend operator fun invoke(
        accessToken: String,
        storeId: Int,
    ): List<WorkerInfo> {
        return repository.getAllWorkers(accessToken, storeId)
    }
}