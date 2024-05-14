package com.uuranus.myschedule.navigation

import androidx.navigation.NavGraphBuilder
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

fun NavGraphBuilder.bossNavigation() {
    composable(route = MyScheduleScreens.BossHome.name) {
//        BossHomeScreen(this)
    }

    composable(
        route = MyScheduleScreens.BossMyPage.name,
        arguments = MyScheduleScreens.BossMyPage.navArguments,
    ) {
//        BossMyPageScreen()
    }
}
