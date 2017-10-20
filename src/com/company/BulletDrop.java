package com.company;
import com.company.Screens.MenuScreen;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class BulletDrop extends Application
{

    public static void main(String[] args)
    {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception
    {
        ScreenManager screenManager = new ScreenManager();
        screenManager.setCurScreen(new MenuScreen());



        primaryStage.setTitle("Bullet Drop v.01");


        Scene theScene = new Scene(screenManager.getCurScreen());

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                screenManager.Update();
            }
        }.start();


        primaryStage.setScene(theScene);
        primaryStage.show();

    }

}
