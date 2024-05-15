package com.uuranus.myschedule.core.network.datasource

import com.uuranus.model.TimeRange

interface MyPageDataSource {

    fun getFixedPossibleTimes(): List<List<TimeRange>>

    fun addFixedPossibleTime(): String
}