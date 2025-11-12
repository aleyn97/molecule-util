@file:OptIn(ExperimentalWasmDsl::class)

import com.android.build.api.dsl.androidLibrary
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.maven.publish)
}

kotlin {
    jvmToolchain(17)

    androidLibrary {
        namespace = "com.aleyn.molecule.ktx"
        compileSdk = 36
        minSdk = 24
        withHostTestBuilder {}.configure {}
        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }
    }

    jvm()
    wasmJs { browser() }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            api(libs.molecule.runtime)
            api(libs.androidx.lifecycle.viewmodelCompose)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }

}

group = "io.github.aleyn97"
version = "1.0.0"

mavenPublishing {
    publishToMavenCentral()
    coordinates(group.toString(), "molecule-util", version.toString())

    pom {
        name = "molecule-ktx"
        description = "Kotlin Multiplatform library molecule util"
        inceptionYear = "2025"
        url = "https://github.com/aleyn97/molecule-util"

        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                distribution = "https://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }

        developers {
            developer {
                id = "aleyn"
                name = "Aleyn Developer"
                url = "https://github.com/aleyn97"
                email = "pclckk@163.com"
                organization = "personal"
                organizationUrl = "https://github.com/aleyn97"
            }
        }

        scm {
            url = "https://github.com/aleyn97/molecule-util"
            connection = "scm:git:git://github.com/aleyn97/molecule-util.git"
            developerConnection = "scm:git:ssh://git@github.com/aleyn97/molecule-util.git"
        }
    }
    if (project.hasProperty("signing.keyId")) signAllPublications()
}