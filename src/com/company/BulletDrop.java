package com.company;

import javax.swing.*;
import java.awt.*;

public class BulletDrop extends JComponent {

    public static void main(String[] args)
    {
        JFrame window = new JFrame("Bullet Drop v0.1");
        window.add(new BulletDrop());
        window.pack();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setSize(new Dimension(Constants.GAME_WIDTH, Constants.GAME_HEIGHT));
        window.setResizable(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawString("Bullet Drop", 640,360);
    }
}
