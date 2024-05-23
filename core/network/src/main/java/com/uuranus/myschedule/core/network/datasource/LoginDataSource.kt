package com.uuranus.myschedule.core.network.datasource

import com.uuranus.model.LoginResult


interface LoginDataSource {
    suspend fun serviceLogin(
        idToken : String,
        fcmToken : String,
    ): LoginResult


    //이름입력
    suspend fun signUp(
        memberName : String
    ): LoginResult
}