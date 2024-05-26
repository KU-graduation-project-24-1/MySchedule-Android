package com.uuranus.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uuranus.designsystem.calendar.DateInfo
import com.uuranus.domain.AddFixedPossibleTimesUseCase
import com.uuranus.domain.DeleteFixedPossibleTimeUseCase
import com.uuranus.domain.GetFixedPossibleTimesUseCase
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
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val getFixedPossibleTimesUseCase: GetFixedPossibleTimesUseCase,
    private val addFixedPossibleTimesUseCase: AddFixedPossibleTimesUseCase,
    private val deleteFixedPossibleTimeUseCase: DeleteFixedPossibleTimeUseCase,
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

    private val _mypageUiState = MutableStateFlow<MyPageUiState>(MyPageUiState.Loading)
    val mypageUiState: StateFlow<MyPageUiState> = _mypageUiState

    init {

        viewModelScope.launch {
            getUserDataUseCase().flatMapLatest {

                _userData.value = it
                flow {
                    emit(getFixedPossibleTimesUseCase(it.accessToken, it.storeId))
                }
            }.map {
                println("it ${it.joinToString("\n")}")
                MyPageUiState.Success(
                    fixedPossibleTimes = it
                )
            }.catch {
                _errorFlow.emit(it)
            }.collect {
                _mypageUiState.value = it
            }
        }

    }

    fun addFixedPossibleTime(weekDayNum: Int, startTime: String, endTime: String) {

        val state = _mypageUiState.value
        if (state !is MyPageUiState.Success) {
            return
        }

        viewModelScope.launch {
            flow {
                emit(
                    addFixedPossibleTimesUseCase(
                        _userData.value.accessToken,
                        _userData.value.storeId,
                        weekDayNum,
                        startTime,
                        endTime
                    )
                )
            }.map { storeAvailableTimeByDayId ->
                state.copy(
                    fixedPossibleTimes = state.fixedPossibleTimes.mapIndexed { index, timeRanges ->
                        if (index == weekDayNum) {
                            timeRanges.plus(
                                TimeRange(
                                    timeId = storeAvailableTimeByDayId,
                                    startTime = startTime,
                                    endTime = endTime
                                )
                            )
                        } else {
                            timeRanges
                        }
                    }
                )
            }.catch {
                _errorFlow.emit(it)
            }.collect {
                _mypageUiState.value = it
            }
        }
    }

    fun deleteFixedPossibleTime(timeId: Int) {
        viewModelScope.launch {
            flow {
                emit(
                    deleteFixedPossibleTimeUseCase(
                        _userData.value.accessToken,
                        _userData.value.storeId,
                        timeId
                    )
                )
            }.map {
                val state = _mypageUiState.value as MyPageUiState.Success

                MyPageUiState.Success(
                    fixedPossibleTimes = state.fixedPossibleTimes.map { weekDayList ->
                        weekDayList.filterNot {
                            it.timeId == timeId
                        }
                    }
                )

            }.catch {
                _errorFlow.emit(it)
            }.collect {
                _mypageUiState.value = it
            }
        }
    }

    fun quit() {
        viewModelScope.launch {

        }
    }
}