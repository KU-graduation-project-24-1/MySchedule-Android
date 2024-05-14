package com.uuranus.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uuranus.designsystem.calendar.DateInfo
import com.uuranus.designsystem.calendar.ScheduleData
import com.uuranus.designsystem.calendar.getLanguageYMDDate
import com.uuranus.designsystem.component.MyScheduleFilledButton
import com.uuranus.designsystem.theme.MyScheduleTheme
import com.uuranus.model.MyScheduleInfo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScheduleBottomSheet(
    sheetState: SheetState,
    content: @Composable (ColumnScope.() -> Unit) = {},
    onDismissRequest: () -> Unit,
) {
    ModalBottomSheet(
        sheetState = sheetState,
        content = content,
        containerColor = MyScheduleTheme.colors.background,
        onDismissRequest = onDismissRequest
    )
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
                //사장이 아닐 때
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