package com.uuranus.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.uuranus.designsystem.theme.MyScheduleTheme

@Composable
fun DeleteDialog(
    title: String,
    content: String,
    onDismissDialog: () -> Unit,
    onConfirmDialog: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = {
            onDismissDialog()
        },
        containerColor = MyScheduleTheme.colors.background,
        title = {
            Text(
                title,
                style = MyScheduleTheme.typography.semiBold16,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        text = {
            Text(
                content,
                style = MyScheduleTheme.typography.regular16,
            )
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
                onConfirmDialog()
            }
        },
    )
}