package me.doubleplusundev.player;

import java.awt.event.KeyEvent;

import me.doubleplusundev.game.IUpdatable;
import me.doubleplusundev.game.UpdateManager;
import me.doubleplusundev.map.structures.Structure;
import me.doubleplusundev.map.structures.StructureType;
import me.doubleplusundev.util.Config;
import me.doubleplusundev.util.Vector2;

public class PlayerController implements IUpdatable {
    public enum PlayerInteractionMode {
        BUILD,
        DESTROY
    }

    private static PlayerController instance;
    private PlayerInteractionMode interactionMode = PlayerInteractionMode.BUILD;
    private StructureType selectedStructure;

    private final Vector2 position;
    private final double speed;

    private PlayerController() {
        position = new Vector2();
        speed = (double)Config.getInt("player_speed", 10);

        UpdateManager.getInstance().register(this);
    }

    public static PlayerController getInstance(){
        if (instance == null){
            instance = new PlayerController();
        }
        return instance;
    }

    public Vector2 getPosition(){
        return position;
    }

    @Override
    public void update() {
        boolean upPressed = KeyInputManager.getInstance().isKeyPressed(KeyEvent.VK_W) || KeyInputManager.getInstance().isKeyPressed(KeyEvent.VK_UP);
        boolean downPressed = KeyInputManager.getInstance().isKeyPressed(KeyEvent.VK_S) || KeyInputManager.getInstance().isKeyPressed(KeyEvent.VK_DOWN);
        boolean leftPressed = KeyInputManager.getInstance().isKeyPressed(KeyEvent.VK_A) || KeyInputManager.getInstance().isKeyPressed(KeyEvent.VK_LEFT);
        boolean rightPressed = KeyInputManager.getInstance().isKeyPressed(KeyEvent.VK_D) || KeyInputManager.getInstance().isKeyPressed(KeyEvent.VK_RIGHT);

        if (upPressed && !downPressed){
            position.y -= speed * UpdateManager.getInstance().getDeltaTime();
        }
        else if (downPressed && !upPressed){
            position.y += speed * UpdateManager.getInstance().getDeltaTime();
        }

        if (leftPressed && !rightPressed){
            position.x -= speed * UpdateManager.getInstance().getDeltaTime();
        }
        else if (rightPressed && !leftPressed){
            position.x += speed * UpdateManager.getInstance().getDeltaTime();
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
