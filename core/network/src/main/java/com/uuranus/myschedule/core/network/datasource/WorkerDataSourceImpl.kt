package com.uuranus.myschedule.core.network.datasource

import com.uuranus.model.MyPossibleTimeInfo
import com.uuranus.model.MyScheduleInfo
import com.uuranus.model.ScheduleUpdate
import com.uuranus.model.WorkerInfo
import com.uuranus.myschedule.core.network.service.MyScheduleService
import org.jetbrains.annotations.Async.Schedule
import javax.inject.Inject


private val workers = listOf(
    WorkerInfo(
        0,
        "최사장",
        "사장",
    ),
    WorkerInfo(
        1,
        "이직원",
        "매니저",
    ),
    WorkerInfo(
        2,
        "김알바",
        "아르바이트",
    ),
    WorkerInfo(
        3,
        "나알바",
        "아르바이트",
    )

)

class WorkerDataSourceImpl @Inject constructor(
    private val service: MyScheduleService,
) : WorkerDataSource {

    override suspend fun getAllWorkers(storeId: Int): List<WorkerInfo> {
        val response = service.getAllWorkers(
            "Bearer eyJraWQiOiI5ZjI1MmRhZGQ1ZjIzM2Y5M2QyZmE1MjhkMTJmZWEiLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJhNDkwMDNmZWM1MTY3ZjY5OTJjMWY4NDU3MTA0NjJiOSIsInN1YiI6IjM0OTAzMTAwOTAiLCJhdXRoX3RpbWUiOjE3MTYxOTM4MzksImlzcyI6Imh0dHBzOi8va2F1dGgua2FrYW8uY29tIiwiZXhwIjoxNzE2MjM3MDM5LCJpYXQiOjE3MTYxOTM4MzksInBpY3R1cmUiOiJodHRwOi8vay5rYWthb2Nkbi5uZXQvZG4vYmY5OTdVL2J0clpOS0oyOUg0L2xKYXpabjBDcWo2TFRGWWlZYWkzSzAvaW1nXzExMHgxMTAuanBnIiwiZW1haWwiOiJ5dXIwOTIwQG5hdmVyLmNvbSJ9.ba_cpd7joYrn5Vfic_VZc7VHOJflC4OV_WhwuHVNTDZXYfCODAMzYYtU6-DH5nINSd9B_GcAqYsk8TzNMvwCz-QJzpdpfCIbUvj4-qYKtsszNOp0KhJuxsOHEK0P-fsI-Yf_pKLzBzodXuUJMawo1GSQJ5h98xNw4dkl4JMOSaTgjVmWLG4ph82SwW5N5r8Mzh36RjjBmOqbqXX4w_wMYf2d6G37wUXX0fRKlBISghT8vP10gENTa81WPAFjZgJxX5u6L5Vzp9uGqBZ2VJNgiFF1rUrRN8dktjtZr1bEd87hJVwORidp04MpCcgWAUGd9bxKHOwQ0YMVWSupOUZBjQ",
            1
        )
        if (response.isSuccessful) {
            return workers
        } else {
            throw Exception(response.message())
        }
    }

    override suspend fun changeWorkerType(
        storeId: Int,
        memberId: Int,
        workerType: String,
    ): Boolean {
        return true
    }

    override suspend fun deleteWorker(storeId: Int, memberId: Int): Boolean {
        return true
    }


}