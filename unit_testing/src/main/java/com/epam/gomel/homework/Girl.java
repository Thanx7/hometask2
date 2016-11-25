package com.epam.gomel.homework;

import lombok.Getter;
import lombok.Setter;

public class Girl extends Human
{

    @Getter
    private boolean isPretty;
    @Getter
    @Setter
    private Boy boyFriend;
    @Getter
    private boolean isSlimFriendGotAFewKilos;

    public Girl(boolean isPretty, boolean isSlimFriendGotAFewKilos, Boy boyFriend)
    {
        this.isPretty = isPretty;
        this.isSlimFriendGotAFewKilos = isSlimFriendGotAFewKilos;
        this.boyFriend = boyFriend;
        if (boyFriend != null)
        {
            this.boyFriend.setGirlFriend(this);
        }
    }

    public Girl(boolean isPretty, boolean isSlimFriendGotAFewKilos)
    {
        this(isPretty, isSlimFriendGotAFewKilos, null);
    }

    public Girl(boolean isPretty)
    {
        this(isPretty, false, null);
    }

    public Girl()
    {
        this(false, true, null);
    }

    @Override
    public Mood getMood()
    {
        if (isPretty() && isBoyfriendRich() && isBoyFriendWillBuyNewShoes() && isSlimFriendBecameFat())
        {
            return Mood.EXCELLENT;
        }
        else if (isPretty() && isBoyfriendRich() && isBoyFriendWillBuyNewShoes())
        {
            return Mood.GOOD;
        }
        else if (isPretty() && isBoyfriendRich())
        {
            return Mood.NEUTRAL;
        }
        else if (isPretty() && isSlimFriendBecameFat())
        {
            return Mood.BAD;
        }
        else if (isPretty() || isBoyfriendRich() || isBoyFriendWillBuyNewShoes() || isSlimFriendBecameFat())
        {
            return Mood.HORRIBLE;
        }
        return Mood.I_HATE_THEM_ALL;
    }

    public void spendBoyFriendMoney(double amountForSpending)
    {
        if (isBoyfriendRich())
        {
            getBoyFriend().spendSomeMoney(amountForSpending);
        }
    }

    public boolean isBoyfriendRich()
    {
        return getBoyFriend() != null && getBoyFriend().isRich();
    }

    public boolean isBoyFriendWillBuyNewShoes()
    {
        return isPretty() && isBoyfriendRich() && getBoyFriend().getMood() == Mood.EXCELLENT
                        || !isPretty() && getBoyFriend() != null && getBoyFriend().getMood() == Mood.BAD;
    }

    public boolean isSlimFriendBecameFat()
    {
        return isSlimFriendGotAFewKilos() && isPretty() || getBoyFriend() != null && getBoyFriend().getMood() == Mood.HORRIBLE;
    }
}
