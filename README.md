# Saver ( Save variables or Object type data)
Saver is a android library which will help you to save a value in shared Preference file in android app. However , You no need to write those code in your project. 
### Supported Data type
1. String
2. Int
3. Float
4. Boolean
5. Any Class Object Containing (String , int , float, boolean)

### how to use it?
you need to go to project level build.gradle file.
add 
*  maven { url "https://jitpack.io" }
 then your gradle file will look like this

 allprojects {
    repositories {
        google()
        jcenter()
        maven { url "https://jitpack.io" }
    }
}

then go to your app level build.gradle file
in the dependencies add this line
*  implementation 'com.github.raykibul:Saver:1.0.0'
now click the 'Sync Now ' from screen top left side.

### its done!!!.

### save a string
1.initialize first
 Save save=Save.getinstance(this);
2. save.savestring("stringName","Value");


