package com.uuranus.home

import androidx.compose.animation.ExperimentalAnimationApi
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uuranus.designsystem.calendar.DateInfo
import com.uuranus.designsystem.calendar.ScheduleCalendar
import com.uuranus.designsystem.calendar.ScheduleData
import com.uuranus.designsystem.calendar.ScheduleInfo
import com.uuranus.designsystem.component.CircularImageComponent
import com.uuranus.designsystem.component.LoadingScreen
import com.uuranus.designsystem.component.MyScheduleAppBar
import com.uuranus.designsystem.theme.MyScheduleTheme
import com.uuranus.model.MyPossibleTimeInfo
import com.uuranus.model.MyScheduleInfo
import com.uuranus.myschedule.core.common.home.MyScheduleBottomSheet
import com.uuranus.navigation.LocalLoginIntent
import com.uuranus.navigation.MyScheduleScreens
import com.uuranus.navigation.currentComposeNavigator
import kotlinx.coroutines.flow.collectLatest

internal val calendarColors = listOf(
    Color(0xFFF3A8A8),
    Color(0xFFA8C6E3),
    Color(0xFFFBCFAE),
    Color(0xFFF9EB9E),
    Color(0xFFC8C7F6),
    Color(0xFF9FE9B4)
)

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    onShowSnackbar: suspend (Throwable) -> Unit,
) {

    val composeNavigator = currentComposeNavigator

    val homeUiState by homeViewModel.homeUiState.collectAsStateWithLifecycle()

    val memberIdColorMap = remember {
        mutableMapOf<Int, Color>()
    }

    val currentColorIndex = remember {
        mutableIntStateOf(0)
    }

    var showCalendarChooseDialog by remember { mutableStateOf(false) }
    var selectedScheduleItem by remember {
        mutableStateOf(0)
    }

    LaunchedEffect(true) {
        homeViewModel.errorFlow.collectLatest { throwable ->
            onShowSnackbar(throwable)
        }
    }

    val context = LocalContext.current
    val intent = LocalLoginIntent.current

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
                                context.startActivity(intent)
                            },
                            contentDescription = "가게 목록으로 이동",
                            painter = painterResource(
                                id = com.uuranus.myschedule.core.designsystem.R.drawable.arrow_left_icon
                            )
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = homeViewModel.getUserData().storeName,
                            style = MyScheduleTheme.typography.bold16
                        )
                    }
                },
                actions = {
                    Image(
                        painter = painterResource(id = com.uuranus.myschedule.core.designsystem.R.drawable.calendar_icon),
                        contentDescription = "캘린더 종류 보기",
                        modifier = Modifier.clickable {
                            showCalendarChooseDialog = true
                        }
                    )
                    Spacer(modifier = Modifier.width(18.dp))
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        CircularImageComponent(
                            painter = painterResource(id = com.uuranus.myschedule.core.designsystem.R.drawable.baseline_person_24),
                            size = 30,
                            onClick = {
                                composeNavigator.navigate(MyScheduleScreens.MyPage.route)
                            }
                        )
                    }
                },
            )

            when (homeUiState) {
                is HomeUiState.Loading -> LoadingScreen()
                is HomeUiState.ScheduleSuccess ->
                    ScheduleHomeContent(
                        homeViewModel = homeViewModel,
                        schedules = (homeUiState as HomeUiState.ScheduleSuccess).schedules.mapValues { (_, scheduleInfo) ->
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
                            })
                        },
                    )

                is HomeUiState.PossibleTimeSuccess ->
                    PossibleTimeHomeContent(
                        homeViewModel = homeViewModel,
                        possibleTimes = (homeUiState as HomeUiState.PossibleTimeSuccess).schedules.mapValues { (_, scheduleInfo) ->
                            scheduleInfo.copy(schedules = scheduleInfo.schedules.map { scheduleData ->
                                scheduleData.copy(
                                    color = MyScheduleTheme.colors.primary
                                )
                            })
                        },
                    )
            }
        }
    }


    if (showCalendarChooseDialog) {
        CalendarChooseDialog(currentItem = selectedScheduleItem) {
            selectedScheduleItem = it
            if (selectedScheduleItem == 0) {
                homeViewModel.getMonthlySchedules()
            } else {
                homeViewModel.getMonthlyPossibleTimes()
            }
            showCalendarChooseDialog = false
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleHomeContent(
    homeViewModel: HomeViewModel,
    schedules: Map<DateInfo, ScheduleInfo<MyScheduleInfo>>,
) {
    val sheetState = rememberModalBottomSheetState()

    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedBottomSheetItem by remember {

        mutableStateOf(DateInfo(0, 0, 0))
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
                        false,
                        true
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
            currentDate = homeViewModel.getCurrentDate(),
            schedules = schedules,
            onDayClick = { dateInfo ->
                selectedBottomSheetItem = dateInfo
                showBottomSheet = true

            },
            onPageChanged = {
                homeViewModel.setCurrentDate(it)
                homeViewModel.getMonthlySchedules()
            }
        )

        if (showBottomSheet) {
            MyScheduleBottomSheet(
                sheetState = sheetState,
                content = {
                    MyScheduleBottomSheetContent(
                        dateInfo = selectedBottomSheetItem,
                        scheduleInfo = schedules[selectedBottomSheetItem]?.schedules ?: emptyList(),
                        onClick = { dateInfo, scheduleData ->
                            showDialog = true
                            selectedScheduleItem = Pair(dateInfo, scheduleData)
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
                com.uuranus.myschedule.core.common.home.RequestFillInDialog(
                    dateInfo = selectedScheduleItem.first,
                    scheduleInfo = selectedScheduleItem.second,
                    onDismissDialog = {
                        showDialog = false
                    },
                    onConfirmClick = {
                        homeViewModel.requestFillIn(selectedScheduleItem.second.detail.scheduleId)
                        showDialog = false
                    }
                )
            } else if (selectedScheduleItem.second.detail.isFillInNeeded) {
                com.uuranus.myschedule.core.common.home.AcceptFillInDialog(
                    dateInfo = selectedScheduleItem.first,
                    scheduleInfo = selectedScheduleItem.second,
                    onDismissDialog = {
                        showDialog = false
                    },
                    onConfirmClick = {
                        homeViewModel.acceptFillIn(selectedScheduleItem.second.detail.scheduleId)
                        showDialog = false
                    }
                )
            }
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PossibleTimeHomeContent(
    homeViewModel: HomeViewModel,
    possibleTimes: Map<DateInfo, ScheduleInfo<MyPossibleTimeInfo>>,
) {
    val sheetState = rememberModalBottomSheetState()

    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedBottomSheetItem by remember {

        mutableStateOf(
            DateInfo(0, 0, 0)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        ScheduleCalendar(
            modifier = Modifier.fillMaxSize(),
            currentDate = homeViewModel.getCurrentDate(),
            schedules = possibleTimes,
            onDayClick = { dateInfo ->
                selectedBottomSheetItem = dateInfo
                showBottomSheet = true

            },
            onPageChanged = {
                homeViewModel.setCurrentDate(dateInfo = it)
                homeViewModel.getMonthlyPossibleTimes()
            }
        )

        if (showBottomSheet) {
            MyScheduleBottomSheet(
                sheetState = sheetState,
                content = {
                    PossibleTimeBottomSheetContent(
                        homeViewModel = homeViewModel,
                        dateInfo = selectedBottomSheetItem,
                        scheduleInfo = possibleTimes[selectedBottomSheetItem]?.schedules
                            ?: emptyList()
                    )
                },
                onDismissRequest = {
                    showBottomSheet = false
                }

            )
        }
    }
}

@Preview
@ExperimentalAnimationApi
@Composable
fun HomeScreenPreview() {

    MyScheduleTheme {

        MyScheduleAppBar(
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier.clickable {

                        },
                        contentDescription = "가게 목록으로 이동",
                        painter = painterResource(
                            id = com.uuranus.myschedule.core.designsystem.R.drawable.arrow_left_icon
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
                    painter = painterResource(id = com.uuranus.myschedule.core.designsystem.R.drawable.calendar_icon),
                    contentDescription = "캘린더 종류 보기",
                    modifier = Modifier.clickable {
                        //캘린더 보기 다이얼로그
                    }
                )
                Spacer(modifier = Modifier.width(18.dp))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    CircularImageComponent(
                        painter = painterResource(id = com.uuranus.myschedule.core.designsystem.R.drawable.baseline_person_24),
                        size = 30,
                        onClick = {
//                            composeNavigator.navigate(MyScheduleScreens.MyPage.route)
                        }
                    )
                }
            },
        )
    }
}