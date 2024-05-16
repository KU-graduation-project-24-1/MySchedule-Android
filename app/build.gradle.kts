plugins {
    id("myschedule.android.application")
    id("myschedule.android.hilt")
    id("myschedule.android.application.compose")
    id("com.google.devtools.ksp")

}

android {
    namespace = "com.uuranus.myschedule"

    defaultConfig {
        applicationId = "com.uuranus.myschedule"
        versionCode = 1
        versionName = "1.0"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
    }

}

dependencies {
    implementation(projects.core.navigation)
    implementation(projects.feature.home)
    implementation(projects.feature.login)
    implementation(projects.feature.mypage)
    implementation(projects.feature.bosshome)
    implementation(projects.feature.bossmypage)

    // cores
    implementation(projects.core.model)
    implementation(projects.core.designsystem)
    implementation(projects.core.navigation)
    implementation(projects.core.domain)

    // compose
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.foundation)

    // di
    implementation(libs.hilt.android)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(project(":feature:bosshome"))
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    ksp(libs.hilt.compiler)
    androidTestImplementation(libs.hilt.testing)
    kspAndroidTest(libs.hilt.compiler)

    implementation(platform(libs.firebase.bom))
    implementation("com.google.firebase:firebase-analytics")

    implementation(libs.androidx.core.splashscreen)

}
