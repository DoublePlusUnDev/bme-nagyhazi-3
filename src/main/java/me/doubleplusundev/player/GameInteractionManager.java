package me.doubleplusundev.player;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import me.doubleplusundev.map.GameMapHandler;
import me.doubleplusundev.map.structures.StructureType;
import me.doubleplusundev.util.Config;
import me.doubleplusundev.util.Vector2;

public class GameInteractionManager implements MouseListener {
    private static GameInteractionManager instance;
    private int tileSize;

    private GameInteractionManager() {
        tileSize = Config.getInt("tile_render_size", 40);
    }
    
    public static GameInteractionManager getInstance() {
        if (instance == null) {
            instance = new GameInteractionManager();
        }

        return instance;
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        int x = pixelToTile(event.getPoint()).x;
        int y = pixelToTile(event.getPoint()).y;
        if (PlayerController.getInstance().getInteractionMode() == PlayerController.PlayerInteractionMode.BUILD)
            GameMapHandler.getInstance().buildStructure(x, y, PlayerController.getInstance().getSelectedStructure());
        else
            GameMapHandler.getInstance().destroyWorldObject(x, y);
    }

    @Override
    public void mousePressed(MouseEvent event) {

    }

    @Override
    public void mouseReleased(MouseEvent event) {

    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }

    @Override
    public void mouseExited(MouseEvent event) {

    }

    private Point pixelToTile(Point pixelCoords) {
        Vector2 playerPosition = PlayerController.getInstance().getPosition();
        return new Point((int)Math.floor((double)pixelCoords.x / tileSize + playerPosition.x), (int)Math.floor((double)pixelCoords.y / tileSize + playerPosition.y));
    }
}
