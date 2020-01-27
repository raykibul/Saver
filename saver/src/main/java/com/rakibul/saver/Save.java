package com.rakibul.saver;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.lang.reflect.Field;

public class Save {
    public Context context;
    public String packagename;
    SharedPreferences sharedPreferences;
    public static final String TAG = "MyTag";


    public static Save instance;

    public static Save getInstance(Context context) {
        Log.e("MyTag", "getInstance: Called");
        if (instance == null) {
            instance = new Save(context);
        }
        return instance;
    }

    public void saveint(String name, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(name, value);
        editor.apply();
    }

    public int getint(String name) {
        return sharedPreferences.getInt(name, 0);
    }

    public void savefloat(String name, float value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(name, value);
        editor.apply();
    }

    public float getfloat(String name) {
        return sharedPreferences.getFloat(name, 0);
    }

    public void saveboolean(String name, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(name, value);
        editor.apply();
    }

    public boolean getboolean(String name) {
        return sharedPreferences.getBoolean(name, false);
    }

    public Save(Context context) {
        this.context = context;
        this.packagename = context.getPackageName();
        this.sharedPreferences = context.getSharedPreferences(packagename, Context.MODE_PRIVATE);
        Log.e("MyTag", "Save: " + packagename);
    }

    public void savestring(String value, String name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(name, value);
        editor.apply();
    }

    public String getstring(String name) {
        return sharedPreferences.getString(name, null);
    }

    public void saveobject(String name, Object myobject) {
        Class<?> myclass = myobject.getClass();
        Field[] fields = myclass.getDeclaredFields();

        for (Field f : fields) {
            //Log.e(TAG, "saveobject: "+f.getType().toString());
            switch (f.getType().toString()) {

                case "class java.lang.String":
                    try {
                        savestring(f.get(myobject).toString(), f.getName() + name);
                        //Log.e(TAG, "saveobjectt: "+f.getName()+name);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;
                case "int":
                    try {
                        saveint(f.getName() + name, Integer.parseInt(f.get(myobject).toString()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;
                case "float":
                    try {
                        savefloat(f.getName() + name, Float.parseFloat(f.get(myobject).toString()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;
                case "boolean":
                    try {
                        saveboolean(f.getName() + name, Boolean.parseBoolean(f.get(myobject).toString()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;

            }
        }
    }

    public Object getobject(String name, Object object) {
        Class<?> myclass = object.getClass();

        Field[] fields = myclass.getDeclaredFields();
        for (Field f : fields) {
            switch (f.getType().toString()) {

                case "class java.lang.String":
                    try {
                        f.set(object, getstring(f.getName() + name));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                    break;
                case "int":
                    try {
                        f.set(object, getint(f.getName() + name));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                    break;
                case "float":
                    try {
                        f.set(object, getfloat(f.getName() + name));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                    break;
                case "boolean":
                    try {
                        f.set(object, getboolean(f.getName() + name));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                    break;

            }
        }
        return object;
    }

}