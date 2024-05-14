package com.uuranus.home

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uuranus.designsystem.calendar.DateInfo
import com.uuranus.designsystem.calendar.ScheduleData
import com.uuranus.designsystem.calendar.ScheduleInfo
import com.uuranus.designsystem.calendar.dashToDateInfo
import com.uuranus.designsystem.calendar.getDashYMDDate
import com.uuranus.domain.GetMonthlyPossibleTimesUseCase
import com.uuranus.domain.GetMonthlyScheduleUseCase
import com.uuranus.domain.GetUserDataUseCase
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
class HomeViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val getMonthlyScheduleUseCase: GetMonthlyScheduleUseCase,
    private val getMonthlyPossibleTimesUseCase: GetMonthlyPossibleTimesUseCase,
) : ViewModel() {

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    private val _userData =
        MutableStateFlow(
            com.uuranus.model.UserData(
                0,
                0,
                "",
                "",
                false
            )
        )

    private val _currentDate = MutableStateFlow(DateInfo.create(Calendar.getInstance()))

    private val _homeUiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()

    init {
        viewModelScope.launch {

            getUserDataUseCase().map {
                _userData.value = it
            }
        }
        getMonthlySchedules()
    }

    fun setCurrentDate(dateInfo: DateInfo) {
        _currentDate.value = dateInfo
    }

    fun getCurrentDate(): DateInfo = _currentDate.value

    fun getMonthlySchedules() {
        println("GetMonthlySchedules")
        viewModelScope.launch {
            flow {
                emit(
                    getMonthlyScheduleUseCase(
                        _userData.value.storeId,
                        getDashYMDDate(_currentDate.value)
                    )
                )
            }.map { schedules ->

                HomeUiState.ScheduleSuccess(
                    schedules = schedules.mapKeys { keys ->
                        keys.key.dashToDateInfo()
                    }.mapValues { values ->
                        ScheduleInfo(
                            isCheckNeeded = values.value.any { it.isFillInNeeded },
                            schedules = values.value.map { myScheduleInfo ->
                                ScheduleData(
                                    title = "${myScheduleInfo.workerName.substring(myScheduleInfo.workerName.length - 2)} ${myScheduleInfo.startTime}",
                                    color = Color.White,
                                    detail = myScheduleInfo
                                )
                            }
                        )
                    }
                )
            }.catch { throwable ->
                _errorFlow.emit(throwable)
            }.collect {
                _homeUiState.value = it
            }
        }
    }

    fun getMonthlyPossibleTimes() {
        viewModelScope.launch {
            flow {
                emit(
                    getMonthlyPossibleTimesUseCase(
                        _userData.value.storeId,
                        getDashYMDDate(_currentDate.value)
                    )
                )
            }.map { schedules ->
                HomeUiState.PossibleTimeSuccess(
                    schedules = schedules.mapKeys { keys ->
                        keys.key.dashToDateInfo()
                    }.mapValues { values ->
                        ScheduleInfo(
                            isCheckNeeded = false,
                            schedules = values.value.map { myPossibleTimesInfo ->
                                ScheduleData(
                                    title = myPossibleTimesInfo.startTime,
                                    color = Color.White,
                                    detail = myPossibleTimesInfo
                                )
                            }
                        )
                    }
                )
            }.catch { throwable ->
                _errorFlow.emit(throwable)
            }.collect {
                _homeUiState.value = it
            }
        }
    }
}