package com.uuranus.myschedule.feat.bossworkermanage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uuranus.domain.DeleteWorkerUseCase
import com.uuranus.domain.EditWorkerTypeUseCase
import com.uuranus.domain.GetAllWorkersInfoUseCase
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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BossWorkerManageViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val getAllWorkersInfo: GetAllWorkersInfoUseCase,
    private val deleteWorker: DeleteWorkerUseCase,
    private val editWorkerType: EditWorkerTypeUseCase,
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
                            userData.accessToken,
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
            if (workerId == _userData.value.memberId) {
                _errorFlow.emit(Exception("고용인은 삭제할 수 없습니다."))
                return@launch
            }

            flow {
                emit(
                    deleteWorker(
                        _userData.value.accessToken,
                        _userData.value.storeId,
                        workerId
                    )
                )
            }
                .map {
                    val state = _bossWorkerManageUiState.value as BossWorkerMangeUiState.Success
                    BossWorkerMangeUiState.Success(
                        state.workers.filterNot {
                            it.memberId == workerId
                        }
                    )
                }.catch {
                    _errorFlow.emit(it)
                }.collect {
                    _bossWorkerManageUiState.value = it
                }
        }
    }

    fun editWorker(workerId: Int, workerType: String) {

        viewModelScope.launch {
            if (workerId == _userData.value.memberId) {
                _errorFlow.emit(Exception("고용인은 고용 형태를 수정할 수 없습니다."))
                return@launch
            }

            flow {
                emit(
                    editWorkerType(
                        _userData.value.accessToken,
                        _userData.value.storeId,
                        workerId,
                        workerType
                    )
                )
            }
                .map {
                    val state = _bossWorkerManageUiState.value as BossWorkerMangeUiState.Success
                    BossWorkerMangeUiState.Success(
                        state.workers.map {
                            if (it.memberId == workerId) {
                                it.copy(workerType = workerType)
                            } else {
                                it
                            }
                        }
                    )
                }.catch {
                    _errorFlow.emit(it)
                }.collect {
                    _bossWorkerManageUiState.value = it
                }
        }
    }
}