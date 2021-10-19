buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven {
            url = uri("https://dl.bintray.com/kotlin/kotlin-eap")
        }
        maven {
            url = uri("https://kotlin.bintray.com/kotlinx")
        }
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30")
        classpath("com.android.tools.build:gradle:7.0.3")
        classpath("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.3")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}