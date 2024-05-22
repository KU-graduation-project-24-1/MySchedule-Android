package com.uuranus.home

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uuranus.designsystem.calendar.DateInfo
import com.uuranus.designsystem.calendar.ScheduleData
import com.uuranus.designsystem.calendar.ScheduleInfo
import com.uuranus.designsystem.calendar.dashToDateInfo
import com.uuranus.designsystem.calendar.getDashYMDDate
import com.uuranus.designsystem.calendar.getDashYMDate
import com.uuranus.domain.AcceptFillInUseCase
import com.uuranus.domain.AddPossibleTimeUseCase
import com.uuranus.domain.DeletePossibleTimeUseCase
import com.uuranus.domain.GetMonthlyPossibleTimesUseCase
import com.uuranus.domain.GetMonthlyScheduleUseCase
import com.uuranus.domain.GetUserDataUseCase
import com.uuranus.domain.RequestFillInUseCase
import com.uuranus.model.MyPossibleTimeInfo
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
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val getMonthlyScheduleUseCase: GetMonthlyScheduleUseCase,
    private val requestFillIn: RequestFillInUseCase,
    private val acceptFillIn: AcceptFillInUseCase,
    private val getMonthlyPossibleTimesUseCase: GetMonthlyPossibleTimesUseCase,
    private val addPossibleTimesUseCase: AddPossibleTimeUseCase,
    private val deletePossibleTimeUseCase: DeletePossibleTimeUseCase,
) : ViewModel() {

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    private val _userData =
        MutableStateFlow(
            com.uuranus.model.UserData(
                -1,
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
        getMonthlySchedules()
    }

    fun getUserData() = _userData.value
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
                            _userData.value.accessToken,
                            _userData.value.storeId,
                            getDashYMDate(_currentDate.value)
                        )
                    )
                }
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

    fun requestFillIn(scheduleId: Int) {
        viewModelScope.launch {
            flow {
                emit(
                    requestFillIn(
                        _userData.value.accessToken,
                        _userData.value.storeId,
                        scheduleId,
                        _userData.value.memberId,
                    )
                )
            }.map {
                val state = _homeUiState.value as HomeUiState.ScheduleSuccess

                HomeUiState.ScheduleSuccess(
                    schedules = state.schedules.mapValues { (_, scheduleInfo) ->
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
                _homeUiState.value = it
            }
        }
    }

    fun acceptFillIn(scheduleId: Int) {
        viewModelScope.launch {
            flow {
                emit(
                    acceptFillIn(
                        _userData.value.accessToken,
                        _userData.value.storeId,
                        scheduleId,
                        _userData.value.memberId
                    )
                )
            }.map { result ->
                val state = _homeUiState.value as HomeUiState.ScheduleSuccess

                if (result) {
                    HomeUiState.ScheduleSuccess(
                        schedules = state.schedules.mapValues { (_, scheduleInfo) ->
                            val schedules = scheduleInfo.schedules.map {
                                if (it.detail.scheduleId == scheduleId) {
                                    it.copy(
                                        detail = it.detail.copy(
                                            memberId = _userData.value.memberId,
                                            workerName = _userData.value.name,
                                            workerType = _userData.value.workerType,
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
                } else {
                    HomeUiState.ScheduleSuccess(
                        schedules = state.schedules.mapValues { (_, scheduleInfo) ->
                            val schedules = scheduleInfo.schedules.filterNot {
                                it.detail.scheduleId == scheduleId
                            }
                            scheduleInfo.copy(
                                isCheckNeeded = (schedules.count { it.detail.isFillInNeeded } != 0),
                                schedules = schedules
                            )
                        }
                    )
                }

            }.catch {
                _errorFlow.emit(it)
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
                        _userData.value.accessToken,
                        _userData.value.storeId,
                        getDashYMDate(_currentDate.value)
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

    fun addPossibleTime(dateInfo: DateInfo, startTime: String, endTime: String) {
        viewModelScope.launch {
            flow {
                emit(
                    addPossibleTimesUseCase(
                        _userData.value.accessToken,
                        _userData.value.memberId,
                        _userData.value.storeId,
                        getDashYMDDate(dateInfo),
                        startTime,
                        endTime
                    )
                )
            }.map { storeAvailableScheduleId ->
                val cur = (_homeUiState.value) as HomeUiState.PossibleTimeSuccess

                if (cur.schedules.containsKey(dateInfo)) {
                    HomeUiState.PossibleTimeSuccess(
                        schedules = cur.schedules.mapValues { (di, scheduleInfo) ->
                            if (di == dateInfo) {
                                scheduleInfo.copy(
                                    schedules = scheduleInfo.schedules.plus(
                                        ScheduleData(
                                            startTime,
                                            Color.White,
                                            MyPossibleTimeInfo(
                                                storeAvailableScheduleId, startTime, endTime
                                            )
                                        )
                                    ).sortedBy { it.detail.startTime }
                                )
                            } else {
                                scheduleInfo
                            }
                        } as HashMap<DateInfo, ScheduleInfo<MyPossibleTimeInfo>>
                    )
                } else {
                    val newScheduleData = ScheduleData(
                        startTime,
                        Color.White,
                        MyPossibleTimeInfo(
                            storeAvailableScheduleId, startTime, endTime
                        )
                    )

                    val updatedSchedules = HashMap(cur.schedules)

                    val existingSchedules = updatedSchedules[dateInfo]?.schedules ?: emptyList()

                    val updatedScheduleList =
                        existingSchedules.plus(newScheduleData).sortedBy { it.detail.startTime }

                    updatedSchedules[dateInfo] = ScheduleInfo(
                        isCheckNeeded = false,
                        schedules = updatedScheduleList
                    )

                    HomeUiState.PossibleTimeSuccess(
                        schedules = updatedSchedules
                    )
                }

            }.catch {
                _errorFlow.emit(it)
            }.collect {
                _homeUiState.value = it
            }

        }
    }

    fun deletePossibleTime(dateInfo: DateInfo, storeMemberAvailableTimeId: Int) {
        viewModelScope.launch {
            flow {
                emit(
                    deletePossibleTimeUseCase(
                        _userData.value.accessToken,
                        _userData.value.memberId,
                        _userData.value.storeId,
                        storeMemberAvailableTimeId
                    )
                )
            }.map {
                val cur = (_homeUiState.value) as HomeUiState.PossibleTimeSuccess
                HomeUiState.PossibleTimeSuccess(
                    schedules = cur.schedules.mapValues { (di, scheduleInfo) ->
                        if (di == dateInfo) {
                            scheduleInfo.copy(
                                schedules = scheduleInfo.schedules.filterNot { scheduleData ->
                                    scheduleData.detail.storeMemberAvailableTimeId == storeMemberAvailableTimeId
                                }
                            )
                        } else {
                            scheduleInfo
                        }
                    })
            }.catch {
                _errorFlow.emit(it)
            }.collect {
                _homeUiState.value = it
            }

        }
    }
}