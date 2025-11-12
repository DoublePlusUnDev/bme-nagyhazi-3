package me.doubleplusundev.ui;

import java.awt.Dimension;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import me.doubleplusundev.map.GameMapHandler;
import me.doubleplusundev.savegame.SaveGameManager;
import me.doubleplusundev.util.UIUtils;

public class MainMenuUI {
    private final UIHandler uiHandler;
    private final GameMapHandler gameMapHandler;
    private final SaveGameManager saveGameManager;
    private final Random random;

    private JTextField seedField;
    private JTextField saveField;

    public MainMenuUI(UIHandler uiHandler, GameMapHandler gameMapHandler, SaveGameManager saveGameManager) {
        this.uiHandler = uiHandler;
        this.gameMapHandler = gameMapHandler;
        this.saveGameManager = saveGameManager;
        this.random = new Random();
    }

    public void initialize() {
        JFrame frame = new JFrame("NHF3 Main Menu");
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setLocationRelativeTo(null);
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton newGameButton = new JButton("Start New Game");
        newGameButton.addActionListener(event -> {
            if (seedField.getText().equals("Random seed...")){
                gameMapHandler.generateWorld(random.nextLong());
            }
            else
                gameMapHandler.generateWorld(seedField.getText().hashCode());
            uiHandler.initialize();
            frame.setVisible(false);
        });
        frame.add(newGameButton);
        frame.add(Box.createRigidArea(new Dimension(0, 10)));
        
        seedField = new JTextField();
        seedField.setMaximumSize(new Dimension(200, 18));
        UIUtils.addPlaceHolder(seedField, "Random seed...");
        frame.add(seedField);
        frame.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton loadGameButton = new JButton("Load Game From File");
        loadGameButton.addActionListener(event -> {
            saveGameManager.setLocation(saveField.getText());
            saveGameManager.load();
            uiHandler.initialize();
            frame.setVisible(false);
        });
        frame.add(loadGameButton);
        frame.add(Box.createRigidArea(new Dimension(0, 10)));

        saveField = new JTextField();
        saveField.setMaximumSize(new Dimension(200, 18));
        UIUtils.addPlaceHolder(saveField, "Load from...");
        frame.add(saveField);

        frame.pack();
        frame.setVisible(true);
    }
    
}
