@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    `maven-publish`
}

kotlin {
    jvmToolchain(17)

    androidTarget { publishLibraryVariants("release") }
    jvm()
    wasmJs { browser() }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            compileOnly(libs.molecule.runtime)
            compileOnly(libs.androidx.lifecycle.viewmodelCompose)
        }
    }

}


android {
    namespace = "com.aleyn.molecule.ktx"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
    }
}

group = "com.aleyn.aleyn97"
version = "1.0.0"


afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                groupId = "com.aleyn.aleyn97"
                artifactId = "molecule-util"
            }
        }
    }
}
