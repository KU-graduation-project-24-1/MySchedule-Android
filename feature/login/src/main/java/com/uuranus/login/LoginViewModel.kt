package com.uuranus.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uuranus.domain.CheckLoginStatusUseCase
import com.uuranus.model.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val checkLoginStatusUseCase: CheckLoginStatusUseCase,
) : ViewModel() {

    private val _userData =
        MutableStateFlow(
            UserData(
                0,
                0,
                "",
                "",
                false
            )
        )
    val userData: StateFlow<UserData> = _userData


    init {
        viewModelScope.launch {
            checkLoginStatusUseCase().collect { isLoggedIn ->
                userData.value.isLoggedIn = isLoggedIn
            }
        }
    }

    fun updateLoginStatus(loggedIn: Boolean) {
        userData.value.isLoggedIn = loggedIn
    }
}