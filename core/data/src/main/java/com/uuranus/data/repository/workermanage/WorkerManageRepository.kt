package com.uuranus.data.repository.workermanage

import com.uuranus.model.MyPossibleTimeInfo
import com.uuranus.model.MyScheduleInfo
import com.uuranus.model.ScheduleUpdate
import com.uuranus.model.WorkerInfo

interface WorkerManageRepository {

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