private const val roomVersion = "2.3.0"
private const val retrofitVersion = "2.9.0"
private const val loggerVersion = "5.0.0-alpha.2"
private const val glideVersion = "4.12.0"
private const val lifeCycle = "2.3.1"
private const val coreVersion = "1.3.2"
private const val materialVersion = "1.4.0-alpha02"
private const val appCompatVersion = "1.2.0"
private const val constraintVersion = "2.0.4"
private const val timberVersion = "4.7.1"
private const val KotlinVersion = "1.5.21"
private const val mockitoKotlinVersion = "3.1.0"
private const val mockitoCoreVersion = "3.9.0"
private const val kotlinxVersion = "1.5.0-RC"
private const val activityVersion = "1.2.2"
private const val fragmentVersion = "1.3.6"
private const val junitVersion = "4.13.2"
private const val mockitoInlineVersion = "3.9.0"
private const val coroutinesVersion = "1.5.1"
private const val googleMapVersion = "17.0.1"
private const val googleMapKtxVersion = "3.1.0"
private const val taskPlayServices = "1.4.1"
private const val locationVersion = "18.0.0"
private const val navigationVersion = "2.3.5"


object Dependencies {
    object Room {
        const val runtime = "androidx.room:room-runtime:$roomVersion"
        const val compiler = "androidx.room:room-compiler:$roomVersion"
        const val ktx = "androidx.room:room-ktx:$roomVersion"
    }

    object Networking {
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val gson = "com.google.code.gson:gson:2.8.6"
        const val logger = "com.squareup.okhttp3:logging-interceptor:$loggerVersion"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
        const val glide = "com.github.bumptech.glide:glide:$glideVersion"
        const val glideCompiler = "com.github.bumptech.glide:compiler:$glideVersion"
    }

    object Components {
        const val activity = "androidx.activity:activity-ktx:$activityVersion"
        const val fragment = "androidx.fragment:fragment-ktx:$fragmentVersion"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifeCycle"
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifeCycle"
        const val compiler = "androidx.lifecycle:lifecycle-compiler:$lifeCycle"
        const val core = "androidx.core:core-ktx:$coreVersion"
        const val material = "com.google.android.material:material:$materialVersion"
        const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintVersion"
        const val timber = "com.jakewharton.timber:timber:$timberVersion"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$KotlinVersion"
    }

    object GoogleMap {
        const val map = "com.google.android.gms:play-services-maps:$googleMapVersion"
        const val ktx = "com.google.maps.android:maps-ktx:$googleMapKtxVersion"
        const val utils = "com.google.maps.android:maps-utils-ktx:$googleMapKtxVersion"
        const val location = "com.google.android.gms:play-services-location:$locationVersion"
    }

    object Navigation {
        const val fragment = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
        const val ui = "androidx.navigation:navigation-ui-ktx:$navigationVersion"
    }

    object Coroutines {
        const val task = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$taskPlayServices"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
        const val kotlinx = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxVersion"
    }

    object Testing {
        const val junit = "junit:junit:$junitVersion"
        const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
        const val room = "androidx.room:room-testing:$roomVersion"
        const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:$mockitoKotlinVersion"
        const val mockitoCore = "org.mockito:mockito-core:$mockitoCoreVersion"
        const val mockitoInline = "org.mockito:mockito-inline:$mockitoInlineVersion"
    }
}