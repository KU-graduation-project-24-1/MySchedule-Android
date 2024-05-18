plugins {
    id("myschedule.android.library")
    id("myschedule.android.library.compose")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("myschedule.android.hilt")
}

android {
    namespace = "com.uuranus.myschedule.core.navigation"
}

dependencies {
    implementation(projects.core.model)

    implementation(libs.kotlinx.coroutines)

    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.animation)
    api(libs.androidx.navigation.compose)

    implementation(libs.moshi)
    implementation(project(":core:designsystem"))
}