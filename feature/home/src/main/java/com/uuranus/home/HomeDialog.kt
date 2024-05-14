package com.uuranus.home

import android.widget.RadioGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
                //viewModel.accept()
            }
        },
    )
}

@Composable
fun CalendarChooseDialog(
    currentItem: Int,
    onDismissDialog: (Int) -> Unit,
) {
    var selectedItem: Int by remember {
        mutableStateOf(currentItem)
    }

    AlertDialog(
        onDismissRequest = {
            onDismissDialog(selectedItem)
        },
        containerColor = MyScheduleTheme.colors.background,
        title = {
            Text(
                "캘린더 보기",
                style = MyScheduleTheme.typography.bold20,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "전체 스케줄",
                        style = MyScheduleTheme.typography.regular16,
                        modifier = Modifier.weight(1f)
                    )
                    RadioButton(
                        selected = selectedItem == 0,
                        onClick = {
                            selectedItem = 0
                        },
                        colors = RadioButtonColors(
                            selectedColor = MyScheduleTheme.colors.primary,
                            unselectedColor = MyScheduleTheme.colors.lightGray,
                            disabledSelectedColor = MyScheduleTheme.colors.lightGray,
                            disabledUnselectedColor = MyScheduleTheme.colors.lightGray
                        )
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "가능한 시간",
                        style = MyScheduleTheme.typography.regular16,
                        modifier = Modifier.weight(1f)
                    )
                    RadioButton(
                        selected = selectedItem == 1,
                        onClick = {
                            selectedItem = 1
                        },
                        colors = RadioButtonColors(
                            selectedColor = MyScheduleTheme.colors.primary,
                            unselectedColor = MyScheduleTheme.colors.lightGray,
                            disabledSelectedColor = MyScheduleTheme.colors.lightGray,
                            disabledUnselectedColor = MyScheduleTheme.colors.lightGray
                        )
                    )
                }
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
                        "확인",
                        style = MyScheduleTheme.typography.semiBold16,
                        color = MyScheduleTheme.colors.textColor
                    )
                }
            ) {
                onDismissDialog(selectedItem)
            }
        },
    )
}