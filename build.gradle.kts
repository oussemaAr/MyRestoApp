// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Classpath.gradle)
        classpath(Classpath.gradlePlugin)
        classpath(Classpath.googleServices)
        classpath(Classpath.secretsMap)
        classpath(Classpath.safeArgs)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
