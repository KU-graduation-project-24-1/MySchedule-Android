package com.uuranus.myschedule.core.common.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import com.uuranus.designsystem.calendar.ScheduleData
import com.uuranus.designsystem.component.toAnnotateString
import com.uuranus.designsystem.theme.MyScheduleTheme
import com.uuranus.model.MyScheduleInfo


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
