package com.example.test;
import android.util.Log;

public class Demo {
    String TAG="AndroidSven Demo";
    static {
        System.loadLibrary("jnidemo");
   }

    public native int nativeOpen();	//以native 申明JNI函数
    public native String displayString();
    public Demo(){
        Log.d(TAG,"get from jni = ");
        Log.d(TAG,"get from jni = "+nativeOpen());
    }
}
