package com.uuranus.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class MyScheduleScreens(
    val route: String,
    val navArguments: List<NamedNavArgument> = emptyList(),
) {
    val name: String = route.appendArguments(navArguments)

    data object Login : MyScheduleScreens("login")

    data object Home : MyScheduleScreens("home")

    data object MyPage : MyScheduleScreens(route = "mypage")

    data object BossHome : MyScheduleScreens(route = "bossHome")


    data object BossMyPage : MyScheduleScreens(route = "bossMyPage")
}

private fun String.appendArguments(navArguments: List<NamedNavArgument>): String {
    val mandatoryArguments = navArguments.filter { it.argument.defaultValue == null }
        .takeIf { it.isNotEmpty() }
        ?.joinToString(separator = "/", prefix = "/") { "{${it.name}}" }
        .orEmpty()
    val optionalArguments = navArguments.filter { it.argument.defaultValue != null }
        .takeIf { it.isNotEmpty() }
        ?.joinToString(separator = "&", prefix = "?") { "${it.name}={${it.name}}" }
        .orEmpty()

    return "$this$mandatoryArguments$optionalArguments"
}
