package me.doubleplusundev.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import me.doubleplusundev.game.IUpdatable;
import me.doubleplusundev.map.GameMapHandler;
import me.doubleplusundev.map.TileType;
import me.doubleplusundev.map.WorldObject;
import me.doubleplusundev.map.resourcenodes.ResourceNode;
import me.doubleplusundev.map.structures.Structure;
import me.doubleplusundev.player.GameInteractionManager;
import me.doubleplusundev.player.KeyInputManager;
import me.doubleplusundev.player.PlayerController;
import me.doubleplusundev.util.Config;
import me.doubleplusundev.util.TextureManager;
import me.doubleplusundev.util.Vector2;

public class GamePanel extends JPanel implements IUpdatable {
    private final transient PlayerController playerController;
    private final transient GameMapHandler gameMapHandler;
    private final int tileSize;

    public GamePanel(GameMapHandler gameMapHandler, PlayerController playerController, GameInteractionManager gameInteractionManager) {
        super();
        this.gameMapHandler = gameMapHandler;
        this.playerController = playerController;

        setFocusable(true);
        addMouseListener(gameInteractionManager);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                requestFocus();
                super.mouseClicked(arg0);
            }
        });
        addKeyListener(KeyInputManager.getInstance());
    
        tileSize = Config.getInt("tile_render_size", 40);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics = (Graphics2D)g;

        Vector2 playerPosition = playerController.getPosition();
        double leftXCoord = playerPosition.x;
        double topYCoord = playerPosition.y;
 
        int leftXFloor = (int)Math.floor(leftXCoord);
        int topYFloor = (int)Math.floor(topYCoord);

        for (int x = leftXFloor; (x - leftXFloor - 1) * tileSize < getWidth(); x++){
            for (int y = topYFloor; (y - topYFloor - 1) * tileSize < getHeight(); y++){
                TileType tile = gameMapHandler.getTile(x, y);
                WorldObject worldObject = gameMapHandler.getWorldObject(x, y);

                int drawX = (int) Math.round((x - leftXCoord) * tileSize);
                int drawY = (int) Math.round((y - topYCoord ) * tileSize);

                graphics.drawImage(TextureManager.getTile(tile), drawX, drawY, tileSize, tileSize, null);
            
                if (worldObject instanceof Structure structure) 
                    graphics.drawImage(TextureManager.getStructure(structure.getType()), drawX, drawY, tileSize, tileSize, null);
                
                if (worldObject instanceof ResourceNode resourceNode) 
                    graphics.drawImage(TextureManager.getResourceNode(resourceNode.getType()), drawX, drawY, tileSize, tileSize, null);
                
            }
        }
    }

    @Override
    public void update() {
        repaint();
    }
}