package com.uuranus.domain

import com.uuranus.data.repository.login.LoginRepository
import com.uuranus.model.LoginResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    fun isLoggedIn(): Flow<Boolean> = repository.isLoggedIn()


    suspend fun serviceLogin(idToken: String, fcmToken: String): LoginResult {
        return repository.serviceLogin(idToken, fcmToken)
    }

    suspend fun signUp(authorization: String, memberName: String): String {
        return repository.signUp(authorization, memberName)
    }
}