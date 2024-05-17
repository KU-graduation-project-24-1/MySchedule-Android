package com.uuranus.model

data class ScheduleUpdate(
    val scheduleId: Int,
    val startTime: String,
    val endTime: String,
    val memberId: Int,
)