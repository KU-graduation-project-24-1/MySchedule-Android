package com.uuranus.home

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.uuranus.designsystem.component.MyScheduleAppBar

@Composable
fun HomeScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    Column(modifier = Modifier.fillMaxSize()) {
        MyScheduleAppBar()

//        HomeContent(
//            animatedVisibilityScope = animatedVisibilityScope,
//            uiState = uiState,
//        )
    }
}