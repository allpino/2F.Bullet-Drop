package com.company.Screens;


import com.company.Constants;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuScreen extends JPanel
{
    BufferedImage bq = null;
    JPanel buttonPanel;

    JButton newGameButton = new JButton("New Game");
    JButton settings = new JButton("Setting");
    JButton exit = new JButton("Exit");

    public MenuScreen(){

        try
        {
            bq = ImageIO.read(new File("C:\\Users\\AlpiN\\Desktop\\2F.Bullet-Drop\\src\\resources\\menu_bq.png"));
        }
        catch (IOException e)
        {

        }

        if (bq == null)
        {
            throw new IllegalArgumentException("Could not load bq");
        }

        setVisible(true);

        buttonPanel = new JPanel();

        buttonPanel.setLayout(new FlowLayout());

        newGameButton.setVisible(true);
        settings.setVisible(true);
        exit.setVisible(true);


        buttonPanel.add(newGameButton);
        buttonPanel.add(settings);
        buttonPanel.add(exit);

        add(buttonPanel);

    }



    @Override
    public void paintComponents(Graphics g)
    {
        super.paintComponents(g);

        g.drawImage(bq,0,0,null);
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
    }
}
