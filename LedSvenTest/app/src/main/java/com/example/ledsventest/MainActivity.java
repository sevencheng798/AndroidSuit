package com.example.ledsventest;

//import android.app.Activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.led.RgbService;

public class MainActivity extends Activity {
    private RgbService ledManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ledManager = new RgbService();
        ledManager.native_EnableSetLed();
        String a_rgb = Integer.toHexString(0x3f);
        String rgb = Integer.toHexString(0x0000ff);
        int index = 0;
        for(int i=0; i<8; i++) {
            String cmd = index + " " + rgb + " " + a_rgb;
            ledManager.native_SetLed(cmd);
            index++;
        }
        ledManager.native_DisableSetLed();
    }
}
