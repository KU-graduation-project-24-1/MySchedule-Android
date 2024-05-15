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
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.uuranus.designsystem.component.MyScheduleAppBar
import com.uuranus.designsystem.component.NetworkImage
import com.uuranus.designsystem.theme.MyScheduleTheme
import com.uuranus.myschedule.core.designsystem.R

@Composable
fun MyPageScreen(
) {

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

            MyInfo()
            Spacer(modifier = Modifier.height(58.dp))
            MyFixedPossibleTime()
        }

    }
}

@Composable
fun MyInfo() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp)
        ) {
            Text(
                "내 정보", style = MyScheduleTheme.typography.semiBold16,
                modifier = Modifier.weight(1f)
            )
            Text(
                "수정", style = MyScheduleTheme.typography.semiBold16,
                modifier = Modifier.clickable {

                }
            )
        }
        HorizontalDivider(thickness = 1.dp, color = MyScheduleTheme.colors.lightGray)
        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            NetworkImage(
                null,
                placeholder = ColorPainter(MyScheduleTheme.colors.gray),
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(20.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "이름", style = MyScheduleTheme.typography.semiBold16,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    "김알바", style = MyScheduleTheme.typography.semiBold16,
                    modifier = Modifier.weight(4f)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "고용형태", style = MyScheduleTheme.typography.semiBold16,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    "아르바이트", style = MyScheduleTheme.typography.semiBold16,
                    modifier = Modifier.weight(4f)
                )
            }
        }
    }
}

@Composable
fun MyFixedPossibleTime() {
    val possibleTimes = listOf(
        emptyList<Pair<String, String>>(),
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
    )
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
                DayPossibleTimeListItem(it, possibleTimes[it])
            }
        }
    }
}

@Composable
fun DayPossibleTimeListItem(
    weekDayNum: Int,
    possibleTimes: List<Pair<String, String>>,
) {

    val weekdays = listOf("월", "화", "수", "목", "금", "토", "일")

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
                            "${it.first}~${it.second}"
                        }
                        .joinToString(", "), style = MyScheduleTheme.typography.regular16
                )
            }
        }

        Image(
            painter = painterResource(id = R.drawable.edit_icon),
            contentDescription = "고정된 가능 시간 추가하기",
            modifier = Modifier.clickable {
            }
        )
        Spacer(modifier = Modifier.width(16.dp))

    }
}