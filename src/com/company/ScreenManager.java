package com.company;

import com.company.Screens.Screen;
import javafx.scene.Group;

public class ScreenManager
{
    //Variables
    Group curScreen;


    public ScreenManager(){

        curScreen = null;
    }

    public void setCurScreen(Group screen)
    {
        curScreen = screen;
    }

    public Group getCurScreen()
    {
        return curScreen;
    }

    public void Update(){

        ((Screen)curScreen).Update();
    }

}
