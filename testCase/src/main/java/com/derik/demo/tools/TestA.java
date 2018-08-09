package com.derik.demo.tools;

/**
 * Created by derik on 18-8-3.
 */

public class TestA {
    public static void main(String[] args){
        A a = new A(1, 2);
        System.out.println("TestA.main t1=" + a.getT1() +",  t2=" +a.getT2());


        A b = new A("A", 3);
        System.out.println("TestA.main t1=" + b.getT1() +",  t2=" +b.getT2());

        A c = new A<>("A",45);

        try {
            A d = a.getClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
