plugins {
    id("myschedule.android.library")
    id("myschedule.android.library.compose")
}

android {
    namespace = "com.uuranus.myschedule.core.designsystem"
}

dependencies {

    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.ui.tooling)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.animation)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
}