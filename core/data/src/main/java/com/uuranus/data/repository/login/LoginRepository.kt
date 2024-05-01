package com.uuranus.data.repository.login

import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    fun isLoggedIn(): Flow<Boolean>

    fun login(): Boolean

    fun makeRoom(): Boolean

    fun enterRoom(): Boolean

}