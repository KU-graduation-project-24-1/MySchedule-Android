package com.uuranus.mypage

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uuranus.designsystem.component.LoadingScreen
import com.uuranus.designsystem.component.MyScheduleAppBar
import com.uuranus.designsystem.component.NetworkImage
import com.uuranus.designsystem.component.TimePickerDialog
import com.uuranus.designsystem.theme.MyScheduleTheme
import com.uuranus.model.TimeRange
import com.uuranus.myschedule.core.common.mypage.MyInfo
import com.uuranus.myschedule.core.designsystem.R

@Composable
fun MyPageScreen(
    myPageViewModel: MyPageViewModel = hiltViewModel(),
) {

    val myPageUiState by myPageViewModel.mypageUiState.collectAsStateWithLifecycle()
    val userData by myPageViewModel.userData.collectAsStateWithLifecycle()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MyScheduleTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            MyScheduleAppBar(title = {
                Text(text = "마이 페이지", style = MyScheduleTheme.typography.bold20)
            },
                actions = {
                    Text("탈퇴", style = MyScheduleTheme.typography.semiBold16,
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .clickable {
                                //mypageViewModel.quit()
                            })
                })

            when (myPageUiState) {
                is MyPageUiState.Loading -> LoadingScreen()
                is MyPageUiState.Success -> Column {
                    MyInfo(userData)
                    Spacer(modifier = Modifier.height(58.dp))
                    MyFixedPossibleTime(
                        myPageViewModel,
                        (myPageUiState as MyPageUiState.Success).fixedPossibleTimes
                    )
                }
            }

        }

    }
}

@Composable
fun MyFixedPossibleTime(
    viewModel: MyPageViewModel,
    possibleTimes: List<List<TimeRange>>,
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp)
        ) {
            Text(
                "고정된 가능 시간", style = MyScheduleTheme.typography.semiBold16,
            )
        }
        HorizontalDivider(thickness = 1.dp, color = MyScheduleTheme.colors.lightGray)
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(possibleTimes.size) {
                DayPossibleTimeListItem(it, possibleTimes[it]) { start, end ->
                    viewModel.addFixedPossibleTime(it, start, end)
                }
            }
        }
    }
}

@Composable
fun DayPossibleTimeListItem(
    weekDayNum: Int,
    possibleTimes: List<TimeRange>,
    onEditButtonClick: (String, String) -> Unit,
) {

    val weekdays = listOf("월", "화", "수", "목", "금", "토", "일")

    var showTimePicker: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }

    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .border(0.5.dp, color = MyScheduleTheme.colors.lightGray)
                .padding(all = 16.dp)
        ) {
            Text(
                weekdays[weekDayNum],
                style = MyScheduleTheme.typography.semiBold16
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp)
        ) {
            if (possibleTimes.isEmpty()) {
                Text(
                    "-- : -- ~ -- : --", style = MyScheduleTheme.typography.regular16
                )
            } else {
                Text(
                    possibleTimes
                        .map {
                            "${it.startTime}~${it.endTime}"
                        }
                        .joinToString(", "), style = MyScheduleTheme.typography.regular16
                )
            }
        }

        Image(
            painter = painterResource(id = R.drawable.edit_icon),
            contentDescription = "고정된 가능 시간 추가하기",
            modifier = Modifier.clickable {
                showTimePicker.value = true
            }
        )
        Spacer(modifier = Modifier.width(16.dp))

    }

    if (showTimePicker.value) {

        TimePickerDialog(onTimeSelected = { start, end ->
            onEditButtonClick(start, end)
            showTimePicker.value = false
        }, onDismissDialog = {
            showTimePicker.value = false
        })
    }
}
