package com.epam.gomel.homework;

import java.util.Arrays;
import java.util.List;

import org.testng.TestNG;

public class TestRunner
{

    public static void main(String[] args)
    {
        TestNG testNG = new TestNG();
        testNG.addListener(new TestListener());
        List<String> files = Arrays.asList("./src/test/resources/suites/tests.xml");
        testNG.setTestSuites(files);
        testNG.run();
    }
}
