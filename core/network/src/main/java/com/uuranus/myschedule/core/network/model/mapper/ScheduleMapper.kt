package com.uuranus.myschedule.core.network.model.mapper

import com.uuranus.model.MyScheduleInfo
import com.uuranus.myschedule.core.network.model.MonthlyScheduleResponse
import com.uuranus.myschedule.core.network.model.MonthlyScheduleWorkDataResponse

internal fun MonthlyScheduleResponse.toData(): HashMap<String, List<MyScheduleInfo>> {
    val map = HashMap<String, List<MyScheduleInfo>>()
    result.daySchedules.forEach {
        map[it.date] = it.workDatas.map { it2 ->
            it2.toData()
        }
    }

    return map
}

internal fun MonthlyScheduleWorkDataResponse.toData(): MyScheduleInfo {
    return MyScheduleInfo(
        scheduleId = memberWorkingTimeId,
        startTime = startTime,
        endTime = endTime,
        memberId = memberId,
        workerName = memberName,
        workerType = memberGrade,
        isMine = mine,
        isFillInNeeded = coverRequested
    )
}