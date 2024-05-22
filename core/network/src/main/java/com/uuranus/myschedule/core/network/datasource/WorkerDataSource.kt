package com.uuranus.myschedule.core.network.datasource

import com.uuranus.model.WorkerInfo

interface WorkerDataSource {
    suspend fun getAllWorkers(
        accessToken:String,
        storeId: Int,
    ): List<WorkerInfo>

    suspend fun changeWorkerType(
        accessToken:String,
        storeId: Int,
        memberId: Int,
        workerType: String,
    ): Boolean

    suspend fun deleteWorker(
        accessToken:String,
        storeId: Int,
        memberId: Int,
    ): Boolean
}