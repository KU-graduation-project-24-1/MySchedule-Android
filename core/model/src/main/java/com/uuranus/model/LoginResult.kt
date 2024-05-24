package com.uuranus.model

data class LoginResult(
    val email:String,
    val accessToken: String,
    val refreshToken: String,
    val imgUrl: String,
    val memberName: String?
)