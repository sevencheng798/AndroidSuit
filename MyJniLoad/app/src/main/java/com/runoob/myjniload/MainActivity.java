package com.runoob.myjniload;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.test.Demo;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("AndroidSven :", "onCreate() event");
        System.out.println(System.getProperty("java.library.path"));
        Demo instance = new Demo();
        tv = (TextView)findViewById(R.id.tv);
        tv.setText("myJniLoad test native displayString() : " + instance.displayString());
        Log.d("AndroidSven :", "after onCreate() event");
    }
}
