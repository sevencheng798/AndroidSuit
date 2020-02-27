//
// Created by User on 2020/2/27.
//
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <jni.h>

#include "include/com_example_sven_javacallc_JNI.h"

/*
 *  把一个jstring转换成一个C语言的char*类型
 * */
char * _Jstring2CStr(JNIEnv *env, jstring jstr) {
    char *rtn = NULL;
    jclass clsstring = (*env)->FindClass(env, "java/lang/String");
    jstring strencode = (*env)->NewStringUTF(env, "GB2312");
    jmethodID mid = (*env)->GetMethodID(env, clsstring, "getBytes", "(Ljava/lang/String;)[B");
    jbyteArray barr = (jbyteArray)(*env)->CallObjectMethod(env, jstr, mid, strencode);
    jsize alen = (*env)->GetArrayLength(env, barr);
    jbyte *ba = (*env)->GetByteArrayElements(env, barr, JNI_FALSE);
    if(alen > 0) {
        rtn = (char *)malloc(alen+1); //'\0'
        memcpy(rtn, ba, alen);
        rtn[alen] = 0;
    }
    (*env)->ReleaseByteArrayElements(env, barr, ba, 0);
    return rtn;
}
/*
*  jint:  返回值
*  Java_全类名_方法名
*  JNIEnv: 里面有很多方法
* jobject: 谁调用了这个方法就是谁的事例
*/
JNIEXPORT jint JNICALL Java_com_example_sven_javacallc_JNI_add
        (JNIEnv *env, jobject jobj, jint jx, jint jy) {

    int sum = jx + jy;
    return sum;
}

/*
 *  从JAVA传入字符串， C代码进行拼接
 *
 *  @param jstr: I am from JAVA
 *          c: I am from C
 *  @return I am from JAVA add I am from C
 * */
JNIEXPORT jstring JNICALL Java_com_example_sven_javacallc_JNI_sayHello
        (JNIEnv *env, jobject jobj, jstring jstr) {
    char *fromJava = _Jstring2CStr(env, jstr);
    // C
    char *fromC="add I am from C";

    int length = strlen(fromJava) + strlen(fromC);
    char *megra = malloc(length);
    sprintf(megra, "%s%s", fromJava, fromC);
    //strcat(fromJava, fromC);

    //jstring     (*NewStringUTF)(JNIEnv*, const char*);
    jstring jmegra = (*env)->NewStringUTF(env, megra);
    free(megra);
    free(fromJava);
    return jmegra;
}

JNIEXPORT jintArray JNICALL Java_com_example_sven_javacallc_JNI_increaseArrayEles
        (JNIEnv *env, jobject jobj, jintArray jarray) {
    //1.得到数组长度
    //jsize       (*GetArrayLength)(JNIEnv*, jarray);
    int size = (*env)->GetArrayLength(env, jarray);
    //2.得到数组元素
    //jint*       (*GetIntArrayElements)(JNIEnv*, jintArray, jboolean*);
    jint *intArray = (*env)->GetIntArrayElements(env, jarray, JNI_FALSE);
    //3.遍历数组，给每个元素+10
    int i;
    for(i=0; i<size; i++) {
        //*(intArray+i) = *(intArray+i) + 10;
        *(intArray+i) += 10;
    }
    //4.返回结果
    return jarray;
}

JNIEXPORT jint JNICALL Java_com_example_sven_javacallc_JNI_checkPwd
        (JNIEnv *env, jobject jobj, jstring jstr) {
    char *origin = "123456";
    char *fromUser = _Jstring2CStr(env, jstr);

    //函数比较字符串是否相同
    int code = strcmp(origin, fromUser);
    free(fromUser);
    if(code == 0) {
        return 200;
    }else{
        return 400;
    }

}
