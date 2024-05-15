package com.uuranus.designsystem.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.uuranus.designsystem.theme.MyScheduleTheme

@Composable
fun <T> ScheduleCalendarListItem(
    modifier: Modifier,
    data: ScheduleData<T>,
) {
    Row(
        modifier = modifier.background(
            color = data.color,
            shape = RoundedCornerShape(2.dp)
        )
    ) {
        Text(
            data.title,
            modifier = Modifier.fillMaxSize(),
            style = MyScheduleTheme.typography.regular10,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ScheduleCalendarMoreListItem(
    modifier: Modifier,
    moreCount: Int,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            "+${moreCount}",
            modifier = Modifier.fillMaxSize(),
            style = MyScheduleTheme.typography.regular10,
        )
    }
}