package com.uuranus.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.uuranus.designsystem.theme.MyScheduleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScheduleBottomSheet(
    sheetState: SheetState,
    content: @Composable (ColumnScope.() -> Unit) = {},
    onDismissRequest: () -> Unit,
) {
    ModalBottomSheet(
        sheetState = sheetState,
        content = content,
        containerColor = MyScheduleTheme.colors.background,
        onDismissRequest = onDismissRequest
    )
}