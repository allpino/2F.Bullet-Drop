package com.company.Screens;


import com.company.Constants;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public  class MenuScreen extends Group
{

    GraphicsContext gc;
    Image bq;

    public MenuScreen()
    {
        Canvas canvas  = new Canvas(Constants.GAME_WIDTH,Constants.GAME_HEIGHT);
        gc = canvas.getGraphicsContext2D();


        System.out.println("LOADING FILEEEEE");
        bq = new Image("C:\\Users\\Elif\\Desktop\\alp\\2F.Bullet-Drop\\src\\resources\\menu_bq.png");
        System.out.println("FILE LOADED");

    }

    public void Update()
    {
        gc.drawImage(bq,0,0);
    }



}
