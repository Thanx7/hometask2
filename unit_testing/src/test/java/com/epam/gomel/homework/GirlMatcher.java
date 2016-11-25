package com.epam.gomel.homework;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class GirlMatcher
{

    public static void assertThat(Mood excellent, Matcher matches)
    {
    }

    public static Matcher matches(final Mood expected)
    {
        return new BaseMatcher()
        {
            protected Mood theExpected = expected;

            public boolean matches(Object o)
            {
                return theExpected.equals(o);
            }

            public void describeTo(Description description)
            {
                description.appendText(theExpected.toString());
            }
        };
    }

    public static void assertThat(double excellent, Matcher matches)
    {
    }

    public static Matcher matches(int i)
    {
        return new BaseMatcher()
        {
            protected Integer theExpected = i;

            public boolean matches(Object o)
            {
                return theExpected.equals(o);
            }

            public void describeTo(Description description)
            {
                description.appendText(theExpected.toString());
            }
        };
    }
}
