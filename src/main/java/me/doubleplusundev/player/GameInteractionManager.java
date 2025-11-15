package me.doubleplusundev.player;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import me.doubleplusundev.map.GameMapHandler;
import me.doubleplusundev.map.worldobject.WorldObjectFactory;
import me.doubleplusundev.map.worldobject.component.BuildingComponent;
import me.doubleplusundev.resource.ResourceManager;
import me.doubleplusundev.util.Config;
import me.doubleplusundev.util.Vector2;

/**
 * Handles player interaction with the map.
 */
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

    /**
     * On mouseclick an interaction will be attempted with the map, depending on the currently selected interaction mode.
     */
    @Override
    public void mouseClicked(MouseEvent event) {
        int x = pixelToTile(event.getPoint()).x;
        int y = pixelToTile(event.getPoint()).y;
        if (playerController.getInteractionMode() == PlayerController.PlayerInteractionMode.BUILD)
            gameMapHandler.tryBuild(x, y, worldObjectFactory.create(playerController.getSelectedBuilding()).getComponent(BuildingComponent.class), resourceManager.getResources());
        else
            gameMapHandler.tryHarvest(x, y, resourceManager.getResources());
    }

    /**
     * Converts a pixel coordinate to the corresponding tile's coordinate.
     * @param pixelCoords Pixel coordinates.
     * @return Tile position.
     */
    private Point pixelToTile(Point pixelCoords) {
        Vector2 playerPosition = playerController.getPosition();
        return new Point((int)Math.floor((double)pixelCoords.x / tileSize + playerPosition.getX()), (int)Math.floor((double)pixelCoords.y / tileSize + playerPosition.getY()));
    }
}
