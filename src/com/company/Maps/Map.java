package com.company.Maps;

import com.company.Force;
import javafx.scene.image.Image;

public interface Map
{

    Image getBackground();

    int getCurrentLevel();

    void increaseCurrentLevel();

    Force[] getForces();
}
