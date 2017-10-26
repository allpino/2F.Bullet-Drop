package com.company.Weapons;


import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Weapon2 implements Weapon
{
    Image pic;
    final int speed = 2; //TODO: Work around with this value
    final int ID = 2;
    final String  name = "M40A3";

    public Weapon2()
    {
        try
        {
            pic = new Image(new FileInputStream("src\\com\\company\\resources\\weapons\\2.png"));
        } catch (FileNotFoundException ex)
        {
            throw new AssertionError("picture could not be loaded");
        }
    }


    public Image getPic()
    {
        return pic;
    }

    public int getSpeed()
    {
        return speed;
    }

    @Override
    public int getId()
    {
        return ID;
    }

    @Override
    public String getName()
    {
        return name;
    }
}
