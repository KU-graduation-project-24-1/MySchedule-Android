package com.uuranus.myschedule.core.common.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.uuranus.designsystem.calendar.DateInfo
import com.uuranus.designsystem.calendar.ScheduleData
import com.uuranus.designsystem.calendar.getLanguageYMDDate
import com.uuranus.designsystem.component.MyScheduleFilledButton
import com.uuranus.designsystem.theme.MyScheduleTheme
import com.uuranus.model.MyScheduleInfo

@Composable
fun RequestFillInDialog(
    dateInfo: DateInfo,
    scheduleInfo: ScheduleData<MyScheduleInfo>,
    onDismissDialog: () -> Unit,
    onConfirmClick: () -> Unit,
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
                color = MyScheduleTheme.colors.primary,
                content = {
                    Text(
                        "요청",
                        style = MyScheduleTheme.typography.semiBold16,
                        color = MyScheduleTheme.colors.textColor
                    )
                }
            ) {
                onConfirmClick()
            }
        }
    )
}

@Composable
fun AcceptFillInDialog(
    dateInfo: DateInfo,
    scheduleInfo: ScheduleData<MyScheduleInfo>,
    onDismissDialog: () -> Unit,
    onConfirmClick: () -> Unit,
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
                    style = MyScheduleTheme.typography.regular16,
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
                color = MyScheduleTheme.colors.primary,
                content = {
                    Text(
                        "수락",
                        style = MyScheduleTheme.typography.semiBold16,
                        color = MyScheduleTheme.colors.textColor
                    )
                }
            ) {
                onConfirmClick()
            }
        },
    )
}
