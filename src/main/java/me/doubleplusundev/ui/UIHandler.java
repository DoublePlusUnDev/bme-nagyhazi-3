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

import me.doubleplusundev.map.structures.StructureType;
import me.doubleplusundev.player.PlayerController;
import me.doubleplusundev.resource.ResourceType;
import me.doubleplusundev.util.TextureManager;

public class UIHandler {
    private static UIHandler instance;
    private  JPanel structureRow;
    private JLabel lastSelectedLabel;

    private UIHandler(){

    }

    public static UIHandler getInstance() {
        if (instance == null){
            instance = new UIHandler();
        }
        return instance;
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

        ResourceDisplay woodDisplay = new ResourceDisplay(ResourceType.WOOD);
        resourcePanel.add(woodDisplay);

        ResourceDisplay stoneDisplay = new ResourceDisplay(ResourceType.STONE);
        resourcePanel.add(stoneDisplay);

        ResourceDisplay ironDisplay = new ResourceDisplay(ResourceType.IRON);
        resourcePanel.add(ironDisplay);

        frame.add(resourcePanel, BorderLayout.WEST);
    }

    private void initializeGamePanel(JFrame frame) {
        GamePanel gamePanel = new GamePanel();        
        frame.add(gamePanel, BorderLayout.CENTER);
    }

    private void initializeBottomRow(JFrame frame) {
        JPanel bottomRow = new JPanel(new FlowLayout());

        JButton saveMapButton = new JButton("Save Map");
        saveMapButton.setFocusable(false);
        bottomRow.add(saveMapButton);

        JTextField saveField = new JTextField(20);
        saveField.setFocusable(true);
        addPlaceHolder(saveField, "File Name...");
        bottomRow.add(saveField);

        JButton loadMapButton = new JButton("Load Map");
        saveMapButton.setFocusable(false);
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
            PlayerController.getInstance().setInteractionMode(PlayerController.PlayerInteractionMode.BUILD);structureRow.setVisible(false);
            structureRow.setVisible(true);
        });
        buttonRow.add(buildButton);

        JButton destroyButton = new JButton("Destroy");
        destroyButton.setFocusable(false);
        destroyButton.addActionListener(event -> { 
            PlayerController.getInstance().setInteractionMode(PlayerController.PlayerInteractionMode.DESTROY);
            structureRow.setVisible(false);
        });
        buttonRow.add(destroyButton);
        top.add(buttonRow);

        structureRow = new JPanel(new FlowLayout());
        for (StructureType structure : StructureType.values()) {
            JLabel imageLabel = new JLabel(new ImageIcon(TextureManager.getInstance().getStructure(structure).getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
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
        PlayerController.getInstance().selectStructure(structure);
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
