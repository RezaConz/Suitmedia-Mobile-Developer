plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.parcelize)
}

android {
    namespace = "com.suitmedia.testportal"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.suitmedia.testportal"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        buildConfigField(
            "String",
            "BASE_URL",
            "\"https://reqres.in/api/\""
        )

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
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //circle img
    implementation(libs.circleImageView)

    //glide
    implementation(libs.glide)
    annotationProcessor(libs.glideCompiler)

    //retrofit + okhttp3
    implementation(libs.retrofit)
    implementation(libs.converterGson)
    implementation(libs.loggingInterceptor)

    //lifecycle
    implementation(libs.lifecycleRuntimeKtx)

    //coroutines
    implementation(libs.lifecycleViewModelKtx)
    implementation(libs.coroutinesCore)
    implementation(libs.lifecycleLivedataKtx)
    implementation(libs.coroutinesAndroid)

    //swipe refresh layout
    implementation(libs.swiperefreshlayout)

    //paging 3
    implementation(libs.androidx.paging.runtime.ktx)
}