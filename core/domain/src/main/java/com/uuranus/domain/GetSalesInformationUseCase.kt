package com.uuranus.domain

import com.uuranus.data.repository.mypage.MyPageRepository
import com.uuranus.model.StoreSalesInformation
import com.uuranus.model.TimeRange
import javax.inject.Inject

class GetSalesInformationUseCase @Inject constructor(
    private val repository: MyPageRepository,
) {
    suspend operator fun invoke(
        accessToken:String,
    ): List<StoreSalesInformation> {
        return repository.getStoreSalesInformation(accessToken)
    }
}