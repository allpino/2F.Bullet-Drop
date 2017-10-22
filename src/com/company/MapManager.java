package com.company;

import com.company.Maps.Map;
import com.company.Maps.Map1;

public class MapManager
{
    Map currentMap;
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

    public Force[] getCurrentLevelForces()
    {
        return currentMap.getForces();
    }

    int randomWithRange(int min, int max) // for [2,5] write 2,5
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }



}
