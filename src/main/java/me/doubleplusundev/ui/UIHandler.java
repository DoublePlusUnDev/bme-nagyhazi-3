package me.doubleplusundev.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import me.doubleplusundev.player.GameInteractionManager;
import me.doubleplusundev.player.KeyInputManager;
import me.doubleplusundev.resource.ResourceType;

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
    
    public void initialize() {
        JFrame frame = new JFrame("NHF3");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setMinimumSize(new Dimension((int) (screenSize.width * 0.6), (int) (screenSize.height * 0.6)));
        frame.setLocationRelativeTo(null);
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        initializeTopRow(frame);

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
        bottomRow.add(quitButton);
    }

    private void initializeTopRow(JFrame frame) {
        JPanel topRow = new JPanel(new FlowLayout());

        JButton buildButton = new JButton("Build");
        buildButton.setFocusable(false);
        topRow.add(buildButton);

        JButton destroyButton = new JButton("Destroy");
        destroyButton.setFocusable(false);
        topRow.add(destroyButton);
        
        frame.add(topRow, BorderLayout.NORTH);
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

            public void focusLost(FocusEvent event) {
                if (field.getText().isEmpty()) {
                    field.setForeground(placeHolderColor);
                    field.setText(placeHolderText);
                }
            }
        });
    }
}
