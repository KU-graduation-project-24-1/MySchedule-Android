package com.uuranus.home.calendar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uuranus.designsystem.theme.MyScheduleTheme
import com.uuranus.home.ScheduleData

@Composable
fun ScheduleCalendar(
    modifier: Modifier,
    schedules: List<ScheduleData>,
) {

    //현재 달 remember
    //swipe 리스너
    Column(modifier = modifier.fillMaxSize()) {
        ScheduleCalendarHeader()
        ScheduleCalendarMonth(schedules)

    }
}


@Composable
fun ScheduleCalendarHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "2024년 4월",
            style = MyScheduleTheme.typography.bold20
        )
    }
}

@Composable
fun ScheduleCalendarMonth(
    schedules: List<ScheduleData>,
) {

    LazyVerticalGrid(columns = GridCells.Fixed(7)) {
        items(38) {
            if (it < 7) {
                ScheduleCalendarWeek(it)
            } else {
                ScheduleCalendarDate(
                    modifier = Modifier,
                    date = it + 1,
                    isCheckNeeded = true,
                    schedules = schedules
                )
            }
        }
    }
}

@Composable
fun ScheduleCalendarWeek(weekNum: Int) {
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
fun ScheduleCalendarDate(
    modifier: Modifier,
    date: Int,
    isCheckNeeded: Boolean,
    schedules: List<ScheduleData>,
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
        DateHeader(date, isCheckNeeded)
        DateContent(Modifier.fillMaxHeight(), schedules)
    }
}

@Composable
fun DateHeader(
    date: Int,
    isCheckNeeded: Boolean,
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

//        Box(
//            modifier = Modifier
//                .size(20.dp)
//                .background(
//                    color = MyScheduleTheme.colors.primary,
//                    shape = CircleShape
//                )
//                .align(Alignment.TopCenter),
//            contentAlignment = Alignment.Center
//        ) {
//            Text(
//                text = date.toString(),
//                style = MyScheduleTheme.typography.regular14,
//                color = MyScheduleTheme.colors.white
//            )
//        }

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = date.toString(),
                style = MyScheduleTheme.typography.regular14,
                color = MyScheduleTheme.colors.black
            )
        }
    }
}

@Composable
fun DateContent(
    modifier: Modifier,
    schedules: List<ScheduleData>,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(10) { index ->
            if (index < 4) {
                ScheduleCalendarListItem(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 1.dp),
                    data = ScheduleData(
                        index,
                        "AA 10:00",
                        "AA(매니저) 10:00 ~ 18:00",
                        MyScheduleTheme.colors.calendarPurple
                    )
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
    MyScheduleTheme {
        ScheduleCalendar(Modifier, emptyList())
    }
}