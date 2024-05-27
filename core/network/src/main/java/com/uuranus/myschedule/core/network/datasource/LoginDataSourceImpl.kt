package com.uuranus.myschedule.core.network.datasource

import com.uuranus.model.LoginResult
import com.uuranus.myschedule.core.network.model.LoginRequest
import com.uuranus.myschedule.core.network.model.SignUpRequest
import com.uuranus.myschedule.core.network.service.MyScheduleService
import javax.inject.Inject

class LoginDataSourceImpl @Inject constructor(
    private val service: MyScheduleService,
) : LoginDataSource {
    override suspend fun serviceLogin(idToken: String, fcmToken: String): LoginResult {
        val loginRequest = LoginRequest(idToken, fcmToken)
        val response = service.serviceLogin(loginRequest)
        return response.result
    }

    override suspend fun signUp(authorization: String, memberName: String): String {
        val signUpRequest = SignUpRequest(memberName)
        val response = service.signUp(authorization, signUpRequest)
        return response.result
    }


}