package com.uuranus.myschedule.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.uuranus.home.HomeScreen
import com.uuranus.mypage.MyPageScreen
import com.uuranus.myschedule.bosshome.BossHomeAddScheduleScreen
import com.uuranus.myschedule.bosshome.BossHomeEditScheduleScreen
import com.uuranus.myschedule.bosshome.BossHomeScreen
import com.uuranus.myschedule.bossmypage.BossMyPageScreen
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
        BossHomeScreen()
    }

    composable(
        route = MyScheduleScreens.BossEditSchedule.name,
        arguments = MyScheduleScreens.BossEditSchedule.navArguments,
    ) {
        BossHomeEditScheduleScreen()
    }

    composable(
        route = MyScheduleScreens.BossAddSchedule.name,
        arguments = MyScheduleScreens.BossAddSchedule.navArguments
    ) {
        BossHomeAddScheduleScreen()
    }

    composable(
        route = MyScheduleScreens.BossWorkerManage.name,
    ) {
//        BossWorkerManageScreen()
    }

    composable(
        route = MyScheduleScreens.BossMyPage.name,
        arguments = MyScheduleScreens.BossMyPage.navArguments,
    ) {
        BossMyPageScreen()
    }
}
