package com.uuranus.data.repository.user

import com.uuranus.datastore.UserDataStore
import com.uuranus.model.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataStore: UserDataStore,
) : UserRepository {
    override fun getUserData(): Flow<UserData> {
        return userDataStore.userData
    }

}