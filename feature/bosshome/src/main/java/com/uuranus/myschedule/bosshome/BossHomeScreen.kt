package com.uuranus.myschedule.bosshome

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uuranus.designsystem.calendar.DateInfo
import com.uuranus.designsystem.calendar.ScheduleCalendar
import com.uuranus.designsystem.calendar.ScheduleData
import com.uuranus.designsystem.calendar.ScheduleInfo
import com.uuranus.designsystem.calendar.getDashYMDDate
import com.uuranus.designsystem.component.CircularImageComponent
import com.uuranus.designsystem.component.LoadingScreen
import com.uuranus.designsystem.component.MyScheduleAppBar
import com.uuranus.designsystem.theme.MyScheduleTheme
import com.uuranus.myschedule.core.common.home.AcceptFillInDialog
import com.uuranus.myschedule.core.common.home.RequestFillInDialog
import com.uuranus.model.MyScheduleInfo
import com.uuranus.myschedule.core.common.home.MyScheduleBottomSheet
import com.uuranus.myschedule.core.designsystem.R
import com.uuranus.navigation.MyScheduleScreens
import com.uuranus.navigation.currentComposeNavigator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.net.UnknownHostException

internal val calendarColors = listOf(
    Color(0xFFF3A8A8),
    Color(0xFFA8C6E3),
    Color(0xFFFBCFAE),
    Color(0xFFF9EB9E),
    Color(0xFFC8C7F6),
    Color(0xFF9FE9B4)
)

@Composable
fun BossHomeScreen(
    bossHomeViewModel: BossHomeViewModel = hiltViewModel(),
) {

    val composeNavigator = currentComposeNavigator

    val bossHomeUiState by bossHomeViewModel.bossHomeUiState.collectAsStateWithLifecycle()

    val memberIdColorMap = remember {
        mutableMapOf<Int, Color>()
    }

    val currentColorIndex = remember {
        mutableIntStateOf(0)
    }

    val coroutineScope = rememberCoroutineScope()

    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(true) {
        bossHomeViewModel.errorFlow.collectLatest { throwable ->
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
        Column(modifier = Modifier.fillMaxSize()) {
            MyScheduleAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            modifier = Modifier.clickable {

                            },
                            contentDescription = "가게 목록으로 이동",
                            painter = painterResource(
                                id = R.drawable.arrow_left_icon
                            )
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "000 떡볶이 건대입구점",
                            style = MyScheduleTheme.typography.bold16
                        )
                    }
                },
                actions = {
                    Image(
                        painter = painterResource(id = R.drawable.person_outline_icon),
                        contentDescription = "직원 관리",
                        modifier = Modifier.clickable {
                            composeNavigator.navigate(MyScheduleScreens.BossWorkerManage.route)
                        },
                    )
                    Spacer(modifier = Modifier.width(18.dp))
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        CircularImageComponent(
                            painter = painterResource(id = R.drawable.baseline_person_24),
                            size = 30,
                            onClick = {
                                composeNavigator.navigate(MyScheduleScreens.BossMyPage.route)
                            }
                        )
                    }
                },
            )

            when (bossHomeUiState) {
                BossHomeUiState.Loading -> LoadingScreen()
                is BossHomeUiState.Success ->

                    BossHomeContent(
                        viewModel = bossHomeViewModel,
                        schedules = (bossHomeUiState as BossHomeUiState.Success).schedules.mapValues { (_, scheduleInfo) ->
                            scheduleInfo.copy(schedules = scheduleInfo.schedules.map { scheduleData ->
                                if (memberIdColorMap.containsKey(scheduleData.detail.memberId)
                                        .not()
                                ) {
                                    memberIdColorMap[scheduleData.detail.memberId] =
                                        calendarColors[currentColorIndex.intValue]
                                    currentColorIndex.intValue += 1
                                }

                                scheduleData.copy(
                                    color = memberIdColorMap[scheduleData.detail.memberId]
                                        ?: MyScheduleTheme.colors.primary
                                )
                            }
                            )
                        },
                    )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BossHomeContent(
    viewModel: BossHomeViewModel,
    schedules: Map<DateInfo, ScheduleInfo<MyScheduleInfo>>,
) {

    val composeNavigator = currentComposeNavigator

    val sheetState = rememberModalBottomSheetState()

    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedBottomSheetItem by remember {

        mutableStateOf(
            DateInfo(0, 0, 0)
        )
    }

    var showDialog by remember { mutableStateOf(false) }

    var selectedScheduleItem by remember {

        mutableStateOf(
            Pair(
                DateInfo(0, 0, 0),
                ScheduleData(
                    title = "AA 10:00",
                    color = Color.White,
                    MyScheduleInfo(
                        0,
                        "10:00",
                        "12:00",
                        3,
                        "AAA",
                        "매니저",
                        isMine = false,
                        isFillInNeeded = true
                    )
                )
            )

        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        ScheduleCalendar(
            modifier = Modifier.fillMaxSize(),
            currentDate = viewModel.getCurrentDate(),
            schedules,
            onDayClick = { dateInfo ->
                selectedBottomSheetItem = dateInfo
                showBottomSheet = true

            },
            onPageChanged = {
                viewModel.setCurrentDate(dateInfo = it)
                viewModel.getMonthlySchedules()
            }
        )

        if (showBottomSheet) {
            MyScheduleBottomSheet(
                sheetState = sheetState,
                content = {
                    MyScheduleBottomSheetContentForBoss(
                        dateInfo = selectedBottomSheetItem,
                        scheduleInfo = schedules[selectedBottomSheetItem]?.schedules ?: emptyList(),
                        onClick = { dateInfo, scheduleData ->
                            showDialog = true
                            selectedScheduleItem = Pair(dateInfo, scheduleData)
                        },
                        onEditClick = { dateInfo, scheduleData ->
                            showBottomSheet = false
                            composeNavigator.navigate(
                                MyScheduleScreens.BossEditSchedule.createRoute(
                                    storeId = viewModel.getUserData().storeId,
                                    dateDashString = getDashYMDDate(dateInfo),
                                    scheduleInfo = scheduleData.detail
                                ),
                            )
                        },
                        onAddClick = { dateInfo ->
                            showBottomSheet = false
                            composeNavigator.navigate(
                                MyScheduleScreens.BossAddSchedule.createRoute(
                                    storeId = viewModel.getUserData().storeId,
                                    dateDashString = getDashYMDDate(dateInfo),
                                    scheduleInfo = MyScheduleInfo(
                                        scheduleId = -1,
                                        startTime = "00:00",
                                        endTime = "00:00",
                                        memberId = viewModel.getUserData().memberId,
                                        workerName = "",
                                        workerType = "",
                                        isMine = false,
                                        isFillInNeeded = false
                                    )
                                )
                            )
                        }
                    )
                },
                onDismissRequest = {
                    showBottomSheet = false
                }

            )
        }

        if (showDialog) {
            if (selectedScheduleItem.second.detail.isMine) {
                RequestFillInDialog(
                    dateInfo = selectedScheduleItem.first,
                    scheduleInfo = selectedScheduleItem.second,
                    onDismissDialog = {
                        showDialog = false
                    },
                    onConfirmClick = {
                        viewModel.requestFillIn(selectedScheduleItem.second.detail.scheduleId)
                        showDialog = false
                    }
                )
            } else if (selectedScheduleItem.second.detail.isFillInNeeded) {
                AcceptFillInDialog(
                    dateInfo = selectedScheduleItem.first,
                    scheduleInfo = selectedScheduleItem.second,
                    onDismissDialog = {
                        showDialog = false
                    },
                    onConfirmClick = {
                        viewModel.acceptFillIn(selectedScheduleItem.second.detail.scheduleId)
                        showDialog = false
                    }
                )
            }
        }

    }

}

