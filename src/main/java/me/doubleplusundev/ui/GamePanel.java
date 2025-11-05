package me.doubleplusundev.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import me.doubleplusundev.game.IUpdatable;
import me.doubleplusundev.game.UpdateManager;
import me.doubleplusundev.map.GameMapHandler;
import me.doubleplusundev.map.TileType;
import me.doubleplusundev.map.WorldObject;
import me.doubleplusundev.map.resourcenodes.ResourceNode;
import me.doubleplusundev.map.structures.Structure;
import me.doubleplusundev.player.PlayerController;
import me.doubleplusundev.util.Config;
import me.doubleplusundev.util.TextureManager;
import me.doubleplusundev.util.Vector2;

public class GamePanel extends JPanel implements IUpdatable {
    private int tileSize;

    public GamePanel() {
        setFocusable(true);

        UpdateManager.getInstance().register(this);
    
        tileSize = Config.getInt("tile_render_size", 40);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics = (Graphics2D)g;

        Vector2 playerPosition = PlayerController.getInstance().getPosition();
        double leftXCoord = playerPosition.x;
        double topYCoord = playerPosition.y;
 
        int leftXFloor = (int)Math.floor(leftXCoord);
        int topYFloor = (int)Math.floor(topYCoord);

        for (int x = leftXFloor; (x - leftXFloor - 1) * tileSize < getWidth(); x++){
            for (int y = topYFloor; (y - topYFloor - 1) * tileSize < getHeight(); y++){
                TileType tile = GameMapHandler.getInstance().getTile(x, y);
                WorldObject worldObject = GameMapHandler.getInstance().getWorldObject(x, y);

                int drawX = (int) Math.floor((x - leftXCoord) * tileSize);
                int drawY = (int) Math.floor((y - topYCoord ) * tileSize);

                graphics.drawImage(TextureManager.getInstance().getTile(tile), drawX, drawY, tileSize, tileSize, null);
            
                if (worldObject instanceof Structure structure) {
                    graphics.drawImage(TextureManager.getInstance().getStructure(structure.getType()), drawX, drawY, tileSize, tileSize, null);
                }
                else if (worldObject instanceof ResourceNode resourceNode) {
                    graphics.drawImage(TextureManager.getInstance().getResourceNode(resourceNode.getType()), drawX, drawY, tileSize, tileSize, null);
                }
            }
        }
    }

    @Override
    public void update() {
        repaint();
    }
}