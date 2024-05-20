plugins {
    id("myschedule.android.library")
    id("myschedule.android.library.compose")
    id("myschedule.android.hilt")
}

android {
    namespace = "com.uuranus.myschedule.feature.main"
}

dependencies {
    implementation(projects.feature.home)
    implementation(projects.feature.login)
    implementation(projects.feature.mypage)

    // cores
    implementation(projects.core.model)
    implementation(projects.core.designsystem)
    implementation(projects.core.navigation)

    // compose
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.foundation)
}