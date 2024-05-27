package com.uuranus.myschedule.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.uuranus.myschedule.LoginActivity
import com.uuranus.myschedule.ui.BossMain
import com.uuranus.myschedule.ui.MyScheduleMain
import com.uuranus.navigation.AppComposeNavigator
import com.uuranus.navigation.LocalComposeNavigator
import com.uuranus.navigation.LocalLoginIntent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    internal lateinit var composeNavigator: AppComposeNavigator

    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val loginIntent = Intent(this, LoginActivity::class.java)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainActivityViewModel.uiState.collectLatest {
                    if (it is MainActivityUiState.Success) {
                        if (it.userData.workerType == "사장"
                        ) {
                            setContent {
                                CompositionLocalProvider(
                                    LocalComposeNavigator provides composeNavigator,
                                    LocalLoginIntent provides loginIntent
                                ) {
                                    BossMain(composeNavigator = composeNavigator)
                                }
                            }
                        } else {
                            setContent {
                                CompositionLocalProvider(
                                    LocalComposeNavigator provides composeNavigator,
                                    LocalLoginIntent provides loginIntent
                                ) {
                                    MyScheduleMain(composeNavigator = composeNavigator)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
