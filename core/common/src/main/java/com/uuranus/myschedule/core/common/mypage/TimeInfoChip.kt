package com.uuranus.myschedule.core.common.mypage

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uuranus.designsystem.theme.MyScheduleTheme
import com.uuranus.model.TimeRange
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import com.uuranus.designsystem.component.DeleteDialog
import com.uuranus.myschedule.core.common.R

@Composable
fun TimeInfoChip(
    timeRange: TimeRange,
    onDeleteClicked: (Int) -> Unit,
) {

    var showDeleteDialog: Boolean by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .padding(all = 1.dp)
            .border(
                1.dp, color = MyScheduleTheme.colors.primary,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Text(
            "${timeRange.startTime}~${timeRange.endTime}",
            style = MyScheduleTheme.typography.regular16
        )
        Spacer(modifier = Modifier.width(7.dp))
        Icon(
            painterResource(id = com.uuranus.myschedule.core.designsystem.R.drawable.close_icon),
            tint = MyScheduleTheme.colors.primary,
            contentDescription = "시간 정보 삭제",
            modifier = Modifier.clickable {
                showDeleteDialog = true
            }
        )
    }

    if (showDeleteDialog) {
        DeleteDialog(title = "시간 삭제",
            content = "해당 시간 정보를 삭제하시겠습니까?",
            onDismissDialog = {
                showDeleteDialog = false
            }, onConfirmDialog = {
                showDeleteDialog = false
                onDeleteClicked(timeRange.timeId)
            }
        )
    }
}