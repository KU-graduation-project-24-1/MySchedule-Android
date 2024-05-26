package com.uuranus.myschedule.bossmypage

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uuranus.designsystem.component.MyScheduleFilledButton
import com.uuranus.designsystem.theme.MyScheduleTheme

@Composable
fun WorkerNumDialog(
    onNumSelected: (String) -> Unit,
    onDismissDialog: () -> Unit,
) {

    var number: String by remember {
        mutableStateOf("")
    }

    val numberError: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }

    AlertDialog(onDismissRequest = onDismissDialog,
        confirmButton = {
            MyScheduleFilledButton(
                modifier = Modifier.fillMaxWidth(),
                paddingValues = PaddingValues(13.dp),
                buttonState = true,
                color = MyScheduleTheme.colors.primary,
                content = {
                    Text(
                        "확인",
                        style = MyScheduleTheme.typography.semiBold16,
                        color = MyScheduleTheme.colors.textColor
                    )
                },
                onClick = {
                    onNumSelected(number)
                }
            )
        }, title = {
            Text("근무 인원", style = MyScheduleTheme.typography.semiBold16)
        }, text = {
            OutlinedTextField(
                value = number,
                onValueChange = {
                    number = it
                    numberError.value = isValidWorkerNumFormat((number)).not()
                },
                colors = TextFieldDefaults.colors().copy(
                    focusedIndicatorColor = MyScheduleTheme.colors.primary,

                    unfocusedIndicatorColor = MyScheduleTheme.colors.gray,
                    focusedContainerColor = MyScheduleTheme.colors.background,
                    unfocusedContainerColor = MyScheduleTheme.colors.background,
                    errorIndicatorColor = MyScheduleTheme.colors.errorColor,
                ),
                isError = numberError.value,
                supportingText = {
                    if (numberError.value) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "00(명) 입력해야합니다",
                            color = MyScheduleTheme.colors.errorColor
                        )
                    }
                },
                textStyle = MyScheduleTheme.typography.regular16,
                placeholder = {
                    Text(text = "0(명)")
                },
                maxLines = 1
            )
        }, containerColor = MyScheduleTheme.colors.background
    )
}


internal fun isValidWorkerNumFormat(time: String): Boolean {
    if (time.isEmpty()) return false
    val timeRegex = Regex("^[0-9]+$")
    return timeRegex.matches(time)
}