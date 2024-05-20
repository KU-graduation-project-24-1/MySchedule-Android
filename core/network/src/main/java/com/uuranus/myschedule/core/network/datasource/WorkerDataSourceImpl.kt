package com.uuranus.myschedule.core.network.datasource

import com.uuranus.model.MyPossibleTimeInfo
import com.uuranus.model.MyScheduleInfo
import com.uuranus.model.ScheduleUpdate
import com.uuranus.model.WorkerInfo
import com.uuranus.myschedule.core.network.service.MyScheduleService
import org.jetbrains.annotations.Async.Schedule
import javax.inject.Inject


private val workers = listOf(
    WorkerInfo(
        0,
        "최사장",
        "사장",
    ),
    WorkerInfo(
        1,
        "이직원",
        "매니저",
    ),
    WorkerInfo(
        2,
        "김알바",
        "아르바이트",
    ),
    WorkerInfo(
        3,
        "나알바",
        "아르바이트",
    )

)

class WorkerDataSourceImpl @Inject constructor(
//    private val service: MyScheduleService,
) : WorkerDataSource {

    override suspend fun getAllWorkers(storeId: Int): List<WorkerInfo> {
        return workers
    }

    override suspend fun changeWorkerType(
        storeId: Int,
        memberId: Int,
        workerType: String,
    ): Boolean {
        return true
    }

    override suspend fun deleteWorker(storeId: Int, memberId: Int): Boolean {
        return true
    }


}