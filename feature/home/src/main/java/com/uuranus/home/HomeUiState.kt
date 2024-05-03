package com.uuranus.home

import androidx.compose.ui.graphics.Color

sealed interface HomeUiState {
    object Loading : HomeUiState
    object Empty : HomeUiState
    data class Schedules(
        val year: Int,
        val month: Int,
        val schedules: List<DateSchedule>,
    ) : HomeUiState

}

data class DateSchedule(
    val date: String,
    val isCheckNeeded: Boolean,
    val title: String,
    val details: List<ScheduleData>,
)

data class ScheduleData(
    val scheduleId: Int,
    val title: String,
    val detail: String,
    val color: Color,
)