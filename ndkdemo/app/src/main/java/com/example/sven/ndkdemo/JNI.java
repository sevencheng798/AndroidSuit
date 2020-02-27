package com.example.sven.ndkdemo;

/**
 *  Creator: @By Sven 2020-02-26
 *  作用： java调用对应的C代码
 **/
public class JNI {
    {
        System.loadLibrary("Hello");
    }
    /* *
    *  定义native方法
    *  调用C代码对应的方法
    *  @return
    */
    public native String sayHello();
}
