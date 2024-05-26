package com.uuranus.myschedule.bossmypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uuranus.domain.AddOpeningHourUseCase
import com.uuranus.domain.AddWorkerNumUserCase
import com.uuranus.domain.DeleteOpeningHourUseCase
import com.uuranus.domain.DeleteStoreUseCase
import com.uuranus.domain.GetSalesInformationUseCase
import com.uuranus.domain.GetUserDataUseCase
import com.uuranus.model.TimeRange
import com.uuranus.model.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BossMyPageViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val getSalesInformationUseCase: GetSalesInformationUseCase,
    private val addWorkerNumUserCase: AddWorkerNumUserCase,
    private val addOpeningHourUseCase: AddOpeningHourUseCase,
    private val deleteOpeningHourUseCase: DeleteOpeningHourUseCase,
    private val deleteStoreUseCase: DeleteStoreUseCase,

    ) : ViewModel() {

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    private val _userData =
        MutableStateFlow(
            UserData(
                0,
                0,
                "",
                "",
                false
            )
        )
    val userData: StateFlow<UserData> = _userData

    private val _bossMypageUiState = MutableStateFlow<BossMyPageUiState>(BossMyPageUiState.Loading)
    val bossMypageUiState: StateFlow<BossMyPageUiState> = _bossMypageUiState

    init {

        viewModelScope.launch {
            getUserDataUseCase().flatMapLatest {
                _userData.value = it
                flow {
                    emit(getSalesInformationUseCase(it.accessToken, it.storeId))
                }
            }.map {
                BossMyPageUiState.Success(
                    salesInformation = it
                )
            }.catch {
                _errorFlow.emit(it)
            }.collect {
                _bossMypageUiState.value = it
            }
        }

    }

    fun addWorkerNum(weekDayNum: Int, workerNum: Int) {
        viewModelScope.launch {
            flow {
                emit(
                    addWorkerNumUserCase(
                        _userData.value.accessToken,
                        _userData.value.storeId,
                        weekDayNum,
                        workerNum
                    )
                )
            }.map {

                val state = _bossMypageUiState.value as BossMyPageUiState.Success

                BossMyPageUiState.Success(
                    salesInformation = state.salesInformation.mapIndexed { index, storeSalesInformation ->
                        if (index == weekDayNum) {
                            storeSalesInformation.copy(
                                workerNum = workerNum
                            )
                        } else {
                            storeSalesInformation
                        }

                    }
                )
            }.catch {
                _errorFlow.emit(it)
            }.collect {
                _bossMypageUiState.value = it
            }
        }
    }

    fun addOpeningHourTime(weekDayNum: Int, startTime: String, endTime: String) {

        viewModelScope.launch {
            addOpeningHourUseCase(
                _userData.value.accessToken,
                _userData.value.storeId,
                weekDayNum,
                startTime,
                endTime
            )

            val state = _bossMypageUiState.value as BossMyPageUiState.Success
            _bossMypageUiState.value =
                BossMyPageUiState.Success(salesInformation = state.salesInformation.mapIndexed { index, storeSalesInformation ->
                    if (index == weekDayNum) {
                        storeSalesInformation.copy(
                            salesTimeRange = storeSalesInformation.salesTimeRange.plus(
                                TimeRange(
                                    startTime = startTime,
                                    endTime = endTime
                                )
                            )
                        )
                    } else {
                        storeSalesInformation
                    }

                }
                )
        }
    }

    fun deleteTimeRange(timeId: Int) {
        viewModelScope.launch {
            flow {
                emit(
                    deleteOpeningHourUseCase(
                        _userData.value.accessToken,
                        _userData.value.storeId,
                        timeId
                    )
                )
            }.map {

                val state = _bossMypageUiState.value as BossMyPageUiState.Success

                BossMyPageUiState.Success(
                    salesInformation = state.salesInformation.map { storeSalesInfo ->
                        storeSalesInfo.copy(
                            salesTimeRange = storeSalesInfo.salesTimeRange.filterNot { timeRange ->
                                timeRange.timeId == timeId
                            }
                        )
                    }
                )
            }.catch {
                _errorFlow.emit(it)
            }.collect {
                _bossMypageUiState.value = it
            }
        }
    }

    fun deleteStore() {
        viewModelScope.launch {
            flow {
                emit(deleteStore())
            }.flatMapLatest {
                flow {
                    emit("!")
//                    emit(logOut())
                }
            }.catch {
                _errorFlow.emit(it)
            }
        }
    }

    fun quit() {
        viewModelScope.launch {

        }
    }
}