package com.uuranus.myschedule.bosshome

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uuranus.designsystem.calendar.DateInfo
import com.uuranus.designsystem.calendar.ScheduleData
import com.uuranus.designsystem.calendar.ScheduleInfo
import com.uuranus.designsystem.calendar.dashToDateInfo
import com.uuranus.designsystem.calendar.getDashYMDDate
import com.uuranus.designsystem.calendar.getDashYMDate
import com.uuranus.domain.AcceptFillIn
import com.uuranus.domain.ChangeScheduleInfo
import com.uuranus.domain.GetMonthlyScheduleUseCase
import com.uuranus.domain.GetUserDataUseCase
import com.uuranus.domain.RequestFillIn
import com.uuranus.model.MyScheduleInfo
import com.uuranus.model.MyScheduleNavType
import com.uuranus.model.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class BossHomeViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val getMonthlyScheduleUseCase: GetMonthlyScheduleUseCase,
    private val requestFillIn: RequestFillIn,
    private val acceptFillIn: AcceptFillIn,
) : ViewModel() {

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    private val _userData =
        MutableStateFlow(
            UserData(
                -1,
                0,
                "",
                "",
                false
            )
        )

    private val _currentDate = MutableStateFlow(DateInfo.create(Calendar.getInstance()))

    private val _bossHomeUiState = MutableStateFlow<BossHomeUiState>(BossHomeUiState.Loading)
    val bossHomeUiState: StateFlow<BossHomeUiState> = _bossHomeUiState.asStateFlow()


    init {
        getMonthlySchedules()
    }

    fun getUserData(): UserData = _userData.value
    fun setCurrentDate(dateInfo: DateInfo) {
        _currentDate.value = dateInfo
    }

    fun getCurrentDate(): DateInfo = _currentDate.value

    fun getMonthlySchedules() {
        viewModelScope.launch {

            getUserDataUseCase().flatMapLatest {
                _userData.value = it
                flow {
                    emit(
                        getMonthlyScheduleUseCase(
                            _userData.value.storeId,
                            getDashYMDate(_currentDate.value)
                        )
                    )
                }
            }.map { schedules ->
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
            }.collect {
                _bossHomeUiState.value = it
            }
        }
    }

    fun requestFillIn(scheduleId: Int) {
        viewModelScope.launch {
            flow {
                emit(
                    requestFillIn(
                        _userData.value.storeId,
                        scheduleId,
                        _userData.value.memberId,
                    )
                )
            }.map {
                val state = _bossHomeUiState.value as BossHomeUiState.Success

                BossHomeUiState.Success(
                    schedules = state.schedules.mapValues { (dateInfo, scheduleInfo) ->
                        val schedules = scheduleInfo.schedules.map {
                            if (it.detail.scheduleId == scheduleId) {
                                it.copy(
                                    detail = it.detail.copy(
                                        isFillInNeeded = true
                                    )
                                )
                            } else {
                                it
                            }
                        }
                        scheduleInfo.copy(
                            isCheckNeeded = (schedules.count { it.detail.isFillInNeeded } != 0),
                            schedules = schedules
                        )
                    }
                )
            }.catch {
                _errorFlow.emit(it)
            }.collect {
                _bossHomeUiState.value = it
            }
        }
    }

    fun acceptFillIn(scheduleId: Int) {
        viewModelScope.launch {
            flow {
                emit(
                    acceptFillIn(
                        _userData.value.storeId,
                        scheduleId,
                        _userData.value.memberId
                    )
                )
            }.map {
                val state = _bossHomeUiState.value as BossHomeUiState.Success

                BossHomeUiState.Success(
                    schedules = state.schedules.mapValues { (dateInfo, scheduleInfo) ->
                        val schedules = scheduleInfo.schedules.map {
                            if (it.detail.scheduleId == scheduleId) {
                                it.copy(
                                    detail = it.detail.copy(
                                        isFillInNeeded = false
                                    )
                                )
                            } else {
                                it
                            }
                        }
                        scheduleInfo.copy(
                            isCheckNeeded = (schedules.count { it.detail.isFillInNeeded } != 0),
                            schedules = schedules
                        )
                    }
                )
            }.catch {
                _errorFlow.emit(it)
            }.collect {
                _bossHomeUiState.value = it
            }
        }
    }

}