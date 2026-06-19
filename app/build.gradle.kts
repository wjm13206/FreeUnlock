plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "xyz.thewhitedog9487.freeunlock"
    compileSdk {
        version = release(36) }

    defaultConfig {
        applicationId = "xyz.thewhitedog9487.freeunlock"
        minSdk = 30
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
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

    buildFeatures {
        buildConfig = true }

    packaging {
        resources {
            merges += "META-INF/xposed/*"
            excludes += "**"
        }
    }

    signingConfigs {
        val KeystorePath = System.getProperty("Android_Keystore_Path")
        if( KeystorePath == null || file(KeystorePath).exists() == false ) {
            return@signingConfigs }

        create("release"){
            storeFile = file(KeystorePath)
            storePassword = System.getProperty("Android_Keystore_Password")
            keyAlias = System.getProperty("Android_Keystore_Alias")
            keyPassword = System.getProperty("Android_Keystore_Alias_Password")

            if ( arrayOf<Any?>( storePassword, keyAlias, keyPassword ).all { it != null } == false ) {
                throw RuntimeException("签名配置错误，缺少必要的环境变量。") } }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)

    compileOnly(libs.libxposed.api)
}

