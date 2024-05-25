package com.uuranus.model

data class UserData(
    val storeId: Int = -1,
    val memberId: Int = -1,
    val name: String = "",
    val workerType: String = "",
    val isLoggedIn: Boolean = false,
    val storeName: String = "",
    val accessToken: String = "",
    val refreshToken: String = "",
    val email: String ="",
    val imgUrl: String?="",
    val fcmToken: String=""
)