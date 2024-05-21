package com.uuranus.myschedule.core.common.mypage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.unit.dp
import com.uuranus.designsystem.component.NetworkImage
import com.uuranus.designsystem.theme.MyScheduleTheme
import com.uuranus.model.UserData

@Composable
fun MyInfo(userData: UserData) {

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp)
        ) {
            Text(
                "내 정보", style = MyScheduleTheme.typography.semiBold16,
                modifier = Modifier.weight(1f)
            )
            Text(
                "수정", style = MyScheduleTheme.typography.semiBold16,
                modifier = Modifier.clickable {

                }
            )
        }
        HorizontalDivider(thickness = 1.dp, color = MyScheduleTheme.colors.lightGray)
        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            NetworkImage(
                null,
                placeholder = ColorPainter(MyScheduleTheme.colors.gray),
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(20.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "이름", style = MyScheduleTheme.typography.semiBold16,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    userData.name, style = MyScheduleTheme.typography.semiBold16,
                    modifier = Modifier.weight(4f)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "고용형태", style = MyScheduleTheme.typography.semiBold16,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    userData.workerType, style = MyScheduleTheme.typography.semiBold16,
                    modifier = Modifier.weight(4f)
                )
            }
        }
    }
}
