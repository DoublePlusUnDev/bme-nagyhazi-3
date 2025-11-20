package me.doubleplusundev.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import me.doubleplusundev.game.IUpdatable;
import me.doubleplusundev.map.GameMapHandler;
import me.doubleplusundev.map.tiles.TileType;
import me.doubleplusundev.map.worldobject.WorldObject;
import me.doubleplusundev.map.worldobject.component.TypeComponent;
import me.doubleplusundev.player.PlayerInteractionManager;
import me.doubleplusundev.player.KeyInputManager;
import me.doubleplusundev.player.PlayerController;
import me.doubleplusundev.util.Config;
import me.doubleplusundev.util.TextureManager;
import me.doubleplusundev.util.Vector2;

/**
 * The panel rendering the actual game.
 * Fetches map data to draw the tiles and worldobjects.
 * Displays the chunk of the map around the players position.
 */
public class GamePanel extends JPanel implements IUpdatable {
    private final transient PlayerController playerController;
    private final transient GameMapHandler gameMapHandler;
    private final int tileSize;

    public GamePanel(GameMapHandler gameMapHandler, PlayerController playerController, PlayerInteractionManager playerInteractionManager, KeyInputManager keyInputManager) {
        super();
        this.gameMapHandler = gameMapHandler;
        this.playerController = playerController;

        setFocusable(true);
        addMouseListener(playerInteractionManager);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                requestFocus();
                super.mouseClicked(arg0);
            }
        });
        addKeyListener(keyInputManager);
    
        tileSize = Config.getInt("tile_render_size", 40);
    }

    /**
     * Overwrites the paintcomponent functions in order to render the sprites for tiles and worldobjects.
     * Top left corner at the player position.
     * Worldobjects are drawn after tiles, naturally they appear on top of them.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics = (Graphics2D)g;

        double coordWidth = (double)getWidth() / tileSize;
        double coordHeight = (double)getHeight() / tileSize;

        Vector2 origin = playerController.getPosition();
        
        int leftXFloor = (int)Math.floor(origin.getX());
        int topYFloor = (int)Math.floor(origin.getY());

        int rightXCeil = (int)Math.ceil(origin.getX() + coordWidth);
        int bottomYCeil = (int)Math.ceil(origin.getY() + coordHeight);

        for (int x = leftXFloor; x < rightXCeil; x++){
            for (int y = topYFloor; y < bottomYCeil; y++){
                TileType tile = gameMapHandler.getTile(x, y);
                WorldObject worldObject = gameMapHandler.getWorldObject(x, y);

                int drawX = (int) Math.round((x - (origin.getX())) * tileSize);
                int drawY = (int) Math.round((y - (origin.getY())) * tileSize);

                graphics.drawImage(TextureManager.getTile(tile), drawX, drawY, tileSize, tileSize, null);
            
                if (worldObject != null)
                    graphics.drawImage(TextureManager.getWorldObject(worldObject.getComponent(TypeComponent.class).getType()), drawX, drawY, tileSize, tileSize, null);
            }
        }
    }

    /**
     * Rerenders on each update.
     */
    @Override
    public void update() {
        repaint();
    }
}