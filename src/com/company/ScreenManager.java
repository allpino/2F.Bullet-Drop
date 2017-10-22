package com.company;


import com.company.Screens.MenuScreen;
import com.company.Screens.Screen;
import com.company.Screens.SettingsScreen;
import com.company.Screens.WeaponSelectionScreen;
import javafx.scene.Group;
import javafx.scene.image.Image;


public class ScreenManager
{
    //Variables
    Group curScreen;
    Settings settings;

    //Screens
    MenuScreen menuScreen;
    SettingsScreen settingsScreen;
    WeaponSelectionScreen weaponSelectionScreen;

    //Managers
    MapManager mapManager;
    WeaponManager weaponManager;


    public ScreenManager(Settings settings, MapManager mapManager, WeaponManager weaponManager)
    {

        menuScreen = new MenuScreen();
        curScreen = menuScreen;

        if (settings == null || mapManager == null || weaponManager == null)
        {
            throw new AssertionError("Settings or mapManager or weaponManager you passed is null");
        }
        else
        {
            this.settings = settings;
            this.mapManager = mapManager;
            this.weaponManager = weaponManager;
        }

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
                mapManager.setCurrentMap(1);
                Image bq = mapManager.getCurrentMap().getBackground();

                weaponSelectionScreen = new WeaponSelectionScreen(bq,weaponManager);
                setCurScreen(weaponSelectionScreen);
                menuScreen = null;


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
