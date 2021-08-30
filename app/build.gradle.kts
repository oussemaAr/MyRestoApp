plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("kapt")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("androidx.navigation.safeargs")
}

android {
    compileSdk = 30

    defaultConfig {
        applicationId = "tn.org.myresto"
        minSdk = 23
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        viewBinding = true
        dataBinding = true
    }

}

dependencies {
    implementation(project(mapOf("path" to ":data")))

    implementation(Dependencies.Components.stdlib)
    implementation(Dependencies.Components.activity)
    implementation(Dependencies.Components.fragment)
    implementation(Dependencies.Components.appCompat)
    implementation(Dependencies.Components.material)
    implementation(Dependencies.Components.constraintLayout)
    implementation(Dependencies.Components.core)
    implementation(Dependencies.Components.compiler)
    implementation(Dependencies.Components.timber)
    implementation(Dependencies.Components.viewModel)
    // Coroutines
    implementation(Dependencies.Coroutines.coroutines)
    implementation(Dependencies.Coroutines.kotlinx)
    implementation(Dependencies.Coroutines.task)
    // Google Map
    implementation(Dependencies.GoogleMap.ktx)
    implementation(Dependencies.GoogleMap.utils)
    implementation(Dependencies.GoogleMap.map)
    // Location
    implementation(Dependencies.GoogleMap.location)
    // Navigation
    implementation(Dependencies.Navigation.fragment)
    implementation(Dependencies.Navigation.ui)
    // Images
    implementation(Dependencies.Networking.glide)
    kapt(Dependencies.Networking.glideCompiler)
    // Lottie
    implementation("com.airbnb.android:lottie:4.1.0")
    // Testing
    androidTestImplementation(Dependencies.Testing.junit)
    androidTestImplementation(Dependencies.Testing.espresso)
    androidTestImplementation("androidx.test:runner:1.4.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.navigation:navigation-testing:2.3.5")

    val fragment_version = "1.3.6"
    debugImplementation("androidx.fragment:fragment-testing:$fragment_version")

}