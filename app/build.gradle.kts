plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    alias(libs.plugins.compose.compiler)
}

android {
    buildTypes {
        getByName("debug") {
            enableUnitTestCoverage = true
            enableAndroidTestCoverage = true
        }
        getByName("release") {
            enableUnitTestCoverage = true
            enableAndroidTestCoverage = true
        }
    }

    namespace = "com.google.maps.android.compose"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.activity)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material)
    implementation(libs.kotlin)
    implementation(libs.material)
    implementation(libs.androidx.compose.ui.preview.tooling)
    debugImplementation(libs.androidx.compose.ui.tooling)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.test.espresso)
    androidTestImplementation(libs.androidx.test.junit.ktx)
    androidTestImplementation(libs.test.junit)
    androidTestImplementation(libs.androidx.test.compose.ui)
    androidTestImplementation(libs.coroutines)

    // Instead of the lines below, regular apps would load these libraries from Maven according to
    // the README installation instructions
    implementation(project(":maps-compose"))
    implementation(project(":maps-compose-widgets"))
    implementation(project(":maps-compose-utils"))
}

secrets {
    // To add your Maps API key to this project:
    // 1. Add this line to your local.properties file, where YOUR_API_KEY is your API key:
    //        MAPS_API_KEY=YOUR_API_KEY
    defaultPropertiesFileName = "local.defaults.properties"
}
