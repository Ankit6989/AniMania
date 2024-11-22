plugins {
        id("com.android.application")
        id("org.jetbrains.kotlin.android")
        id("kotlin-android")
        id("androidx.navigation.safeargs")
        id("dagger.hilt.android.plugin")
        id("kotlin-parcelize")
        id("kotlin-kapt")
}
android {
    namespace = "com.apcoding.animania"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.apcoding.animania"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }


    allprojects {
        tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
            }
        }
    }


    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation("androidx.databinding:databinding-runtime:8.7.2")
    val exoVersion = "2.19.1"
    val roomVersion = "2.6.0"
    val hiltVersion = "2.44.2"

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")
    // implementation(project(":AnimeScrapCommon"))
    // implementation(project(":animeSources"))

    // ExoPlayer + HLS + UI + MediaSession
    implementation("com.google.android.exoplayer:exoplayer:$exoVersion")
    implementation("com.google.android.exoplayer:exoplayer-ui:$exoVersion")
    implementation("com.google.android.exoplayer:exoplayer-hls:$exoVersion")
    implementation("com.google.android.exoplayer:extension-mediasession:$exoVersion")

    // MVVM
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // Navigation - Jetpack
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.4")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.4")

    // Network
    implementation("org.jsoup:jsoup:1.15.2") // Web scraping tool
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.google.code.gson:gson:2.9.0")  // JSON parser
    implementation("io.coil-kt:coil:2.2.2") // Photo from network

    // Room components
    implementation("androidx.room:room-ktx:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    androidTestImplementation("androidx.room:room-testing:$roomVersion")

    // Swipe to Refresh
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    // Preference / Settings
    implementation("androidx.preference:preference-ktx:1.2.1")

    // Hilt dependency injection
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-compiler:$hiltVersion")

    // Shimmer
    implementation("com.facebook.shimmer:shimmer:0.5.0")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
