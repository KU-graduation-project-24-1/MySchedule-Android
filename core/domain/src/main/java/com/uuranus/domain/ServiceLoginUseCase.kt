package com.uuranus.domain

import com.uuranus.data.repository.login.LoginRepository
import com.uuranus.model.LoginResult
import javax.inject.Inject

class ServiceLoginUseCase @Inject constructor(
    private val repository: LoginRepository,
) {

    suspend operator fun invoke(
        idToken : String,
        fcmToken : String,
    ): LoginResult{
        return repository.serviceLogin(
            idToken,
            fcmToken
        )
    }
}