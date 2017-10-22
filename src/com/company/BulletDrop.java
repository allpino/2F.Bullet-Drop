package com.company;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BulletDrop extends Application
{

    Settings settings;

    public static void main(String[] args)
    {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception
    {

        //Settings init
        settings = new Settings();

        //Managers init
        MapManager mapManager = new MapManager();
        WeaponManager weaponManager = new WeaponManager();
        ScreenManager screenManager = new ScreenManager(settings,mapManager,weaponManager);


        primaryStage.setTitle("Bullet Drop v.01");


        Scene theScene = new Scene(screenManager.getCurScreen());
        primaryStage.setScene(theScene);

        primaryStage.show();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                screenManager.Update();

                theScene.setRoot(screenManager.getCurScreen());
                primaryStage.setScene(theScene);

            }
        }.start();

    }

}
