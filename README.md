Accomplish what's meaningful to you each day. Your very own... ToDo list
-> Google login/signout authentication
-> Add multiplpe to-do's
-> Separate storage of tasks for every user
-> Swipe to delete functionality
-> Checkboxes to keep track of your tasks every day
-> Based on Firebase 



build.gradle(Module: app) 



plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
    id 'com.google.gms.google-services'
}

android {
    compileSdk 32

    defaultConfig {
        applicationId "com.example.projecttodo"
        minSdk 21
        targetSdk 32
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = '1.8'
    }
}

dependencies {

    implementation 'androidx.core:core-ktx:1.7.0'
    implementation 'androidx.appcompat:appcompat:1.4.1'
    implementation 'com.google.android.material:material:1.5.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.3'
    implementation 'com.google.firebase:firebase-auth:21.0.3'
    implementation 'androidx.test:runner:1.4.0'
    implementation 'com.google.firebase:firebase-firestore-ktx:24.1.1'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'

    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.6.21"
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.1.1'



    //Firebase
    implementation 'com.google.firebase:firebase-auth:21.0.3'
    implementation 'com.google.android.gms:play-services-auth:20.2.0'
    implementation 'com.google.firebase:firebase-auth'
    implementation 'com.google.firebase:firebase-firestore'
    implementation 'com.firebaseui:firebase-ui-database:8.0.0'
    implementation 'com.firebaseui:firebase-ui-firestore:8.0.0'
    implementation 'com.firebaseui:firebase-ui-auth:8.0.0'
    implementation 'com.firebaseui:firebase-ui-storage:8.0.0'
    implementation platform('com.google.firebase:firebase-bom:29.0.4')


    //Glide
    implementation 'com.github.bumptech.glide:glide:4.11.0'

}
