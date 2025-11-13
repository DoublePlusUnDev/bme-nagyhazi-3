package me.doubleplusundev.player;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import me.doubleplusundev.map.GameMapHandler;
import me.doubleplusundev.map.worldobject.WorldObjectFactory;
import me.doubleplusundev.resource.ResourceManager;
import me.doubleplusundev.util.Config;
import me.doubleplusundev.util.Vector2;

public class GameInteractionManager extends MouseAdapter{
    private final GameMapHandler gameMapHandler;
    private final PlayerController playerController;
    private final ResourceManager resourceManager;
    private final WorldObjectFactory worldObjectFactory;

    private final int tileSize;

    public GameInteractionManager(GameMapHandler gameMapHandler, PlayerController playerController, ResourceManager resourceManager, WorldObjectFactory worldObjectFactory) {
        this.gameMapHandler = gameMapHandler;
        this.playerController = playerController;
        this.resourceManager = resourceManager;
        this.worldObjectFactory = worldObjectFactory;

        tileSize = Config.getInt("tile_render_size", 40);
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        int x = pixelToTile(event.getPoint()).x;
        int y = pixelToTile(event.getPoint()).y;
        if (playerController.getInteractionMode() == PlayerController.PlayerInteractionMode.BUILD)
            gameMapHandler.tryBuildStructure(x, y, worldObjectFactory.create(playerController.getSelectedBuilding(), x, y), resourceManager);
        else
            gameMapHandler.destroyWorldObject(x, y);
    }

    private Point pixelToTile(Point pixelCoords) {
        Vector2 playerPosition = playerController.getPosition();
        return new Point((int)Math.floor((double)pixelCoords.x / tileSize + playerPosition.getX()), (int)Math.floor((double)pixelCoords.y / tileSize + playerPosition.getY()));
    }
}
