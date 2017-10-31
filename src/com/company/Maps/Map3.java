package com.company.Maps;

import com.company.Constants;
import com.company.Force;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static com.company.Constants.MAP_WIDTH;

public class Map3 implements Map
{
    Image bq;
    Image forceBox;
    Force[] forces;
    int currentLevel;
    final int mapDifficultyConstant = 3; // TODO: Find good constants to differentiate between maps
    final int ID = 3;


    public Map3()
    {
        //LOAD MAP BACKGROUND
        try
        {
            bq = new Image(new FileInputStream("src\\com\\company\\resources\\maps\\3.jpg"));
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


        currentLevel = 1;

        if (currentLevel < 1 || currentLevel >3)
        {
            throw new AssertionError("wrong current level init");
        }

        forces = new Force[10];

        //Sets the positions of the forces randomly.
        for (int i = 0; i < forces.length-1; i++)
        {
            forces[i] = new Force();
            forces[i].setPosition((double) (Constants.GAME_WIDTH + 100 + i*670),(double)(forces[i].isDownwards() ? 0:
                    Constants
                            .GAME_HEIGHT));

            if (forces[i].isDownwards())
            {
                //TODO: Maybe to find good balance change 10 or 20 or both and same with negative force
                forces[i].setPower(-((mapDifficultyConstant + randomWithRange(1,10)) /20.0 ));
            }
            else
            {
                forces[i].setPower(+((mapDifficultyConstant + randomWithRange(1,10)) /20.0 ) );
            }

            forces[i].setImage(forceBox);
            forces[i].setWidthAndHeight(120,Constants.GAME_HEIGHT);
        }

        //Set Gravity
        //TODO: Make sure gravity is noticable power in each map. Find good value for 200
        forces[9] = new Force(mapDifficultyConstant/200.0,true);
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

    public int getID(){ return ID;}
    public Force[] getForces()
    {
        return forces;
    }
}
