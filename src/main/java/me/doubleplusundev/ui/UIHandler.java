package me.doubleplusundev.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import me.doubleplusundev.GamePanel;
import me.doubleplusundev.player.InputManager;

public class UIHandler {
    private static UIHandler instance;

    private UIHandler(){

    }

    public static UIHandler getInstance() {
        if (instance == null){
            instance = new UIHandler();
        }
        return instance;
    }
    
    public void initialize(){
        JFrame frame = new JFrame("NHF3");
        GamePanel gamePanel = new GamePanel();

        frame.add(gamePanel, BorderLayout.CENTER);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        frame.setMinimumSize(new Dimension((int) (screenSize.width * 0.6), (int) (screenSize.height * 0.6)));
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        gamePanel.addKeyListener(InputManager.getInstance());
    }
}
