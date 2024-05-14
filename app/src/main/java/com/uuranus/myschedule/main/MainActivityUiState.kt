package com.uuranus.myschedule.main

import com.uuranus.model.UserData


sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState
    data class Success(val userData: UserData) : MainActivityUiState
}
