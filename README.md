# <p align="center"> News 📰 </p>

This is a news application that provides instant access to breaking news. Users can read news in different languages. NewsData.io API has been used for news retrieval.

<!-- Screenshots -->
## 📸 Screenshots
<p align="center">
  <img src="https://github.com/selincengiz41/news/assets/60012262/d21fc5ff-d5fd-430e-9be2-a6351f3020c5" width="130" height="300"/> 
  <img src="https://github.com/selincengiz41/news/assets/60012262/ee50b50d-4b6a-40ab-a35b-6b8dc5de5688" width="130" height="300"/> 
  <img src="https://github.com/selincengiz41/news/assets/60012262/4c4f4755-96d6-4ee4-b173-8f939d4e84b0" width="130" height="300"/>
  <img src="https://github.com/selincengiz41/news/assets/60012262/f7cd1021-ea83-4ec6-bea7-bd2a333d57be" width="130" height="300"/>
  <img src="https://github.com/selincengiz41/news/assets/60012262/8e2aa80c-bfc3-4560-b96b-a10e0c3ed391" width="130" height="300"/>
  <img src="https://github.com/selincengiz41/news/assets/60012262/cd303a94-d36a-49f7-bc6d-79c2523e9f66" width="130" height="300"/>
  <img src="https://github.com/selincengiz41/news/assets/60012262/446946d2-11d4-4084-a42b-9344ffb3fb2a" width="130" height="300"/>
  <img src="https://github.com/selincengiz41/news/assets/60012262/0e324953-0a47-40c8-9caa-38e4b0abd299" width="130" height="300"/>
  <img src="https://github.com/selincengiz41/news/assets/60012262/014eed8d-e5c9-4bf4-8867-7668d22dca3f" width="130" height="300"/>
  


</p>


<br>

## :point_down: Structures 
- MVVM
- Firebase Auth
- Firestore
- FCM
- Navigation
- Hilt
- Coroutines
- Retrofit
- Room 
- Data Binding 
- Clean Architecture
- Glide
- SDP/SSP Library
- Lottie
- Roundable Layout
- Motion Layout
- Shared Pref
- Gson
- Push notification


## :pencil2: Dependency
```
    dependencies {

   // SSP-SDP library
    implementation 'com.intuit.ssp:ssp-android:1.1.0'
    implementation 'com.intuit.sdp:sdp-android:1.1.0'

    // Navigation
    implementation 'androidx.navigation:navigation-fragment-ktx:2.6.0'
    implementation 'androidx.navigation:navigation-ui-ktx:2.6.0'

    //Retrofit
    implementation "com.squareup.retrofit2:retrofit:2.9.0"
    implementation "com.squareup.retrofit2:converter-gson:2.9.0"

    //Glide
    implementation "com.github.bumptech.glide:glide:4.15.1"

    //Roundable Layout
    implementation 'com.github.zladnrms:RoundableLayout:1.1.4'

    //Lottie
    implementation 'com.airbnb.android:lottie:6.1.0'

    //Hilt
    implementation 'com.google.dagger:hilt-android:2.47'
    kapt 'com.google.dagger:hilt-compiler:2.47'


    //Chucker
    implementation("com.github.chuckerteam.chucker:library:4.0.0")

    //Room
    implementation("androidx.room:room-runtime:2.5.2")
    implementation("androidx.room:room-ktx:2.5.2")
    kapt "androidx.room:room-compiler:2.5.2"

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")


    //Firebase
    implementation platform('com.google.firebase:firebase-bom:32.7.3')
    // Add the dependency for the Firebase Authentication library
    implementation("com.google.firebase:firebase-auth-ktx")

    implementation ("jp.wasabeef:glide-transformations:4.3.0")

}
```

app build.gradle:

```
plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
    id 'kotlin-parcelize'
    id 'androidx.navigation.safeargs'
    //For the annotations
    id 'kotlin-kapt'
    id 'dagger.hilt.android.plugin'
    id 'com.google.gms.google-services'
}

buildFeatures {
      dataBinding = true
 }
```
project build.gradle:

```
plugins {
      id 'com.android.application' version '8.0.2' apply false
    id 'com.android.library' version '8.0.2' apply false
    id 'org.jetbrains.kotlin.android' version '1.8.20' apply false
    id 'com.google.gms.google-services' version '4.4.1' apply false
    id 'androidx.navigation.safeargs.kotlin' version '2.5.1' apply false
    id 'com.google.dagger.hilt.android' version "2.44" apply false
}
```
