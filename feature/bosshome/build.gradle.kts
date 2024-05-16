plugins {
    id("myschedule.android.library")
    id("myschedule.android.library.compose")
    id("myschedule.android.hilt")
}

android {
    namespace = "com.uuranus.myschedule.feature.bosshome"
}

dependencies {
    implementation(projects.core.designsystem)
    implementation(projects.core.navigation)
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.core.common)
}