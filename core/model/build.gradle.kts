plugins {
    id("myschedule.android.library")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")

}

android {
    namespace = "com.uuranus.myschedule.core.model"
}

dependencies {
    // compose stable marker
    compileOnly(libs.compose.stable.marker)

    // moshi
    implementation(libs.moshi)
    ksp(libs.moshi.codegen)

    // kotlinx
    api(libs.kotlinx.immutable.collection)
}