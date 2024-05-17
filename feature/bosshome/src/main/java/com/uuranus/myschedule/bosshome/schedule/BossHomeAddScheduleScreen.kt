package com.uuranus.myschedule.bosshome.schedule

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uuranus.designsystem.calendar.getLanguageMDWDate
import com.uuranus.designsystem.component.MyScheduleAppBar
import com.uuranus.designsystem.theme.MyScheduleTheme
import com.uuranus.model.MyScheduleNavType
import com.uuranus.navigation.MyScheduleScreens
import com.uuranus.navigation.currentComposeNavigator

@Composable
fun BossHomeAddScheduleScreen(
    viewModel: BossHomeScheduleViewModel = hiltViewModel(),
) {
    val composeNavigator = currentComposeNavigator

    val myScheduleInfo by viewModel.myScheduleInfo.collectAsStateWithLifecycle()

    var startTime: String by remember {
        mutableStateOf("00:00")
    }
    var endTime: String by remember {
        mutableStateOf("00:00")
    }
    var selectedWorker: Int by remember {
        mutableStateOf(0)
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MyScheduleTheme.colors.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            MyScheduleAppBar(title = {
                Text(
                    text = "스케줄 추가",
                    style = MyScheduleTheme.typography.bold16
                )
            }, actions = {
                Text(
                    text = "완료",
                    style = MyScheduleTheme.typography.semiBold16,
                    modifier = Modifier.clickable {
                        composeNavigator.navigateBackWithResult(
                            key = "updatedSchedule",
                            result = MyScheduleNavType(
                                scheduleId = -1,
                                getLanguageMDWDate(myScheduleInfo.dateInfo),
                                startTime = startTime,
                                endTime = endTime,
                                memberId = selectedWorker
                            ),
                            route = MyScheduleScreens.BossHome.name
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
                " 시간 선택",
                style = MyScheduleTheme.typography.semiBold16,
                modifier = Modifier.padding(horizontal = screenPadding, vertical = 8.dp)
            )
            HorizontalDivider(color = MyScheduleTheme.colors.lightGray)

            Spacer(modifier = Modifier.height(22.dp))

            ScheduleTimeInput(
                startTime,
                endTime, onStartTimeChanged = {
                    startTime = it
                }, onEndTimeChanged = {
                    endTime = it
                })

            Spacer(modifier = Modifier.height(57.dp))
            Text(
                " 직원 선택",
                style = MyScheduleTheme.typography.semiBold16,
                modifier = Modifier.padding(horizontal = screenPadding, vertical = 8.dp)
            )
            HorizontalDivider(color = MyScheduleTheme.colors.lightGray)

            Spacer(modifier = Modifier.height(22.dp))

            ScheduleWorkerInput(
                selectedWorker = selectedWorker,
                onWorkerChanged = {
                    selectedWorker = it
                })
        }

    }
}

@Preview
@Composable
fun BossHomeAddScheduleScreenPreview() {
    MyScheduleTheme {
        BossHomeAddScheduleScreen()
    }
}