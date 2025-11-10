package me.doubleplusundev.player;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import me.doubleplusundev.map.GameMapHandler;
import me.doubleplusundev.util.Config;
import me.doubleplusundev.util.Vector2;

public class GameInteractionManager extends MouseAdapter{
    private final GameMapHandler gameMapHandler;
    private final PlayerController playerController;

    private final int tileSize;

    public GameInteractionManager(GameMapHandler gameMapHandler, PlayerController playerController) {
        this.gameMapHandler = gameMapHandler;
        this.playerController = playerController;

        tileSize = Config.getInt("tile_render_size", 40);
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        int x = pixelToTile(event.getPoint()).x;
        int y = pixelToTile(event.getPoint()).y;
        if (playerController.getInteractionMode() == PlayerController.PlayerInteractionMode.BUILD)
            gameMapHandler.buildStructure(x, y, playerController.getSelectedStructure());
        else
            gameMapHandler.destroyWorldObject(x, y);
    }

    private Point pixelToTile(Point pixelCoords) {
        Vector2 playerPosition = playerController.getPosition();
        return new Point((int)Math.floor((double)pixelCoords.x / tileSize + playerPosition.x), (int)Math.floor((double)pixelCoords.y / tileSize + playerPosition.y));
    }
}
