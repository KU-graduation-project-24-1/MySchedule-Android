package com.uuranus.myschedule.core.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PostPossibleTimeBody(
    val storeId: Int,
    val date: String,
    val startTime: String,
    val endTime: String,
)

@JsonClass(generateAdapter = true)
data class DeletePossibleTimeBody(
    val storeId: Int,
    val storeAvailableScheduleId: Int,
)

@JsonClass(generateAdapter = true)
data class PatchWorkerTypeBody(
    val storeId: Int,
    val employeeId: Int,
    val memberGrade: String,
)

@JsonClass(generateAdapter = true)
data class DeleteWorkerBody(
    val storeId: Int,
    val employeeId: Int,
)

@JsonClass(generateAdapter = true)
data class PostScheduleBody(
    val storeId: Int,
    val employeeId: Int,
    val date: String,
    val startTime: String,
    val endTime: String,
)

@JsonClass(generateAdapter = true)
data class PatchScheduleBody(
    val scheduleId: Int,
    val employeeId: Int,
    val startTime: String,
    val endTime: String,
)

@JsonClass(generateAdapter = true)
data class PatchScheduleCover(
    val scheduleId: Int?,
    val employeeId: Int?,
    val date: String?,
    val startTime: String?,
    val endTime: String?,
    val deleted: Boolean,
)

@JsonClass(generateAdapter = true)
data class PatchOperationInfoBody(
    val dayOfWeek: Int,
    val requiredEmployees: Int,
    val startTime: String,
    val endTime: String,
)

@JsonClass(generateAdapter = true)
data class StoreOperationInfoBody(
    val storeOperationInfoId: Int,
)

@JsonClass(generateAdapter = true)
data class LoginRequest(val idToken: String, val fcmToken: String)

@JsonClass(generateAdapter = true)
data class SignUpRequest(val memberName: String)