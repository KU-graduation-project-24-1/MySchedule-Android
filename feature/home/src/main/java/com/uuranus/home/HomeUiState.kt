package com.uuranus.home

import com.uuranus.home.calendar.DateInfo
import com.uuranus.home.calendar.MyScheduleInfo
import com.uuranus.home.calendar.ScheduleInfo

sealed interface HomeUiState {
    object Loading : HomeUiState
    object Empty : HomeUiState
    data class Schedules(
        val year: Int,
        val month: Int,
        val schedules: Map<DateInfo, ScheduleInfo<MyScheduleInfo>>,
    ) : HomeUiState

}

