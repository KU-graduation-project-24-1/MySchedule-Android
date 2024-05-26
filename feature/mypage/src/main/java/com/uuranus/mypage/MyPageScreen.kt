package com.uuranus.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uuranus.designsystem.component.DeleteDialog
import com.uuranus.designsystem.component.LoadingScreen
import com.uuranus.designsystem.component.MyScheduleAppBar
import com.uuranus.designsystem.component.MyScheduleOutlinedButton
import com.uuranus.designsystem.component.TimePickerDialog
import com.uuranus.designsystem.theme.MyScheduleTheme
import com.uuranus.model.TimeRange
import com.uuranus.myschedule.core.common.mypage.MyInfo
import com.uuranus.myschedule.core.common.mypage.TimeInfoChip
import com.uuranus.myschedule.core.designsystem.R
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MyPageScreen(
    myPageViewModel: MyPageViewModel = hiltViewModel(),
    onShowSnackbar: suspend (Throwable) -> Unit,
) {

    val myPageUiState by myPageViewModel.mypageUiState.collectAsStateWithLifecycle()
    val userData by myPageViewModel.userData.collectAsStateWithLifecycle()

    var showDeleteDialog by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(true) {
        myPageViewModel.errorFlow.collectLatest { throwable ->
            onShowSnackbar(throwable)
        }
    }

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
                                showDeleteDialog = true
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

    if (showDeleteDialog) {
        DeleteDialog(
            title = "회원 탈퇴",
            content = "회원 탈퇴하시겠습니까?\n(탈퇴하면 모든 가게 정보가 삭제됩니다)",
            onDismissDialog = {
                showDeleteDialog = false
            },
            onConfirmDialog = {
                showDeleteDialog = false
                myPageViewModel.quit()
            }
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MyFixedPossibleTime(
    viewModel: MyPageViewModel,
    possibleTimes: List<List<TimeRange>>,
) {

    val weekdays = listOf("월", "화", "수", "목", "금", "토", "일")
    var showTimePicker: Boolean by remember {
        mutableStateOf(false)
    }
    var selectedWeekNum: Int by remember {
        mutableStateOf(-1)
    }

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
        LazyVerticalGrid(modifier = Modifier.fillMaxSize(), columns = GridCells.Fixed(8)) {
            items(weekdays.size * 3, span = { index ->
                val spanCount =
                    if (index % 3 == 0) 1 else if (index % 3 == 1) 7 else 8
                GridItemSpan(spanCount)
            }) { index ->
                val possibles = possibleTimes[index / 3]
                val weekDayNum = index / 3

                if (index % 3 == 0) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(all = 16.dp)
                    ) {
                        Text(
                            weekdays[weekDayNum],
                            style = MyScheduleTheme.typography.semiBold16
                        )
                    }
                } else if (index % 3 == 1) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 14.dp, horizontal = 8.dp),
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            if (possibles.isEmpty()) {
                                Text(
                                    "-- : -- ~ -- : --",
                                    modifier = Modifier.padding(horizontal = 2.dp),
                                    style = MyScheduleTheme.typography.regular16
                                )
                            } else {
                                FlowRow {
                                    possibles.forEach { timeRange ->
                                        TimeInfoChip(
                                            timeRange, onDeleteClicked = { timeId ->
                                                viewModel.deleteFixedPossibleTime(timeId)
                                            }
                                        )
                                    }
                                }
                            }
                        }
                        MyScheduleOutlinedButton(
                            paddingValues = PaddingValues(
                                horizontal = 13.dp, vertical = 5.dp
                            ),
                            buttonState = true,
                            content = {
                                Text(
                                    text = "추가",
                                    style = MyScheduleTheme.typography.regular14
                                )
                            },
                            onClick = {
                                selectedWeekNum = index / 3
                                showTimePicker = true
                            },
                            modifier = Modifier
                                .padding(all = 1.dp)
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .height(1.dp)
                            .background(color = MyScheduleTheme.colors.lightGray)
                    )
                }
            }
        }
    }

    if (showTimePicker) {

        TimePickerDialog(onTimeSelected = { start, end ->

            if (start.isNotEmpty() && end.isNotEmpty()) {
                viewModel.addFixedPossibleTime(selectedWeekNum, start, end)
            }
            showTimePicker = false
        }, onDismissDialog = {
            showTimePicker = false
        })
    }
}
