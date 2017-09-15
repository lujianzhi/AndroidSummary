package com.example.lawson.androidsummery.junittest;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Ian.Lu on 2017/9/15.
 * Project : AndroidSummary
 */
public class JUnitTestTest {

    private double a;
    private double b;
    private JUnitTest junitTest;

    @Before
    public void setUp() throws Exception {
        a = 4.0;
        b = 8.0;
        junitTest = new JUnitTest();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void add() throws Exception {
        TestCase.assertEquals(12.0, junitTest.add(a, b));
    }

    @Test
    public void sub() throws Exception {
        TestCase.assertEquals(-4d, junitTest.sub(a, b));
    }

    @Test
    public void mul() throws Exception {
        TestCase.assertEquals(32, junitTest.mul(a, b));
    }

    @Test
    public void div() throws Exception {
        TestCase.assertEquals(0.5, junitTest.div(a, b));
    }

}