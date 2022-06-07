plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    packaging
}

android {
    compileSdk = Sdk.COMPILE_SDK_VERSION
    testOptions.unitTests.isIncludeAndroidResources = true

    defaultConfig {
        minSdk = Sdk.MIN_SDK_VERSION
        targetSdk = Sdk.TARGET_SDK_VERSION

        applicationId = AppCoordinates.APP_ID
        versionCode = AppCoordinates.APP_VERSION_CODE
        versionName = AppCoordinates.APP_VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true

        buildConfigField("String", "serverUrl", "\"https://jsonplaceholder.typicode.com/\"")
    }

    signingConfigs {
        create("release") {
         //
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            applicationIdSuffix = ".release"
            versionNameSuffix = "-release"
        }

        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }
    }

    flavorDimensions.addAll(listOf("style"))

    productFlavors {
        create("splash") {
            dimension = "style"
            applicationIdSuffix = ".splash"
            versionNameSuffix = "-splash"
            versionCode = 1
            versionName = "0.1"

            resValue("string", "app_name", "Splashy Splash")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
        unitTests.isIncludeAndroidResources = true
    }

    buildFeatures {
        viewBinding = true
    }

    lint {
        warningsAsErrors = true
        abortOnError = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation("androidx.core:core-splashscreen:1.0.0-rc01")

    implementation(Support.appCompat)
    implementation(Support.material)
    implementation(Support.constraintLayout)
    implementation(Support.recyclerview)
    implementation(Support.annotations)
    implementation(Ktx.activity)
    implementation(Ktx.fragment)
    implementation(Ktx.liveData)
    implementation(Ktx.savedState)
    implementation(Ktx.viewModel)
    implementation(Ktx.core)
    implementation(Ktx.collections)
    implementation(Navigation.navigationFragments)
    implementation(Navigation.navigationUI)

    implementation(Libs.gson)
    implementation(Retrofit.okHttp)
    implementation(Retrofit.loggingInterceptor)
    implementation(Retrofit.retrofit) {
        exclude("com.squareup.okhttp3", "okhttp")
    }
    implementation(Retrofit.gsonConverter) {
        exclude("com.google.code.gson", "gson")
    }
    implementation(Retrofit.rxJava) {
        exclude("io.reactivex.rxjava2", "rxjava")
    }

    implementation(Dagger.dagger)
    kapt(Dagger.compiler)
    implementation(Libs.kotlinpref)
    implementation(Libs.timberLogger)

    debugImplementation(Libs.leakCanary)
    implementation(Firebase.analytics)
    implementation(Firebase.crashlytics)
    implementation(Libs.stetho)
    implementation(Libs.stethoOkHttp)
    implementation(WorkManager.workManager)

    testImplementation(TestingLib.jUnit)
    implementation(TestingLib.testCoreX)

    testImplementation(TestingLib.testArchCompX)
    //testImplementation(TestingLib.robolectric)
    testImplementation(TestingLib.mockitoCore)
    testImplementation(TestingLib.mockitoKotlin)
    testImplementation(TestingLib.mockitoInline)
    testImplementation(TestingLib.runner)
    testImplementation(TestingLib.espresso)
    testImplementation(TestingLib.espressoIntents)
    testImplementation(TestingLib.rules)
    testImplementation(TestingLib.truth)
    testImplementation(TestingLib.kluent)
    testImplementation(TestingLib.coroutineTest)

    androidTestImplementation(TestingLib.jUnit)
    androidTestImplementation(TestingLib.testCoreX)
    androidTestImplementation(TestingLib.runner)
    androidTestImplementation(TestingLib.espresso)
}
