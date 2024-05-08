package com.uuranus.home.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uuranus.designsystem.theme.MyScheduleTheme
import com.uuranus.model.MyScheduleInfo
import java.util.Calendar


internal val today = DateInfo.create(Calendar.getInstance())

@Composable
fun <T> ScheduleCalendar(
    modifier: Modifier,
    schedules: Map<DateInfo, ScheduleInfo<T>>, //DateInfo날의 스케줄 정보
    onDayClick: (DateInfo, List<ScheduleData<T>>) -> Unit,
) {

    var currentDate by remember { mutableStateOf(DateInfo.create(Calendar.getInstance())) }

    val monthInfo = rememberMonthInfo(currentDate)

    val pagerState = rememberPagerState(pageCount = { 7 }, initialPage = 1)

    LaunchedEffect(pagerState.currentPage) {
        val direction = pagerState.currentPageOffsetFraction
        if (direction < 0) { //왼쪽으로 밀면 오른쪽으로 이동
            currentDate = currentDate.addMonth(1)
            if (pagerState.currentPage == pagerState.pageCount -1){
                pagerState.animateScrollToPage(1)
            }

        } else if (direction > 0) {
            currentDate = currentDate.addMonth(-1)
            if (pagerState.currentPage == 0){
                pagerState.animateScrollToPage(pagerState.pageCount - 2)
            }
        }
  }

    HorizontalPager(
        state = pagerState,
        modifier = modifier
            .fillMaxSize(),
    ) { _ ->

        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            ScheduleCalendarHeader(currentDate)
            ScheduleCalendarMonth(currentDate, monthInfo, schedules, onDayClick)
        }
    }

}

@Composable
fun ScheduleCalendarHeader(
    currentDate: DateInfo,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = getLanguageYMDate(currentDate),
            style = MyScheduleTheme.typography.semiBold20
        )
    }
}

@Composable
fun <T> ScheduleCalendarMonth(
    currentDate: DateInfo,
    monthInfo: MonthInfo,
    schedules: Map<DateInfo, ScheduleInfo<T>>,
    onDayClick: (DateInfo, List<ScheduleData<T>>) -> Unit,
) {
    LazyVerticalGrid(columns = GridCells.Fixed(7)) {
        items(7) {
            ScheduleCalendarWeekDay(it)
        }
        items(monthInfo.firstDayOfWeek) {
            EmptyScheduleCalendarDate(modifier = Modifier)
        }
        items(monthInfo.numberOfDays) { dayIndex ->
            val date = currentDate.setDate(dayIndex + 1)
            val currentDateSchedules = schedules[date] ?: ScheduleInfo(false, emptyList())
            ScheduleCalendarDate(
                modifier = Modifier,
                date = date,
                isCheckNeeded = currentDateSchedules.isCheckNeeded,
                isToday = date == today && today.date == dayIndex + 1,
                scheduleInfo = currentDateSchedules,
                onDayClick = onDayClick
            )
        }
    }
}

@Composable
fun ScheduleCalendarWeekDay(weekNum: Int) {
    val week = listOf("월", "화", "수", "목", "금", "토", "일")
    val color =
        MyScheduleTheme.colors.gray

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .drawBehind {

                val strokeWidth = 0.7f * density
                val y = size.height - strokeWidth / 2

                drawLine(
                    color,
                    Offset(0f, y),
                    Offset(size.width, y),
                    strokeWidth
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            week[weekNum],
            style = MyScheduleTheme.typography.regular14
        )
    }
}

@Composable
fun <T> ScheduleCalendarDate(
    modifier: Modifier,
    date: DateInfo,
    isCheckNeeded: Boolean,
    isToday: Boolean,
    scheduleInfo: ScheduleInfo<T>,
    onDayClick: (DateInfo, List<ScheduleData<T>>) -> Unit,
) {
    val borderColor =
        MyScheduleTheme.colors.gray
    Column(
        modifier = modifier
            .height(100.dp)
            .drawBehind {

                val strokeWidth = 0.7f * density
                val y = size.height - strokeWidth / 2

                drawLine(
                    borderColor,
                    Offset(0f, y),
                    Offset(size.width, y),
                    strokeWidth
                )
            }
            .clickable {
                onDayClick(date, scheduleInfo.schedules)
            }
            .padding(3.dp),
    ) {
        DateHeader(date, isCheckNeeded, isToday)
        DateContent(Modifier.fillMaxHeight(), scheduleInfo.schedules)
    }
}

@Composable
fun EmptyScheduleCalendarDate(
    modifier: Modifier,
) {
    val color =
        MyScheduleTheme.colors.gray
    Column(
        modifier = modifier
            .height(100.dp)
            .drawBehind {

                val strokeWidth = 0.7f * density
                val y = size.height - strokeWidth / 2

                drawLine(
                    color,
                    Offset(0f, y),
                    Offset(size.width, y),
                    strokeWidth
                )
            }
            .padding(3.dp),
    ) {
    }
}


@Composable
fun DateHeader(
    date: DateInfo,
    isCheckNeeded: Boolean,
    isToday: Boolean,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        if (isCheckNeeded) {
            Box(
                modifier = Modifier
                    .background(color = MyScheduleTheme.colors.errorColor, shape = CircleShape)
                    .size(8.dp)
                    .align(Alignment.TopEnd)
            )
        }

        if (isToday) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .background(
                        color = MyScheduleTheme.colors.primary,
                        shape = CircleShape
                    )
                    .align(Alignment.TopCenter),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = date.date.toString(),
                    style = MyScheduleTheme.typography.regular14,
                    color = MyScheduleTheme.colors.white
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.TopCenter),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = date.date.toString(),
                    style = MyScheduleTheme.typography.regular14,
                )
            }
        }
    }
}

@Composable
fun <T> DateContent(
    modifier: Modifier,
    schedules: List<ScheduleData<T>>,
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(schedules.size) { index ->
            if (index < 4) {
                ScheduleCalendarListItem(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 1.dp),
                    data = schedules[index]
                )
            } else if (index == 4) {
                ScheduleCalendarMoreListItem(Modifier.fillMaxWidth(), 10 - index)
            }
        }
    }
}


@Preview
@Composable
fun PreviewCalendar() {
    val dummyDate = HashMap<DateInfo, ScheduleInfo<MyScheduleInfo>>().apply {
        put(
            DateInfo.create(2024, 5, 5),
            ScheduleInfo(
                false,
                listOf(
                    ScheduleData(
                        "AAA 10:00",
                        MyScheduleTheme.colors.calendarBlue,
                        detail = MyScheduleInfo(
                            0,
                            "10:00",
                            "12:00",
                            3,
                            "AAA",
                            "매니저",
                            false,
                            true
                        )
                    ),
                    ScheduleData(
                        "BBB 12:00",
                        MyScheduleTheme.colors.calendarOrange,
                        MyScheduleInfo(
                            1,
                            "12:00",
                            "15:00",
                            2,
                            "BBB",
                            "아르바이트",
                            true,
                            false
                        ),
                    ),
                    ScheduleData(
                        "KKK 15:00",
                        MyScheduleTheme.colors.calendarPurple,
                        MyScheduleInfo(
                            2,
                            "15:00",
                            "18:00",
                            1,
                            "KKK",
                            "사장",
                            false,
                            false
                        ),
                    )
                )
            )
        )
        put(
            DateInfo.create(2024, 5, 14),
            ScheduleInfo(
                true,
                listOf(
                    ScheduleData(
                        "AAA 10:00",
                        MyScheduleTheme.colors.calendarBlue,
                        MyScheduleInfo(
                            3,
                            "10:00",
                            "12:00",
                            3,
                            "AAA",
                            "매니저",
                            false,
                            true
                        ),
                    )
                )
            )
        )
    }

    MyScheduleTheme {
        ScheduleCalendar(Modifier, dummyDate) { dateInfo, scheduleData ->

        }
    }
}