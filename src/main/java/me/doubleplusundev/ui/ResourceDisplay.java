package me.doubleplusundev.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import me.doubleplusundev.game.IUpdatable;
import me.doubleplusundev.game.UpdateManager;
import me.doubleplusundev.resource.ResourceManager;
import me.doubleplusundev.resource.ResourceType;
import me.doubleplusundev.util.TextureManager;

public class ResourceDisplay extends JPanel implements IUpdatable {
    private ResourceType type;
    
    private JTextArea amountText;

    public ResourceDisplay(ResourceType type) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        this.type = type;

        UpdateManager.getInstance().register(this);

        JLabel imageLabel = new JLabel(new ImageIcon(TextureManager.getInstance().getResource(type)));
        add(imageLabel);
        amountText = new JTextArea();
        amountText.setEditable(false);
        amountText.setFocusable(false);
        amountText.setMaximumSize(new Dimension(200, amountText.getPreferredSize().height));
        amountText.setFont(new Font("Monospaced", Font.PLAIN, 12));
        amountText.setOpaque(false);
        add(amountText);
    }



    private void updateAmount() {
        amountText.setText(String.format("%10s", String.valueOf(ResourceManager.getInstance().getResource(type))));
    }



    @Override
    public void update() {
        updateAmount();
    }
}
