package com.uuranus.myschedule.core.network.model

import com.squareup.moshi.JsonClass
import com.uuranus.model.LoginResult
import com.uuranus.model.StoreResult


@JsonClass(generateAdapter = true)
data class ApiResponse<T>(
    val code: Int,
    val status: Int,
    val message: String,
    val result: T,
)

@JsonClass(generateAdapter = true)
data class GetMonthlyScheduleResult(
    val daySchedules: List<DaySchedule>,
)

@JsonClass(generateAdapter = true)
data class DaySchedule(
    val date: String,
    val workDatas: List<WorkData>,
)

@JsonClass(generateAdapter = true)
data class WorkData(
    val scheduleId: Int,
    val memberId: Int,
    val memberName: String,
    val memberGrade: String,
    val startTime: String,
    val endTime: String,
    val coverRequested: Boolean,
    val mine: Boolean,
)

@JsonClass(generateAdapter = true)
data class GetMonthlyPossibleTimesResult(
    val memberGrade: String,
    val dailyAvailableSchehdules: List<DailyAvailableSchedule>,
)

@JsonClass(generateAdapter = true)
data class DailyAvailableSchedule(
    val date: String,
    val availableTimesInDay: List<AvailableTimesInDay>,
)

@JsonClass(generateAdapter = true)
data class AvailableTimesInDay(
    val storeAvailableScheduleId: Int,
    val startTime: String,
    val endTime: String,
)


@JsonClass(generateAdapter = true)
data class PostPossibleTime(
    val storeAvailableScheduleId: Int,
)

@JsonClass(generateAdapter = true)
data class GetAllWorkersResult(
    val employees: List<Employee>,
)

@JsonClass(generateAdapter = true)
data class Employee(
    val name: String,
    val memberId: Int,
    val memberGrade: String,
)

@JsonClass(generateAdapter = true)
data class PostScheduleResult(
    val scheduleId: Int,
    val employeeId: Int,
    val date: String,
    val startTime: String,
    val endTime: String,
)

@JsonClass(generateAdapter = true)
data class PatchScheduleResult(
    val scheduleId: Int?,
    val employeeId: Int?,
    val date: String?,
    val startTime: String?,
    val endTime: String?,
    val deleted: Boolean?,
)

@JsonClass(generateAdapter = true)
data class LoginResponse(
    val result: LoginResult,
)

@JsonClass(generateAdapter = true)
data class SignUpResponse(
    val code: Int,
    val status: Int,
    val message: String,
    val result: String,
)

@JsonClass(generateAdapter = true)
data class StoreResponse(
    val code: Int,
    val status: Int,
    val message: String,
    val result: StoreResult,
)

@JsonClass(generateAdapter = true)
data class GetOperationInfoResult(
    val dayOfWeek: String,
    val storeOperationInfoId: Int,
    val startTime: String,
    val endTime: String,
    val requiredEmployees: Int,
)

@JsonClass(generateAdapter = true)
data class PostOperationInfoResponse(
    val storeOperationInfoId: Int,
)

@JsonClass(generateAdapter = true)
data class PatchFixedScheduleResponse(
    val storeAvailableTimeByDayId: Int,
)

@JsonClass(generateAdapter = true)
data class GetFixedScheduleResponse(
    val availableTimeByDayId: List<Int>,
    val dayOfWeeks: List<String>,
    val startTimes: List<String>,
    val endTimes: List<String>,
)
