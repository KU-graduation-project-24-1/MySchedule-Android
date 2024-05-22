package com.uuranus.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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

@Composable
fun StoreListScreen() {
    var stores by remember { mutableStateOf(sampleStores) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "내 가게 목록",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            OutlinedButton(
                onClick = { /* Handle add store */ },
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.Black // Set text color to black
                ),
                border = BorderStroke(1.dp, Color(0xFFC1DAB9)) // Set border color to black
            ) {
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "추가")
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            stores.forEach { store ->
                StoreItem(store = store)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun StoreItem(store: Store) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent // Set the background color to transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color(0xFFC1DAB9)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = store.name,
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "고용형태: ${store.employmentType}",
                    style = TextStyle(fontSize = 16.sp)
                )

            }
            Row {
                IconButton(onClick = {
                    //clipboardManager.setText(AnnotatedString(store.name))
                }) {
                    Icon(Icons.Filled.ContentCopy, contentDescription = "Copy")
                }
                IconButton(onClick = { /* Handle navigation */ }) {
                    Icon(Icons.Filled.SubdirectoryArrowRight, contentDescription = "Navigate")
                }
            }
        }
    }
}

data class Store(val name: String, val employmentType: String)

val sampleStores = listOf(
    Store("000 떡볶이 건대입구점", "사장"),
    Store("□□ 카페 성수점", "사장"),
    Store("○○ 카페 건대입구점", "사장"),
    Store("000 떡볶이 성수점", "사장")
)
