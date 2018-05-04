#include "com_derik_demo_c_third_jnitest_MyJniClass.h"

JNIEXPORT jstring JNICALL Java_com_derik_demo_c_1third_jnitest_MyJniClass_getString
        (JNIEnv *env, jobject obj, jstring js){
    return js;
}