package com.uuranus.data.repository.login

import com.uuranus.model.LoginResult
import kotlinx.coroutines.flow.Flow


interface LoginRepository {

    fun isLoggedIn(): Flow<Boolean>
    //로그인
    suspend fun serviceLogin(
        idToken : String,
        fcmToken : String,
    ): LoginResult


    //이름입력
    suspend fun signUp(
        memberName : String
    ): LoginResult

}