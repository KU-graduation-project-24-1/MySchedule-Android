package com.uuranus.myschedule.core.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class MonthlyScheduleResponse(
    @field:Json(name = "code") val code: Int,
    @field:Json(name = "status") val status: Int,
    @field:Json(name = "message") val message: String,
    @field:Json(name = "result") val result: MonthlyScheduleResultResponse,
)

@JsonClass(generateAdapter = true)
data class MonthlyScheduleResultResponse(
    @field:Json(name = "daySchedules") val daySchedules: List<MonthlyScheduleDayScheduleResponse>,
)


@JsonClass(generateAdapter = true)
data class MonthlyScheduleDayScheduleResponse(
    @field:Json(name = "date") val date: String,
    @field:Json(name = "workDatas") val workDatas: List<MonthlyScheduleWorkDataResponse>,
)


@JsonClass(generateAdapter = true)
data class MonthlyScheduleWorkDataResponse(
    @field:Json(name = "memberWorkingTimeId") val memberWorkingTimeId: Int,
    @field:Json(name = "memberId") val memberId: Int,
    @field:Json(name = "memberName") val memberName: String,
    @field:Json(name = "memberGrade") val memberGrade: String,
    @field:Json(name = "startTime") val startTime: String,
    @field:Json(name = "endTime") val endTime: String,
    @field:Json(name = "coverRequested") val coverRequested: Boolean,
    @field:Json(name = "mine") val mine: Boolean,
)