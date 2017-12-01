package com.company.Screens;

import com.company.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class HowToPlayScreen extends Group implements Screen {
	
    private GraphicsContext gc;
    private Image bq;
    private Image howToPlayPic;
    private Button back;
    
    private boolean switchToSettingsScreen;
 
    public HowToPlayScreen ()
    {
    	switchToSettingsScreen = false;

        Canvas canvas = new Canvas(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        gc = canvas.getGraphicsContext2D();

        try
        {
            bq = new Image(new FileInputStream("src\\com\\company\\resources\\menu_bq.png"));
        } catch (FileNotFoundException ex)
        {
            throw new AssertionError();
        }
        try
        {
        	howToPlayPic = new Image(new FileInputStream("src\\com\\company\\resources\\howToPlay.png"));
        } catch (FileNotFoundException ex)
        {
            throw new AssertionError();
        }

        back = new Button("Back");
        back.setTranslateX(20);
        back.setTranslateY(Constants.GAME_HEIGHT-70);
        back.setPrefWidth(120);
        back.setPrefHeight(30);
        back.setStyle("-fx-font: 25 arial; -fx-base: #7e0101;");



        back.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
            	switchToSettingsScreen = true;
            }
        });


        //ADD TO CANVAS
        getChildren().add(canvas);
        getChildren().add(back);
    }
    
    public boolean isSwitchToSettingsScreen()
    {
        return switchToSettingsScreen;
    }
    
	public void Update(double dt)
    {
        gc.drawImage(bq, 0, 0);
        gc.drawImage(howToPlayPic, 140, 60);
    }
}
