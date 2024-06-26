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
    viewModel: LoginViewModel, onClickLogin: (Context, (String)->Unit) -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = MyScheduleScreens.Login.route,
    ) {
        loginNavigation(viewModel, onClickLogin)
    }

}

@Composable
fun StoreListNavHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = MyScheduleScreens.StoreList.route,
    ) {
        storeListNavigation()
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
