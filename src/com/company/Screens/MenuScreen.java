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
            bq = ImageIO.read(new File("menu_bq.png"));
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

        newGameButton.setSize(600,400);
        settings.setSize(600,400);
        exit.setSize(600,400);

        newGameButton.setVisible(true);
        settings.setVisible(true);
        exit.setVisible(true);

    }



    @Override
    public void paintComponents(Graphics g)
    {
        super.paintComponents(g);

        g.drawImage(bq,0,0,null);


        buttonPanel.add(newGameButton);
        buttonPanel.add(settings);
        buttonPanel.add(exit);

        add(buttonPanel);

    }
}
