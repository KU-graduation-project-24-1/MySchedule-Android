package com.uuranus.myschedule.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.uuranus.home.HomeScreen
import com.uuranus.mypage.MyPageScreen
import com.uuranus.navigation.MyScheduleScreens

fun NavGraphBuilder.myScheduleNavigation() {
    composable(route = MyScheduleScreens.Home.name) {
        HomeScreen(this)
    }

    composable(
        route = MyScheduleScreens.MyPage.name,
        arguments = MyScheduleScreens.MyPage.navArguments,
    ) {
        MyPageScreen()
    }
}
