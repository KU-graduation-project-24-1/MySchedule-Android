package com.uuranus.myschedule.bossmypage

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uuranus.designsystem.component.DeleteDialog
import com.uuranus.designsystem.component.LoadingScreen
import com.uuranus.designsystem.component.MyScheduleAppBar
import com.uuranus.designsystem.component.MyScheduleFilledButton
import com.uuranus.designsystem.component.MyScheduleOutlinedButton
import com.uuranus.designsystem.component.TimePickerDialog
import com.uuranus.designsystem.theme.MyScheduleTheme
import com.uuranus.model.StoreSalesInformation
import com.uuranus.model.TimeRange
import com.uuranus.myschedule.core.common.mypage.MyInfo
import com.uuranus.myschedule.core.designsystem.R
import com.uuranus.navigation.LocalLoginIntent
import com.uuranus.navigation.MyScheduleScreens
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.net.UnknownHostException

@Composable
fun BossMyPageScreen(
    bossMyPageViewModel: BossMyPageViewModel = hiltViewModel(),
) {

    val bossMyPageUiState by bossMyPageViewModel.bossMypageUiState.collectAsStateWithLifecycle()
    val userData by bossMyPageViewModel.userData.collectAsStateWithLifecycle()

    var showQuitDialog: Boolean by remember {
        mutableStateOf(false)
    }

    val coroutineScope = rememberCoroutineScope()

    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(true) {
        bossMyPageViewModel.errorFlow.collectLatest { throwable ->
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
                                showQuitDialog = true
                            })
                })

            when (bossMyPageUiState) {
                is BossMyPageUiState.Loading -> LoadingScreen()
                is BossMyPageUiState.Success -> Column {
                    val state = bossMyPageUiState as BossMyPageUiState.Success
                    MyInfo(userData)
                    Spacer(modifier = Modifier.height(58.dp))
                    StoreSalesInfo(
                        viewModel = bossMyPageViewModel,
                        salesInfo = state.salesInformation
                    )
                }
            }

        }

    }

    val context = LocalContext.current
    val intent = LocalLoginIntent.current

    if (showQuitDialog) {
        DeleteDialog(
            title = "회원 탈퇴",
            content = "탈퇴하시겠습니까?\n(지금까지의 모든 가게 정보가 사라집니다)",
            onDismissDialog = {
                showQuitDialog = false
            },
            onConfirmDialog = {
                bossMyPageViewModel.quit()
                showQuitDialog = false
                context.startActivity(intent)
            }
        )
    }

}

val screenPadding = 16.dp

@Composable
fun StoreSalesInfo(
    viewModel: BossMyPageViewModel,
    salesInfo: List<StoreSalesInformation>,
) {

    val weekdays = listOf("월", "화", "수", "목", "금", "토", "일")

    val showWorkerNumDialog: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }
    var showStoreDeleteDialog: Boolean by remember {
        mutableStateOf(false)
    }

    val showTimePicker: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }

    val selectedWeekNum: MutableState<Int> = remember {
        mutableIntStateOf(0)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = screenPadding, vertical = 14.dp)
            ) {
                Text(
                    "영업 정보", style = MyScheduleTheme.typography.semiBold16,
                )
            }
            HorizontalDivider(thickness = 1.dp, color = MyScheduleTheme.colors.lightGray)
            LazyVerticalGrid(modifier = Modifier.fillMaxSize(), columns = GridCells.Fixed(8)) {
                item(span = { GridItemSpan(1) }) {
                    Box(
                        modifier = Modifier
                    )
                }
                item(span = { GridItemSpan(2) }) {
                    Box(
                        modifier = Modifier
                            .padding(2.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "근무 인원", style = MyScheduleTheme.typography.semiBold16)
                    }
                }
                item(span = { GridItemSpan(5) }) {
                    Box(
                        modifier = Modifier
                            .padding(2.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "영업 시간", style = MyScheduleTheme.typography.semiBold16)
                    }
                }
                item(span = { GridItemSpan(8) }) {
                    Box(
                        modifier = Modifier
                            .height(1.dp)
                            .background(color = MyScheduleTheme.colors.lightGray)
                    )
                }

                items(salesInfo.size * 4, span = { index ->
                    val spanCount =
                        if (index % 4 == 0) 1 else if (index % 4 == 1) 2 else if (index % 4 == 2) 5 else 8
                    GridItemSpan(spanCount)
                }) { index ->

                    val scheduleInfo = salesInfo[index / 4]
                    if (index % 4 == 0) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .height(IntrinsicSize.Max)
                                .padding(all = screenPadding)
                        ) {
                            Text(
                                weekdays[index / 4],
                                style = MyScheduleTheme.typography.semiBold16
                            )
                        }
                    } else if (index % 4 == 1) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .padding(all = screenPadding)
                        ) {
                            Text(
                                "${scheduleInfo.workerNum}명",
                                style = MyScheduleTheme.typography.regular16,
                                modifier = Modifier.clickable {
                                    showWorkerNumDialog.value = true
                                    selectedWeekNum.value = index / 4
                                }
                            )
                        }
                    } else if (index % 4 == 2) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(screenPadding)
                            ) {
                                if (scheduleInfo.salesTimeRange.isEmpty()) {
                                    Text(
                                        "-- : -- ~ -- : --",
                                        style = MyScheduleTheme.typography.regular16
                                    )
                                } else {
                                    Text(
                                        scheduleInfo.salesTimeRange.joinToString(", ") {
                                            "${it.startTime}~${it.endTime}"
                                        }, style = MyScheduleTheme.typography.regular16
                                    )
                                }
                            }

                            Image(
                                painter = painterResource(id = R.drawable.edit_icon),
                                contentDescription = "영업 시간 추가하기",
                                modifier = Modifier.clickable {
                                    selectedWeekNum.value = index / 3
                                    showTimePicker.value = true
                                }
                            )
                            Spacer(modifier = Modifier.width(screenPadding))

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

        MyScheduleOutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(screenPadding)
                .align(Alignment.BottomCenter),
            paddingValues = PaddingValues(all = 13.dp),
            buttonState = true,
            content = {
                Text("가게 식제", style = MyScheduleTheme.typography.semiBold16)
            }
        ) {
            showStoreDeleteDialog = true
        }
    }


    if (showWorkerNumDialog.value) {
        AlertDialog(onDismissRequest = {
            showWorkerNumDialog.value = false
        }, confirmButton = {
            MyScheduleFilledButton(
                modifier = Modifier.fillMaxWidth(),
                paddingValues = PaddingValues(13.dp),
                buttonState = true,
                color = MyScheduleTheme.colors.primary,
                content = {
                    Text(
                        "확인",
                        style = MyScheduleTheme.typography.semiBold16,
                        color = MyScheduleTheme.colors.textColor
                    )
                },
                onClick = {

                    showWorkerNumDialog.value = false
                }
            )
        }, title = {
            Text("근무 인원", style = MyScheduleTheme.typography.semiBold16)
        }, text = {
            OutlinedTextField(
                value = "",
                onValueChange = {
                },
                colors = TextFieldDefaults.colors().copy(
                    focusedIndicatorColor = MyScheduleTheme.colors.primary,

                    unfocusedIndicatorColor = MyScheduleTheme.colors.gray,
                    focusedContainerColor = MyScheduleTheme.colors.background,
                    unfocusedContainerColor = MyScheduleTheme.colors.background,
                ),
                textStyle = MyScheduleTheme.typography.regular16,
                placeholder = {
                    Text(text = "0(명)")
                }
            )
        }, containerColor = MyScheduleTheme.colors.background)
    }

    val context = LocalContext.current
    val intent = LocalLoginIntent.current

    if (showStoreDeleteDialog) {
        DeleteDialog(
            title = "가게 삭제",
            content = "해당 가게를 삭제하시겠습니까?",
            onDismissDialog = {
                showStoreDeleteDialog = false
            },
            onConfirmDialog = {
                viewModel.deleteStore()
                showStoreDeleteDialog = false
                context.startActivity(intent)
            }
        )
    }

    if (showTimePicker.value) {

        TimePickerDialog(onTimeSelected = { start, end ->
            viewModel.addOpeningHourTime(selectedWeekNum.value, start, end)
            showTimePicker.value = false
        }, onDismissDialog = {
            showTimePicker.value = false
        })
    }
}
