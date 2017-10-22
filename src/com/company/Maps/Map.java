package com.company.Maps;

import com.company.Force;
import javafx.scene.image.Image;

public interface Map
{
    int MAP_WIDTH = 7680;

    Image getBackground();

    int getCurrentLevel();

    void increaseCurrentLevel();

    Force[] getForces();
}
