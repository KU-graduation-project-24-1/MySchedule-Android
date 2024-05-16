package com.uuranus.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf

public val LocalComposeNavigator: ProvidableCompositionLocal<AppComposeNavigator> =
    compositionLocalOf {
        error(
            "No AppComposeNavigator provided! " +
                    "Make sure to wrap all usages of Pokedex components in PokedexTheme.",
        )
    }

public val currentComposeNavigator: AppComposeNavigator
    @Composable
    @ReadOnlyComposable
    get() = LocalComposeNavigator.current