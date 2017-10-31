package com.company;

import javafx.scene.image.Image;
import javafx.geometry.Rectangle2D;

public class Sprite
{
    private Image image;
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;

    public Sprite()
    {
        positionX = 0;
        positionY = 0;
        velocityX = 0;
        velocityY = 0;
    }

    public void setImage(Image i)
    {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    public Image getImage()
    {
        return image;
    }

    public void setWidthAndHeight(int width, int height)
    {
        if (image != null && !Constants.DEBUG)
        {
            throw new AssertionError("You should not set custom width with image uploaded");
        }
        else
        {
            this.width = width;
            this.height = height;
        }
    }

    public void setPosition(double x, double y)
    {
        positionX = x;
        positionY = y;
    }

    public void setVelocity(double x, double y)
    {
        velocityX = x;
        velocityY = y;
    }

    public void addVelocity(double x, double y)
    {
        velocityX += x;
        velocityY += y;
    }

    public double getPositionX()
    {
        return positionX;
    }

    public double getPositionY()
    {
        return positionY;
    }

    public void calculatePosition(double time)
    {
        positionX += velocityX * time;
        positionY += velocityY * time;
    }

    public double getVelocityX()
    {
        return velocityX;
    }

    public double getVelocityY()
    {
        return velocityY;
    }

    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(positionX,positionY,width,height);
    }

    public boolean intersectsWithForce(Sprite s)
    {
        Rectangle2D force  = s.getBoundary();
        Rectangle2D bullet = this.getBoundary();

        return force.getMinX() < bullet.getMinX() && force.getMaxX() > bullet.getMaxX();
    }

    public boolean intersectsWithTarget(Sprite s)
    {
        Rectangle2D target = s.getBoundary();
        Rectangle2D bullet = this.getBoundary();

        return bullet.intersects(target);
    }

    public String toString()
    {
        return " Position: [" + positionX + "," + positionY + "]"
                + " Velocity: [" + velocityX + "," + velocityY + "]";
    }
}