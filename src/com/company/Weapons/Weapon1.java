package com.company.Weapons;


import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Weapon1 implements Weapon
{
    Image pic;
    final int speed = 1; //TODO: Work around with this value
    final int ID = 1;
    final String name = "Dragunov SVD";


    public Weapon1()
    {
        try
        {
            pic = new Image(new FileInputStream("src\\com\\company\\resources\\weapons\\1.png"));
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
