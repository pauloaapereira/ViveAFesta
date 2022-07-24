package com.pp.ppcompose.buildsrc

object Configs {
    const val CompileSdkVersion = 32
    const val MinSdkVersion = 26
    const val TargetSdkVersion = 32

    const val VersionCode = 1
    const val VersionName = "1.0"
}

object ClassPaths {
    const val gradlePlugin = "com.android.tools.build:gradle:7.2.1"
    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0"
}

object Compose {
    const val composeVersion = "1.3.0-alpha01"

    const val animation = "androidx.compose.animation:animation:$composeVersion"
    const val iconsExtended = "androidx.compose.material:material-icons-extended:$composeVersion"
    const val material = "androidx.compose.material:material:$composeVersion"
    const val runtime = "androidx.compose.runtime:runtime:$composeVersion"
    const val tooling = "androidx.compose.ui:ui-tooling:$composeVersion"
    const val ui = "androidx.compose.ui:ui:$composeVersion"
    const val uiUtil = "androidx.compose.ui:ui-util:$composeVersion"
    const val uiTest = "androidx.compose.ui:ui-test-junit4:$composeVersion"
    const val activityCompose = "androidx.activity:activity-compose:1.6.0-alpha05"
    const val navigationCompose = "androidx.navigation:navigation-compose:2.5.0-alpha02"
    const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:1.0.0"
}

object Tests {
    private const val junitVersion = "4.13.2"
    private const val junitKtx = "1.1.4-alpha07"

    const val junit = "junit:junit:$junitVersion"
    const val junitKotlin = "androidx.test.ext:junit-ktx:$junitKtx"
}

object Core {
    const val androidXCore = "androidx.core:core-ktx:1.9.0-alpha05"
    const val appCompat = "androidx.appcompat:appcompat:1.6.0-alpha05"
    const val material = "com.google.android.material:material:1.7.0-alpha03"
}

object Libs {

    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.7.0"
    }

    object Coroutines {
        private const val version = "1.6.4"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object Lottie {
        const val lottie = "com.airbnb.android:lottie-compose:5.2.0"
    }

    object Accompanist {
        const val insets = "com.google.accompanist:accompanist-insets:0.24.13-rc"
    }

    object GoogleLocation {
        const val location = "com.google.android.gms:play-services-location:20.0.0"
    }

    object Hilt {
        private const val version = "2.43"
        const val library = "com.google.dagger:hilt-android:$version"
        const val googleAndroidCompiler = "com.google.dagger:hilt-android-compiler:$version"
        const val googleCompiler = "com.google.dagger:hilt-compiler:$version"
        const val testing = "com.google.dagger:hilt-android-testing:$version"
        const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
    }

}
