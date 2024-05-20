package com.uuranus.myschedule.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.uuranus.home.HomeScreen
import com.uuranus.login.BusinessRegistrationForm
import com.uuranus.login.EmploymentTypeScreen
import com.uuranus.login.InviteCodeForm
import com.uuranus.login.InviteCodeScreen
import com.uuranus.login.LoginScreen
import com.uuranus.login.NameInputScreen
import com.uuranus.login.StoreListScreen
import com.uuranus.login.StoreNameForm
import com.uuranus.mypage.MyPageScreen
import com.uuranus.myschedule.bosshome.schedule.BossHomeAddScheduleScreen
import com.uuranus.myschedule.bosshome.schedule.BossHomeEditScheduleScreen
import com.uuranus.myschedule.bosshome.BossHomeScreen
import com.uuranus.myschedule.bossmypage.BossMyPageScreen
import com.uuranus.myschedule.feat.bossworkermanage.BossWorkerManageScreen
import com.uuranus.navigation.MyScheduleScreens

fun NavGraphBuilder.loginNavigation() {
    composable(MyScheduleScreens.Login.route) {
        LoginScreen()
    }
    composable(MyScheduleScreens.NameInput.route) {
        NameInputScreen()
    }
    composable(MyScheduleScreens.StoreList.route) {
        StoreListScreen()
    }
    composable(MyScheduleScreens.EmploymentType.route) {
        EmploymentTypeScreen()
    }
    composable(MyScheduleScreens.BusinessRegistration.route) {
        BusinessRegistrationForm()
    }
    composable(MyScheduleScreens.InviteCode.route) {
        InviteCodeScreen()
    }
    composable(MyScheduleScreens.StoreName.route) {
        StoreNameForm()
    }
    composable(MyScheduleScreens.InviteRegistration.route) {
        InviteCodeForm()
    }
}

fun NavGraphBuilder.myScheduleNavigation() {
    composable(route = MyScheduleScreens.Home.name) {
        HomeScreen(this)
    }

    composable(
        route = MyScheduleScreens.MyPage.name,
        arguments = MyScheduleScreens.MyPage.navArguments,
    ) {
        MyPageScreen()
    }
}

fun NavGraphBuilder.bossNavigation() {
    composable(
        route = MyScheduleScreens.BossHome.name
    ) {
        BossHomeScreen()
    }

    composable(
        route = MyScheduleScreens.BossEditSchedule.name,
        arguments = MyScheduleScreens.BossEditSchedule.navArguments,
    ) {
        BossHomeEditScheduleScreen()
    }

    composable(
        route = MyScheduleScreens.BossAddSchedule.name,
        arguments = MyScheduleScreens.BossAddSchedule.navArguments
    ) {
        BossHomeAddScheduleScreen()
    }

    composable(
        route = MyScheduleScreens.BossWorkerManage.name,
    ) {
        BossWorkerManageScreen()
    }

    composable(
        route = MyScheduleScreens.BossMyPage.name,
        arguments = MyScheduleScreens.BossMyPage.navArguments,
    ) {
        BossMyPageScreen()
    }
}
