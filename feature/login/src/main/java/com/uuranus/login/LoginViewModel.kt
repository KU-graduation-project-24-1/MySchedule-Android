package com.uuranus.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uuranus.domain.CheckLoginStatusUseCase
import com.uuranus.model.UserData
import com.uuranus.domain.ServiceLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val serviceLoginUseCase : ServiceLoginUseCase,
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
    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow


//    init {
//        viewModelScope.launch {
//            checkLoginStatusUseCase().collect { isLoggedIn ->
//                _userData.value = _userData.value.copy(isLoggedIn = isLoggedIn)
//            }
//        }
//    }

    fun updateLoginStatus(loggedIn: Boolean) {
        _userData.value = _userData.value.copy(isLoggedIn = loggedIn)
    }
<<<<<<< Updated upstream

    fun serviceLogin(idToken: String, fcmToken: String){
        viewModelScope.launch{
            flow{
                emit(
                    serviceLoginUseCase(
                        idToken,
                        fcmToken
                    )
                )
            }.map{loginResult ->
                if (loginResult.memberName?.isNotEmpty() == true) {
                    _userData.value = _userData.value.copy(
                        email = loginResult.email,
                        accessToken = loginResult.accessToken,
                        refreshToken = loginResult.refreshToken,
                        imgUrl = loginResult.imgUrl,
                        name = loginResult.memberName!!
                    )
                } else {
                    _userData.value = _userData.value.copy(
                        email = loginResult.email,
                        accessToken = loginResult.accessToken,
                        refreshToken = loginResult.refreshToken,
                        imgUrl = loginResult.imgUrl
                        // Do not update name if loginResult.name is empty
                    )
                }
            }.catch {throwable ->
                _errorFlow.emit(throwable)
            }
        }
    }
=======
    
>>>>>>> Stashed changes
}