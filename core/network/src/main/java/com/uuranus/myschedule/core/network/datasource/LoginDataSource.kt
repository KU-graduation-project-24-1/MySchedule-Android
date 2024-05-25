package com.uuranus.myschedule.core.network.datasource

import com.uuranus.model.LoginResult

interface LoginDataSource {
    suspend fun serviceLogin(idToken: String, fcmToken: String): LoginResult
    suspend fun signUp(authorization: String, memberName: String): String

}