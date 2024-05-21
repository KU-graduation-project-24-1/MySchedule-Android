package com.uuranus.myschedule.bossmypage

import com.uuranus.model.StoreSalesInformation

sealed interface BossMyPageUiState {
    object Loading : BossMyPageUiState
    data class Success(
        val salesInformation: List<StoreSalesInformation>,
    ) : BossMyPageUiState

}

