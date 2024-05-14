package com.uuranus.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.uuranus.designsystem.theme.MyScheduleTheme

@Composable
fun TimePickerDialog(
    selectedTime: String,
    onTimeSelected: (String) -> Unit,
) {
    val time: MutableState<Pair<String, String>> = remember {
        mutableStateOf(Pair("", ""))
    }

    Dialog(onDismissRequest = {}) {
        Card(
            shape = RoundedCornerShape(8.dp), // Card의 모든 꼭지점에 8.dp의 둥근 모서리 적용
        ) {
            Column(
                modifier = Modifier
                    .width(300.dp)
                    .wrapContentHeight()
                    .background(
                        color = Color.White,
                    )
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Text(
                    "시작 시간",
                    style = MyScheduleTheme.typography.semiBold16
                )

                Spacer(modifier = Modifier.height(5.dp))

                OutlinedTextField(value = "", onValueChange = {
                    time.value.copy(first = it)
                }, textStyle = MyScheduleTheme.typography.regular16,
                    placeholder = {
                        Text(text = "00:00")
                    })

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    "종료 시간",
                    style = MyScheduleTheme.typography.semiBold16
                )

                Spacer(modifier = Modifier.height(5.dp))

                OutlinedTextField(value = "", onValueChange = {
                    time.value.copy(first = it)
                }, textStyle = MyScheduleTheme.typography.regular16,
                    placeholder = {
                        Text(text = "00:00")
                    })

                Spacer(modifier = Modifier.height(35.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    MyScheduleFilledButton(
                        modifier = Modifier.fillMaxWidth(),
                        paddingValues = PaddingValues(all = 12.dp),
                        onClick = {
//                            onTimeSelected(String.format("%02d:%02d", hour, minute))
                            onTimeSelected("${time.value.first}~${time.value.second}")
                        },
                        buttonState = true,
                        color = MyScheduleTheme.colors.primary,
                        content = {
                            Text(text = "확인", style = MyScheduleTheme.typography.semiBold16)
                        }
                    )
                }
            }
        }
    }
}

//@Composable
//fun TimePicker(
//    modifier: Modifier = Modifier,
//    hour: Int,
//    minute: Int,
//    onTimeSelected: (Int, Int) -> Unit,
//) {
//    Column(modifier) {
//        // 시간 선택
//        var selectedHour by remember { mutableStateOf(hour) }
//        var selectedMinute by remember { mutableStateOf(minute) }
//
//        // 시간 피커
//        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Text("Hour: ", modifier = Modifier.padding(end = 8.dp))
//                NumberPicker(
//                    value = selectedHour,
//                    onValueChange = { selectedHour = it },
//                    minValue = 0,
//                    maxValue = 23
//                )
//            }
//            Spacer(modifier = Modifier.height(16.dp))
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Text("Minute: ", modifier = Modifier.padding(end = 8.dp))
//                NumberPicker(
//                    value = selectedMinute,
//                    onValueChange = { selectedMinute = it },
//                    minValue = 0,
//                    maxValue = 59
//                )
//            }
//            Spacer(modifier = Modifier.height(16.dp))
//            Button(
//                onClick = {
//                    onTimeSelected(selectedHour, selectedMinute)
//                },
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text("Select")
//            }
//        }
//    }
//}