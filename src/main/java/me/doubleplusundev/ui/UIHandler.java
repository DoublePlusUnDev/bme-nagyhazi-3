package me.doubleplusundev.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import me.doubleplusundev.game.UpdateManager;
import me.doubleplusundev.map.GameMapHandler;
import me.doubleplusundev.map.structures.StructureType;
import me.doubleplusundev.player.GameInteractionManager;
import me.doubleplusundev.player.KeyInputManager;
import me.doubleplusundev.player.PlayerController;
import me.doubleplusundev.resource.ResourceManager;
import me.doubleplusundev.resource.ResourceType;
import me.doubleplusundev.savegame.SaveGameManager;
import me.doubleplusundev.util.TextureManager;

public class UIHandler {
    private final GameMapHandler gameMapHandler;
    private final ResourceManager resourceManager;
    private final UpdateManager updateManager;
    private final PlayerController playerController;
    private final KeyInputManager keyInputManager;
    private final GameInteractionManager gameInteractionManager;
    private final SaveGameManager saveGameManager;

    private  JPanel structureRow;
    private JLabel lastSelectedLabel;

    public UIHandler(GameMapHandler gameMapHandler, ResourceManager resourceManager, UpdateManager updateManager, PlayerController playerController, KeyInputManager keyInputManager){
        this.gameMapHandler = gameMapHandler;
        this.resourceManager = resourceManager;
        this.updateManager = updateManager;
        this.playerController = playerController;
        this.keyInputManager = keyInputManager;
        this.gameInteractionManager = new GameInteractionManager(gameMapHandler, playerController);
        this.saveGameManager = new SaveGameManager(gameMapHandler, resourceManager);
    }
    
    public void initialize() {
        JFrame frame = new JFrame("NHF3");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setMinimumSize(new Dimension((int) (screenSize.width * 0.6), (int) (screenSize.height * 0.6)));
        frame.setLocationRelativeTo(null);
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        initializeTop(frame);

        initializeGamePanel(frame);

        initializeResourcePanel(frame);

        initializeBottomRow(frame);

        frame.setVisible(true);

    }

    private void initializeResourcePanel(JFrame frame) {
        JPanel resourcePanel = new JPanel();
        resourcePanel.setLayout(new BoxLayout(resourcePanel, BoxLayout.Y_AXIS));

        ResourceDisplay woodDisplay = new ResourceDisplay(ResourceType.WOOD, resourceManager);
        updateManager.register(woodDisplay);
        resourcePanel.add(woodDisplay);

        ResourceDisplay stoneDisplay = new ResourceDisplay(ResourceType.STONE, resourceManager);
        updateManager.register(stoneDisplay);
        resourcePanel.add(stoneDisplay);

        ResourceDisplay ironDisplay = new ResourceDisplay(ResourceType.IRON, resourceManager);
        updateManager.register(ironDisplay);
        resourcePanel.add(ironDisplay);

        frame.add(resourcePanel, BorderLayout.WEST);
    }

    private void initializeGamePanel(JFrame frame) {
        GamePanel gamePanel = new GamePanel(gameMapHandler, playerController, gameInteractionManager, keyInputManager);     
        updateManager.register(gamePanel);   
        frame.add(gamePanel, BorderLayout.CENTER);
    }

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
        addPlaceHolder(saveField, "File Name...");
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

    private void initializeTop(JFrame frame) {
        JPanel top = new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
        JPanel buttonRow = new JPanel(new FlowLayout());

        JButton buildButton = new JButton("Build");
        buildButton.setFocusable(false);
        buildButton.addActionListener(event -> { 
            playerController.setInteractionMode(PlayerController.PlayerInteractionMode.BUILD);
            structureRow.setVisible(true);
        });
        buttonRow.add(buildButton);

        JButton destroyButton = new JButton("Destroy");
        destroyButton.setFocusable(false);
        destroyButton.addActionListener(event -> { 
            playerController.setInteractionMode(PlayerController.PlayerInteractionMode.DESTROY);
            structureRow.setVisible(false);
        });
        buttonRow.add(destroyButton);
       top.add(buttonRow);

        structureRow = new JPanel(new FlowLayout());
        for (StructureType structure : StructureType.values()) {
            JLabel imageLabel = new JLabel(new ImageIcon(TextureManager.getStructure(structure).getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
            imageLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    selectLabel(structure, imageLabel);
                }
            });

            if (structure == StructureType.CENTER) {
                selectLabel(structure, imageLabel);
            }

            structureRow.add(imageLabel);
        }
        top.add(structureRow);

        frame.add(top, BorderLayout.NORTH);
    }

    private void selectLabel(StructureType structure, JLabel imageLabel) {
        if (lastSelectedLabel != null)
            lastSelectedLabel.setBorder(BorderFactory.createEmptyBorder());
        playerController.selectStructure(structure);
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        lastSelectedLabel = imageLabel;
    }

    private static void addPlaceHolder(JTextField field, String placeHolderText) {
        Color placeHolderColor = Color.LIGHT_GRAY;
        field.setForeground(placeHolderColor);
        field.setText(placeHolderText);

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent event) {
                if (field.getForeground() == placeHolderColor) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent event) {
                if (field.getText().isEmpty()) {
                    field.setForeground(placeHolderColor);
                    field.setText(placeHolderText);
                }
            }
        });
    }
}
