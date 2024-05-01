@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
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