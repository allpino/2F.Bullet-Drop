package com.company;

import com.company.Maps.Map1;
import com.company.Screens.MenuScreen;
import com.company.Screens.Screen;
import com.company.Screens.SettingsScreen;
import javafx.scene.Group;

public class ScreenManager
{
    //Variables
    Group curScreen;
    Settings settings;

    //Screens
    MenuScreen menuScreen;
    SettingsScreen settingsScreen;


    public ScreenManager(Settings settings){

        this.settings = settings;
        if (settings == null)
        {
            throw new AssertionError();
        }
        menuScreen = new MenuScreen();
        curScreen = menuScreen;
    }

    public void setCurScreen(Group screen)
    {
        curScreen = screen;
    }

    public Group getCurScreen()
    {
        return curScreen;
    }

    public void Update()
    {
        //MENU SCREEN SETTINGS
        if (curScreen instanceof MenuScreen)
        {
            if (((MenuScreen) curScreen).isSwitchToSettingsScreen())
            {
                settingsScreen = new SettingsScreen(settings);
                setCurScreen(settingsScreen);
                menuScreen = null;
            }
            else if(((MenuScreen) curScreen).isSwitchToNewGameScreen())
            {
                //TODO: switch to new game
            }
        }
        else if(curScreen instanceof SettingsScreen)
        {
            if (((SettingsScreen) curScreen).isSwitchToMainMenuScreen())
            {
                menuScreen = new MenuScreen();
                setCurScreen(menuScreen);
                settingsScreen = null;
            }
            else if(((SettingsScreen) curScreen).isSwitchToHowToPlayScreen())
            {
                //TODO: switch to how to play screen
            }
        }


        ((Screen)curScreen).Update();
    }

}
