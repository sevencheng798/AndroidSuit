package com.example.mydemojni;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.test.Demo;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("AndroidSven : ", "onCreate() event");
        Demo instance = new Demo();
       //  Log.d("AndroidSven : ", "Demo test nativeOpen() is: " + ret);
        tv = (TextView)findViewById(R.id.tv);
        tv.setText("Demo test native displayString() : " + instance.displayString());
    }
}
