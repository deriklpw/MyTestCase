package com.derik.myapps.junitTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    private Calculator mCalculator;
    private double a;
    private double b;
    private double delta;

    @Before
    public void setUp() throws Exception {
        mCalculator = new Calculator();
        a = 45;
        b = 12;
        delta = 0.3;
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void sum() throws Exception {
        System.out.println("sum: " + mCalculator.sum(a, b));
        assertEquals(57.2, mCalculator.sum(a, b), delta);
    }

    @Test
    public void subtract() throws Exception {
        System.out.println("sub: " + mCalculator.subtract(a, b));
    }

    @Test
    public void divide() throws Exception {
        System.out.println("div: " + mCalculator.divide(a, b));
    }

    @Test
    public void multiply() throws Exception {
        System.out.println("mul: " + mCalculator.multiply(a, b));
    }

    @Test
    public void myTestFunction() throws Exception {
        mCalculator.myTestFunction();
    }

}