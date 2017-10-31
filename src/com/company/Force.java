package com.company;


public class Force extends Sprite
{
    double power;
    boolean isDownwards;
    boolean isGravity;

    public Force()
    {
        power = 0;
        isDownwards = randomWithRange(0,2) != 0;
        isGravity = false;
    }

    public Force(double pow, boolean isGravity)
    {
        this.power = pow;
        this.isDownwards = false;
        this.isGravity = isGravity;

        if (!isGravity)
        {
            throw new AssertionError("Dont use this constructor other than setting gravity");
        }
    }

    int randomWithRange(int min, int max) // for [2,5] write 2,5
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    public double getPower()
    {
        return power;
    }

    public boolean isDownwards()
    {
        return isDownwards;
    }

    public void setPower(double power)
    {
        this.power = power;
    }

    public boolean isGravity()
    {
        return isGravity;
    }
}
