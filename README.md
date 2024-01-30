# Food Tracker
###  "Food Tracker" is an Android application designed to facilitate the process of keeping track of food items. Users can manually input data or utilize the camera for automatic recognition, with the option to correct any inaccuracies detected during the scanning process. The app also provides personalized features through its Profile pages. In the Profile section, users can specify allergens and substances they wish to avoid. The application alerts the users when potentially harmful or undesirable ingredients are found. In brief, Food Tracker combines various technologies within a user-friendly interface to make the management of food products easier. Our goal is to simplify users’ daily routine and contribute to a more efficient approach to food consumption.

### This application can be used by cloning the source code in a directory and running it with a proper application such as Android Studio. In order to make use of the diet help part, an OpenAI API (ChatGPT) key should be obtained. 

![My Skills](https://skillicons.dev/icons?i=java,androidstudio,gradle,sqlite&perline=50)

<p align="center">
    <img src="https://github.com/engincanoz/FoodTracker/blob/main/ScreenShots%20Food%20Tracker.gif" alt="">
</p>






# Dependencies
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.gms:play-services-tasks:18.0.2")
    implementation("com.google.android.gms:play-services-mlkit-text-recognition-common:19.0.0")
    implementation("com.google.android.gms:play-services-mlkit-text-recognition:19.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.navigation:navigation-fragment:2.7.6")
    implementation("androidx.navigation:navigation-ui:2.7.6")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
