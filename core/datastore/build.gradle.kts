@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("myschedule.android.library")
    id("myschedule.android.hilt")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.uuranus.myshcedule.core.datastore"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.network)
    testImplementation(projects.core.testing)

    // coroutines
    implementation(libs.kotlinx.coroutines)
    testImplementation(libs.kotlinx.coroutines)
    testImplementation(libs.kotlinx.coroutines.test)

    // database
    implementation(libs.androidx.datastore)

    // json parsing
    implementation(libs.moshi)
    ksp(libs.moshi.codegen)

    // unit test
    testImplementation(libs.junit)
    testImplementation(libs.androidx.test.core)
}