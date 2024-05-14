plugins {
    id("myschedule.android.library")
    id("myschedule.android.hilt")
    id("com.google.devtools.ksp")
    alias(libs.plugins.protobuf)
}

android {
    namespace = "com.uuranus.myshcedule.core.datastore"
}

protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                register("java") {
                    option("lite")
                }
                register("kotlin") {
                    option("lite")
                }
            }
        }
    }
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.network)
    testImplementation(projects.core.testing)
    implementation(libs.protobuf.kotlin.lite)

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