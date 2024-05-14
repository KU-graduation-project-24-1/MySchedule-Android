package com.uuranus.domain

import android.service.autofill.UserData
import com.uuranus.data.repository.home.HomeRepository
import com.uuranus.data.repository.user.UserRepository
import com.uuranus.model.MyScheduleInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(
    private val repository: UserRepository,
) {
    suspend operator fun invoke(): Flow<com.uuranus.model.UserData> {
        return repository.getUserData()
    }
}