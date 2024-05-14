package com.uuranus.data.repository.login

import com.uuranus.datastore.UserDataStore
import com.uuranus.myschedule.core.network.service.MyScheduleService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val dataStore: UserDataStore,
    private val service: MyScheduleService,
) : LoginRepository {

    override fun isLoggedIn(): Flow<Boolean> {
        return dataStore.userData.map { it.isLoggedIn }
    }

    override fun login(): Boolean {
        TODO("Not yet implemented")
    }

    override fun makeRoom(): Boolean {
        TODO("Not yet implemented")
    }

    override fun enterRoom(): Boolean {
        TODO("Not yet implemented")
    }
}