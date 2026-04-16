plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "ntu.ntth.ontapgk"
    // Using a more stable compileSdk version unless 36.1 is specifically required.
    // However, the error is about the JDK, so I will focus on that.
    compileSdk = 36

    defaultConfig {
        applicationId = "ntu.ntth.ontapgk"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

// Removing the toolchain block to allow Gradle to use the JDK specified in gradle.properties
// or the default IDE JDK, avoiding the broken JRE from the RedHat extension.
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(24))
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
