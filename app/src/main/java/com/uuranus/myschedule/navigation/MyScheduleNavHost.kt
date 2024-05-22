package com.uuranus.myschedule.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.uuranus.navigation.MyScheduleScreens

@Composable
fun MyScheduleNavHost(
    navHostController: NavHostController,
    onShowSnackbar: suspend (Throwable) -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = MyScheduleScreens.Home.route,
    ) {
        myScheduleNavigation(onShowSnackbar)
    }

}

@Composable
fun BossNavHost(
    navHostController: NavHostController,
    onShowSnackbar: suspend (Throwable) -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = MyScheduleScreens.BossHome.route,
    ) {
        bossNavigation(onShowSnackbar)
    }

}
