package com.uuranus.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.uuranus.designsystem.theme.MyScheduleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScheduleAppBar(
    title: @Composable () -> Unit,
    actions: @Composable (RowScope.() -> Unit) = {},
) {
    Surface(
        shadowElevation = 4.dp
    ) {
        TopAppBar(
            modifier = Modifier
                .background(MyScheduleTheme.colors.background)
                .padding(top = 8.dp),
            title = title,
            actions = actions,
            colors = TopAppBarDefaults.topAppBarColors().copy(
                containerColor = MyScheduleTheme.colors.background,
            ),
        )
    }
}

@Composable
fun CircularImageComponent(painter: Painter, size: Int, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(size.dp)
            .clip(CircleShape)
            .background(MyScheduleTheme.colors.gray)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = null
        )
    }
}