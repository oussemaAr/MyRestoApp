private const val gradleVersion = "7.0.1"
private const val gradlePluginVersion = "1.5.0"
private const val googleServicesVersion = "4.3.5"
private const val secretVersion = "1.3.0"
private const val navVersion = "2.3.5"

object Classpath {
    const val safeArgs ="androidx.navigation:navigation-safe-args-gradle-plugin:$navVersion"
    const val gradle = "com.android.tools.build:gradle:$gradleVersion"
    const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$gradlePluginVersion"
    const val googleServices = "com.google.gms:google-services:$googleServicesVersion"
    const val secretsMap =
        "com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:$secretVersion"

}