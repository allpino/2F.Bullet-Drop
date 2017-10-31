package com.company;


import com.company.Screens.*;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.control.Label;
import javafx.scene.image.Image;


public class ScreenManager
{
    //Variables
    Group curScreen;
    Settings settings;
    ParallelCamera camera;

    //Screens
    MenuScreen menuScreen;
    SettingsScreen settingsScreen;
    WeaponSelectionScreen weaponSelectionScreen;
    WeaponPlacementScreen weaponPlacementScreen;
    FireScreen fireScreen;
    ResultScreen resultScreen;

    //Managers
    MapManager mapManager;
    WeaponManager weaponManager;


    public ScreenManager(Settings settings, MapManager mapManager, WeaponManager weaponManager, ParallelCamera camera)
    {

        menuScreen = new MenuScreen();
        curScreen = menuScreen;

        if (settings == null || mapManager == null || weaponManager == null || camera == null )
        {
            throw new AssertionError("Settings or mapManager or weaponManager you passed is null");
        }
        else
        {
            this.settings = settings;
            this.mapManager = mapManager;
            this.weaponManager = weaponManager;
            this.camera = camera;
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

    public void Update(double dt)
    {
        //MENU SCREEN ALTERNATIONS
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
                mapManager.resetSettings();
                weaponManager.resetSettings();
                Image bq = mapManager.getCurrentMap().getBackground();

                weaponSelectionScreen = new WeaponSelectionScreen(bq,weaponManager);
                setCurScreen(weaponSelectionScreen);
                menuScreen = null;


            }
        } // SETTINGS SCREEN ALTERNATIONS
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
        } // WEAPON SELECTION SCREEN ALTERNATIONS
        else if(curScreen instanceof WeaponSelectionScreen)
        {
            if (((WeaponSelectionScreen) curScreen).isSwitchToMainMenuScreen())
            {
                menuScreen = new MenuScreen();
                setCurScreen(menuScreen);
                weaponManager.resetSettings();
                weaponSelectionScreen = null;
            }
            else if (((WeaponSelectionScreen) curScreen).isSwitchToWeaponPlacementScreen())
            {
                weaponPlacementScreen = new WeaponPlacementScreen(mapManager.getCurrentMap().getBackground(),
                        weaponManager);
                setCurScreen(weaponPlacementScreen);
                weaponSelectionScreen = null;
            }
        }//WEAPON PLACEMENT SCREEN ALTERNATIONS
        else if(curScreen instanceof WeaponPlacementScreen)
        {
            if (((WeaponPlacementScreen) curScreen).isSwitchToFireScreen())
            {
                fireScreen = new FireScreen(mapManager.getCurrentMap().getBackground(),weaponManager,mapManager,camera);
                setCurScreen(fireScreen);
                weaponPlacementScreen = null;
            }
        }//FIRE SCREEN ALTERNATIONS
        else if(curScreen instanceof FireScreen)
        {
            if (((FireScreen) curScreen).isSwitchToResultScreen())
            {
                resultScreen = new ResultScreen(mapManager.getCurrentMap().getBackground(),((FireScreen) curScreen)
                        .isSuccess(),mapManager,camera,weaponManager);
                setCurScreen(resultScreen);
                weaponPlacementScreen = null;
            }
        }//RESULT SCREEN ALTERNATIONS
        else if(curScreen instanceof ResultScreen)
        {
            if (((ResultScreen) curScreen).isSwitchToMainMenuScreen())
            {
                menuScreen = new MenuScreen();
                setCurScreen(menuScreen);
                weaponManager.resetSettings();
                mapManager.resetSettings();
                resultScreen = null;
            }
            else if (((ResultScreen) curScreen).isSwitchToWeaponSelectionScreen())
            {
                weaponManager.resetSettings();
                weaponSelectionScreen = new WeaponSelectionScreen(mapManager.getCurrentMap().getBackground(),
                        weaponManager);
                setCurScreen(weaponSelectionScreen);
                resultScreen = null;
            }
            else if (((ResultScreen) curScreen).isSwitchToWeaponPlacementScreen())
            {
                weaponPlacementScreen = new WeaponPlacementScreen(mapManager.getCurrentMap().getBackground(),
                        weaponManager);
                setCurScreen(weaponPlacementScreen);
                resultScreen = null;
            }
        }

        //Keep this at the bottom
        ((Screen)curScreen).Update(dt);
    }


}
