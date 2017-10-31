package com.company.Screens;

import com.company.Constants;
import com.company.MapManager;
import com.company.WeaponManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;



public class ResultScreen extends Group implements Screen
{
    private GraphicsContext gc;
    private Image bq;
    private boolean switchToWeaponSelectionScreen;
    private boolean switchToMainMenuScreen;
    private boolean switchToWeaponPlacementScreen;
    private boolean isSuccess;
    private Rectangle blackRect;
    private Button option;
    private Button exit;
    private Label label;
    private MapManager mapManager;
    private ParallelCamera camera;
    private WeaponManager weaponManager;

    public ResultScreen(Image bq, boolean isSuccess, MapManager mapManager, ParallelCamera camera, WeaponManager weaponManager)
    {
        switchToMainMenuScreen = false;
        switchToWeaponSelectionScreen = false;
        switchToWeaponPlacementScreen = false;

        this.isSuccess = isSuccess;
        if (mapManager == null)
        {
            throw  new AssertionError("Map maanger is null");
        }

        this.mapManager = mapManager;
        this.camera = camera;
        this.weaponManager = weaponManager;

        camera.setTranslateX(0);

        Canvas canvas = new Canvas(Constants.MAP_WIDTH, Constants.GAME_HEIGHT);
        gc = canvas.getGraphicsContext2D();

        if (bq == null)
        {
            throw new AssertionError("Background is null");
        }

        this.bq = bq;


        Font theFont = Font.font( "Times New Roman", FontWeight.BOLD, 60 );

        label = new Label(isSuccess? "Success!" : "No hit!");
        label.setFont(theFont);
        label.setTextFill(Color.ORANGE);
        label.setTranslateX((double) (Constants.GAME_HEIGHT/2 + 180));
        label.setTranslateY(100.0);

        option = new Button(isSuccess? "Next" : "Try Again");
        option.setTranslateX(800);
        option.setTranslateY(250);
        option.setPrefHeight(100);
        option.setPrefWidth(300);
        option.setStyle("-fx-font: 30 arial; -fx-base: #ee2211;");

        exit = new Button("Exit");
        exit.setTranslateX(200);
        exit.setTranslateY(250);
        exit.setPrefHeight(100);
        exit.setPrefWidth(300);
        exit.setStyle("-fx-font: 30 arial; -fx-base: #ee2211;");

        blackRect = new Rectangle(0,0,Constants.MAP_WIDTH,Constants.GAME_HEIGHT);
        blackRect.setFill(new Color(0,0,0,0.5));


        //Event handlers
        option.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                if (isSuccess)
                {
                    if (mapManager.getCurrentMap().getID() == mapManager.getNumOfMaps() && mapManager.getCurrentMap()
                            .getCurrentLevel() == mapManager.getNumOfLevels())
                    {
                        switchToMainMenuScreen = true;
                    }
                    else
                    {
                        if (mapManager.getCurrentMap().getCurrentLevel() == mapManager.getNumOfLevels())
                        {
                            mapManager.setCurrentMap(mapManager.getCurrentMap().getID()+1);
                            switchToWeaponSelectionScreen = true;
                        }
                        else
                        {
                            mapManager.getCurrentMap().increaseCurrentLevel();
                            switchToWeaponPlacementScreen = true;
                            weaponManager.newTargetPosition();
                        }
                    }
                }
                else
                {
                    switchToWeaponPlacementScreen = true;
                }
            }
        });

        exit.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                switchToMainMenuScreen = true;
            }
        });


        //Add to group
        getChildren().add(canvas);
        getChildren().add(blackRect);
        getChildren().add(label);
        getChildren().add(option);
        getChildren().add(exit);
    }

    public boolean isSwitchToWeaponSelectionScreen()
    {
        return switchToWeaponSelectionScreen;
    }

    public boolean isSwitchToMainMenuScreen()
    {
        return switchToMainMenuScreen;
    }

    public boolean isSwitchToWeaponPlacementScreen()
    {
        return switchToWeaponPlacementScreen;
    }

    public boolean isSuccess()
    {
        return isSuccess;
    }

    @Override
    public void Update(double dt)
    {
        gc.drawImage(bq, Constants.MAP_WIDTH-Constants.MAP_WIDTH, 0);
    }
}
