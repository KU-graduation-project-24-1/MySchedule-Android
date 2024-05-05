package com.uuranus.home.calendar

import androidx.compose.ui.graphics.Color

data class ScheduleInfo<T>(
    val isCheckNeeded: Boolean,
    val schedules: List<ScheduleData<T>>,
)

data class ScheduleData<T>(
    val title: String,
    val color: Color,
    val detail: T,
)


//우리 앱에서 쓰는 정보
data class MyScheduleInfo(
    val scheduleId: Int,
    val startTime: String,
    val endTime: String,
    val workerName: String,
    val workerType: String,
    val isMine: Boolean,
    val color: Color,
    val isFillInNeeded: Boolean,
)