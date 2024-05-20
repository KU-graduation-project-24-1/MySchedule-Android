package com.uuranus.data.repository.mypage

import com.uuranus.model.TimeRange

interface MyPageRepository {

    suspend fun getFixedPossibleTimes(): List<List<TimeRange>>

    suspend fun addFixedPossibleTime(): String
}