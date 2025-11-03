
package me.doubleplusundev;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import me.doubleplusundev.util.InputManager;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("NHF3");
        GamePanel gamePanel = new GamePanel();

        frame.add(gamePanel, BorderLayout.CENTER);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        frame.setMinimumSize(new Dimension((int) (screenSize.width * 0.6), (int) (screenSize.height * 0.6)));
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        gamePanel.addKeyListener(InputManager.getInstance());
        
        
        UpdateManager.getInstance().start();
        PlayerController.getInstance();
        
    }
}