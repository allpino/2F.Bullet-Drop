package com.company;
import com.company.Screens.MenuScreen;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BulletDrop extends Application
{

    public static void main(String[] args)
    {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Bullet Drop v.01");

        final MenuScreen menuScreen = new MenuScreen();
        Scene theScene = new Scene(menuScreen);

        final long startNanoTime = System.nanoTime();
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                menuScreen.Update();
            }
        }.start();

        primaryStage.setScene(theScene);
        primaryStage.show();



    }
}
