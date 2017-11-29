package com.company;


import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Weapon
{
    Image pic;
    int speed;
    int ID;
    String name;


    public Weapon(String name, int ID, int speed)
    {
        this.name = name;
        this.ID = ID;
        this.speed = speed;


        try
        {
            pic = new Image(new FileInputStream("src\\com\\company\\resources\\weapons\\" + ID +".png"));
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

    public int getId()
    {
        return ID;
    }

    public String getName()
    {
        return name;
    }
}
