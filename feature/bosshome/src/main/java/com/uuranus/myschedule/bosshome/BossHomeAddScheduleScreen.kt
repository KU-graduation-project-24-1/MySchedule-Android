package com.uuranus.myschedule.bosshome

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
import com.uuranus.designsystem.calendar.DateInfo
import com.uuranus.designsystem.calendar.getLanguageYMWDate
import com.uuranus.designsystem.component.MyScheduleAppBar
import com.uuranus.designsystem.theme.MyScheduleTheme
import com.uuranus.model.MyScheduleNavType
import com.uuranus.navigation.MyScheduleScreens
import com.uuranus.navigation.currentComposeNavigator

@Composable
fun BossHomeAddScheduleScreen(
) {
    val composeNavigator = currentComposeNavigator

    val dateInfo = DateInfo(2024, 5, 19)
    var startTime: String by remember {
        mutableStateOf("00:00")
    }
    var endTime: String by remember {
        mutableStateOf("00:00")
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
                                getLanguageYMWDate(dateInfo),
                                startTime = startTime,
                                endTime = endTime,
                                memberId = 0
                            ),
                            route = MyScheduleScreens.BossHome.name
                        )
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
            })

            Spacer(modifier = Modifier.height(25.dp))
            Text(
                getLanguageYMWDate(dateInfo),
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

            ScheduleTimeInput()


            Spacer(modifier = Modifier.height(57.dp))
            Text(
                " 직원 선택",
                style = MyScheduleTheme.typography.semiBold16,
                modifier = Modifier.padding(horizontal = screenPadding, vertical = 8.dp)
            )
            HorizontalDivider(color = MyScheduleTheme.colors.lightGray)

            Spacer(modifier = Modifier.height(22.dp))

            ScheduleWorkerInput()
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