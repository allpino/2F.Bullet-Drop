package com.company.Screens;

import com.company.*;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

public class FireScreen extends Group implements Screen
{
    private GraphicsContext gc;
    private Image bq;
    private WeaponManager weaponManager;
    private MapManager mapManager;
    private boolean switchToResultScreen;
    private SequenceImage muzzleFlash;
    private Sprite bullet;
    private Image bulletImage;
    private ParallelCamera camera;

    public FireScreen(Image bq, WeaponManager weaponManager, MapManager mapManager, ParallelCamera camera)
    {
        Canvas canvas = new Canvas(Constants.MAP_WIDTH, Constants.GAME_HEIGHT);
        gc = canvas.getGraphicsContext2D();

        if (bq == null)
        {
            throw new AssertionError("Background is null");
        }

        this.bq = bq;
        this.weaponManager = weaponManager;
        this.mapManager = mapManager;
        this.camera = camera;

        muzzleFlash = new SequenceImage();
        Image[] muzzleFlashFrameArray = new Image[38];
        for (int i = 0; i < 38; i++)
        {
            try
            {
                muzzleFlashFrameArray[i] = new Image(new FileInputStream
                        ("src\\com\\company\\resources\\muzzle_flash\\muzzle_flash_" + i +
                    ".png"));
            } catch (FileNotFoundException ex)
            {
                throw new AssertionError("file could not be loaded");
            }
        }
        muzzleFlash.frames = muzzleFlashFrameArray;
        muzzleFlash.duration = 0.100;

        bullet = new Sprite();
        try
        {
            bulletImage = new Image(new FileInputStream("src\\com\\company\\resources\\bullet2.png"));
        } catch (FileNotFoundException ex)
        {
            throw new AssertionError("file could not be loaded");
        }

        bullet.setImage(bulletImage);
        bullet.setPosition(400,weaponManager.getWeapPosHeight()+85);
        bullet.setVelocity((weaponManager.getCurWeapon().getSpeed())*400,0.0);


        //ADD TO CANVAS
        getChildren().add(canvas);
        getChildren().add(camera);


    }

    @Override
    public void Update(double dt)
    {

        gc.drawImage(bq, 0, 0);

        ArrayList<Force> curLevelForces = mapManager.getCurrentLevelForces();
        if (!curLevelForces.isEmpty())
        {
            Iterator<Force> itr = curLevelForces.iterator();

            while(itr.hasNext())
            {
                Force curForce = itr.next();
                if (Constants.DEBUG && curForce.getImage() != null)
                {
                    gc.drawImage(curForce.getImage(), curForce.getPositionX(),0);
                }
                if (bullet.intersects(curForce))
                {
                    System.out.println("ADDING POWER: " + curForce.getPower() + " IS UPWARDS: " + curForce.isUpwards());
                    bullet.addVelocity(0.0,(double) curForce.getPower());
                    System.out.println("VELOCITY X:" + bullet.getVelocityX() + " VELOCITY Y: " + bullet.getVelocityY());
                }
            }
        }

        bullet.calculatePosition(dt);



        gc.drawImage(bulletImage,bullet.getPositionX(), bullet.getPositionY());
        gc.drawImage(weaponManager.getCurWeapon().getPic(), -5,weaponManager.getWeapPosHeight());
        gc.drawImage(muzzleFlash.getFrame(),340,weaponManager.getWeapPosHeight()-85);

        if (bullet.getPositionX() < Constants.GAME_WIDTH)
        {
            camera.setTranslateX(camera.getTranslateX() + ((Constants.GAME_WIDTH - 200) / bullet.getVelocityX()));
        }
        else
        {
            camera.setTranslateX(bullet.getPositionX()-620);
        }



    }
}
