package com.company.Screens;

import com.company.Constants;
import com.company.Settings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SettingsScreen extends Group implements Screen
{
    private GraphicsContext gc;
    private Image bq;
    private Button sfx;
    private Button howToPlay;
    private Button back;

    private boolean switchToMainMenuScreen;
    private boolean switchToHowToPlayScreen;

    public SettingsScreen(Settings settings )
    {
        switchToMainMenuScreen = false;
        switchToHowToPlayScreen = false;

        Canvas canvas = new Canvas(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        gc = canvas.getGraphicsContext2D();

        try
        {
            bq = new Image(new FileInputStream("src\\com\\company\\resources\\menu_bq.png"));
        } catch (FileNotFoundException ex)
        {
            throw new AssertionError();
        }

        sfx = new Button("Toggle Sfx");
        sfx.setTranslateX(900);
        sfx.setTranslateY(150);
        sfx.setPrefHeight(70);
        sfx.setPrefWidth(200);
        sfx.setStyle("-fx-font: 30 arial; -fx-base: #ee2211;");

        howToPlay = new Button("How to Play");
        howToPlay.setTranslateX(900);
        howToPlay.setTranslateY(230);
        howToPlay.setPrefHeight(70);
        howToPlay.setPrefWidth(200);
        howToPlay.setStyle("-fx-font: 28 arial; -fx-base: #ee2211;");

        back = new Button("Back");
        back.setTranslateX(900);
        back.setTranslateY(310);
        back.setPrefHeight(70);
        back.setPrefWidth(200);
        back.setStyle("-fx-font: 30 arial; -fx-base: #ee2211;");


        sfx.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                settings.isSfxOn = !settings.isSfxOn;
                System.out.println("SFX IS NOW " + (settings.isSfxOn? "TRUE" : "FALSE"));
            }
        });

        howToPlay.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                switchToHowToPlayScreen = true;
            }
        });

        back.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                switchToMainMenuScreen = true;
            }
        });


        //ADD TO CANVAS
        getChildren().add(canvas);
        getChildren().add(sfx);
        getChildren().add(howToPlay);
        getChildren().add(back);
    }

    public void Update(double dt)
    {
        gc.drawImage(bq, 0, 0);
    }

    public boolean isSwitchToMainMenuScreen()
    {
        return switchToMainMenuScreen;
    }

    public boolean isSwitchToHowToPlayScreen()
    {
        return switchToHowToPlayScreen;
    }
}



