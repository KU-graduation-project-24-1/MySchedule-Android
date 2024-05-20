package com.uuranus.myschedule.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.uuranus.navigation.MyScheduleScreens

@Composable
fun LoginNavHost(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = MyScheduleScreens.Login.route,
    ) {
        loginNavigation()
    }

}

@Composable
fun MyScheduleNavHost(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = MyScheduleScreens.Home.route,
    ) {
        myScheduleNavigation()
    }

}

@Composable
fun BossNavHost(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = MyScheduleScreens.BossHome.route,
    ) {
        bossNavigation()
    }

}
