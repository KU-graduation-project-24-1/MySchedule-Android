package com.uuranus.myschedule.core.network.model.mapper

import com.uuranus.model.MyScheduleInfo
import com.uuranus.model.WorkerInfo
import com.uuranus.myschedule.core.network.model.Employee
import com.uuranus.myschedule.core.network.model.GetAllWorkersResult
import com.uuranus.myschedule.core.network.model.GetMonthlyScheduleResult
import com.uuranus.myschedule.core.network.model.WorkData

internal fun GetMonthlyScheduleResult.toData(): HashMap<String, List<MyScheduleInfo>> {
    val map = HashMap<String, List<MyScheduleInfo>>()
    daySchedules.forEach {
        map[it.date] = it.workDatas.map { it2 ->
            it2.toData()
        }
    }

    return map
}

internal fun WorkData.toData(): MyScheduleInfo {
    return MyScheduleInfo(
        scheduleId = scheduleId,
        startTime = startTime,
        endTime = endTime,
        memberId = memberId,
        workerName = memberName,
        workerType = memberGrade,
        isMine = mine,
        isFillInNeeded = coverRequested
    )
}

internal fun GetAllWorkersResult.toData(): List<WorkerInfo> {
    return employees.map {
        it.toData()
    }
}

internal fun Employee.toData(): WorkerInfo = WorkerInfo(
    memberId = memberId,
    workerName = name,
    workerType = memberGrade
)