package com.uuranus.model

data class MyScheduleInfo(
    val scheduleId: Int,
    val startTime: String,
    val endTime: String,
    val memberId: Int,
    val workerName: String,
    val workerType: String,
    val isMine: Boolean,
    val isFillInNeeded: Boolean,
)