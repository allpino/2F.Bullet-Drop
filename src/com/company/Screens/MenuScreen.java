package com.company.Screens;

import com.company.Constants;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class MenuScreen extends Group implements Screen
{
    private GraphicsContext gc;
    private Image bq;
    private Button newGame;
    private Button settings;
    private Button exit;
    private Button credits;

    private boolean switchToNewGameScreen;
    private boolean switchTosettingsScreen;
    private boolean switchToMainMenuScreen;


    public MenuScreen()
    {

        switchToNewGameScreen = false;
        switchTosettingsScreen = false;
        switchToMainMenuScreen = false;

        Canvas canvas = new Canvas(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        gc = canvas.getGraphicsContext2D();


        //LOAD BACKGROUND
        try
        {
            bq = new Image(new FileInputStream("src\\com\\company\\resources\\menu_bq.png"));
        } catch (FileNotFoundException ex)
        {
            throw new AssertionError();
        }


        newGame = new Button("New Game");
        newGame.setTranslateX(900);
        newGame.setTranslateY(150);
        newGame.setPrefHeight(70);
        newGame.setPrefWidth(200);
        newGame.setStyle("-fx-font: 30 arial; -fx-base: #ee2211;");

        settings = new Button("Settings");
        settings.setTranslateX(900);
        settings.setTranslateY(230);
        settings.setPrefHeight(70);
        settings.setPrefWidth(200);
        settings.setStyle("-fx-font: 30 arial; -fx-base: #ee2211;");
        
        credits = new Button("Credits");
        credits.setTranslateX(900);
        credits.setTranslateY(310);
        credits.setPrefHeight(70);
        credits.setPrefWidth(200);
        credits.setStyle("-fx-font: 30 arial; -fx-base: #ee2211;");

        exit = new Button("Exit");
        exit.setTranslateX(900);
        exit.setTranslateY(390);
        exit.setPrefHeight(70);
        exit.setPrefWidth(200);
        exit.setStyle("-fx-font: 30 arial; -fx-base: #ee2211;");


        newGame.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                switchToNewGameScreen = true;
            }
        });

        settings.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                switchTosettingsScreen = true;
            }
        });

        credits.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
            	switchToMainMenuScreen = true;
            }
        });
        
        exit.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                Runtime.getRuntime().exit(0);
            }
        });
        
        


        //ADD TO CANVAS
        getChildren().add(canvas);
        getChildren().add(newGame);
        getChildren().add(settings);
        getChildren().add(credits);
        getChildren().add(exit);

    }

    public void Update(double dt)
    {
        gc.drawImage(bq, 0, 0);
    }


    public boolean isSwitchToNewGameScreen()
    {
        return switchToNewGameScreen;
    }

    public boolean isSwitchToSettingsScreen()
    {
        return switchTosettingsScreen;
    }
    
    public boolean isSwitchToMainMenuScreen() {
    	return switchToMainMenuScreen;
    }
}
