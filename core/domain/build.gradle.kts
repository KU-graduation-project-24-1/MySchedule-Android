plugins {
    id("myschedule.android.library")
    id("myschedule.android.hilt")
}

android {
    namespace = "com.uuranus.myschedule.core.domain"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.model)
}