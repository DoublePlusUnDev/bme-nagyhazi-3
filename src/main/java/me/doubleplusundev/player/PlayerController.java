package me.doubleplusundev.player;

import java.awt.event.KeyEvent;

import me.doubleplusundev.game.IUpdatable;
import me.doubleplusundev.game.UpdateManager;
import me.doubleplusundev.map.structures.StructureType;
import me.doubleplusundev.util.Config;
import me.doubleplusundev.util.Vector2;

public class PlayerController implements IUpdatable {
    public enum PlayerInteractionMode {
        BUILD,
        DESTROY
    }

    private final UpdateManager updateManager;
    private final KeyInputManager keyInputManager;

    private PlayerInteractionMode interactionMode = PlayerInteractionMode.BUILD;
    private StructureType selectedStructure = StructureType.CENTER;

    private final Vector2 position;
    private final double speed;

    public  PlayerController(UpdateManager updateManager, KeyInputManager keyInputManager) {
        this.updateManager = updateManager;
        this.keyInputManager = keyInputManager;

        position = new Vector2();
        speed = Config.getInt("player_speed", 10);
    }

    public Vector2 getPosition(){
        return position;
    }

    @Override
    public void update() {
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

    public PlayerInteractionMode getInteractionMode() {
        return interactionMode;
    }
    
    public void setInteractionMode(PlayerInteractionMode mode) {
        this.interactionMode = mode;
    }    

    public void selectStructure(StructureType type) {
        this.selectedStructure = type;
    }

    public StructureType getSelectedStructure() {
        return selectedStructure;
    }
}
