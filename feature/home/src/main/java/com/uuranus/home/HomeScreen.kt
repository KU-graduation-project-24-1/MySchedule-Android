package com.uuranus.home

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterExitState
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Transition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.uuranus.designsystem.component.CircularImageComponent
import com.uuranus.designsystem.component.MyScheduleAppBar
import com.uuranus.designsystem.theme.MyScheduleTheme
import com.uuranus.home.calendar.DateInfo
import com.uuranus.home.calendar.MyScheduleInfo
import com.uuranus.home.calendar.ScheduleCalendar
import com.uuranus.home.calendar.ScheduleData
import com.uuranus.home.calendar.ScheduleInfo
import com.uuranus.navigation.MyScheduleScreens
import com.uuranus.navigation.currentComposeNavigator
import java.util.Calendar

@Composable
fun HomeScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val dummyDate = HashMap<DateInfo, ScheduleInfo<MyScheduleInfo>>().apply {
        put(
            DateInfo.create(2024, 5, 5),
            ScheduleInfo(
                false,
                listOf(
                    ScheduleData(
                        0,
                        "AAA 10:00",
                        MyScheduleTheme.colors.calendarBlue,
                        detail = MyScheduleInfo(
                            "10:00",
                            "12:00",
                            "AAA",
                            "매니저",
                            false,
                            MyScheduleTheme.colors.calendarBlue,
                            true
                        )
                    ),
                    ScheduleData(
                        1,
                        "BBB 12:00",
                        MyScheduleTheme.colors.calendarOrange,
                        MyScheduleInfo(
                            "12:00",
                            "15:00",
                            "BBB",
                            "아르바이트",
                            true,
                            MyScheduleTheme.colors.calendarOrange,
                            false
                        ),
                    ),
                    ScheduleData(
                        2,
                        "KKK 15:00",
                        MyScheduleTheme.colors.calendarPurple,
                        MyScheduleInfo(
                            "15:00",
                            "18:00",
                            "KKK",
                            "사장",
                            false,
                            MyScheduleTheme.colors.calendarPurple,
                            false
                        ),
                    )
                )
            )
        )
        put(
            DateInfo.create(2024, 5, 14),
            ScheduleInfo(
                true,
                listOf(
                    ScheduleData(
                        3,
                        "AAA 10:00",
                        MyScheduleTheme.colors.calendarBlue,
                        MyScheduleInfo(
                            "10:00",
                            "12:00",
                            "AAA",
                            "매니저",
                            false,
                            MyScheduleTheme.colors.calendarBlue,
                            true
                        ),
                    )
                )
            )
        )
    }

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
                        style = MyScheduleTheme.typography.bold16
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

            HomeContent(
                animatedVisibilityScope = animatedVisibilityScope,
//                uiState = uiState,
                dummyDate
            )
        }
    }


}

@Composable
fun HomeContent(
    animatedVisibilityScope: AnimatedVisibilityScope,
    schedules: Map<DateInfo, ScheduleInfo<MyScheduleInfo>>,
) {
    ScheduleCalendar(modifier = Modifier.fillMaxSize(), schedules) {
        println("!!!!! ${it.joinToString(" ")}")
    }
}

@Preview
@ExperimentalAnimationApi
@Composable
fun HomeScreenPreview() {

    MyScheduleTheme {

        Column(modifier = Modifier.fillMaxSize()) {
            val animatedVisibilityScope = remember {
                object : AnimatedVisibilityScope {
                    override val transition: Transition<EnterExitState>
                        get() = TODO("Not yet implemented")

                }
            }
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
            HomeContent(animatedVisibilityScope, emptyMap())
        }
    }
}