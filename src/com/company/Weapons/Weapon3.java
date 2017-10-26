package com.company.Weapons;


import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Weapon3 implements Weapon
{
    Image pic;
    final int speed = 3; //TODO: Work around with this value
    final int ID = 3;
    final String name = "R700";

    public Weapon3()
    {
        try
        {
            pic = new Image(new FileInputStream("src\\com\\company\\resources\\weapons\\3.png"));
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
