import java.util.Locale

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp.kotlin)
    jacoco
}

android {
    namespace = "com.pedromonteiro.catsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.pedromonteiro.catsapp"
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
        debug {
            enableAndroidTestCoverage = true
            enableUnitTestCoverage = true
        }
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
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.coil.compose)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    annotationProcessor(libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler)
    testImplementation(libs.junit)
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

val exclusions = listOf(
    "**/R.class",
    "**/R\$*.class",
    "**/BuildConfig.*",
    "**/Manifest*.*",
    "**/*Test*.*",
    "**/databinding/*",
    "**/Dagger*",
    "**/Hilt*",
    "**/DataBinding*",
    "**/DataBinder*",
    "**/*_*",

    // di
    "**/di",

    // ui
    "**/components",
    "**/theme",
    "**/*ScreenKt*.*",
    "**/CatsAppKt*.*",
    "**/*NavGraph*.*",
    "**/*Activity*.*"
)

tasks.withType(Test::class) {
    useJUnitPlatform()
    configure<JacocoTaskExtension> {
        isIncludeNoLocationClasses = true
        excludes = listOf("jdk.internal.*")
    }
}

android {
    applicationVariants.all(closureOf<com.android.build.gradle.internal.api.BaseVariantImpl> {
        val variant = this@closureOf.name.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        }

        val unitTests = "test${variant}UnitTest"
        // In case of setup jacoco with UI tests
//        val androidTests = "connected${variant}AndroidTest"

        tasks.register<JacocoReport>("jacoco${variant}TestReport") {
            dependsOn(
                listOf(
                    unitTests,
//                    androidTests
                )
            )
            group = "Reporting"
            description = "Execute ui and unit tests, generate and combine Jacoco coverage report"
            reports {
                xml.required.set(true)
                html.required.set(true)
            }
            sourceDirectories.setFrom(layout.projectDirectory.dir("src/main"))
            classDirectories.setFrom(files(
                fileTree(layout.buildDirectory.dir("intermediates/javac/")) {
                    exclude(exclusions)
                },
                fileTree(layout.buildDirectory.dir("tmp/kotlin-classes/")) {
                    exclude(exclusions)
                }
            ))
            executionData.setFrom(files(
                fileTree(layout.buildDirectory) { include(listOf("**/*.exec", "**/*.ec")) }
            ))
        }
    })
}