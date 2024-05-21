package com.uuranus.myschedule.bosshome.schedule

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uuranus.designsystem.calendar.DateInfo
import com.uuranus.designsystem.calendar.dashToDateInfo
import com.uuranus.designsystem.calendar.getDashYMDDate
import com.uuranus.domain.AddSchedule
import com.uuranus.domain.DeleteSchedule
import com.uuranus.domain.GetAllWorkersInfo
import com.uuranus.domain.GetUserDataUseCase
import com.uuranus.domain.UpdateSchedule
import com.uuranus.model.MyScheduleNavType
import com.uuranus.model.ScheduleUpdate
import com.uuranus.model.UserData
import com.uuranus.model.WorkerInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BossHomeScheduleViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getUserDataUseCase: GetUserDataUseCase,
    val getAllWorkersInfo: GetAllWorkersInfo,
    val updateSchedule: UpdateSchedule,
    val deleteSchedule: DeleteSchedule,
    val addSchedule: AddSchedule,
) : ViewModel() {

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow: SharedFlow<Throwable> get() = _errorFlow

    private val scheduleInfo =
        savedStateHandle.getStateFlow<MyScheduleNavType?>("scheduleInfo", null)

    private val _myScheduleInfo: MutableStateFlow<BossScheduleEditInfo> =
        MutableStateFlow(BossScheduleEditInfo(0, 0, DateInfo.create(0, 0, 0), "", "", 0))

    val myScheduleInfo: StateFlow<BossScheduleEditInfo> = _myScheduleInfo.asStateFlow()

    private val _workers: MutableStateFlow<List<WorkerInfo>> = MutableStateFlow(emptyList())
    val workers: StateFlow<List<WorkerInfo>> = _workers

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

    init {
        viewModelScope.launch {
            getUserDataUseCase().flatMapLatest { userData ->
                _userData.value = userData

                scheduleInfo.filterNotNull()
            }.flatMapLatest { myScheduleNavType ->
                _myScheduleInfo.value = BossScheduleEditInfo(
                    storeId = myScheduleNavType.storeId,
                    scheduleId = myScheduleNavType.scheduleId,
                    dateInfo = myScheduleNavType.dateDashString.dashToDateInfo(),
                    startTime = myScheduleNavType.startTime,
                    endTime = myScheduleNavType.endTime,
                    memberId = myScheduleNavType.memberId,
                )
                println("myscheduleInfo ${_myScheduleInfo.value}")
                flow {
                    emit(
                        getAllWorkersInfo(
                            _userData.value.accessToken,
                            myScheduleNavType.storeId
                        )
                    )
                }
            }.catch {
                _errorFlow.emit(it)
            }.collect {
                _workers.value = it
            }
        }

    }

    fun saveStartTime(startTime: String) {
        _myScheduleInfo.value = myScheduleInfo.value.copy(
            startTime = startTime
        )
    }

    fun saveEndTime(endTime: String) {
        _myScheduleInfo.value = myScheduleInfo.value.copy(
            endTime = endTime
        )
    }

    fun saveWorkerId(memberId: Int) {
        _myScheduleInfo.value = myScheduleInfo.value.copy(
            memberId = memberId
        )
    }

    fun editSchedule() {
        viewModelScope.launch {
            updateSchedule(
                _userData.value.accessToken,
                _myScheduleInfo.value.storeId,
                ScheduleUpdate(
                    _myScheduleInfo.value.scheduleId,
                    getDashYMDDate(_myScheduleInfo.value.dateInfo),
                    _myScheduleInfo.value.startTime,
                    _myScheduleInfo.value.endTime,
                    _myScheduleInfo.value.memberId,
                )
            )
        }
    }

    fun deleteSchedule() {
        viewModelScope.launch {
            deleteSchedule(
                _userData.value.accessToken,
                _userData.value.storeId,
                _myScheduleInfo.value.scheduleId
            )
        }
    }

    fun addSchedule() {
        viewModelScope.launch {
            addSchedule(
                _userData.value.accessToken,
                _myScheduleInfo.value.storeId,
                ScheduleUpdate(
                    -1,
                    getDashYMDDate(_myScheduleInfo.value.dateInfo),
                    _myScheduleInfo.value.startTime,
                    _myScheduleInfo.value.endTime,
                    _myScheduleInfo.value.memberId
                )
            )
        }
    }
}


data class BossScheduleEditInfo(
    val storeId: Int,
    val scheduleId: Int,
    val dateInfo: DateInfo,
    val startTime: String,
    val endTime: String,
    val memberId: Int,
)