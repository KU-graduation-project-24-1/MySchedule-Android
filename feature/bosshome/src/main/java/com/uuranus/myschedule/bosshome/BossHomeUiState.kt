package com.uuranus.myschedule.bosshome

import com.uuranus.designsystem.calendar.DateInfo
import com.uuranus.designsystem.calendar.ScheduleInfo
import com.uuranus.model.MyScheduleInfo

sealed interface BossHomeUiState {
    object Loading : BossHomeUiState
    data class Success(
        val schedules: Map<DateInfo, ScheduleInfo<MyScheduleInfo>>,
    ) : BossHomeUiState

}

