package com.uuranus.myschedule.bossmypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uuranus.designsystem.calendar.DateInfo
import com.uuranus.domain.AddFixedPossibleTimesUseCase
import com.uuranus.domain.GetFixedPossibleTimesUseCase
import com.uuranus.domain.GetSalesInformationUseCase
import com.uuranus.domain.GetUserDataUseCase
import com.uuranus.model.TimeRange
import com.uuranus.model.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class BossMyPageViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val getSalesInformationUseCase: GetSalesInformationUseCase,
    private val addFixedPossibleTimesUseCase: AddFixedPossibleTimesUseCase,
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

    private val _currentDate = MutableStateFlow(DateInfo.create(Calendar.getInstance()))

    private val _bossMypageUiState = MutableStateFlow<BossMyPageUiState>(BossMyPageUiState.Loading)
    val bossMypageUiState: StateFlow<BossMyPageUiState> = _bossMypageUiState

    init {
        viewModelScope.launch {

            getUserDataUseCase().catch {
                _errorFlow.emit(it)
            }.collect {
                _userData.value = it
            }
        }

        viewModelScope.launch {
            flow {
                emit(getSalesInformationUseCase())
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
            val state = _bossMypageUiState.value as BossMyPageUiState.Success
            _bossMypageUiState.value = BossMyPageUiState.Success(
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
        }
    }

    fun addOpeningHourTime(weekDayNum: Int, startTime: String, endTime: String) {

        viewModelScope.launch {
            val state = _bossMypageUiState.value as BossMyPageUiState.Success
            _bossMypageUiState.value =
                BossMyPageUiState.Success(salesInformation = state.salesInformation.mapIndexed { index, storeSalesInformation ->
                    if (index == weekDayNum) {
                        storeSalesInformation.copy(
                            salesTimeRange = storeSalesInformation.salesTimeRange.plus(
                                TimeRange(
                                    startTime,
                                    endTime
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

    fun deleteStore() {
        viewModelScope.launch {

        }
    }

    fun quit(){
        viewModelScope.launch {

        }
    }
}