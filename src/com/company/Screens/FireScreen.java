package com.company.Screens;

import com.company.*;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class FireScreen extends Group implements Screen
{
    private GraphicsContext gc;
    private Image bq;
    private WeaponManager weaponManager;
    private boolean switchToResultScreen;
    private boolean isSuccess;
    private SequenceImage muzzleFlash;
    private Sprite bullet;
    private Sprite target;
    private ParallelCamera camera;
    private boolean inForce;
    private boolean inTrackBullet;
    private int curForce;
    private HashMap<Integer,Force> curLevelForces;

    public FireScreen(Image bq, WeaponManager weaponManager, MapManager mapManager, ParallelCamera camera)
    {
        inForce = false;
        isSuccess = false;
        inTrackBullet = false;

        Canvas canvas = new Canvas(Constants.MAP_WIDTH, Constants.GAME_HEIGHT);
        gc = canvas.getGraphicsContext2D();

        if (bq == null)
        {
            throw new AssertionError("Background is null");
        }

        this.bq = bq;
        this.weaponManager = weaponManager;
        this.camera = camera;

        camera.setTranslateX(0);

        muzzleFlash = new SequenceImage();
        Image[] muzzleFlashFrameArray = new Image[10];
        for (int i = 0; i < muzzleFlashFrameArray.length; i++)
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
        muzzleFlash.duration = 0.200;

        bullet = new Sprite();
        Image bulletImage;
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


        target = new Sprite();
        target.setPosition(weaponManager.getTargetPositionX(),weaponManager.getTargetPositionY());
        target.setImage(weaponManager.getTargetImage());


        curForce = 1;
        curLevelForces = mapManager.getCurrentLevelForces();


        //ADD TO CANVAS
        getChildren().add(canvas);
        getChildren().add(camera);


    }

    @Override
    public void Update(double dt)
    {

        gc.drawImage(bq, 0, 0);

        if (curLevelForces != null)
        {
            if (curLevelForces.size() >= 1)
            {
                if (!curLevelForces.get(0).isGravity())
                {
                    throw new  AssertionError("No gravity force!");
                }

                if (bullet.intersectsWithForce(curLevelForces.get(0)))
                {
                    System.out.println("IN GRAVITY WITH POWER: " + curLevelForces.get(0).getPower());
                    bullet.addVelocity(0.0,curLevelForces.get(0).getPower());
                }

                if (curForce < curLevelForces.size())
                {
                    if (Constants.DEBUG && curLevelForces.get(curForce).getImage() != null)
                    {
                        gc.drawImage(curLevelForces.get(curForce).getImage(), curLevelForces.get(curForce).getPositionX(),0);
                    }
                    if (bullet.intersectsWithForce(curLevelForces.get(curForce)))
                    {
                        System.out.println("ADDING POWER: " + curLevelForces.get(curForce).getPower() + " IS UPWARDS: " + curLevelForces.get(curForce).isDownwards());
                        bullet.addVelocity(0.0,(double) curLevelForces.get(curForce).getPower());
                        System.out.println("VELOCITY X:" + bullet.getVelocityX() + " VELOCITY Y: " + bullet.getVelocityY());
                        inForce = true;
                    }
                    else
                    {
                        if (inForce)
                        {
                            curForce++;
                        }
                        inForce = false;
                    }
                }
            }
        }

        bullet.calculatePosition(dt);

        if (bullet.intersectsWithTarget(target))
        {
            isSuccess = true;
            switchToResultScreen = true;
            System.out.println("HIT!");
        }
        else if (bullet.getPositionX() > Constants.MAP_WIDTH || bullet.getPositionY() > Constants.GAME_HEIGHT ||
                bullet.getPositionY() < 0)
        {
            switchToResultScreen = true;
            System.out.println("NO HIT!");
        }

        gc.drawImage(target.getImage(),target.getPositionX(),target.getPositionY());
        gc.drawImage(bullet.getImage(),bullet.getPositionX(), bullet.getPositionY());
        gc.drawImage(weaponManager.getCurWeapon().getPic(), -5,weaponManager.getWeapPosHeight());
        if (!muzzleFlash.isAnimationFinished)
        {
            gc.drawImage(muzzleFlash.getFrame(),340,weaponManager.getWeapPosHeight()-85);
        }

        if (inTrackBullet)
        {
            if (bullet.getPositionX()>Constants.MAP_WIDTH-(Constants.GAME_WIDTH/2))
            {
                camera.setTranslateX(Constants.MAP_WIDTH-Constants.GAME_WIDTH);
            }
            else
            {
                camera.setTranslateX(bullet.getPositionX()-Constants.GAME_WIDTH/2);
            }
        }
        else
        {
            //TODO:Below code smooths out bullet following cam and normal cam. However it looks buggy in high speed
            // bullets
            if ((bullet.getPositionX()-Constants.GAME_WIDTH/2) > 0)
            {
                if (camera.getTranslateX() < (bullet.getPositionX()
                        -Constants.GAME_WIDTH/2))
                {
                    camera.setTranslateX(camera.getTranslateX() + (bullet.getVelocityX()/1.0)*dt);
                }
                else
                {
                    inTrackBullet = true;
                }
            }
        }
    }

    public boolean isSuccess()
    {
        return isSuccess;
    }

    public boolean isSwitchToResultScreen()
    {
        return switchToResultScreen;
    }
}
