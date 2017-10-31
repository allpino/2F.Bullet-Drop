package com.company;

import com.company.Maps.*;

import java.util.HashMap;

public class MapManager
{
    private  Map currentMap;
    final int NUM_OF_MAPS = 4;
    final int NUM_OF_LEVELS = 3;

    public MapManager()
    {
        setCurrentMap(1);
    }

    public Map getCurrentMap()
    {
        return currentMap;
    }

    public Map createRandomMap()
    {
        int rand = randomWithRange(1,NUM_OF_MAPS);
        if (rand == 1)
        {
            Map1 map1 = new Map1();
            currentMap = map1;
            return currentMap;
        }//TODO: Do the rest
        else
        {
            return currentMap;
        }
    }

    public void setCurrentMap(int map)
    {
        if (map == 1)
        {
            Map1 map1 = new Map1();
            currentMap = map1;
        }
        else if (map == 2)
        {
            Map2 map2 = new Map2();
            currentMap = map2;
        }
        else if (map == 3)
        {
            Map3 map3 = new Map3();
            currentMap = map3;
        }
        else if (map == 4)
        {
            Map4 map4 = new Map4();
            currentMap = map4;
        }
        else
        {
            throw new AssertionError("Wrong map number!");
        }
    }

    public HashMap<Integer,Force> getCurrentLevelForces()
    {
        HashMap<Integer,Force> list = new HashMap<>();

        if (currentMap == null)
        {
            throw new AssertionError("Current map is null");
        }
        else
        {
            if (currentMap.getCurrentLevel() == 1)
            {
                return null;
            }
            else if (currentMap.getCurrentLevel() == 2)
            {
                list.put(0,currentMap.getForces()[9]);
            }
            else if (currentMap.getCurrentLevel() == 3)
            {
                Force[] force = currentMap.getForces();
                for (int i = 0; i < force.length-1; i++)
                {
                    list.put(i+1,force[i]);

                }
                if (force[9].isGravity)
                {
                    list.put(0,force[9]);
                }
                else
                {
                    throw new AssertionError("No gravity in level 3");
                }
            }
            return list;
        }



    }

    public int getNumOfMaps()
    {
        return NUM_OF_MAPS;
    }

    public void resetSettings()
    {
        if (currentMap.getID() != 1)
        {
            setCurrentMap(1);
        }
    }

    public int getNumOfLevels(){return NUM_OF_LEVELS;}

    int randomWithRange(int min, int max) // for [2,5] write 2,5
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }



}
