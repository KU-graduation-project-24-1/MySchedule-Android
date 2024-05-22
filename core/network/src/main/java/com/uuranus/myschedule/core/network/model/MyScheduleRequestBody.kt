package com.uuranus.myschedule.core.network.model

data class PostPossibleTimeBody(
    val storeId: Int,
    val date: String,
    val startTime: String,
    val endTime: String,
)

data class DeletePossibleTimeBody(
    val storeId: Int,
    val storeAvailableScheduleId: Int,
)

data class PatchWorkerTypeBody(
    val storeId: Int,
    val employeeId: Int,
    val memberGrade: String,
)

data class DeleteWorkerBody(
    val storeId: Int,
    val employeeId: Int,
)

data class PostScheduleBody(
    val storeId: Int,
    val employeeId: Int,
    val date: String,
    val startTime: String,
    val endTime: String,
)

data class PatchScheduleBody(
    val scheduleId: Int,
    val employeeId: Int,
    val startTime: String,
    val endTime: String,
)

data class PatchScheduleCover(
    val scheduleId: Int,
    val employeeId: Int,
    val date: String,
    val startTime: String,
    val endTime: String,
    val deleted: Boolean,
)
