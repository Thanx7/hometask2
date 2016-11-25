package com.epam.gomel.homework;

import java.util.Arrays;
import java.util.List;

import org.testng.TestNG;

public class TestRunnerWithThreads
{

    public static void main(String[] args)
    {
        TestNG testNG = new TestNG();
        testNG.addListener(new TestListener());
        List<String> files = Arrays.asList("./src/test/resources/suites/testsWithThreads.xml");
        testNG.setTestSuites(files);
        testNG.run();
    }
}
