package com.uuranus.designsystem.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MyScheduleDialog(
    showDialog: Boolean,
    title: @Composable() () -> Unit = {},
    content: @Composable() (() -> Unit) = {},
    confirmButton: @Composable() (() -> Unit) = {},
    onCloseDialog: () -> Unit,
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onCloseDialog,
            title = title,
            text = content,
            confirmButton = confirmButton,
            modifier = Modifier.padding(16.dp)
        )
    }
}