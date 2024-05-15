package com.uuranus.mypage

import com.uuranus.designsystem.calendar.DateInfo
import com.uuranus.designsystem.calendar.ScheduleInfo
import com.uuranus.model.MyPossibleTimeInfo
import com.uuranus.model.MyScheduleInfo
import com.uuranus.model.TimeRange
import com.uuranus.model.UserData

sealed interface MyPageUiState {
    object Loading : MyPageUiState
    data class Success(
        val fixedPossibleTimes: List<List<TimeRange>>,
    ) : MyPageUiState

}

