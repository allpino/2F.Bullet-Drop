package com.company.Screens;

import com.company.Constants;
import com.company.WeaponManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class WeaponSelectionScreen extends Group implements Screen
{
    private GraphicsContext gc;
    private Image bq;
    private Image weaponHolder;
    private WeaponManager weaponManager;
    private Text weaponName;
    private Text weaponSpeed;
    private Button nextWeapon;
    private Button previousWeapon;
    private Button selectWeapon;

    private boolean switchToMainMenuScreen;
    private boolean switchToWeaponPlacementScreen;

    public WeaponSelectionScreen(Image bq,WeaponManager weaponManager)
    {
        switchToMainMenuScreen = false;
        switchToWeaponPlacementScreen = false;

        Canvas canvas = new Canvas(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        gc = canvas.getGraphicsContext2D();


        if (bq == null)
        {
            throw new AssertionError("Background is null");
        }

        try
        {
            weaponHolder = new Image(new FileInputStream("src\\com\\company\\resources\\weaponHolder.png"));
        } catch (FileNotFoundException ex)
        {
            throw new AssertionError("file could not be loaded");
        }


        this.bq = bq;
        this.weaponManager = weaponManager;

        weaponName = new Text(weaponManager.getCurWeapon().getName());
        Font theFont = Font.font( "Times New Roman", FontWeight.BOLD, 30 );
        weaponName.setFont(theFont);
        weaponName.setLineSpacing(2);
        weaponName.setFill(Color.DARKBLUE);
        weaponName.setTextAlignment(TextAlignment.CENTER);
        weaponName.setTranslateX((double)Constants.GAME_WIDTH/2-100);
        weaponName.setTranslateY((double)310);

        weaponSpeed = new Text("Bullet Speed: " + weaponManager.getCurWeapon().getSpeed());
        weaponSpeed.setFont(theFont);
        weaponSpeed.setLineSpacing(3);
        weaponSpeed.setFill(Color.DARKRED);
        weaponSpeed.setTranslateX((double)Constants.GAME_WIDTH/2-180);
        weaponSpeed.setTranslateY((double)130);

        nextWeapon = new Button("Next");
        nextWeapon.setTranslateX(890);
        nextWeapon.setTranslateY(180);
        nextWeapon.setPrefHeight(50);
        nextWeapon.setPrefWidth(150);
        nextWeapon.setStyle("-fx-font: 30 arial; -fx-base: #ee2211;");

        previousWeapon = new Button("Previous");
        previousWeapon.setTranslateX(220);
        previousWeapon.setTranslateY(180);
        previousWeapon.setPrefHeight(50);
        previousWeapon.setPrefWidth(150);
        previousWeapon.setStyle("-fx-font: 25 arial; -fx-base: #ee2211;");


        //Action Listeners
        nextWeapon.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                weaponManager.requestNextWeapon();
            }
        });

        previousWeapon.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                weaponManager.requestPreviousWeapon();
            }
        });


        //ADD TO CANVAS
        getChildren().add(canvas);
        getChildren().add(weaponName);
        getChildren().add(weaponSpeed);
        getChildren().add(nextWeapon);
        getChildren().add(previousWeapon);

    }

    @Override
    public void Update()
    {
        gc.drawImage(bq, 0, 0);
        gc.drawImage(weaponHolder,0,0);
        gc.drawImage(weaponManager.getCurWeapon().getPic(),Constants.GAME_WIDTH/2-250,100);

        weaponName.setText(weaponManager.getCurWeapon().getName());
        weaponSpeed.setText("Bullet Speed: " + weaponManager.getCurWeapon().getSpeed());



    }
}
