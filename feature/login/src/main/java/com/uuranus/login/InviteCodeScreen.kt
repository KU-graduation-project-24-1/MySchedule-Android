package com.uuranus.login


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue


@Composable
fun InviteCodeScreen() {
    var inviteCode by remember { mutableStateOf(TextFieldValue("")) }
    var isError by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "초대 코드 입력하기",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = inviteCode,
            onValueChange = { newValue ->
                if (newValue.text.all { it.isDigit() }) {
                    inviteCode = newValue
                    if (isError) {
                        isError = false
                    }
                }
            },
            label = { Text("초대코드를 입력해주세요") },
//            keyboardOptions = KeyboardOptions.Default.copy(
//                keyboardType = KeyboardType.Number
//            ),
            modifier = Modifier.fillMaxWidth(),
            isError = isError
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (inviteCode.text.length != 10) {
                    isError = true
                } else {
                    showDialog = true
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFC1DAB9)
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "확인", color = Color.Black)
        }

        if (isError) {
            Text(
                text = "초대 코드가 잘못되었습니다",
                color = Color.Red,
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = {
                    Text(text = "이 가게가 맞으신가요?")
                },
                text = {
                    Text(text = "000 떡볶이 건대입구점")
                },
                confirmButton = {
                    Button(
                        onClick = { showDialog = false },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFC1DAB9)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("네, 맞습니다.", color = Color.Black)
                    }
                },
                dismissButton = {
                    OutlinedButton(
                        onClick = { showDialog = false },
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("취소하기")
                    }
                },
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

