package me.doubleplusundev.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import me.doubleplusundev.game.IUpdatable;
import me.doubleplusundev.resource.ResourceManager;
import me.doubleplusundev.resource.ResourceType;
import me.doubleplusundev.util.Config;
import me.doubleplusundev.util.TextureManager;

public class ResourceMonitor extends JPanel implements IUpdatable {    
    private final transient ResourceManager resourceManager;
    
    private final ResourceType type;
    
    private JTextArea amountText;
    private JTextArea changeText;
    
    private final int tickRate;
    private double currentValue;
    private LinkedList<Double> previousValues = new LinkedList<>();
    private int trackLength = 113;

    public ResourceMonitor(ResourceType type, ResourceManager resourceManager) {
        super();

        this.resourceManager = resourceManager;

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        this.type = type;

        tickRate = Config.getInt("tick_speed", 20);
        setupUI(type);

        
    }

    private void setupUI(ResourceType type1) {
        JLabel imageLabel = new JLabel(new ImageIcon(TextureManager.getResource(type1)));
        add(imageLabel);

        amountText = new JTextArea();
        amountText.setEditable(false);
        amountText.setFocusable(false);
        amountText.setMaximumSize(new Dimension(200, amountText.getPreferredSize().height));
        amountText.setFont(new Font("Monospaced", Font.PLAIN, 12));
        amountText.setOpaque(false);
        add(amountText);

        changeText = new JTextArea();
        changeText.setEditable(false);
        changeText.setFocusable(false);
        changeText.setMaximumSize(new Dimension(200, changeText.getPreferredSize().height));
        changeText.setFont(new Font("Monospaced", Font.PLAIN, 12));
        changeText.setOpaque(false);
        add(changeText); 
    }

    @Override
    public void update() {
        currentValue = resourceManager.getResource(type);
        updateAmount();
        updateChange();
        previousValues.addFirst(currentValue);
        if (previousValues.size() > trackLength)
            previousValues.removeLast();
    }

    private void updateAmount() {
        amountText.setText(String.format("%10.1f", currentValue));
    }

    private void updateChange() {
        double totalChange = 0;
        double previousValue = 0;
        boolean isFirst = true;
        for (double value : previousValues) {
            if (!isFirst)
                totalChange += (previousValue - value);

            isFirst = false;
            previousValue = value;
        }
        double changeRate = totalChange / trackLength * tickRate;
        changeText.setText(String.format("%10.1f", changeRate));
    }
}
