package com.uuranus.login

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun EmploymentTypeScreen() {
    var selectedType by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "고용형태를 알려주세요",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = { selectedType = "사장" },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFC1DAB9),
                contentColor = Color.Black
            ),
            border = BorderStroke(1.dp, Color(0xFFC1DAB9)),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = "사장")
        }

        Button(
            onClick = { selectedType = "직원" },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Black
            ),
            border = BorderStroke(1.dp, Color(0xFFC1DAB9)),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = "직원")
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    //LoginScreen()
//    //NameInputScreen()
//    //StoreListScreen()
//    //EmploymentTypeScreen()
//    //BusinessRegistrationForm() // todo
//    //InviteCodeScreen2()
//    //StoreNameForm()
//    //StoreListScreen()
//}