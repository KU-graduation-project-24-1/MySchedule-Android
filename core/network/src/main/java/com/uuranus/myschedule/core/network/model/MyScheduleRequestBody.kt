package com.uuranus.myschedule.core.network.model

data class PostPossibleTimeBody(
    val storeId: Int,
    val dateDashYMDString: String,
    val starTime: String,
    val endTime: String,
)

data class DeletePossibleTimeBody(
    val storeId: Int,
    val storeMemberAvailableTimeId: Int,
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
    val dateDashYMDString: String,
    val starTime: String,
    val endTime: String,
)

data class PatchScheduleBody(
    val scheduleId: Int,
    val employeeId: Int,
    val starTime: String,
    val endTime: String,
)

