package com.company;

import com.company.Screens.MenuScreen;

import javax.swing.*;
import java.awt.*;

public class BulletDrop extends JFrame {

    public static void main(String[] args)
    {
        BulletDrop bulletDrop = new BulletDrop();
        bulletDrop.setTitle("Bullet Drop v.01");
        bulletDrop.pack();
        bulletDrop.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        bulletDrop.setLocationRelativeTo(null);
        bulletDrop.setVisible(true);
        bulletDrop.setResizable(false);
        bulletDrop.add(new MenuScreen());
        bulletDrop.revalidate();
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
    }

    @Override
    public void paintComponents(Graphics g)
    {
        super.paintComponents(g);
    }
}
