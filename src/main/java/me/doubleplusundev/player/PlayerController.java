package me.doubleplusundev.player;

import java.awt.event.KeyEvent;

import me.doubleplusundev.game.IUpdatable;
import me.doubleplusundev.game.UpdateManager;
import me.doubleplusundev.map.worldobject.WorldObjectType;
import me.doubleplusundev.util.Config;
import me.doubleplusundev.util.Vector2;

/**
 * The class responsible for controlling the player's position and interaction mode.
 */
public class PlayerController implements IUpdatable {
    /**
     * The enum that determines what happens on an interaction with the map.
     */
    public enum PlayerInteractionMode {
        BUILD,
        DESTROY
    }

    private final UpdateManager updateManager;
    private final KeyInputManager keyInputManager;

    private PlayerInteractionMode interactionMode = PlayerInteractionMode.BUILD; /** The current interaction mode. */
    private WorldObjectType selectedBuilding = WorldObjectType.CENTER; /** If the interaction needs a building type argument this will be used. */

    private final Vector2 position; /** Players map position, floating point number must be floored to get corresponding tile. */
    private final double speed; /** The hover speed over the map. */

    public  PlayerController(UpdateManager updateManager, KeyInputManager keyInputManager) {
        this.updateManager = updateManager;
        this.keyInputManager = keyInputManager;

        position = new Vector2();
        speed = Config.getInt("player_speed", 10);
    }

    /**
     * Updates the player's position each update.
     */
    @Override
    public void update() {
        updatePlayerPosition();
    }

    /**
     * Updates the player's position based on the pressed down keys adjusted for frame time.
     */
    private void updatePlayerPosition() {
        boolean upPressed = keyInputManager.isKeyPressed(KeyEvent.VK_W) || keyInputManager.isKeyPressed(KeyEvent.VK_UP);
        boolean downPressed = keyInputManager.isKeyPressed(KeyEvent.VK_S) || keyInputManager.isKeyPressed(KeyEvent.VK_DOWN);
        boolean leftPressed = keyInputManager.isKeyPressed(KeyEvent.VK_A) || keyInputManager.isKeyPressed(KeyEvent.VK_LEFT);
        boolean rightPressed = keyInputManager.isKeyPressed(KeyEvent.VK_D) || keyInputManager.isKeyPressed(KeyEvent.VK_RIGHT);

        if (upPressed && !downPressed){
            position.setY(position.getY() - speed * updateManager.getDeltaTime());
        }
        else if (downPressed && !upPressed){
            position.setY(position.getY() + speed * updateManager.getDeltaTime());
        }

        if (leftPressed && !rightPressed){
            position.setX(position.getX() - speed * updateManager.getDeltaTime());
        }
        else if (rightPressed && !leftPressed){
            position.setX(position.getX() + speed * updateManager.getDeltaTime());
        }
    }

    /**
     * Getter forthe player's floating point position. 
     * @return The player's floating point position.
     */
    public Vector2 getPosition(){
        return position;
    }

    /**
     * Getter for the interactin mode.
     * @return The interaction mode.
     */
    public PlayerInteractionMode getInteractionMode() {
        return interactionMode;
    }
    
    /**
     * Setter for the interaction mode.
     * @param mode The interaction mode.
     */
    public void setInteractionMode(PlayerInteractionMode mode) {
        this.interactionMode = mode;
    }    

    /**
     * Getter for the selected building.
     * @return The selected building.
     */
    public WorldObjectType getSelectedBuilding() {
        return selectedBuilding;
    }
    
    /**
     * Setter for the selected building.
     * @param type The type of the selected building.
     */
    public void selectBuilding(WorldObjectType type) {
        this.selectedBuilding = type;
    }
}
