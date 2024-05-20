@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("myschedule.android.library")
    id("myschedule.android.library.compose")
    id("myschedule.android.hilt")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.uuranus.myschedule.feature.login"
}

dependencies {
    implementation(libs.androidx.material3.android)
    implementation(projects.core.domain)
    implementation("androidx.compose.ui:ui:1.2.0")
    implementation("androidx.compose.ui:ui-tooling:1.2.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.2.0")
    implementation("androidx.compose.material:material-icons-extended:1.0.5")
    implementation("androidx.compose.material:material:1.2.0")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))

    // When using the BoM, you don't specify versions in Firebase library dependencies

    // Add the dependency for the Firebase SDK for Google Analytics
    implementation("com.google.firebase:firebase-analytics-ktx")

    implementation("com.kakao.sdk:v2-user:2.20.1") // 카카오 로그인 API 모듈

}