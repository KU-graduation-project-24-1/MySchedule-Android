package com.uuranus.home

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.uuranus.designsystem.component.CircularImageComponent
import com.uuranus.designsystem.component.MyScheduleAppBar
import com.uuranus.designsystem.theme.MyScheduleTheme
import com.uuranus.home.calendar.ScheduleCalendar
import com.uuranus.myschedule.feature.home.R
import com.uuranus.navigation.MyScheduleScreens
import com.uuranus.navigation.currentComposeNavigator

@Composable
fun HomeScreen(
//    animatedVisibilityScope: AnimatedVisibilityScope,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {

    val composeNavigator = currentComposeNavigator

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MyScheduleTheme.colors.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            MyScheduleAppBar(
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
                            painter = painterResource(id = com.uuranus.myschedule.core.designsystem.R.drawable.baseline_person_24),
                            size = 30,
                            onClick = {
                                composeNavigator.navigate(MyScheduleScreens.MyPage.route)
                            }
                        )
                    }
                },
            )

//            HomeContent(
//                animatedVisibilityScope = animatedVisibilityScope,
//                uiState = uiState,

//            )
        }
    }


}

@Composable
fun HomeContent(
//    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    ScheduleCalendar(modifier = Modifier.fillMaxSize(), emptyList())
}

@Preview
@Composable
fun HomeScreenPreview() {
    MyScheduleTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            MyScheduleAppBar(
                title = {
                    Text(
                        text = "000 떡볶이 건대입구점",
                        style = MyScheduleTheme.typography.semiBold16
                    )
                },
                actions = {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        CircularImageComponent(
                            painter = painterResource(id = com.uuranus.myschedule.core.designsystem.R.drawable.baseline_person_24),
                            size = 30,
                            onClick = {
//                                composeNavigator.navigate(MyScheduleScreens.MyPage.route)
                            }
                        )
                    }
                },
            )
            HomeContent()
        }
    }
}