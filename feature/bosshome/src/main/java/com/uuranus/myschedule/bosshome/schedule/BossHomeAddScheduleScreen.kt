package com.uuranus.myschedule.bosshome.schedule

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uuranus.designsystem.calendar.getLanguageMDWDate
import com.uuranus.designsystem.component.MyScheduleAppBar
import com.uuranus.designsystem.theme.MyScheduleTheme
import com.uuranus.navigation.MyScheduleScreens
import com.uuranus.navigation.currentComposeNavigator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.net.UnknownHostException

@Composable
fun BossHomeAddScheduleScreen(
    viewModel: BossHomeScheduleViewModel = hiltViewModel(),
) {

    val composeNavigator = currentComposeNavigator

    val myScheduleInfo by viewModel.myScheduleInfo.collectAsStateWithLifecycle()
    val workers by viewModel.workers.collectAsStateWithLifecycle()

    val coroutineScope = rememberCoroutineScope()

    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(true) {
        viewModel.errorFlow.collectLatest { throwable ->
            coroutineScope.launch {
                snackBarHostState.showSnackbar(
                    when (throwable) {
                        is UnknownHostException -> "네트워크 연결이 원활하지 않습니다"
                        else -> "알 수 없는 오류가 발생했습니다"
                    }
                )
            }
        }
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
                        text = "스케줄 추가",
                        style = MyScheduleTheme.typography.bold16
                    )
                }, actions = {
                    Text(
                        text = "완료",
                        style = MyScheduleTheme.typography.semiBold16,
                        modifier = Modifier.clickable {
                            viewModel.addSchedule()
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
                    " 시간 추가",
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
                    " 직원 선택",
                    style = MyScheduleTheme.typography.semiBold16,
                    modifier = Modifier.padding(horizontal = screenPadding, vertical = 8.dp)
                )
                HorizontalDivider(color = MyScheduleTheme.colors.lightGray)

                Spacer(modifier = Modifier.height(22.dp))

                if (workers.isNotEmpty()) {
                    ScheduleWorkerInput(
                        workers = workers,
                        selectedWorker = myScheduleInfo.memberId,
                        onWorkerChanged = {
                            viewModel.saveWorkerId(it)
                        },
                    )
                }

            }

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