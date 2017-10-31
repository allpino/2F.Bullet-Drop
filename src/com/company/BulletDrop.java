package com.company;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.security.AccessControlException;

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
        ParallelCamera camera = new ParallelCamera();

        //Settings init
        settings = new Settings();

        //Managers init
        MapManager mapManager = new MapManager();
        WeaponManager weaponManager = new WeaponManager();
        ScreenManager screenManager = new ScreenManager(settings,mapManager,weaponManager,camera);

        primaryStage.setTitle("Bullet Drop v.01");

        Scene theScene = new Scene(screenManager.getCurScreen());

        try {
            System.setProperty("prism.verbose", "true");
            System.setProperty("prism.dirtyopts", "false");
            System.setProperty("javafx.animation.pulse", "10");
        } catch (AccessControlException e) {}


        theScene.setCamera(camera);
        primaryStage.setScene(theScene);

        primaryStage.show();

        LongValue lastNanoTime = new LongValue(System.nanoTime());

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {

                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
                lastNanoTime.value = currentNanoTime;

                screenManager.Update(elapsedTime);

                theScene.setRoot(screenManager.getCurScreen());

                primaryStage.setScene(theScene);

            }
        }.start();

    }

}
