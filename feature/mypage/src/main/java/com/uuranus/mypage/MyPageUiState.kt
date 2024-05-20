package com.uuranus.mypage

import com.uuranus.model.TimeRange

sealed interface MyPageUiState {
    data object Loading : MyPageUiState
    data class Success(
        val fixedPossibleTimes: List<List<TimeRange>>,
    ) : MyPageUiState

}

