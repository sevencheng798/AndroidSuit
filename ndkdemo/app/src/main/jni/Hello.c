//
// Created by User on 2020/2/26.
//

#include <stdio.h>
#include <stdlib.h>
#include <jni.h>

/*
*  jstring:  返回值
*  Java_全类名_方法名
*  JNIEnv: 里面有很多方法
* jobject: 谁调用了这个方法就是谁的事例
*/
jstring Java_com_example_sven_ndkdemo_JNI_sayHello(JNIEnv *env, jobject jobj) {
    char *text = "I am from c";
    return (*env)->NewStringUTF(env, text);
}