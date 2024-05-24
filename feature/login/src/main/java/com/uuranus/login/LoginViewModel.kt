package com.uuranus.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uuranus.domain.CheckLoginStatusUseCase
import com.uuranus.domain.LoginUseCase
import com.uuranus.model.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
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
    private val checkLoginStatusUseCase: CheckLoginStatusUseCase,
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _userData = MutableStateFlow(UserData())
    val userData: StateFlow<UserData> get() = _userData

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    val isLoggedIn: Flow<Boolean> = loginUseCase.isLoggedIn()


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

    fun serviceLogin(idToken: String, fcmToken: String) {
        viewModelScope.launch {
            try {
                val loginResult = loginUseCase.serviceLogin(idToken, fcmToken)
                if (loginResult.memberName.isNullOrEmpty()) {
                    _userData.value = _userData.value.copy(
                        isLoggedIn = true,
                        email = loginResult.email,
                        accessToken = loginResult.accessToken,
                        refreshToken = loginResult.refreshToken,
                        imgUrl = loginResult.imgUrl,
                    )
                } else {
                    _userData.value = _userData.value.copy(
                        isLoggedIn = true,
                        email = loginResult.email,
                        accessToken = loginResult.accessToken,
                        refreshToken = loginResult.refreshToken,
                        imgUrl = loginResult.imgUrl,
                        name = loginResult.memberName!!
                    )
                }
            } catch (throwable: Throwable) {
                _errorFlow.emit(throwable)
            }
        }
    }

    fun signUp(authorization: String, memberName: String) {
        viewModelScope.launch {
            try {
                val signUpResult = loginUseCase.signUp(authorization, memberName)
                // Update the _userData state if needed with signUpResult
            } catch (e: Exception) {
                _errorFlow.emit(e)
            }
        }
    }

    fun setFcmToken(fcmToken: String){
        _userData.value = _userData.value.copy(fcmToken = fcmToken)
    }


//    fun serviceLogin(idToken: String, fcmToken: String){
//        viewModelScope.launch{
//            flow{
//                emit(
//                    serviceLoginUseCase(
//                        idToken,
//                        fcmToken
//                    )
//                )
//            }.map{loginResult ->
//                Log.d("ddd","sss")
//                if (loginResult.memberName.isNullOrEmpty()) {
//                    _userData.value = _userData.value.copy(
//                        isLoggedIn = true,
//                        email = loginResult.email,
//                        accessToken = loginResult.accessToken,
//                        refreshToken = loginResult.refreshToken,
//                        imgUrl = loginResult.imgUrl,
//                    )
//                } else {
//                    _userData.value = _userData.value.copy(
//                        isLoggedIn = true,
//                        email = loginResult.email,
//                        accessToken = loginResult.accessToken,
//                        refreshToken = loginResult.refreshToken,
//                        imgUrl = loginResult.imgUrl,
//                        name = loginResult.memberName!!
//                    )
//                }
//                Log.d("email",_userData.value.email)
//                Log.d("username",_userData.value.name)
//            }.catch {throwable ->
//                _errorFlow.emit(throwable)
//            }
//        }
//    }
}