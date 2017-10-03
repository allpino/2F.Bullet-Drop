package com.company;

import com.company.Screens.MenuScreen;

import javax.swing.*;
import java.awt.*;

public class BulletDrop extends JComponent {

    static JFrame window;
    static JPanel curScreen = null;

    public static void main(String[] args)
    {
        window = new JFrame("Bullet Drop v0.1");
        window.pack();
        BulletDrop bulletDrop = new BulletDrop();
        window.add(bulletDrop);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setSize(new Dimension(Constants.GAME_WIDTH, Constants.GAME_HEIGHT));
        window.setResizable(false);

        setCurScreen(new MenuScreen());

    }

    public static void setCurScreen(JPanel screen)
    {
        curScreen = screen;
        window.repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (curScreen != null)
        {
            curScreen.paintComponents(g);
        }

    }
}
