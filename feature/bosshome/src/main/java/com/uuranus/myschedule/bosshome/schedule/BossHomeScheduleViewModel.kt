package com.uuranus.myschedule.bosshome.schedule

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uuranus.designsystem.calendar.DateInfo
import com.uuranus.designsystem.calendar.dashToDateInfo
import com.uuranus.model.MyScheduleNavType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class BossHomeScheduleViewModel @Inject constructor(

    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val scheduleInfo =
        savedStateHandle.getStateFlow<MyScheduleNavType?>("scheduleInfo", null)
    val myScheduleInfo: StateFlow<BossScheduleEditInfo> =
        scheduleInfo.filterNotNull().flatMapLatest { scheduleInfo ->
            flow {
                emit(
                    BossScheduleEditInfo(
                        scheduleId = -1,
                        dateInfo = scheduleInfo.dateDashString.dashToDateInfo(),
                        startTime = scheduleInfo.startTime,
                        endTime = scheduleInfo.endTime,
                        memberId = scheduleInfo.memberId,
                    )
                )
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = BossScheduleEditInfo(
                scheduleId = -1,
                dateInfo = DateInfo.create(0, 0, 0),
                startTime = "00:00",
                endTime = "00:00",
                memberId = -1,
            ),
        )


}


data class BossScheduleEditInfo(
    val scheduleId: Int,
    val dateInfo: DateInfo,
    val startTime: String,
    val endTime: String,
    val memberId: Int,
)