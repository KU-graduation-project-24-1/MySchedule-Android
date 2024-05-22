package com.uuranus.myschedule.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.uuranus.home.HomeScreen
import com.uuranus.mypage.MyPageScreen
import com.uuranus.myschedule.bosshome.schedule.BossHomeAddScheduleScreen
import com.uuranus.myschedule.bosshome.schedule.BossHomeEditScheduleScreen
import com.uuranus.myschedule.bosshome.BossHomeScreen
import com.uuranus.myschedule.bossmypage.BossMyPageScreen
import com.uuranus.myschedule.feat.bossworkermanage.BossWorkerManageScreen
import com.uuranus.navigation.MyScheduleScreens

fun NavGraphBuilder.myScheduleNavigation(onShowSnackbar: suspend (Throwable) -> Unit) {
    composable(route = MyScheduleScreens.Home.name) {
        HomeScreen(onShowSnackbar = onShowSnackbar)
    }

    composable(
        route = MyScheduleScreens.MyPage.name,
        arguments = MyScheduleScreens.MyPage.navArguments,
    ) {
        MyPageScreen(onShowSnackbar = onShowSnackbar)
    }
}

fun NavGraphBuilder.bossNavigation(onShowSnackbar: suspend (Throwable) -> Unit) {
    composable(
        route = MyScheduleScreens.BossHome.name
    ) {
        BossHomeScreen(onShowSnackbar = onShowSnackbar)
    }

    composable(
        route = MyScheduleScreens.BossEditSchedule.name,
        arguments = MyScheduleScreens.BossEditSchedule.navArguments,
    ) {
        BossHomeEditScheduleScreen(onShowSnackbar = onShowSnackbar)
    }

    composable(
        route = MyScheduleScreens.BossAddSchedule.name,
        arguments = MyScheduleScreens.BossAddSchedule.navArguments
    ) {
        BossHomeAddScheduleScreen(onShowSnackbar = onShowSnackbar)
    }

    composable(
        route = MyScheduleScreens.BossWorkerManage.name,
    ) {
        BossWorkerManageScreen(onShowSnackbar = onShowSnackbar)
    }

    composable(
        route = MyScheduleScreens.BossMyPage.name,
        arguments = MyScheduleScreens.BossMyPage.navArguments,
    ) {
        BossMyPageScreen(onShowSnackbar = onShowSnackbar)
    }
}
