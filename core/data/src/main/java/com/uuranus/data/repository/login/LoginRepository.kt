package com.uuranus.data.repository.login

import com.uuranus.model.LoginResult
import kotlinx.coroutines.flow.Flow


interface LoginRepository {

    fun isLoggedIn(): Flow<Boolean>
    suspend fun serviceLogin(idToken: String, fcmToken: String): LoginResult
    suspend fun signUp(authorization: String, memberName: String): String

}