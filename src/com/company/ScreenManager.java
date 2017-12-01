package com.company;


import com.company.Screens.*;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;


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
    HowToPlayScreen howToPlayScreen;
    CreditsScreen creditsScreen;

    //Managers
    MapManager mapManager;
    WeaponManager weaponManager;

    //Sounds
    MediaPlayer bqMusic;
    MediaPlayer fireScreenTension;
    MediaPlayer rifleShot;
    MediaPlayer success;
    MediaPlayer failure;


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

        String bqMusic = "src\\com\\company\\resources\\bq_music.wav";
        Media bqMusicMedia = new Media((new File(bqMusic).toURI().toString()));
        this.bqMusic = new MediaPlayer(bqMusicMedia);
        this.bqMusic.setOnEndOfMedia(new Runnable() {
            public void run() {
                ScreenManager.this.bqMusic.seek(Duration.ZERO);
            }
        });
        this.bqMusic.play();

        String fireScreenTension = "src\\com\\company\\resources\\firescreen_tension.mp3";
        Media fireScreenTensionMedia = new Media((new File(fireScreenTension).toURI().toString()));
        this.fireScreenTension = new MediaPlayer(fireScreenTensionMedia);

        String rifleShot = "src\\com\\company\\resources\\rifleshot.wav";
        Media rifleShotMedia = new Media((new File(rifleShot).toURI().toString()));
        this.rifleShot = new MediaPlayer(rifleShotMedia);

        String success = "src\\com\\company\\resources\\success.wav";
        Media successMedia = new Media((new File(success).toURI().toString()));
        this.success = new MediaPlayer(successMedia);

        String failure = "src\\com\\company\\resources\\failure.wav";
        Media failureMedia = new Media((new File(failure).toURI().toString()));
        this.failure = new MediaPlayer(failureMedia);

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
            else if(((MenuScreen) curScreen).isSwitchToMainMenuScreen())
            {
            	creditsScreen = new CreditsScreen();
            	setCurScreen(creditsScreen);
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
            	howToPlayScreen = new HowToPlayScreen();
            	setCurScreen(howToPlayScreen);
            	settingsScreen = null;
            	
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
                bqMusic.stop();

                rifleShot.seek(Duration.ZERO);
                rifleShot.play();

                fireScreenTension.seek(Duration.ZERO);
                fireScreenTension.play();
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

                fireScreenTension.stop();

                if (resultScreen.isSuccess())
                {
                    success.seek(Duration.ZERO);
                    success.play();
                }
                else
                {
                    failure.seek(Duration.ZERO);
                    failure.play();
                }
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

                bqMusic.seek(Duration.ZERO);
                bqMusic.play();
            }
            else if (((ResultScreen) curScreen).isSwitchToWeaponSelectionScreen())
            {
                weaponManager.resetSettings();
                weaponSelectionScreen = new WeaponSelectionScreen(mapManager.getCurrentMap().getBackground(),
                        weaponManager);
                setCurScreen(weaponSelectionScreen);
                resultScreen = null;

                bqMusic.seek(Duration.ZERO);
                bqMusic.play();
            }
            else if (((ResultScreen) curScreen).isSwitchToWeaponPlacementScreen())
            {
                weaponPlacementScreen = new WeaponPlacementScreen(mapManager.getCurrentMap().getBackground(),
                        weaponManager);
                setCurScreen(weaponPlacementScreen);
                resultScreen = null;

                bqMusic.seek(Duration.ZERO);
                bqMusic.play();
            }
        }//HOWTOPLAY SCREEN ALTERNATIONS
        else if(curScreen instanceof HowToPlayScreen)
        {
            if (((HowToPlayScreen) curScreen).isSwitchToSettingsScreen())
            {
            	settingsScreen = new SettingsScreen(settings);
                setCurScreen(settingsScreen);
                howToPlayScreen = null;
            }
        }//CREDITS SCREEN ALTERNATIONS
        else if(curScreen instanceof CreditsScreen)
        {
            if (((CreditsScreen) curScreen).isSwitchToMainMenuScreen())
            {
            	menuScreen = new MenuScreen();
                setCurScreen(menuScreen);
                creditsScreen = null;
            }
        }

        //Keep this at the bottom
        ((Screen)curScreen).Update(dt);


        //Mediaplayer settingss
        bqMusic.setMute(!settings.isSfxOn);
        rifleShot.setMute(!settings.isSfxOn);
        fireScreenTension.setMute(!settings.isSfxOn);
        success.setMute(!settings.isSfxOn);
        failure.setMute(!settings.isSfxOn);
    }


}
