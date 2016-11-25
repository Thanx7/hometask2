package com.epam.gomel.homework;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class BoyTest extends BaseConfigurationTest
{
    @Test(groups = {"boy"}, priority = 1)
    public void testGetMood()
    {
        Girl prettyGirl = new Girl(true);
        Boy boyExcellent = new Boy(Month.JUNE, Human.RICH, prettyGirl);
        Mood resultExcellent = boyExcellent.getMood();
        assertEquals(Mood.EXCELLENT, resultExcellent);

        Boy boyGood = new Boy(Month.JANUARY, Human.RICH, prettyGirl);
        Mood resultGood = boyGood.getMood();
        assertEquals(Mood.GOOD, resultGood);

        Boy boyNeutral = new Boy(Month.JUNE, Human.RICH);
        Mood resultNeutral = boyNeutral.getMood();
        assertEquals(Mood.NEUTRAL, resultNeutral);

        /* 1: isRich() */
        Boy boyRichBad = new Boy(Month.JANUARY, Human.RICH);
        Mood resultRichBad = boyRichBad.getMood();
        boolean a = boyRichBad.isRich();
        boolean b = boyRichBad.isPrettyGirlFriend();
        boolean c = boyRichBad.isSummerMonth();
        assertTrue(a);
        assertFalse(b);
        assertFalse(c);
        assertEquals(Mood.BAD, resultRichBad);

        /* 2: isPrettyGirlFriend() */
        Boy boyPrettyGirlFriendBad = new Boy(Month.JANUARY, Human.POOR, prettyGirl);
        Mood resultPrettyGirlFriendBad = boyPrettyGirlFriendBad.getMood();
        a = boyPrettyGirlFriendBad.isRich();
        b = boyPrettyGirlFriendBad.isPrettyGirlFriend();
        c = boyPrettyGirlFriendBad.isSummerMonth();
        assertFalse(a);
        assertTrue(b);
        assertFalse(c);
        assertEquals(Mood.BAD, resultPrettyGirlFriendBad);

        /* 3: isSummerMonth() */
        Boy boySummerMonthBad = new Boy(Month.JUNE);
        Mood resultSummerMonthBad = boySummerMonthBad.getMood();
        a = boySummerMonthBad.isRich();
        b = boySummerMonthBad.isPrettyGirlFriend();
        c = boySummerMonthBad.isSummerMonth();
        assertFalse(a);
        assertFalse(b);
        assertTrue(c);
        assertEquals(Mood.BAD, resultSummerMonthBad);

        Boy boyHorrible = new Boy(Month.JANUARY, Human.POOR);
        Mood resultHorrible = boyHorrible.getMood();
        assertEquals(Mood.HORRIBLE, resultHorrible);
    }

    @Test(groups = {"boy"}, dependsOnMethods = {"testGetMood"}, priority = 5)
    public void testSpendSomeMoney()
    {
        Boy boySmallA = new Boy(Month.JANUARY, Human.RICH);
        double smallAmountForSpending = Human.POOR;

        Boy boyLargeA = new Boy(Month.JANUARY, Human.POOR);
        double largeAmountForSpending = Human.RICH;

        boySmallA.spendSomeMoney(smallAmountForSpending);
        assertEquals(boySmallA.getWealth(), Human.RICH - smallAmountForSpending, 0.001);

        boolean secondAssertPassed = false;
        try
        {
            boyLargeA.spendSomeMoney(largeAmountForSpending);
        }
        catch (RuntimeException e)
        {
            final String msg = String.format("Not enough money! Requested amount is %s$ but you can't spend more then %s$",
                            largeAmountForSpending, boyLargeA.getWealth());
            assertEquals(msg, e.getMessage());
            secondAssertPassed = true;
        }
        finally
        {
            assertTrue(secondAssertPassed);
        }
    }

    @Test(groups = {"boy"}, priority = 4)
    public void testIsSummerMonth()
    {
        for (int i = 1; i <= 12; i++)
        {
            Boy boy = new Boy(Month.values()[i - 1]);
            if (i > 5 & i < 9)
            {
                assertEquals(true, boy.isSummerMonth());
            }
            else
            {
                assertEquals(false, boy.isSummerMonth());
            }
        }
    }

    @Test(groups = {"boy"}, priority = 3)
    public void testIsRich()
    {
        Boy richBoy = new Boy(Month.JANUARY, Human.RICH);
        assertEquals(richBoy.isRich(), true);

        Boy poorBoy = new Boy(Month.JANUARY, Human.POOR);
        assertEquals(poorBoy.isRich(), false);
    }

    @Test(groups = {"boy"}, priority = 2)
    public void testIsPrettyGirlFriend()
    {
        Boy boyWithoutGirl = new Boy(Month.JANUARY);
        assertFalse(boyWithoutGirl.isPrettyGirlFriend());

        Girl noPrettyGirl = new Girl(false);
        Boy boyWithoutPrettyGirl = new Boy(Month.JANUARY, Human.RICH, noPrettyGirl);
        assertFalse(boyWithoutPrettyGirl.isPrettyGirlFriend());

        Girl prettyGirl = new Girl(true);
        Boy boyWithPrettyGirl = new Boy(Month.JANUARY, Human.RICH, prettyGirl);
        assertTrue(boyWithPrettyGirl.isPrettyGirlFriend());
    }
}
