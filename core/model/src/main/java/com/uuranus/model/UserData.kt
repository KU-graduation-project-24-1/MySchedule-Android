package com.uuranus.model

data class UserData(
    val storeId: Int,
    val memberId: Int,
    val name: String,
    val workerType: String,
    val isLoggedIn: Boolean,
)