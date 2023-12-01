plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.navviewfe"
    compileSdk = 34



    defaultConfig {
        applicationId = "com.example.navviewfe"
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

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

}

dependencies {

    implementation("com.google.android.material:material:1.0.0")


    implementation ("androidx.recyclerview:recyclerview:1.3.2")
    implementation ("org.java-websocket:Java-WebSocket:1.5.1")
    implementation ("com.android.volley:volley:1.2.1")


    // Retrofit dependencies
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")



    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    implementation ("androidx.test:runner:1.5.2")
    implementation ("androidx.cardview:cardview:1.0.0")
    androidTestImplementation ("androidx.test:rules:1.5.0")
    testImplementation ("junit:junit:4.13.2")


}