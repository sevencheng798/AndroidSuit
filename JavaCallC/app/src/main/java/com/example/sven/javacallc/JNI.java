package com.example.sven.javacallc;

/**
 *  Creator: @By Sven 2020-02-26
 *  作用： java调用对应的C代码
 **/
public class JNI {
    {
        System.loadLibrary("testJavaCallc");
    }
    /**
     *  让C代码做加法运算，把结果返回
     * @param x
     * @param y
     * @return
     * */
    public native int add(int x, int y);
    /* *
     *  从java传入字符串， C代码进行拼接，并返回
     *
     * @param s I am from java
     *  @return I am from java add I am from C
     */
    public native String sayHello(String s);
    /*
     * 让C代码给每个元素都加上10
     *
     * @param intArrays
     * @return
     * */
    public native int[] increaseArrayEles(int[] intArrays);
    /*
     *  应用：检查密码是否正确，如果正确返回200， 否则返回400
     * */
    public native int checkPwd(String pwd);
}
