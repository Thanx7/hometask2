package com.epam.gomel.homework;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GirlTest extends BaseConfigurationTest
{
    private Boy boyRich;
    private Boy boyPoor;
    private Boy boyRichWinter;
    private Boy boyPoorWinter;
    private Girl girlExcellent;
    private Girl girlGood;
    private Girl girlNeutral;
    private Girl girlRichBad;
    private Girl girlHorriblePretty;
    private Girl girlHorribleBoyFriendWillBuyNewShoes;
    private Girl girlHorribleSlimFriendBecameFat;
    private Girl girlHate;

    private int param;

    public GirlTest(int param)
    {
        this.param = param;
    }

    @BeforeMethod(groups = {"girl"})
    public void beforeMethod()
    {
        boyRich = new Boy(Month.JUNE, Human.RICH);
        boyPoor = new Boy(Month.JUNE, Human.POOR);
        boyRichWinter = new Boy(Month.JANUARY, Human.RICH, new Girl(true, true));
        boyPoorWinter = new Boy(Month.JANUARY, Human.POOR);
        girlExcellent = new Girl(true, true, boyRich);
        girlGood = new Girl(true, false, boyRich);
        girlNeutral = new Girl(true, false, boyRichWinter);
        girlRichBad = new Girl(true, true);
        girlHorriblePretty = new Girl(true, false, boyPoor);
        girlHorribleBoyFriendWillBuyNewShoes = new Girl(false, false, boyPoor);
        girlHorribleSlimFriendBecameFat = new Girl(false, false, boyPoorWinter);
        girlHate = new Girl(false, false);
    }

    @Test(groups = {"girl"})
    public void testGetMood()
    {
        Girl girlExcellent = new Girl(true, true, boyRich);
        Mood resultExcellent = girlExcellent.getMood();
        GirlMatcher.assertThat(Mood.EXCELLENT, GirlMatcher.matches(resultExcellent));

        Mood resultGood = girlGood.getMood();
        GirlMatcher.assertThat(Mood.GOOD, GirlMatcher.matches(resultGood));

        Mood resultNeutral = girlNeutral.getMood();
        GirlMatcher.assertThat(Mood.NEUTRAL, GirlMatcher.matches(resultNeutral));

        Mood resultRichBad = girlRichBad.getMood();
        GirlMatcher.assertThat(Mood.BAD, GirlMatcher.matches(resultRichBad));

        /* 1: isPretty() */
        Mood resultHorriblePretty = girlHorriblePretty.getMood();
        boolean a = girlHorriblePretty.isPretty();
        boolean b = girlHorriblePretty.isBoyfriendRich();
        boolean c = girlHorriblePretty.isBoyFriendWillBuyNewShoes();
        boolean d = girlHorriblePretty.isSlimFriendBecameFat();
        assertTrue(a);
        assertFalse(b);
        assertFalse(c);
        assertFalse(d);
        assertEquals(Mood.HORRIBLE, resultHorriblePretty);

        /* 2: isBoyfriendRich() */
        Girl girlHorribleBoyfriendRich = new Girl(false, false, boyRich);
        Mood resultHorribleBoyfriendRich = girlHorribleBoyfriendRich.getMood();
        a = girlHorribleBoyfriendRich.isPretty();
        b = girlHorribleBoyfriendRich.isBoyfriendRich();
        c = girlHorribleBoyfriendRich.isBoyFriendWillBuyNewShoes();
        d = girlHorribleBoyfriendRich.isSlimFriendBecameFat();
        assertFalse(a);
        assertTrue(b);
        assertFalse(c);
        assertFalse(d);
        assertEquals(Mood.HORRIBLE, resultHorribleBoyfriendRich);

        /* 3: isBoyFriendWillBuyNewShoes() */
        Mood resultHorribleBoyFriendWillBuyNewShoes = girlHorribleBoyFriendWillBuyNewShoes.getMood();
        a = girlHorribleBoyFriendWillBuyNewShoes.isPretty();
        b = girlHorribleBoyFriendWillBuyNewShoes.isBoyfriendRich();
        c = girlHorribleBoyFriendWillBuyNewShoes.isBoyFriendWillBuyNewShoes();
        d = girlHorribleBoyFriendWillBuyNewShoes.isSlimFriendBecameFat();
        assertFalse(a);
        assertFalse(b);
        assertTrue(c);
        assertFalse(d);
        assertEquals(Mood.HORRIBLE, resultHorribleBoyFriendWillBuyNewShoes);

        /* 4: isSlimFriendBecameFat() */
        Mood resultHorribleSlimFriendBecameFat = girlHorribleSlimFriendBecameFat.getMood();
        a = girlHorribleSlimFriendBecameFat.isPretty();
        b = girlHorribleSlimFriendBecameFat.isBoyfriendRich();
        c = girlHorribleSlimFriendBecameFat.isBoyFriendWillBuyNewShoes();
        d = girlHorribleSlimFriendBecameFat.isSlimFriendBecameFat();
        assertFalse(a);
        assertFalse(b);
        assertFalse(c);
        assertTrue(d);
        assertEquals(Mood.HORRIBLE, resultHorribleSlimFriendBecameFat);

        Mood resultHate = girlHate.getMood();
        a = girlHate.isPretty();
        b = girlHate.isBoyfriendRich();
        c = girlHate.isBoyFriendWillBuyNewShoes();
        d = girlHate.isSlimFriendBecameFat();
        assertFalse(a);
        assertFalse(b);
        assertFalse(c);
        assertFalse(d);
        assertEquals(Mood.I_HATE_THEM_ALL, resultHate);
    }

    @Test(groups = {"girl"})
    public void testSpendBoyFriendMoney()
    {
        /* Boyfriend is rich */
        girlExcellent.spendBoyFriendMoney(Human.RICH);
        GirlMatcher.assertThat(boyRich.getWealth(), GirlMatcher.matches(0));

        /* Boyfriend is poor */
        girlHorriblePretty.spendBoyFriendMoney(Human.POOR);
        GirlMatcher.assertThat(boyPoor.getWealth(), GirlMatcher.matches(Human.POOR));
    }

    @Test(groups = {"girl"})
    public void testIsBoyfriendRich()
    {
        /* getBoyFriend() == null */
        assertFalse(girlRichBad.isBoyfriendRich());
        /* !getBoyFriend().isRich() */
        assertFalse(girlHorriblePretty.isBoyfriendRich());
        /* getBoyFriend() != null && getBoyFriend().isRich() */
        assertTrue(girlExcellent.isBoyfriendRich());
    }

    @Test(groups = {"girl"})
    public void testIsBoyFriendWillBuyNewShoes()
    {
        /* isPretty() == true */

        /* isBoyfriendRich() == false */
        assertFalse(girlHorriblePretty.isBoyFriendWillBuyNewShoes());

        /* isBoyfriendRich() == true */
        /* getBoyFriend().getMood() == Mood.EXCELLENT */
        assertTrue(girlExcellent.isBoyFriendWillBuyNewShoes());
        /* isBoyfriendRich() == true */
        /* getBoyFriend().getMood() != Mood.EXCELLENT */
        Boy boyGood = new Boy(Month.JANUARY, Human.RICH, new Girl(true));
        Girl girlBoyGood = new Girl(true, false, boyGood);
        assertTrue(boyGood.getMood() == Mood.GOOD);
        assertFalse(girlBoyGood.isBoyFriendWillBuyNewShoes());
        // Boy boyNeutral is impossible because he is with girl
        // Boy boyBad is impossible because he is without girl
        // Boy boyHorrible is impossible if isBoyfriendRich() == true

        /* isPretty() == false */

        /* getBoyFriend() == null */
        assertFalse(girlHate.isBoyFriendWillBuyNewShoes());
        /* getBoyFriend() != null */
        /* getBoyFriend().getMood() == Mood.BAD */
        assertTrue(girlHorribleBoyFriendWillBuyNewShoes.getBoyFriend().getMood() == Mood.BAD);
        assertTrue(girlHorribleBoyFriendWillBuyNewShoes.isBoyFriendWillBuyNewShoes());
        /* getBoyFriend() != null */
        /* getBoyFriend().getMood() == Mood.HORRIBLE */
        assertTrue(girlHorribleSlimFriendBecameFat.getBoyFriend().getMood() == Mood.HORRIBLE);
        assertFalse(girlHorribleSlimFriendBecameFat.isBoyFriendWillBuyNewShoes());
        /* getBoyFriend() != null */
        /* getBoyFriend().getMood() == Mood.NEUTRAL */
        // Boy boyNeutral is impossible because he is with girl

    }

    @Test(groups = {"girl"}, dataProvider = "girlData")
    public void testIsSlimFriendBecameFat(Girl girlHateFromDP)
    {
        /* getBoyFriend() == null */

        /* isPretty() == false */
        assertFalse(girlHateFromDP.isSlimFriendBecameFat()); /* dataprovider used here */
        /* isPretty() == true */
        /* isSlimFriendGotAFewKilos() == true */
        assertTrue(girlRichBad.isSlimFriendBecameFat());

        /* isPretty() == false */

        /* getBoyFriend() == null */
        assertFalse(girlHateFromDP.isSlimFriendBecameFat()); /* dataprovider used here also */
        /* getBoyFriend() != null */
        /* getBoyFriend().getMood() == Mood.HORRIBLE */
        assertTrue(girlHorribleSlimFriendBecameFat.isSlimFriendBecameFat());
        /* getBoyFriend() != null */
        /* getBoyFriend().getMood() == Mood.BAD */
        assertFalse(girlHorribleBoyFriendWillBuyNewShoes.isSlimFriendBecameFat());
    }

    @DataProvider(name = "girlData")
    public Object[][] getGirlData()
    {
        return new Object[][] {{girlHate}};
    }

    @Test(groups = {"e"})
    @Parameters("testName")
    public void parameterAndFactory(String testName)
    {
        System.out.println("Parameterized value is : " + testName); /* parameterization used here */
        System.out.println("Factory index is : " + param); /* factory used here */
    }
}
