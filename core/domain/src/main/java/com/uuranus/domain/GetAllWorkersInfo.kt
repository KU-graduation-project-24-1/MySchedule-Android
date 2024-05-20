package com.uuranus.domain

import com.uuranus.data.repository.home.HomeRepository
import com.uuranus.model.MyPossibleTimeInfo
import com.uuranus.model.WorkerInfo
import javax.inject.Inject

class GetAllWorkersInfo @Inject constructor(
    private val repository: HomeRepository,
) {
    suspend operator fun invoke(
        storeId: Int,
    ): List<WorkerInfo> {
        return repository.getAllWorkers(storeId)
    }
}