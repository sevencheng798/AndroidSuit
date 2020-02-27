package com.example.sven.ndkdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    String TAG="NDKDemo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String result = new JNI().sayHello();
        System.out.println(result);
        Log.d(TAG, "the string from JNC C '"+result + "'");
    }
}
