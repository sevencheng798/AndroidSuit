package com.example.sven.javacallc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    String TAG="NDKJavaCallcDemo";
    private JNI jni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jni = new JNI();
    }
    public void add(View view) {
        int result = jni.add(10, 20);
        Log.d(TAG, "result= "+result);
    }

    public void string(View view) {
        String result = jni.sayHello("I am from JAVA ");
        Log.d(TAG, "result= "+result);
    }

    public void array(View view) {
        int intArray[] = {1, 2, 3, 4, 5, 6, 7, 8};
        jni.increaseArrayEles(intArray);
        for(int i=0; i<intArray.length; i++) {
            Log.d(TAG, "array["+i+"]=="+intArray[i]);
        }
    }

    public void checkpw(View view) {
        int result = jni.checkPwd("12345678");
        Log.d(TAG, "result== "+result);
    }
}
