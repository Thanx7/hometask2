package com.epam.gomel.homework;

import org.testng.annotations.Factory;

public class SimpleTestFactory
{
    @Factory
    public Object[] factoryMethod()
    {
        return new Object[] {new GirlTest(0)};
    }
}
