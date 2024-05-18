package com.uuranus.model

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
@JsonClass(generateAdapter = true)
data class MyScheduleNavType(
    @field:Json(name = "storeId")
    val storeId: Int,
    @field:Json(name = "scheduleId")
    val scheduleId: Int,
    @field:Json(name = "dateDashString")
    val dateDashString: String,
    @field:Json(name = "startTime")
    val startTime: String,
    @field:Json(name = "endTime")
    val endTime: String,
    @field:Json(name = "memberId")
    val memberId: Int,
) : Parcelable


fun MyScheduleInfo.toMyScheduleNavType(storeId: Int, dateDashString: String) =
    MyScheduleNavType(
        storeId = storeId,
        scheduleId = scheduleId,
        dateDashString = dateDashString,
        startTime = startTime,
        endTime = endTime,
        memberId = memberId
    )