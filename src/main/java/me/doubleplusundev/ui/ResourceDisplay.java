package me.doubleplusundev.ui;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import me.doubleplusundev.game.IUpdatable;
import me.doubleplusundev.game.ResourceManager;
import me.doubleplusundev.game.UpdateManager;
import me.doubleplusundev.resource.ResourceType;
import me.doubleplusundev.util.TextureManager;

public class ResourceDisplay extends JPanel implements IUpdatable {
    private ResourceType type;
    
    private JTextArea amountText;

    public ResourceDisplay(ResourceType type) {
        super(new BorderLayout());

        this.type = type;

        UpdateManager.getInstance().register(this);

        JLabel imageLabel = new JLabel(new ImageIcon(TextureManager.getInstance().getResource(type)));
        add(imageLabel, BorderLayout.WEST);
        amountText = new JTextArea(" 0");
        amountText.setEditable(false);
        amountText.setFocusable(false);
        add(amountText, BorderLayout.EAST);
    }



    private void updateAmount() {
        amountText.setText(String.valueOf(ResourceManager.getInstance().getResource(type)));
    }



    @Override
    public void update() {
        updateAmount();
    }
}
