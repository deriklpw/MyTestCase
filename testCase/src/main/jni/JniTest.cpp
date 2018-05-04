//
// Created by derik on 17-1-10.
//
#include "com_derik_demo_c_third_jnitest_JniTestActivity.h"
JNIEXPORT jint JNICALL Java_com_derik_demo_c_1third_jnitest_JniTestActivity_Add
  (JNIEnv *env, jobject obj, jdouble num1, jdouble num2){
  return (num1 + num2);
  }


JNIEXPORT jint JNICALL Java_com_derik_demo_c_1third_jnitest_JniTestActivity_Sub
  (JNIEnv *env, jobject obj, jdouble num1, jdouble num2){
  return (num1 - num2);
  }


JNIEXPORT jint JNICALL Java_com_derik_demo_c_1third_jnitest_JniTestActivity_Mul
  (JNIEnv *env, jobject obj, jdouble num1, jdouble num2){
  return (num1 * num2);
  }


JNIEXPORT jint JNICALL Java_com_derik_demo_c_1third_jnitest_JniTestActivity_Div
  (JNIEnv *env, jobject obj, jdouble num1, jdouble num2){
  return (num1 / num2);
  }

