package com.uuranus.data.repository.user

import com.uuranus.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUserData(): Flow<UserData>

    suspend fun updateUserData(newUserData: UserData)
}