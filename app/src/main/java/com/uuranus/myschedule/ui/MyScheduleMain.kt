package com.uuranus.myschedule.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.uuranus.designsystem.theme.MyScheduleTheme
import com.uuranus.myschedule.navigation.BossNavHost
import com.uuranus.myschedule.navigation.MyScheduleNavHost
import com.uuranus.navigation.AppComposeNavigator
import kotlinx.coroutines.launch
import java.net.UnknownHostException

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyScheduleMain(
    composeNavigator: AppComposeNavigator,
) {
    MyScheduleTheme {
        val navHostController = rememberNavController()

        val coroutineScope = rememberCoroutineScope()

        val snackBarHostState = remember { SnackbarHostState() }

        LaunchedEffect(Unit) {
            composeNavigator.handleNavigationCommands(navHostController)
        }

        Scaffold(
            snackbarHost = { SnackbarHost(snackBarHostState) },
            modifier = Modifier.fillMaxSize()
        ) { padding ->
            MyScheduleNavHost(
                navHostController = navHostController,
                onShowSnackbar = { throwable ->
                    coroutineScope.launch {
                        snackBarHostState.showSnackbar(
                            when (throwable) {
                                is UnknownHostException -> "네트워크 연결이 원활하지 않습니다"
                                else -> throwable.message ?: "알 수 없는 오류가 발생했습니다"
                            }
                        )
                    }
                })

        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BossMain(
    composeNavigator: AppComposeNavigator,
) {
    MyScheduleTheme {
        val navHostController = rememberNavController()

        val coroutineScope = rememberCoroutineScope()

        val snackBarHostState = remember { SnackbarHostState() }

        LaunchedEffect(Unit) {
            composeNavigator.handleNavigationCommands(navHostController)
        }

        Scaffold(
            snackbarHost = { SnackbarHost(snackBarHostState) },
            modifier = Modifier.fillMaxSize()
        ) { padding ->
            BossNavHost(
                navHostController = navHostController,
                onShowSnackbar = { throwable ->
                    coroutineScope.launch {
                        snackBarHostState.showSnackbar(
                            when (throwable) {
                                is UnknownHostException -> "네트워크 연결이 원활하지 않습니다"
                                else -> "알 수 없는 오류가 발생했습니다"
                            }
                        )
                    }
                })

        }
    }
}