package com.uuranus.domain

import com.uuranus.data.repository.login.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckLoginStatusUseCase @Inject constructor(
    private val repository: LoginRepository
) {

    suspend operator fun invoke(): Flow<Boolean> {
        return repository.isLoggedIn()
    }
}