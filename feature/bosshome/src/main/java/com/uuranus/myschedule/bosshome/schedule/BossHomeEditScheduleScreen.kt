package com.uuranus.myschedule.bosshome.schedule

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uuranus.designsystem.calendar.getLanguageMDWDate
import com.uuranus.designsystem.component.DeleteDialog
import com.uuranus.designsystem.component.MyScheduleAppBar
import com.uuranus.designsystem.component.MyScheduleOutlinedButton
import com.uuranus.designsystem.component.TimePickerSingleDialog
import com.uuranus.designsystem.theme.MyScheduleTheme
import com.uuranus.model.WorkerInfo
import com.uuranus.navigation.MyScheduleScreens
import com.uuranus.navigation.currentComposeNavigator

val screenPadding = 16.dp

@Composable
fun BossHomeEditScheduleScreen(
    viewModel: BossHomeScheduleViewModel = hiltViewModel(),
) {

    val composeNavigator = currentComposeNavigator

    val myScheduleInfo by viewModel.myScheduleInfo.collectAsStateWithLifecycle()

    var showDeleteDialog: Boolean by remember {
        mutableStateOf(false)
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MyScheduleTheme.colors.background
    ) {
        Box(Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.fillMaxSize()) {
                MyScheduleAppBar(title = {
                    Text(
                        text = "스케줄 변경",
                        style = MyScheduleTheme.typography.bold16
                    )
                }, actions = {
                    Text(
                        text = "완료",
                        style = MyScheduleTheme.typography.semiBold16,
                        modifier = Modifier.clickable {
                            viewModel.editSchedule()
                            composeNavigator.popUpTo(
                                route = MyScheduleScreens.BossHome.name,
                                inclusive = false
                            )
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                })

                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    getLanguageMDWDate(myScheduleInfo.dateInfo),
                    style = MyScheduleTheme.typography.semiBold16,
                    modifier = Modifier.padding(horizontal = screenPadding)
                )
                Spacer(modifier = Modifier.height(35.dp))

                Text(
                    " 시간 변경",
                    style = MyScheduleTheme.typography.semiBold16,
                    modifier = Modifier.padding(horizontal = screenPadding, vertical = 8.dp)
                )
                HorizontalDivider(color = MyScheduleTheme.colors.lightGray)

                Spacer(modifier = Modifier.height(22.dp))

                ScheduleTimeInput(
                    myScheduleInfo.startTime,
                    myScheduleInfo.endTime, onStartTimeChanged = {
                        viewModel.saveStartTime(it)
                    }, onEndTimeChanged = {
                        viewModel.saveEndTime(it)
                    })

                Spacer(modifier = Modifier.height(57.dp))
                Text(
                    " 직원 변경",
                    style = MyScheduleTheme.typography.semiBold16,
                    modifier = Modifier.padding(horizontal = screenPadding, vertical = 8.dp)
                )
                HorizontalDivider(color = MyScheduleTheme.colors.lightGray)

                Spacer(modifier = Modifier.height(22.dp))

                ScheduleWorkerInput(
                    selectedWorker = myScheduleInfo.memberId,
                    onWorkerChanged = {
                        viewModel.saveWorkerId(it)
                    },
                    viewModel = viewModel
                )
            }


            MyScheduleOutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(screenPadding)
                    .align(Alignment.BottomCenter),
                paddingValues = PaddingValues(all = 13.dp),
                buttonState = true,
                content = {
                    Text("스케줄 식제", style = MyScheduleTheme.typography.semiBold16)
                }
            ) {
                showDeleteDialog = true
            }
        }

    }

    if (showDeleteDialog) {
        DeleteDialog(
            title = "스케줄 저장",
            content = "해당 스케줄을 삭제하시겠습니까?",
            onDismissDialog = {
                showDeleteDialog = false
            },
            onConfirmDialog = {
                viewModel.deleteSchedule()
                showDeleteDialog = false
                composeNavigator.popUpTo(
                    route = MyScheduleScreens.BossHome.name,
                    inclusive = false
                )
            }
        )
    }
}

@Composable
fun ScheduleTimeInput(
    startTime: String,
    endTime: String,
    onStartTimeChanged: (String) -> Unit,
    onEndTimeChanged: (String) -> Unit,
) {

    var showStartTimePicker: Boolean by remember {
        mutableStateOf(false)
    }

    var showEndTimePicker: Boolean by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.padding(horizontal = screenPadding)) {
        Row {
            Text(
                text = "시작 시간",
                modifier = Modifier.weight(1f),
                style = MyScheduleTheme.typography.semiBold16
            )
            Text(
                text = startTime, modifier = Modifier
                    .weight(4f)
                    .clickable {
                        showStartTimePicker = true
                    },
                style = MyScheduleTheme.typography.regular16
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row {
            Text(
                text = "종료 시간", modifier = Modifier.weight(1f),
                style = MyScheduleTheme.typography.semiBold16
            )
            Text(
                text = endTime, modifier = Modifier
                    .weight(4f)
                    .clickable {
                        showEndTimePicker = true
                    },
                style = MyScheduleTheme.typography.regular16
            )
        }
    }

    if (showStartTimePicker) {
        TimePickerSingleDialog(onTimeSelected = {
            onStartTimeChanged(it)
            showStartTimePicker = false
        }, onDismissDialog = {
            showStartTimePicker = false
        })
    }

    if (showEndTimePicker) {
        TimePickerSingleDialog(onTimeSelected = {
            onEndTimeChanged(it)
            showEndTimePicker = false
        }, onDismissDialog = {
            showStartTimePicker = false
        })
    }
}

@Composable
fun ScheduleWorkerInput(
    viewModel: BossHomeScheduleViewModel,
    selectedWorker: Int,
    onWorkerChanged: (Int) -> Unit,
) {

    val workers by viewModel.workers.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier
            .padding(horizontal = screenPadding)
    ) {
        items(workers.size) { index ->
            WorkerListItem(
                (workers[index].memeberId == selectedWorker),
                onWorkerSelected = {
                    onWorkerChanged(workers[index].memeberId)
                },
                workers[index]
            )
        }
    }

}

@Composable
fun WorkerListItem(
    selected: Boolean,
    onWorkerSelected: () -> Unit,
    workerInfo: WorkerInfo,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .border(
                width = 1.dp, shape = RoundedCornerShape(12.dp),
                color = if (selected) MyScheduleTheme.colors.primary else MyScheduleTheme.colors.lightGray
            )
            .clickable {
                onWorkerSelected()
            }
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Text(
            workerInfo.workerName,
            style = MyScheduleTheme.typography.semiBold16
        )
        Text(
            "고용형태: ${workerInfo.workerType}",
            style = MyScheduleTheme.typography.regular14
        )
    }
}

@Preview
@Composable
fun BossHomeEditScheduleScreenPreview() {
    MyScheduleTheme {
        BossHomeEditScheduleScreen()
    }
}