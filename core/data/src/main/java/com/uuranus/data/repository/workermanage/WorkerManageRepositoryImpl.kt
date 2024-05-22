package com.uuranus.data.repository.workermanage

import com.uuranus.model.WorkerInfo
import com.uuranus.myschedule.core.network.datasource.WorkerDataSource
import javax.inject.Inject

class WorkerManageRepositoryImpl @Inject constructor(
    private val dataSource: WorkerDataSource,
) : WorkerManageRepository {
    override suspend fun getAllWorkers(
        accessToken: String, storeId: Int,
    ): List<WorkerInfo> {
        return dataSource.getAllWorkers(accessToken, storeId)
    }

    override suspend fun changeWorkerType(
        accessToken: String,
        storeId: Int,
        memberId: Int,
        workerType: String,
    ): Boolean {
        return dataSource.changeWorkerType(
            accessToken, storeId, memberId, workerType
        )
    }

    override suspend fun deleteWorker(
        accessToken: String, storeId: Int, memberId: Int,
    ): Boolean {
        return dataSource.deleteWorker(
            accessToken,
            storeId, memberId
        )
    }

}