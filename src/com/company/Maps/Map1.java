package com.company.Maps;

import com.company.Constants;
import com.company.Force;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static com.company.Constants.MAP_WIDTH;

public class Map1 implements Map
{
    Image bq;
    Image forceBox;
    Force[] forces;
    int currentLevel;
    final int mapDifficultyConstant = 1; // TODO: Fix this later by doing test


    public Map1()
    {
        //LOAD MAP BACKGROUND
        try
        {
            bq = new Image(new FileInputStream("src\\com\\company\\resources\\maps\\1.jpg"));
        } catch (FileNotFoundException ex)
        {
            throw new AssertionError();
        }

        //LOAD FORCE BOX
        if (Constants.DEBUG)
        {
            try
            {
                forceBox = new Image(new FileInputStream("src\\com\\company\\resources\\forceBox.png"));
            } catch (FileNotFoundException ex)
            {
                throw new AssertionError();
            }
        }


        currentLevel = 3; //TODO: Change later

        forces = new Force[10];

        //Sets the positions of the forces randomly.
        for (int i = 0; i < 8; i++)
        {
            forces[i] = new Force();
            forces[i].setPosition((double) (Constants.GAME_WIDTH + 100 + i*670),(double)(forces[i].isUpwards() ? 0:
                    Constants
                    .GAME_HEIGHT));

            forces[i].setWidthAndHeight(50,Constants.GAME_HEIGHT);

            if (forces[i].isUpwards())
            {
                forces[i].setPower((mapDifficultyConstant + randomWithRange(1,10)) /20.0 );
            }
            else
            {
                forces[i].setPower(-((mapDifficultyConstant + randomWithRange(1,10)) /20.0 ) );
            }

            forces[i].setImage(forceBox);
        }

        //Set Gravity
        forces[9] = new Force(mapDifficultyConstant + 1,true); //TODO: FIX GRAVITY LATER
        forces[9].setWidthAndHeight(MAP_WIDTH - Constants.GAME_WIDTH,Constants.GAME_HEIGHT); //Gravity will take
        // place after 1 map width circle
    }

    public Image getBackground()
    {
        return bq;
    }

    public int getCurrentLevel(){
        return currentLevel;
    }

    public void increaseCurrentLevel(){
        currentLevel++;
    }

    int randomWithRange(int min, int max) // for [2,5] write 2,5
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    public Force[] getForces()
    {
        return forces;
    }
}
