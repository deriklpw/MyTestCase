package com.derik.demo.tools;

/**
 * Created by derik on 18-8-3.
 */

public class A<T1, T2, K1, K2> implements TestInterface<K1, K2> {
    T1 t1;
    T2 t2;

    A(T1 t1, T2 t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    public T1 getT1() {
        return t1;
    }

    public void setT1(T1 t1) {
        this.t1 = t1;
    }

    public T2 getT2() {
        return t2;
    }

    public void setT2(T2 t2) {
        this.t2 = t2;
    }


    public void test(T1 t1, T2 t2) {
        System.out.println("A.test t1=" + t1 + ", t2=" + t2);
    }

    public static <S1, S2> S2 test2(S1 s1, S2 s2) {
        if (s1 != null) {
            return s2;
        }
        return null;
    }

    @Override
    public void onResponse(K1 k1, K2 k2) {

    }
}

interface TestInterface<K1, K2> {
    void onResponse(K1 k1, K2 k2);
}
