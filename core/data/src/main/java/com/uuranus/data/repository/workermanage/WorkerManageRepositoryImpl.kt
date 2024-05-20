package com.uuranus.data.repository.workermanage

import com.uuranus.model.WorkerInfo
import com.uuranus.myschedule.core.network.datasource.WorkerDataSource
import javax.inject.Inject

class WorkerManageRepositoryImpl @Inject constructor(
    private val dataSource: WorkerDataSource,
) : WorkerManageRepository {
    override suspend fun getAllWorkers(storeId: Int): List<WorkerInfo> {
        return dataSource.getAllWorkers(storeId)
    }

    override suspend fun changeWorkerType(
        storeId: Int,
        memberId: Int,
        workerType: String,
    ): Boolean {
        return dataSource.changeWorkerType(
            storeId, memberId, workerType
        )
    }

    override suspend fun deleteWorker(storeId: Int, memberId: Int): Boolean {
        return dataSource.deleteWorker(
            storeId, memberId
        )
    }

}