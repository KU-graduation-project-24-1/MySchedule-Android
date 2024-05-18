package com.uuranus.myschedule.bosshome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uuranus.designsystem.calendar.DateInfo
import com.uuranus.designsystem.calendar.ScheduleData
import com.uuranus.designsystem.calendar.getLanguageYMDDate
import com.uuranus.designsystem.component.MyScheduleFilledButton
import com.uuranus.designsystem.component.MyScheduleOutlinedButton
import com.uuranus.designsystem.theme.MyScheduleTheme
import com.uuranus.model.MyScheduleInfo
import com.uuranus.myschedule.core.common.home.MyScheduleDetailListItem

@Composable
fun MyScheduleBottomSheetContentForBoss(
    dateInfo: DateInfo,
    scheduleInfo: List<ScheduleData<MyScheduleInfo>>,
    onClick: (DateInfo, ScheduleData<MyScheduleInfo>) -> Unit = { _, _ -> },
    onEditClick: (DateInfo, ScheduleData<MyScheduleInfo>) -> Unit = { _, _ -> },
    onAddClick: (DateInfo) -> Unit = { _ -> },
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = getLanguageYMDDate(dateInfo),
                    style = MyScheduleTheme.typography.semiBold16,
                    modifier = Modifier.align(Alignment.Center)
                )

                MyScheduleOutlinedButton(modifier = Modifier
                    .align(Alignment.CenterEnd),
                    paddingValues = PaddingValues(
                        horizontal = 14.dp,
                        vertical = 5.dp
                    ), buttonState = true,
                    content = {
                        Text("추가", style = MyScheduleTheme.typography.regular14)
                    }, onClick = {
                        onAddClick(dateInfo)
                    })
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
                Row {
                    if (scheduleInfo[index].detail.isFillInNeeded || scheduleInfo[index].detail.isMine) {
                        MyScheduleFilledButton(
                            modifier = Modifier,
                            paddingValues = PaddingValues(
                                horizontal = 14.dp,
                                vertical = 5.dp
                            ), buttonState = true,
                            color = MyScheduleTheme.colors.primary,
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

                Spacer(modifier = Modifier.width(10.dp))
                MyScheduleOutlinedButton(modifier = Modifier,
                    paddingValues = PaddingValues(
                        horizontal = 14.dp,
                        vertical = 5.dp
                    ), buttonState = true,
                    content = {
                        Text("변경", style = MyScheduleTheme.typography.regular14)
                    }, onClick = {
                        onEditClick(dateInfo, scheduleInfo[index])
                    })
            }
        }
    }

}