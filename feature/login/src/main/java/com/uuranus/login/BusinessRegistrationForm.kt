package com.uuranus.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
fun BusinessRegistrationForm() {
    var registrationNumber by remember { mutableStateOf(TextFieldValue("")) }
    var openingDate by remember { mutableStateOf(TextFieldValue("")) }
    var ownerName by remember { mutableStateOf(TextFieldValue("")) }
    var showError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "사업자 등록 번호를 입력해주세요",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "사업자 등록번호",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = registrationNumber,
            onValueChange = { newValue ->
                if (newValue.text.all { it.isDigit() } && newValue.text.length <= 10) {
                    registrationNumber = newValue
                }
            },
            label = { Text("- 기호 제외 10자리를 입력해주세요") },
//            keyboardOptions = KeyboardOptions.Default.copy(
//                keyboardType = KeyboardType.Number
//            ),
            modifier = Modifier.fillMaxWidth(),
            isError = showError && registrationNumber.text.length != 10
        )
        if (showError && registrationNumber.text.length != 10) {
            Text(
                text = "- 기호 제외 10자리를 입력해주세요",
                color = Color.Red,
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Text(
            text = "*사업자 등록 번호는 암호화되어 저장되며, 가게 등록 해제 시 모든 정보는 즉시 폐기됩니다.",
            color = Color.Gray,
            style = TextStyle(fontSize = 12.sp),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "가게 개업일자",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = openingDate,
            onValueChange = { newValue ->
                if (newValue.text.all { it.isDigit() }) {
                    openingDate = newValue
                }
            },
            label = { Text("개업일자를 입력해주세요 (yyyymmdd)") },
//            keyboardOptions = KeyboardOptions.Default.copy(
//                keyboardType = KeyboardType.Number
//            ),
            modifier = Modifier.fillMaxWidth(),
            isError = showError && !openingDate.text.matches(Regex("\\d{8}"))
        )
        if (showError && !openingDate.text.matches(Regex("\\d{8}"))) {
            Text(
                text = "yyyymmdd 형식으로 입력해주세요",
                color = Color.Red,
                style = TextStyle(fontSize = 14.sp),
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Text(
            text = "사업자 이름",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = ownerName,
            onValueChange = { ownerName = it },
            label = { Text("이름(실명)을 입력해주세요") },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                showError = registrationNumber.text.length != 10 || !openingDate.text.matches(Regex("\\d{8}"))
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFC1DAB9)
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "확인", color = Color.Black)
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (showError) {
            Text(
                text = "사업자 정보가 올바르지 않습니다",
                color = Color.White,
                style = TextStyle(
                    fontSize = 14.sp,
                    background = Color.DarkGray,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color.DarkGray)
            )
        }
    }
}