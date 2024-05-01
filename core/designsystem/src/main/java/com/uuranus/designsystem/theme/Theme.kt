package com.uuranus.designsystem.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId


private val LocalColors = compositionLocalOf<MyScheduleColors> {
    error("No colors provided!")
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MyScheduleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colors: MyScheduleColors = if (darkTheme) {
        MyScheduleColors.defaultDarkColors()
    } else {
        MyScheduleColors.defaultLightColors()
    },
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {

    CompositionLocalProvider(
        LocalColors provides colors,
        LocalTypography provides Typography
    ) {
        Box(
            modifier = Modifier
                .background(MyScheduleTheme.colors.background)
                .semantics { testTagsAsResourceId = true },
        ) {
            content()
        }
    }
}

object MyScheduleTheme {

    val colors: MyScheduleColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current


    val typography: MyScheduleTypography
        @Composable
        get() = LocalTypography.current
}