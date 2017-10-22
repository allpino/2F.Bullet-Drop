package com.company.Weapons;


import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Weapon4 implements Weapon
{
    Image pic;
    final int speed = 40; //TODO: Work around with this value
    final int ID = 4;
    final String name = "Barrett M82";

    public Weapon4()
    {
        try
        {
            pic = new Image(new FileInputStream("src\\com\\company\\resources\\weapons\\4.png"));
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
