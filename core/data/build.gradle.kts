plugins {
    id("myschedule.android.library")
    id("myschedule.android.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "com.uuranus.myschedule.core.data"
}

dependencies {
    // core modules
    api(projects.core.model)
    implementation(projects.core.datastore)
    implementation(projects.core.network)
    testImplementation(projects.core.testing)

    // kotlinx
    api(libs.kotlinx.immutable.collection)

    // coroutines
    implementation(libs.kotlinx.coroutines)
    testImplementation(libs.kotlinx.coroutines)
    testImplementation(libs.kotlinx.coroutines.test)

    // network
    implementation(libs.retrofit)

    // unit test
    testImplementation(libs.junit)
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
}