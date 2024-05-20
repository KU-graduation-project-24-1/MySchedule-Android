package com.uuranus.myschedule.core.network.datasource

import com.uuranus.model.WorkerInfo

interface WorkerDataSource {
    suspend fun getAllWorkers(
        storeId: Int,
    ): List<WorkerInfo>

    suspend fun changeWorkerType(
        storeId: Int,
        memberId: Int,
        workerType: String,
    ): Boolean

    suspend fun deleteWorker(
        storeId: Int,
        memberId: Int,
    ): Boolean
}