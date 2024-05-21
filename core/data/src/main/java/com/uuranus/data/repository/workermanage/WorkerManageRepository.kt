package com.uuranus.data.repository.workermanage

import com.uuranus.model.MyPossibleTimeInfo
import com.uuranus.model.MyScheduleInfo
import com.uuranus.model.ScheduleUpdate
import com.uuranus.model.WorkerInfo

interface WorkerManageRepository {

    suspend fun getAllWorkers(
        accessToken: String,
        storeId: Int,
    ): List<WorkerInfo>

    suspend fun changeWorkerType(
        accessToken: String,
        storeId: Int,
        memberId: Int,
        workerType: String,
    ): Boolean

    suspend fun deleteWorker(
        accessToken: String,
        storeId: Int,
        memberId: Int,
    ): Boolean

}