package com.uuranus.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.uuranus.designsystem.theme.MyScheduleTheme
import com.uuranus.myschedule.core.designsystem.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScheduleAppBar() {
    Surface(
        shadowElevation = 4.dp // elevation 추가
    ) {
        TopAppBar(
            modifier = Modifier
                .background(MyScheduleTheme.colors.background)
                .padding(top = 8.dp),
            title = {
                Text(
                    text = "000 떡볶이 건대입구점",
                    style = MyScheduleTheme.typography.bold20
                )
            },
            actions = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    CircularImageComponent(
                        painter = painterResource(id = R.drawable.baseline_person_24),
                        size = 30
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors().copy(
                containerColor = MyScheduleTheme.colors.background,
            ),
        )
    }
}


@Composable
fun CircularImageComponent(painter: Painter, size: Int) {
    Box(
        modifier = Modifier
            .size(size.dp)
            .clip(CircleShape)
            .background(MyScheduleTheme.colors.gray),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = null
        )
    }
}