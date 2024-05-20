package com.uuranus.myschedule.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.rememberNavController
import com.uuranus.designsystem.theme.MyScheduleTheme
import com.uuranus.myschedule.navigation.BossNavHost
import com.uuranus.myschedule.navigation.LoginNavHost
import com.uuranus.myschedule.navigation.MyScheduleNavHost
import com.uuranus.navigation.AppComposeNavigator


@Composable
fun LoginMain(
    composeNavigator: AppComposeNavigator,
) {
    MyScheduleTheme {
        val navHostController = rememberNavController()

        LaunchedEffect(Unit) {
            composeNavigator.handleNavigationCommands(navHostController)
        }

        LoginNavHost(navHostController = navHostController)
    }
}

@Composable
fun MyScheduleMain(
    composeNavigator: AppComposeNavigator,
) {
    MyScheduleTheme {
        val navHostController = rememberNavController()

        LaunchedEffect(Unit) {
            composeNavigator.handleNavigationCommands(navHostController)
        }

        MyScheduleNavHost(navHostController = navHostController)
    }
}

@Composable
fun BossMain(
    composeNavigator: AppComposeNavigator,
) {
    MyScheduleTheme {
        val navHostController = rememberNavController()

        LaunchedEffect(Unit) {
            composeNavigator.handleNavigationCommands(navHostController)
        }

        BossNavHost(navHostController = navHostController)
    }
}