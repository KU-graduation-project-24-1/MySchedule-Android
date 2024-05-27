package com.uuranus.myschedule.core.network.model.mapper

import com.uuranus.model.MyPossibleTimeInfo
import com.uuranus.model.MyScheduleInfo
import com.uuranus.model.StoreSalesInformation
import com.uuranus.model.TimeRange
import com.uuranus.model.WorkerInfo
import com.uuranus.myschedule.core.network.model.AvailableTimesInDay
import com.uuranus.myschedule.core.network.model.DailyAvailableSchedule
import com.uuranus.myschedule.core.network.model.Employee
import com.uuranus.myschedule.core.network.model.GetAllWorkersResult
import com.uuranus.myschedule.core.network.model.GetFixedScheduleResponse
import com.uuranus.myschedule.core.network.model.GetMonthlyPossibleTimesResult
import com.uuranus.myschedule.core.network.model.GetMonthlyScheduleResult
import com.uuranus.myschedule.core.network.model.GetOperationInfoResult
import com.uuranus.myschedule.core.network.model.PatchScheduleResult
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

internal fun GetMonthlyPossibleTimesResult.toData(): HashMap<String, List<MyPossibleTimeInfo>> {
    val map = HashMap<String, List<MyPossibleTimeInfo>>()
    dailyAvailableSchehdules.forEach {
        map[it.date] = it.availableTimesInDay.map { it2 ->
            it2.toData()
        }
    }

    return map
}

internal fun AvailableTimesInDay.toData(): MyPossibleTimeInfo = MyPossibleTimeInfo(
    storeMemberAvailableTimeId = storeAvailableScheduleId,
    startTime = startTime,
    endTime = endTime
)


internal fun List<GetOperationInfoResult>.toData(): StoreSalesInformation {
    return if (isEmpty()) {
        StoreSalesInformation(0, emptyList())
    } else {
        StoreSalesInformation(
            this[0].requiredEmployees,
            map {
                TimeRange(
                    timeId = it.storeOperationInfoId,
                    startTime = it.startTime.dropLast(3),
                    endTime = it.endTime.dropLast(3)
                )
            }
        )
    }

}


internal fun GetFixedScheduleResponse.toData(): List<List<TimeRange>> {
    val list: List<MutableList<TimeRange>> = listOf(
        mutableListOf(),
        mutableListOf(),
        mutableListOf(),
        mutableListOf(),
        mutableListOf(),
        mutableListOf(),
        mutableListOf(),
    )

    for (element in dayOfWeeks.indices) {
        val listIndex = when (dayOfWeeks[element]) {
            "MONDAY" -> 0
            "TUESDAY" -> 1
            "WEDNESDAY" -> 2
            "THURSDAY" -> 3
            "FRIDAY" -> 4
            "SATURDAY" -> 5
            "SUNDAY" -> 6
            else -> 0
        }

        list[listIndex].add(
            TimeRange(
                timeId = availableTimeByDayId[element],
                startTime = startTimes[element].dropLast(3),
                endTime = endTimes[element].dropLast(3)
            )
        )
    }

    return list.map { it.toList() }
}