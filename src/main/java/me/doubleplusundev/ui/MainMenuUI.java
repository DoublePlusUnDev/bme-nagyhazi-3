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
import me.doubleplusundev.map.worldgen.WorldGenerator;
import me.doubleplusundev.savegame.SaveGameManager;
import me.doubleplusundev.util.UIUtils;

/**
 * Class for handling the main menu GUI.
 * It has a a button for generating a new world based on the seed inputted.
 * Also has a button for loading a world from the specified file.
 * When an option is selected the UIhandler is started and the main menu is discarded.
 */
public class MainMenuUI {
    private final UIHandler uiHandler;
    private final GameMapHandler gameMapHandler;
    private final SaveGameManager saveGameManager;
    private final WorldGenerator worldGenerator;
    private final Random random;

    private JTextField seedField;
    private JTextField saveField;

    public MainMenuUI(UIHandler uiHandler, GameMapHandler gameMapHandler, SaveGameManager saveGameManager, WorldGenerator worldGenerator) {
        this.uiHandler = uiHandler;
        this.gameMapHandler = gameMapHandler;
        this.saveGameManager = saveGameManager;
        this.worldGenerator = worldGenerator;
        this.random = new Random();
    }

    /**
     * Creates the main menu UI.
     */
    public void initialize() {
        JFrame frame = new JFrame("NHF3 Main Menu");
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setLocationRelativeTo(null);
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton newGameButton = new JButton("Start New Game");
        newGameButton.addActionListener(event -> {
            if (seedField.getText().equals("Random seed...")){
                gameMapHandler.setMap(worldGenerator.generateWorld(250, 250, random.nextLong()));
            }
            else {
                gameMapHandler.setMap(worldGenerator.generateWorld(250, 250, seedField.getText().hashCode()));
            }
            
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
