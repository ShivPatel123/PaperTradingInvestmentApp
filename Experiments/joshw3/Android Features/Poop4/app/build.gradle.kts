plugins {
    id("com.android.application")
//    id ("java-library")
}




android {
    namespace = "com.example.poop4"
    compileSdk = 34




    defaultConfig {
        applicationId = "com.example.poop4"
        minSdk = 21
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
    //implementation fileTree(dir: 'libs', include: ['*.jar'])

    implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))
//    implementation ("org.java-websocket:Java-WebSocket:1.5.1")
//    implementation ("org.java-android-websocket:Java-WebSocket:1.5.1")

    //implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))

//    implementation ("org.java_websocket.handshake.ServerHandshake")

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation ("com.android.volley:volley:1.2.1")
    implementation ("org.java-websocket:Java-WebSocket:1.5.1")
    implementation ("androidx.legacy:legacy-support-v4:1.0.0")



    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}