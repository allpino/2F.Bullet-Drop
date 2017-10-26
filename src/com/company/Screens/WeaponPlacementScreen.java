package com.company.Screens;

import com.company.Constants;
import com.company.WeaponManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;


public class WeaponPlacementScreen extends Group implements Screen
{

    private GraphicsContext gc;
    private Image bq;
    private WeaponManager weaponManager;
    private Polygon increaseHeight;
    private Polygon decreaseHeight;
    private int triangleScale;
    private boolean increaseClicked;
    private boolean decreaseClicked;
    private Button fire;
    private boolean switchToFireScreen;

    public WeaponPlacementScreen(Image bq,WeaponManager weaponManager)
    {
        switchToFireScreen = false;
        triangleScale = 60;

        increaseClicked = false;
        decreaseClicked = false;


        Canvas canvas = new Canvas(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        gc = canvas.getGraphicsContext2D();

        if (bq == null)
        {
            throw new AssertionError("Background is null");
        }

        this.bq = bq;
        this.weaponManager = weaponManager;

        decreaseHeight = new Polygon();
        decreaseHeight.getPoints().addAll(new Double[]{
                500.0, 660.0,
                500.0 + triangleScale /2, 660.0 + triangleScale,
                500.0 + triangleScale, 660.0 });
        decreaseHeight.setFill(Color.RED);


        increaseHeight = new Polygon();
        increaseHeight.getPoints().addAll(new Double[]{
                500.0, 70.0,
                500.0 + triangleScale /2, 70.0 - triangleScale,
                500.0 + triangleScale, 70.0 });
        increaseHeight.setFill(Color.RED);

        fire = new Button("Fire!");
        fire.setTranslateX(1100);
        fire.setTranslateY(250);
        fire.setShape(new Circle(70));
        fire.setMinSize(140.0,140.0);
        fire.setMaxSize(140.0,140.0);
        fire.setStyle("-fx-font: 25 arial; -fx-base: #18b200;");


        //EVENT HANDLERS
        increaseHeight.setOnMousePressed(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                increaseClicked = true;
            }
        });

        increaseHeight.setOnMouseReleased(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                increaseClicked = false;
            }
        });

        decreaseHeight.setOnMousePressed(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                decreaseClicked = true;
            }
        });

        decreaseHeight.setOnMouseReleased(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                decreaseClicked = false;
            }
        });


        fire.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                switchToFireScreen = true;
            }
        });

        //ADD TO CANVAS
        getChildren().add(canvas);
        getChildren().add(increaseHeight);
        getChildren().add(decreaseHeight);
        getChildren().add(fire);

    }


    @Override
    public void Update(double dt)
    {
        gc.drawImage(bq, 0, 0);

        if (decreaseClicked)
        {
            weaponManager.decreaseWeapPosHeight();
        }
        else if(increaseClicked)
        {
            weaponManager.increaseWeapPosHeight();
        }

        gc.drawImage(weaponManager.getCurWeapon().getPic(), -5,weaponManager.getWeapPosHeight());

    }

    public boolean isSwitchToFireScreen()
    {
        return switchToFireScreen;
    }
}
