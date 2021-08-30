plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = 30

    defaultConfig {
        minSdk = 23
        targetSdk = 30
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            buildConfigField(
                type = "String",
                name = "BASE_URL",
                value = "\"https://api.foursquare.com\""
            )
            buildConfigField(
                type = "String",
                name = "CLIENT_ID",
                value = "\"DDMR45LM1HUMXOQHEMKZEDPVZNGBJO0NIO4XBFDTOLVH4NIR\""
            )
            buildConfigField(
                type = "String",
                name = "CLIENT_SECRET",
                value = "\"DT53T4LBTV1SEMFLGRDA53LVTUBSYGPULA1VFR44QFOM20LF\""
            )

            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            buildConfigField(
                type = "String",
                name = "BASE_URL",
                value = "\"https://api.foursquare.com\""
            )
            buildConfigField(
                type = "String",
                name = "CLIENT_ID",
                value = "\"DDMR45LM1HUMXOQHEMKZEDPVZNGBJO0NIO4XBFDTOLVH4NIR\""
            )
            buildConfigField(
                type = "String",
                name = "CLIENT_SECRET",
                value = "\"DT53T4LBTV1SEMFLGRDA53LVTUBSYGPULA1VFR44QFOM20LF\""
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    // Room
    kapt(Dependencies.Room.compiler)
    implementation(Dependencies.Room.ktx)
    implementation(Dependencies.Room.runtime)
    // Networking
    implementation(Dependencies.Networking.retrofit)
    implementation(Dependencies.Networking.logger)
    implementation(Dependencies.Networking.gson)
    implementation(Dependencies.Networking.gsonConverter)
    // Testing
    androidTestImplementation(Dependencies.Testing.junit)
    androidTestImplementation("androidx.test:runner:1.4.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")

}