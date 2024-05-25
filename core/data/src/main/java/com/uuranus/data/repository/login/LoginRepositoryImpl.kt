package com.uuranus.data.repository.login

import com.uuranus.model.LoginResult
import com.uuranus.myschedule.core.network.datasource.LoginDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val dataSource: LoginDataSource
) : LoginRepository {

    private val _isLoggedIn = MutableStateFlow(true)
    override fun isLoggedIn(): Flow<Boolean> = _isLoggedIn.asStateFlow()

    override suspend fun serviceLogin(idToken: String, fcmToken: String): LoginResult {
        val result = dataSource.serviceLogin(idToken, fcmToken)
        _isLoggedIn.value = result.accessToken.isNotEmpty()
        return result
    }

    override suspend fun signUp(authorization: String, memberName: String): String {
        return dataSource.signUp(authorization, memberName)
    }
}