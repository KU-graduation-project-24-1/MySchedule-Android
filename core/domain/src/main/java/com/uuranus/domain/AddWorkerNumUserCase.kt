package com.uuranus.domain

import com.uuranus.data.repository.mypage.MyPageRepository
import javax.inject.Inject

class AddWorkerNumUserCase @Inject constructor(
    private val repository: MyPageRepository,
) {
    suspend operator fun invoke(
        accessToken: String,
        weekNum: Int,
        workerNum: Int,
    ): Boolean {
        return repository.addWorkerNum(
            accessToken,
            weekNum,
            workerNum
        )
    }
}