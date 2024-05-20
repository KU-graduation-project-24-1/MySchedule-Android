package com.uuranus.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.uuranus.designsystem.calendar.DateInfo
import com.uuranus.model.MyScheduleInfo
import com.uuranus.model.MyScheduleNavType
import com.uuranus.model.toMyScheduleNavType

sealed class MyScheduleScreens(
    val route: String,
    val navArguments: List<NamedNavArgument> = emptyList(),
) {
    val name: String = route.appendArguments(navArguments)

    data object Login : MyScheduleScreens("login")

    data object NameInput : MyScheduleScreens("nameInput")

    data object StoreList : MyScheduleScreens("storeList")

    data object EmploymentType : MyScheduleScreens("employmentType")

    data object BusinessRegistration : MyScheduleScreens("businessRegistration")

    data object InviteCode : MyScheduleScreens("inviteCode")

    data object StoreName : MyScheduleScreens("storeName")

    data object InviteRegistration : MyScheduleScreens("inviteRegistration")

    data object Home : MyScheduleScreens("home")

    data object MyPage : MyScheduleScreens(route = "mypage")

    data object BossHome : MyScheduleScreens(route = "bossHome")

    data object BossEditSchedule : MyScheduleScreens(
        route = "bossEditSchedule",
        navArguments = listOf(
            navArgument("scheduleInfo") {
                type = MyScheduleType()
                nullable = false
            },
        ),
    ) {
        fun createRoute(storeId: Int, dateDashString: String, scheduleInfo: MyScheduleInfo) =
            name.replace(
                "{${navArguments.first().name}}",
                MyScheduleType.encodeToString(
                    scheduleInfo.toMyScheduleNavType(
                        storeId = storeId,
                        dateDashString = dateDashString
                    )
                )
            )
    }

    data object BossAddSchedule : MyScheduleScreens(
        route = "bossAddSchedule",
        navArguments = listOf(
            navArgument("scheduleInfo") {
                type = MyScheduleType()
                nullable = false
            },
        ),
    ) {
        fun createRoute(storeId: Int, dateDashString: String, scheduleInfo: MyScheduleInfo) =
            name.replace(
                "{${navArguments.first().name}}",
                MyScheduleType.encodeToString(
                    scheduleInfo.toMyScheduleNavType(
                        storeId,
                        dateDashString
                    )
                )
            )
    }

    data object BossWorkerManage : MyScheduleScreens(route = "bossWorkerManage")

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
