package com.uuranus.domain

import com.uuranus.data.repository.home.HomeRepository
import com.uuranus.data.repository.mypage.MyPageRepository
import javax.inject.Inject

class DeleteStore  @Inject constructor(
    private val repository: MyPageRepository,
) {
    suspend operator fun invoke(
        accessToken: String,
        storeId: Int
    ): Boolean {
        return repository.deleteStore(accessToken, storeId)
    }
}