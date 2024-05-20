package com.uuranus.myschedule.core.network.datasource

import com.uuranus.model.TimeRange
import javax.inject.Inject

class MyPageDataSourceImpl @Inject constructor(
//    private val service: MyScheduleService,
) : MyPageDataSource {
    override fun getFixedPossibleTimes(): List<List<TimeRange>> {
        return listOf(
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
        )
    }

    override fun addFixedPossibleTime(): String {
        return "고정 시간 추가 성공"
    }
}