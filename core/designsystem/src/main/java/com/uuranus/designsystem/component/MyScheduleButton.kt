package com.uuranus.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uuranus.designsystem.theme.MyScheduleTheme

@Composable
fun MyScheduleFilledButton(
    paddingValues: PaddingValues,
    buttonState: Boolean,
    content: @Composable (BoxScope.() -> Unit) = {},
) {
    Box(
        modifier = Modifier
            .background(
                color = if (buttonState) MyScheduleTheme.colors.primary else MyScheduleTheme.colors.lightGray,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
fun MyScheduleOutlinedButton(
    modifier: Modifier,
    buttonState: Boolean,
    content: @Composable (BoxScope.() -> Unit) = {},
) {
    Box(
        modifier = modifier
            .border(
                1.dp,
                color = if (buttonState) MyScheduleTheme.colors.primary else MyScheduleTheme.colors.lightGray,
                shape = RoundedCornerShape(12.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}