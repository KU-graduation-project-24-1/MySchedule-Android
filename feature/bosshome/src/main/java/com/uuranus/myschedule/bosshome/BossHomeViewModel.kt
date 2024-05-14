package com.uuranus.myschedule.bosshome

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uuranus.designsystem.calendar.DateInfo
import com.uuranus.designsystem.calendar.ScheduleData
import com.uuranus.designsystem.calendar.ScheduleInfo
import com.uuranus.designsystem.calendar.dashToDateInfo
import com.uuranus.designsystem.calendar.getDashYMDDate
import com.uuranus.domain.GetMonthlyScheduleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class BossHomeViewModel @Inject constructor(
    private val getMonthlyScheduleUseCase: GetMonthlyScheduleUseCase,
) : ViewModel() {

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    private val _userDate = MutableStateFlow<Int>(1)

    private val _homeUiState = MutableStateFlow<BossHomeUiState>(BossHomeUiState.Loading)
    val homeUiState: StateFlow<BossHomeUiState> = _homeUiState

    init {
        //userData 가져오기
//        getMonthlySchedules(DateInfo.create(Calendar.getInstance()))
    }

    fun getMonthlySchedules(dateInfo: DateInfo) {
        viewModelScope.launch {
            combine(
                homeUiState,
                flow {
                    emit(
                        getMonthlyScheduleUseCase(
                            _userDate.value,
                            getDashYMDDate(dateInfo)
                        )
                    )
                }
            ) { _, schedules ->

                BossHomeUiState.Success(
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
            }.collect { _homeUiState.value = it }
        }
    }
}