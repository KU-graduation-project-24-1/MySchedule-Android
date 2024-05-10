package com.uuranus.home

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uuranus.designsystem.component.CircularImageComponent
import com.uuranus.designsystem.component.LoadingScreen
import com.uuranus.designsystem.component.MyScheduleAppBar
import com.uuranus.designsystem.component.MyScheduleDialog
import com.uuranus.designsystem.component.MyScheduleFilledButton
import com.uuranus.designsystem.component.toAnnotateString
import com.uuranus.designsystem.theme.MyScheduleTheme
import com.uuranus.home.calendar.DateInfo
import com.uuranus.home.calendar.ScheduleCalendar
import com.uuranus.home.calendar.ScheduleData
import com.uuranus.home.calendar.ScheduleInfo
import com.uuranus.home.calendar.getLanguageYMDDate
import com.uuranus.model.MyScheduleInfo
import com.uuranus.navigation.MyScheduleScreens
import com.uuranus.navigation.currentComposeNavigator
import java.util.Date

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
    animatedVisibilityScope: AnimatedVisibilityScope,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {

    val composeNavigator = currentComposeNavigator

    val homeUiState by homeViewModel.homeUiState.collectAsStateWithLifecycle()

    val memberIdColorMap = mutableMapOf<Int, Color>()

    val currentColorIndex = remember {
        mutableIntStateOf(0)
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
                HomeUiState.Loading -> LoadingScreen()
                is HomeUiState.Success ->

                    HomeContent(
                        homeViewModel = homeViewModel,
                        schedules = (homeUiState as HomeUiState.Success).schedules.mapValues { (_, scheduleInfo) ->
                            scheduleInfo.copy(schedules = scheduleInfo.schedules.map { scheduleData ->
                                if (memberIdColorMap.containsKey(scheduleData.detail.memberId)
                                        .not()
                                ) {
                                    memberIdColorMap.put(
                                        scheduleData.detail.memberId,
                                        calendarColors[currentColorIndex.value]
                                    )
                                    currentColorIndex.value += 1
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
fun HomeContent(
    homeViewModel: HomeViewModel,
    schedules: Map<DateInfo, ScheduleInfo<MyScheduleInfo>>,
) {
    val sheetState = rememberModalBottomSheetState()

    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedBottomSheetItem by remember {

        mutableStateOf(
            Pair<DateInfo, List<ScheduleData<MyScheduleInfo>>>(
                DateInfo(0, 0, 0),
                emptyList()
            )
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
            schedules,
            onDayClick = { dateInfo, schedules: List<ScheduleData<MyScheduleInfo>> ->
                selectedBottomSheetItem = Pair(dateInfo, schedules)
                showBottomSheet = true

            },
            onPageChanged = {
                homeViewModel.getMonthlySchedules(it)
            }
        )

        if (showBottomSheet) {
            MyScheduleBottomSheet(
                sheetState = sheetState,
                content = {
                    BottomSheetContent(
                        dateInfo = selectedBottomSheetItem.first,
                        scheduleInfo = selectedBottomSheetItem.second,
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
                RequestFillInDialog(
                    dateInfo = selectedScheduleItem.first,
                    scheduleInfo = selectedScheduleItem.second
                ) {
                    showDialog = false
                }
            } else if (selectedScheduleItem.second.detail.isFillInNeeded) {
                AcceptFillInDialog(
                    dateInfo = selectedScheduleItem.first,
                    scheduleInfo = selectedScheduleItem.second
                ) {
                    showDialog = false
                }
            }
        }

    }

}

@Composable
fun BottomSheetContent(
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
            MyScheduleDetailListItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                scheduleInfo[index]
            ) {
                //내가 사장일 때

                //아닐 때
                if (scheduleInfo[index].detail.isFillInNeeded || scheduleInfo[index].detail.isMine) {
                    Row {
                        MyScheduleFilledButton(
                            modifier = Modifier,
                            paddingValues = PaddingValues(
                                horizontal = 14.dp,
                                vertical = 5.dp
                            ), buttonState = true,
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
fun MyScheduleDetailListItem(
    modifier: Modifier,
    scheduleInfo: ScheduleData<MyScheduleInfo>,
    actions: @Composable (RowScope.() -> Unit) = {},
) {

    Row(
        modifier = modifier
            .padding(bottom = 12.dp)
            .wrapContentHeight()
            .drawBehind {
                drawLine(
                    color = scheduleInfo.color,
                    start = Offset(x = 0f, y = 0F),
                    end = Offset(
                        x = 0f,
                        y = this.size.height
                    ),
                    strokeWidth = 5.dp.toPx()
                )

            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier
                .width(10.dp)
        )
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .weight(1F)
        ) {

            if (scheduleInfo.detail.isFillInNeeded) {
                Text(
                    ("${scheduleInfo.detail.startTime} ~ ${scheduleInfo.detail.endTime} ").toAnnotateString(
                        "(대타요청중)", MyScheduleTheme.colors.errorColor
                    ),
                    style = MyScheduleTheme.typography.regular16
                )
            } else {
                Text(
                    "${scheduleInfo.detail.startTime} ~ ${scheduleInfo.detail.endTime}",
                    style = MyScheduleTheme.typography.regular16
                )
            }
            Text(
                if (scheduleInfo.detail.isMine) {
                    "${scheduleInfo.detail.workerName} (${scheduleInfo.detail.workerType}, 나)"
                } else {
                    "${scheduleInfo.detail.workerName} (${scheduleInfo.detail.workerType})"
                },
                style = MyScheduleTheme.typography.regular16
            )
        }
        Spacer(
            modifier = Modifier
                .width(10.dp)
        )
        actions()
    }
}


@Composable
fun RequestFillInDialog(
    dateInfo: DateInfo,
    scheduleInfo: ScheduleData<MyScheduleInfo>,
    onDismissDialog: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismissDialog,
        containerColor = MyScheduleTheme.colors.background,
        title = {
            Text(
                "대타 요청",
                style = MyScheduleTheme.typography.bold20
            )
        },
        text = {
            Column() {
                Text(
                    getLanguageYMDDate(dateInfo = dateInfo),
                    style = MyScheduleTheme.typography.regular16
                )
                Spacer(modifier = Modifier.height(18.dp))
                MyScheduleDetailListItem(
                    modifier = Modifier,
                    scheduleInfo = scheduleInfo
                )
            }
        },
        confirmButton = {
            MyScheduleFilledButton(
                modifier = Modifier
                    .fillMaxWidth(),
                paddingValues = PaddingValues(all = 13.dp),
                buttonState = true,
                content = {
                    Text(
                        "요청",
                        style = MyScheduleTheme.typography.semiBold16
                    )
                }
            ) {
                //viewModel.request()
            }
        }
    )
}

@Composable
fun AcceptFillInDialog(
    dateInfo: DateInfo,
    scheduleInfo: ScheduleData<MyScheduleInfo>,
    onDismissDialog: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismissDialog,
        containerColor = MyScheduleTheme.colors.background,
        title = {
            Text(
                "대타 수락",
                style = MyScheduleTheme.typography.bold20
            )
        },
        text = {
            Column() {
                Text(
                    getLanguageYMDDate(dateInfo = dateInfo),
                    style = MyScheduleTheme.typography.regular16
                )
                Spacer(modifier = Modifier.height(18.dp))
                MyScheduleDetailListItem(
                    modifier = Modifier,
                    scheduleInfo = scheduleInfo
                )
            }

        },
        confirmButton = {
            MyScheduleFilledButton(
                modifier = Modifier.fillMaxWidth(),
                paddingValues = PaddingValues(all = 13.dp),
                buttonState = true,
                content = {
                    Text(
                        "수락",
                        style = MyScheduleTheme.typography.semiBold16,
                    )
                }
            ) {
                //viewModel.accept()
            }
        },
    )
}


@Preview
@ExperimentalAnimationApi
@Composable
fun HomeScreenPreview() {

    MyScheduleTheme {
//        MyScheduleDetailListItem(
//            modifier = Modifier
//                .fillMaxWidth(),
//            ScheduleData(
//                "AAA 10:00",
//                MyScheduleTheme.colors.calendarBlue,
//                detail = MyScheduleInfo(
//                    0,
//                    "10:00",
//                    "12:00",
//                    "AAA",
//                    "매니저",
//                    false,
//                    MyScheduleTheme.colors.calendarBlue,
//                    true
//                )
//            ),
//        ) {
//            //내가 사장일 때
//
//            //아닐 때
//            MyScheduleFilledButton(
//                paddingValues = PaddingValues(horizontal = 14.dp, vertical = 5.dp),
//                buttonState = true
//            ) {
//                Text("수락", style = MyScheduleTheme.typography.regular14)
//            }
//        }


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