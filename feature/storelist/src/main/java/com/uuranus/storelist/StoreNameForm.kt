package com.uuranus.storelist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.input.TextFieldValue
import com.uuranus.navigation.MyScheduleScreens
import com.uuranus.navigation.currentComposeNavigator


@Composable
fun StoreNameForm() {
    var storeName by remember { mutableStateOf(TextFieldValue("")) }
    var showDialog by remember { mutableStateOf(false) }
    val composeNavigator = currentComposeNavigator

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "가게 이름을 알려주세요",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = storeName,
                onValueChange = { storeName = it },
                label = { Text("가게 이름을 입력해주세요") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFC1DAB9),
                    unfocusedBorderColor =Color(0xFFC1DAB9),
                )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { showDialog = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFC1DAB9)
                ),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "확인", color = Color.Black)
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(text = "이 가게가 맞으신가요?")
            },
            text = {
                Text(text = storeName.text)
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        composeNavigator.navigate(MyScheduleScreens.InviteCode.route)},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFC1DAB9)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("예.", color = Color.Black)
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = { showDialog = false },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("아니오")
                }
            },
            modifier = Modifier.padding(16.dp)
        )
    }
}
