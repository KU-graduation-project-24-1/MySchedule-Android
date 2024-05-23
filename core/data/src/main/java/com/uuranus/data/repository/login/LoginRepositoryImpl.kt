package com.uuranus.data.repository.login

import com.uuranus.datastore.UserDataStore
import com.uuranus.model.LoginResult
import com.uuranus.myschedule.core.network.datasource.LoginDataSource
import com.uuranus.myschedule.core.network.service.MyScheduleService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val dataStore: UserDataStore,
    private val dataSource: LoginDataSource,
    private val service: MyScheduleService,
) : LoginRepository {
    override fun isLoggedIn(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun serviceLogin(
        idToken : String,
        fcmToken : String,
    ): LoginResult{
        return dataSource.serviceLogin(idToken,fcmToken)
    }

    override suspend fun signUp(memberName: String): LoginResult {
        return dataSource.signUp(memberName)
    }
}