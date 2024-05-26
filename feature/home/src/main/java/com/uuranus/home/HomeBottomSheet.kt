package com.uuranus.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uuranus.designsystem.calendar.DateInfo
import com.uuranus.designsystem.calendar.ScheduleData
import com.uuranus.designsystem.calendar.getLanguageYMDDate
import com.uuranus.designsystem.component.DeleteDialog
import com.uuranus.designsystem.component.MyScheduleFilledButton
import com.uuranus.designsystem.component.TimePickerDialog
import com.uuranus.designsystem.theme.MyScheduleTheme
import com.uuranus.model.MyPossibleTimeInfo
import com.uuranus.model.MyScheduleInfo

@Composable
fun MyScheduleBottomSheetContent(
    dateInfo: DateInfo,
    scheduleInfo: List<ScheduleData<MyScheduleInfo>>,
    onClick: (DateInfo, ScheduleData<MyScheduleInfo>) -> Unit,
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = getLanguageYMDDate(dateInfo),
                    style = MyScheduleTheme.typography.semiBold16
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
        items(scheduleInfo.size) { index ->
            com.uuranus.myschedule.core.common.home.MyScheduleDetailListItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                scheduleInfo[index]
            ) {
                if (scheduleInfo[index].detail.isFillInNeeded || scheduleInfo[index].detail.isMine) {
                    Row {
                        MyScheduleFilledButton(
                            modifier = Modifier,
                            paddingValues = PaddingValues(
                                horizontal = 14.dp,
                                vertical = 5.dp
                            ), buttonState = true,
                            color = MyScheduleTheme.colors.primary,
                            content = {
                                if (scheduleInfo[index].detail.isMine) {
                                    Text(
                                        "요청", style = MyScheduleTheme.typography.regular14,
                                    )
                                } else if (scheduleInfo[index].detail.isFillInNeeded) {
                                    Text("수락", style = MyScheduleTheme.typography.regular14)
                                }
                            }
                        ) {
                            onClick(dateInfo, scheduleInfo[index])
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun PossibleTimeBottomSheetContent(
    homeViewModel: HomeViewModel,
    dateInfo: DateInfo,
    scheduleInfo: List<ScheduleData<MyPossibleTimeInfo>>,
) {

    val showTimePicker: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }

    var showDeleteDialog by remember {
        mutableStateOf(false)
    }

    var selectedTimeId by remember {
        mutableStateOf(-1)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                Text(
                    text = getLanguageYMDDate(dateInfo),
                    style = MyScheduleTheme.typography.semiBold16,
                    modifier = Modifier.align(Alignment.Center)
                )

                MyScheduleFilledButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    paddingValues = PaddingValues(
                        horizontal = 14.dp,
                        vertical = 5.dp
                    ), buttonState = homeViewModel.isPossibleAdd(dateInfo),
                    color = MyScheduleTheme.colors.primary,
                    content = {
                        Text(
                            "추가", style = MyScheduleTheme.typography.regular14,
                        )
                    }
                ) {
                    showTimePicker.value = true
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
        items(scheduleInfo.size) { index ->
            PossibleTimeDetailListItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                scheduleInfo[index]
            ) {
                Row {
                    MyScheduleFilledButton(
                        modifier = Modifier,
                        paddingValues = PaddingValues(
                            horizontal = 14.dp,
                            vertical = 5.dp
                        ), buttonState = true,
                        color = MyScheduleTheme.colors.lightGray,
                        content = {
                            Text(
                                "삭제", style = MyScheduleTheme.typography.regular14,
                            )
                        }
                    ) {
                        showDeleteDialog = true
                        selectedTimeId = scheduleInfo[index].detail.storeMemberAvailableTimeId
                    }
                }
            }
        }
    }

    if (showTimePicker.value) {

        TimePickerDialog(onTimeSelected = { start, end ->
            if (start.isNotEmpty() && end.isNotEmpty()) {
                homeViewModel.addPossibleTime(dateInfo, start, end)
            }

            showTimePicker.value = false
        }, onDismissDialog = {

            showTimePicker.value = false
        })
    }

    if (showDeleteDialog) {
        DeleteDialog(
            title = "가능한 시간 삭제",
            content = "해당 시간을 삭제하시겠습니까?",
            onDismissDialog = {
                showDeleteDialog = false
            }, onConfirmDialog = {
                showDeleteDialog = false
                homeViewModel.deletePossibleTime(
                    dateInfo,
                    selectedTimeId
                )
            })
    }


}