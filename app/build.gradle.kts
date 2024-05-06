plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.daggerHiltAndroid)
    alias(libs.plugins.secretsGradle)
}

android {
    namespace = "vn.hoanguyen.compose.onlinestore"
    compileSdk = 34

    defaultConfig {
        applicationId = "vn.hoanguyen.compose.onlinestore"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.play.services.location)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.kotlin.coroutines.play)
    implementation(libs.gson)
    implementation(libs.coil)
    implementation("com.canopas.compose-animated-navigationbar:bottombar:1.0.1")
    implementation("com.google.maps.android:maps-compose:4.4.2")
    implementation("com.google.accompanist:accompanist-permissions:0.34.0")

    //UI
    implementation(libs.androidx.compose.material.iconsExtended)

    // Hilt
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.android.compiler)

    //Navigation
    implementation(libs.androidx.compose.navigation)
    implementation(libs.androidx.hilt.navigation.compose)
}

secrets {
    // To add your Maps API key to this project:
    // 1. Add this line to your local.properties file, where YOUR_API_KEY is your API key:
    //        MAPS_API_KEY=YOUR_API_KEY
    propertiesFileName = "secrets.properties"
    defaultPropertiesFileName = "local.properties"
}
