package com.uuranus.myschedule.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.uuranus.login.LoginViewModel
import com.uuranus.navigation.MyScheduleScreens

@Composable
fun LoginNavHost(
    navHostController: NavHostController,
    viewModel: LoginViewModel, onClickLogin: (Context) -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = MyScheduleScreens.Login.route,
    ) {
        loginNavigation(viewModel, onClickLogin)
    }

}

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
