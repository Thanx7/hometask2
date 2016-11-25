package com.epam.gomel.homework;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseConfigurationTest
{

    @BeforeClass
    public void beforeClass()
    {
        long id = Thread.currentThread().getId();
        System.out.println("Before test-class. Thread id is: " + id);
    }

    @AfterClass
    public void afterClass()
    {
        long id = Thread.currentThread().getId();
        System.out.println("After test-class. Thread id is: " + id);
    }

}
