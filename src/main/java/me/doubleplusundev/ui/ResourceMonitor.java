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

/**
 * A GUI element comprised of the resource image, the current amount and the change in the amount laid out in a row.
 * The values are update in each update call.
 */
public class ResourceMonitor extends JPanel implements IUpdatable {    
    private final transient ResourceManager resourceManager;
    
    private final ResourceType type;
    
    private JTextArea amountText;
    private JTextArea changeText;
    
    private final int tickRate;
    private double currentValue;
    private final LinkedList<Double> previousValues = new LinkedList<>(); /** A linked list of previous values used for tracking change. */
    private static final int TRACKLENGTH = 113; /** Number of values tracked. */

    public ResourceMonitor(ResourceType type, ResourceManager resourceManager) {
        super();

        this.resourceManager = resourceManager;

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        this.type = type;

        tickRate = Config.getInt("tick_speed", 20);
        setupUI();
    }

    /**
     * Creates the elements tracking the value and delta.
     */
    private void setupUI() {
        JLabel imageLabel = new JLabel(new ImageIcon(TextureManager.getResource(type)));
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

    /**
     * Each updates the newest values and fetched and the delta is recalculated.
     */
    @Override
    public void update() {
        currentValue = resourceManager.getResource(type);
        updateAmount();
        updateChange();
        previousValues.addFirst(currentValue);
        if (previousValues.size() > TRACKLENGTH)
            previousValues.removeLast();
    }

    /**
     * Updates the current value.
     */
    private void updateAmount() {
        amountText.setText(String.format("%10.1f", currentValue));
    }

    /**
     * Calculates the rate of change based on past data and updates the data.
     */
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
        double changeRate = totalChange / TRACKLENGTH * tickRate;
        changeText.setText(String.format("%10.1f", changeRate));
    }
}
