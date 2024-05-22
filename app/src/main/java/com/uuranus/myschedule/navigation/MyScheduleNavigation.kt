package com.uuranus.myschedule.navigation

import android.content.Context
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.uuranus.home.HomeScreen
import com.uuranus.login.BusinessRegistrationForm
import com.uuranus.login.EmploymentTypeScreen
import com.uuranus.login.InviteCodeForm
import com.uuranus.login.InviteCodeScreen
import com.uuranus.login.LoginScreen
import com.uuranus.login.LoginViewModel
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

fun NavGraphBuilder.loginNavigation(viewModel: LoginViewModel, onClickLogin: (Context) -> Unit) {
    composable(route = MyScheduleScreens.Login.name) {
        LoginScreen(viewModel, onClickLogin)
    }

    composable(MyScheduleScreens.NameInput.name) {
        NameInputScreen()
    }

    composable(MyScheduleScreens.StoreList.name) {
        StoreListScreen()
    }

    composable(MyScheduleScreens.EmploymentType.name) {
        EmploymentTypeScreen()
    }

    composable(MyScheduleScreens.BusinessRegistration.name) {
        BusinessRegistrationForm()
    }

    composable(MyScheduleScreens.InviteCode.name) {
        InviteCodeScreen()
    }

    composable(MyScheduleScreens.StoreName.name) {
        StoreNameForm()
    }

    composable(MyScheduleScreens.InviteRegistration.name) {
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
