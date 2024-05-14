package com.uuranus.home

import com.uuranus.designsystem.calendar.DateInfo
import com.uuranus.designsystem.calendar.ScheduleInfo
import com.uuranus.model.MyScheduleInfo

sealed interface HomeUiState {
    object Loading : HomeUiState
    data class Success(
        val schedules: Map<DateInfo, ScheduleInfo<MyScheduleInfo>>,
    ) : HomeUiState

}

