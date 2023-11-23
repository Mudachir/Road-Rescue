plugins {
    id("com.android.application")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.roadrescue"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.roadrescue"
        minSdk = 24
        targetSdk = 33
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
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment:2.5.3")
    implementation("androidx.navigation:navigation-ui:2.5.3")
    implementation("com.google.firebase:firebase-database:20.3.0")

    implementation("com.google.firebase:firebase-storage:20.3.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.intuit.sdp:sdp-android:1.0.6")
    implementation("com.intuit.sdp:sdp-android:1.0.6")
    implementation("de.hdodenhof:circleimageview:3.1.0")
    implementation ("com.google.android.gms:play-services-maps:18.1.0")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.google.android.gms:play-services-location:21.0.1")
    implementation("com.karumi:dexter:6.2.1")
    implementation ("com.github.denzcoskun:ImageSlideshow:0.1.0")
    implementation ("com.amadeus:amadeus-java:7.1.0")
    implementation ("com.squareup.picasso:picasso:2.8")



    implementation("com.google.android.material:material:1.9.0")

    implementation("com.google.firebase:firebase-auth:22.2.0")

    implementation("com.google.firebase:firebase-crashlytics-buildtools:2.9.9")
    implementation("com.android.volley:volley:1.2.1")



    implementation ("de.hdodenhof:circleimageview:3.1.0")
    implementation("androidx.cardview:cardview:1.0.0")




   




}