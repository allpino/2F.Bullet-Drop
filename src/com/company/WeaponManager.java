package com.company;

import com.company.Weapons.*;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class WeaponManager
{
    Weapon curWeapon;
    Weapon selectedWeapon;
    Weapon[] weapons;

    int weapPosHeight;
    double targetPositionX;
    double targetPositionY;
    Image targetImage;

    final int NUM_OF_WEAPONS = 5;

    public WeaponManager()
    {
        selectedWeapon = null;

        weapons = new Weapon[NUM_OF_WEAPONS];
        weapons[0] = new Weapon1();
        weapons[1] = new Weapon2();
        weapons[2] = new Weapon3();
        weapons[3] = new Weapon4();
        weapons[4] = new Weapon5();

        curWeapon = weapons[0];

        weapPosHeight = 250;


        try
        {
            targetImage = new Image(new FileInputStream("src\\com\\company\\resources\\target.png"));
        } catch (FileNotFoundException ex)
        {
            throw new AssertionError("file could not be loaded");
        }

        targetPositionX = Constants.MAP_WIDTH-targetImage.getWidth();
        targetPositionY = (double)randomWithRange(0,Constants.GAME_HEIGHT - (int)targetImage.getHeight());
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
            if (curWeapon.getId() == NUM_OF_WEAPONS)
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

    public void resetSettings()
    {
        if (weapons[0] == null)
        {
            throw new AssertionError("Weapon array is empty!");
        }
        else
        {
            selectedWeapon = null;
            curWeapon = weapons[0];

            targetPositionX = Constants.MAP_WIDTH-targetImage.getWidth();
            targetPositionY = (double)randomWithRange((int)targetImage.getHeight(),Constants.GAME_HEIGHT  - (int)targetImage
                    .getHeight());
        }
    }

    public void newTargetPosition()
    {
        targetPositionX = Constants.MAP_WIDTH-targetImage.getWidth();
        targetPositionY = (double)randomWithRange(0,Constants.GAME_HEIGHT - (int)targetImage.getHeight());
    }

    public void decreaseWeapPosHeight()
    {
        if (weapPosHeight < Constants.GAME_HEIGHT- 200)
        {
            weapPosHeight++;
        }
    }

    public void increaseWeapPosHeight()
    {
        if (weapPosHeight > 0 )
        {
            weapPosHeight--;
        }
    }

    public int getWeapPosHeight()
    {
        return weapPosHeight;
    }

    public Weapon getCurWeapon()
    {
        return curWeapon;
    }


    public double getTargetPositionX()
    {
        return targetPositionX;
    }

    public double getTargetPositionY()
    {
        return targetPositionY;
    }

    public Image getTargetImage()
    {
        return targetImage;
    }

    int randomWithRange(int min, int max) // for [2,5] write 2,5
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }
}
