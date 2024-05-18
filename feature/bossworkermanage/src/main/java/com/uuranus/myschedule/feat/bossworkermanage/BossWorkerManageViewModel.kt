package com.uuranus.myschedule.feat.bossworkermanage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uuranus.domain.GetAllWorkersInfo
import com.uuranus.domain.GetUserDataUseCase
import com.uuranus.model.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BossWorkerManageViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val getAllWorkersInfo: GetAllWorkersInfo,
) : ViewModel() {


    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    private val _userData: MutableStateFlow<UserData> =
        MutableStateFlow(UserData(0, 0, "", "", false))

    private val _bossWorkerManageUiState =
        MutableStateFlow<BossWorkerMangeUiState>(BossWorkerMangeUiState.Loading)
    val bossWorkerManageUiState: StateFlow<BossWorkerMangeUiState> =
        _bossWorkerManageUiState.asStateFlow()

    init {
        viewModelScope.launch {
            getUserDataUseCase().flatMapLatest { userData ->
                _userData.value = userData
                flow {
                    emit(
                        getAllWorkersInfo(
                            userData.storeId
                        )
                    )
                }
            }.map {
                BossWorkerMangeUiState.Success(
                    workers = it
                )
            }.catch {
                _errorFlow.emit(it)
            }.collect {
                _bossWorkerManageUiState.value = it
            }
        }
    }

    fun deleteWorker(workerId: Int) {
        viewModelScope.launch {

        }
    }

    fun editWorker(workerId: Int, workerType: String) {

    }
}