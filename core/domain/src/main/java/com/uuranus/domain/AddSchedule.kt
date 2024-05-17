package com.uuranus.domain

import com.uuranus.data.repository.home.HomeRepository
import com.uuranus.model.MyScheduleInfo
import javax.inject.Inject

class AddSchedule @Inject constructor(
    private val repository: HomeRepository,
) {
    suspend operator fun invoke(
        myScheduleInfo: MyScheduleInfo,
    ): Boolean {
        return repository.addSchedule(myScheduleInfo)
    }
}