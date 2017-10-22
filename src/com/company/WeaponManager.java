package com.company;

import com.company.Weapons.*;

public class WeaponManager
{
    Weapon curWeapon;
    Weapon selectedWeapon;
    Weapon[] weapons;

    final int NUM_OF_WEAPONS = 4;

    public WeaponManager()
    {

        selectedWeapon = null;

        weapons = new Weapon[NUM_OF_WEAPONS];
        weapons[0] = new Weapon1();
        weapons[1] = new Weapon2();
        weapons[2] = new Weapon3();
        weapons[3] = new Weapon4();

        curWeapon = weapons[0];
    }

    public Weapon getSelectedWeapon()
    {
        return selectedWeapon;
    }

    public void requestNextWeapon()
    {
        if (curWeapon == null)
        {
            throw new AssertionError("requesting next weapon before declaring curWeapon");
        }
        else
        {
            if (curWeapon.getId() == 4)
            {
                curWeapon = weapons[0];
            }
            else
            {
                curWeapon = weapons[curWeapon.getId()];
            }
        }
    }

    public void requestPreviousWeapon()
    {
        if (curWeapon == null)
        {
            throw new AssertionError("requesting next weapon before declaring curWeapon");
        }
        else
        {
            if (curWeapon.getId() == 1)
                curWeapon = weapons[NUM_OF_WEAPONS-1];
            else
            {
                curWeapon = weapons[curWeapon.getId()-2];
            }
        }
    }

    public void setSelectedWeapon(Weapon wep)
    {
        if (wep == null)
        {
            throw new AssertionError("selected weapon is null");
        }
        else
        {
            selectedWeapon = wep;
        }
    }

    public Weapon getCurWeapon()
    {
        return curWeapon;
    }
}
