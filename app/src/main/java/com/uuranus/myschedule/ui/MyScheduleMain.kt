package com.uuranus.myschedule.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.rememberNavController
import com.uuranus.designsystem.theme.MyScheduleTheme
import com.uuranus.myschedule.navigation.MyScheduleNavHost
import com.uuranus.navigation.AppComposeNavigator

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