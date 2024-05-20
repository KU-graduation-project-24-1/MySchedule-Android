package com.uuranus.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.uuranus.domain.CheckLoginStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val checkLoginStatusUseCase: CheckLoginStatusUseCase,
) : ViewModel() {

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean>
        get() = _isLoggedIn

    init {
        viewModelScope.launch {
            checkLoginStatusUseCase().collect { isLoggedIn ->
                _isLoggedIn.value = isLoggedIn
            }
        }
    }

    fun updateLoginStatus(loggedIn: Boolean) {
        _isLoggedIn.value = loggedIn
    }

    fun performLogin(context: Context) {
        // 카카오계정으로 로그인 공통 callback 구성
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e("로그인", "카카오계정으로 로그인 실패", error)
                updateLoginStatus(false)
            } else if (token != null) {
                Log.i("로그인", "카카오계정으로 로그인 성공 ${token.idToken}")
                updateLoginStatus(true)
            }
        }

        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) {
                    Log.e("로그인", "카카오톡으로 로그인 실패", error)

                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        updateLoginStatus(false)
                        return@loginWithKakaoTalk
                    }

                    UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
                } else if (token != null) {
                    Log.i("로그인", "카카오톡으로 로그인 성공 ${token.idToken}")
                    updateLoginStatus(true)
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
        }
    }

}