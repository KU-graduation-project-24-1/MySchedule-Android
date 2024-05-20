package com.uuranus.myschedule.feat.bossworkermanage

import com.uuranus.model.WorkerInfo

sealed interface BossWorkerMangeUiState {
    object Loading : BossWorkerMangeUiState
    data class Success(
        val workers: List<WorkerInfo>,
    ) : BossWorkerMangeUiState

}
