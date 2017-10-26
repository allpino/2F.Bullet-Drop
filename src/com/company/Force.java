package com.company;


public class Force extends Sprite
{
    double power;
    boolean isUpwards;
    boolean isGravity;

    public Force()
    {
        power = 0;
        isUpwards = randomWithRange(0,1) == 0 ? false : true;
        isGravity = false;
    }

    public Force(int pow, boolean isGravity)
    {
        this.power = pow;
        this.isUpwards = false;
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

    public boolean isUpwards()
    {
        return isUpwards;
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
