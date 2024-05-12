package com.uuranus.home

import com.uuranus.home.calendar.DateInfo
import com.uuranus.home.calendar.ScheduleInfo
import com.uuranus.model.MyScheduleInfo

sealed interface HomeUiState {
    object Loading : HomeUiState
    data class Success(
        val schedules: Map<DateInfo, ScheduleInfo<MyScheduleInfo>>,
    ) : HomeUiState

}

