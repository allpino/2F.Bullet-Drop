package com.company;

import com.company.Maps.Map;
import com.company.Maps.Map1;

import java.util.ArrayList;
import java.util.HashMap;

public class MapManager
{
    private  Map currentMap;
    final int NUM_OF_MAPS = 4;

    public MapManager()
    {
        currentMap = null;
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
        //TODO: DO the rest
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
            if (currentMap.getCurrentLevel() == 0)
            {
                return null;
            }
            else if (currentMap.getCurrentLevel() == 1)
            {
                list.put(0,currentMap.getForces()[9]);
            }
            else if (currentMap.getCurrentLevel() == 2)
            {
                for (Force force: currentMap.getForces())
                {
                    int i = 1;
                    if (force.isGravity)
                    {
                        list.put(0,force);
                    }
                    else
                    {
                        list.put(i,force);
                        i++;
                    }
                }
            }
            return list;
        }



    }

    int randomWithRange(int min, int max) // for [2,5] write 2,5
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }



}
