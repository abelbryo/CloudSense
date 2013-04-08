ICQA
==========
Cloudsense is an indoor climate quality assessment app for android. The App works for Android 3.0 ( API LEVEL 11 ) and newer devices.


Dependency jar files
======================
android-suppport-v7-gridlayout.jar
---------------------------
GridLayout in Android was added in API Level 14. If you want to use for older platforms, they you will have to import the support library. Import *sdk/extras/android/support/v7/gridlayout* into eclipse and add it as a library to the project.  

* google-play-services_lib.jar
* google-play-services.jar

 Facebook API
--------------
The facebook API at the moment is only used for user authentication on the mobile.
It uses facebook-android-sdk-3.0. Download the sdk and import it into eclipse as an existing project and set it as a library. Once you do that, register the package and the activity with facebook.
Following thorough [this tutorial](https://developers.facebook.com/docs/getting-started/facebook-sdk-for-android/3.0/) is recommended.



 AChartEngine (v1.1.0-rc2)
---------------------------
AChartEngine is a light weight charting API for android.
Make sure you build the jar file from [source] (https://code.google.com/p/achartengine/source/checkout) using `ant dist`, becuase as of this writing, the `setXLabelPadding` and `setYLabelPadding` doesn't work otherwise.
