# TestTask_company2
In result this app will parse info from https://unsplash.com and then create activity with list of images and their decription. When we click on image, created new activity where image open in full screen size

When I started writing this app, I knew nothing about Android development. After spending about 50 hours, I realized:
How to create applications for android
What are View and their attributes. Also, how to create them programmatically, not in XML.
I learned what activities are, the transitions between them, and their life cycle.
How to set an image in an ImageView or ImageButton
How to work with buttons so that they perform actions when pressed
How to parse API information in JSON format and wrap them
How to get a photo by URL

I continue to learn every day. I find all the information on Google, YouTube and developer.android.com

My progress in development this app:

1. Added one image and text
2. Added possibility opening new activity by click on photo
3. Added possibility close this activity and come back to main activity by click on ImageButton
4. Added many photo with text on screen and added possibility to scroll
5. Added methods for open current image in new activity with max image size on the screen by clicking on a photo from the list
6. 6.Added Fast Android Networking Library.

Problem:
Caused by: org.gradle.internal.resolve.ModuleVersionNotFoundException: Could not find com.amitshekhar.android:android-networking:1.0.2.
Solution: add "jcenter()" to gradle.properties ->dependencyResolutionManagement
->repositories

I find solution on stackoverflow.com

7.Added reading JSONObject from URL and getting username from that JSONObject
