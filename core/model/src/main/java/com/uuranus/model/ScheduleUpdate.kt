package com.uuranus.model

data class ScheduleUpdate(
    val scheduleId: Int,
    val dateDashString: String,
    val startTime: String,
    val endTime: String,
    val memberId: Int,
)