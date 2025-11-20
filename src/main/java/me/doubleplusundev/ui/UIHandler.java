package me.doubleplusundev.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import me.doubleplusundev.game.UpdateManager;
import me.doubleplusundev.map.GameMapHandler;
import me.doubleplusundev.map.worldobject.WorldObjectType;
import me.doubleplusundev.player.PlayerInteractionManager;
import me.doubleplusundev.player.KeyInputManager;
import me.doubleplusundev.player.PlayerController;
import me.doubleplusundev.resource.ResourceManager;
import me.doubleplusundev.resource.ResourceType;
import me.doubleplusundev.savegame.SaveGameManager;
import me.doubleplusundev.util.TextureManager;
import me.doubleplusundev.util.UIUtils;

/**
 * The top row has a list of buttons for possible player interactions.
 * An optional building selection row may appear below it when the building action is selected.
 * 
 * On the left there is a resource monitor screen for each in-game resource measuring the current amount and it's delta.
 * 
 * In the bottom row there are the save/load options.
 * 
 * The gamepanel handles the rendering of the game.
 */
public class UIHandler {
    private final GameMapHandler gameMapHandler;
    private final ResourceManager resourceManager;
    private final UpdateManager updateManager;
    private final SaveGameManager saveGameManager;
    private final PlayerController playerController;
    private final KeyInputManager keyInputManager;
    private final PlayerInteractionManager playerInteractionManager;

    private JPanel buildingSelectorRow;
    private JLabel lastSelectedLabel;

    public UIHandler(GameMapHandler gameMapHandler, ResourceManager resourceManager, UpdateManager updateManager, SaveGameManager saveGameManager, PlayerController playerController, PlayerInteractionManager playerInteractionManager, KeyInputManager keyInputManager){
        this.gameMapHandler = gameMapHandler;
        this.resourceManager = resourceManager;
        this.updateManager = updateManager;
        this.saveGameManager = saveGameManager;
        this.playerController = playerController;
        this.playerInteractionManager = playerInteractionManager; 
        this.keyInputManager = keyInputManager;
    }
    
    /**
     * Initializes the main GUI of the game and sets up the ui functionality.
     */
    public void initialize() {
        JFrame frame = new JFrame("NHF3");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setMinimumSize(new Dimension((int) (screenSize.width * 0.6), (int) (screenSize.height * 0.6)));
        frame.setLocationRelativeTo(null);
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        initializeTop(frame);

        initializeResourcePanel(frame);

        initializeGamePanel(frame);

        initializeBottomRow(frame);

        frame.setVisible(true);
    }

    /**
     * Initializes the top row; the interaction buttons and the optional building selector row.
     * @param frame
     */
    private void initializeTop(JFrame frame) {
        JPanel top = new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
        JPanel buttonRow = new JPanel(new FlowLayout());

        JButton buildButton = new JButton("Build");
        buildButton.setFocusable(false);
        buildButton.addActionListener(event -> { 
            playerController.setInteractionMode(PlayerController.PlayerInteractionMode.BUILD);
            buildingSelectorRow.setVisible(true);
        });
        buttonRow.add(buildButton);

        JButton destroyButton = new JButton("Destroy");
        destroyButton.setFocusable(false);
        destroyButton.addActionListener(event -> { 
            playerController.setInteractionMode(PlayerController.PlayerInteractionMode.DESTROY);
            buildingSelectorRow.setVisible(false);
        });
        buttonRow.add(destroyButton);
       top.add(buttonRow);

        buildingSelectorRow = new JPanel(new FlowLayout());
        for (WorldObjectType structure : new WorldObjectType[]{ WorldObjectType.CENTER, 
            WorldObjectType.ROAD, WorldObjectType.LUMBERHUT, WorldObjectType.QUARRY, WorldObjectType.BLACKSMITH }) {
            JLabel imageLabel = new JLabel(new ImageIcon(TextureManager.getWorldObject(structure).getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
            imageLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    selectBuildingLabel(structure, imageLabel);
                }
            });

            if (structure == WorldObjectType.CENTER) {
                selectBuildingLabel(structure, imageLabel);
            }

            buildingSelectorRow.add(imageLabel);
        }
        top.add(buildingSelectorRow);

        frame.add(top, BorderLayout.NORTH);
    }

    /**
     * Sets up a panel on the left, a row of resource monitors, one for each resource in game.
     * @param frame
     */
    private void initializeResourcePanel(JFrame frame) {
        JPanel resourcePanel = new JPanel();
        resourcePanel.setLayout(new BoxLayout(resourcePanel, BoxLayout.Y_AXIS));

        JTextArea topLabel = new JTextArea("Resource  Amount  Change");
        topLabel.setMaximumSize(new Dimension(200, 18));
        topLabel.setFocusable(false);
        topLabel.setOpaque(false);
        resourcePanel.add(topLabel);

        ResourceMonitor woodDisplay = new ResourceMonitor(ResourceType.WOOD, resourceManager);
        updateManager.registerForUpdate(woodDisplay);
        resourcePanel.add(woodDisplay);

        ResourceMonitor stoneDisplay = new ResourceMonitor(ResourceType.STONE, resourceManager);
        updateManager.registerForUpdate(stoneDisplay);
        resourcePanel.add(stoneDisplay);

        ResourceMonitor ironDisplay = new ResourceMonitor(ResourceType.IRON, resourceManager);
        updateManager.registerForUpdate(ironDisplay);
        resourcePanel.add(ironDisplay);

        frame.add(resourcePanel, BorderLayout.WEST);
    }

    /**
     * Sets up the game renderer panel.
     * @param frame
     */
    private void initializeGamePanel(JFrame frame) {
        GamePanel gamePanel = new GamePanel(gameMapHandler, playerController, playerInteractionManager, keyInputManager);     
        updateManager.registerForUpdate(gamePanel);   
        frame.add(gamePanel, BorderLayout.CENTER);
    }

    /**
     * Initializes the panel responsible for handling saving and loading the map.
     * @param frame
     */
    private void initializeBottomRow(JFrame frame) {
        JPanel bottomRow = new JPanel(new FlowLayout());

        JButton saveMapButton = new JButton("Save Map");
        saveMapButton.setFocusable(false);
        saveMapButton.addActionListener(event -> saveGameManager.save());
        bottomRow.add(saveMapButton);

        JTextField saveField = new JTextField(20);
        saveField.setFocusable(true);
        saveField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                saveGameManager.setLocation(saveField.getText());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                saveGameManager.setLocation(saveField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                saveGameManager.setLocation(saveField.getText());
            }
            
        });
        UIUtils.addPlaceHolder(saveField, "File Name...");
        bottomRow.add(saveField);

        JButton loadMapButton = new JButton("Load Map");
        saveMapButton.setFocusable(false);
        loadMapButton.addActionListener(event -> saveGameManager.load());
        bottomRow.add(loadMapButton);
        frame.add(bottomRow, BorderLayout.SOUTH);

        JButton quitButton = new JButton("Quit Game");
        quitButton.setFocusable(false);
        quitButton.addActionListener(event -> System.exit(0));
        bottomRow.add(quitButton);
    }

    /**
     * Sets the building selector UI to the given imagelabel, and sets the selected structure type. 
     * @param structure
     * @param imageLabel
     */
    private void selectBuildingLabel(WorldObjectType structure, JLabel imageLabel) {
        if (lastSelectedLabel != null)
            lastSelectedLabel.setBorder(BorderFactory.createEmptyBorder());
        playerController.selectBuilding(structure);
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        lastSelectedLabel = imageLabel;
    }
}
