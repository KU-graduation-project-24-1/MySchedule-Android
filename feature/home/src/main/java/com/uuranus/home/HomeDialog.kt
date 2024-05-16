package com.uuranus.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.uuranus.designsystem.component.MyScheduleFilledButton
import com.uuranus.designsystem.theme.MyScheduleTheme


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