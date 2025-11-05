package me.doubleplusundev.player;

import java.awt.event.KeyEvent;

import me.doubleplusundev.game.IUpdatable;
import me.doubleplusundev.game.UpdateManager;
import me.doubleplusundev.util.Config;
import me.doubleplusundev.util.Vector2;

public class PlayerController implements IUpdatable {
    private static PlayerController instance;
    
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
        boolean upPressed = InputManager.getInstance().isKeyPressed(KeyEvent.VK_W) || InputManager.getInstance().isKeyPressed(KeyEvent.VK_UP);
        boolean downPressed = InputManager.getInstance().isKeyPressed(KeyEvent.VK_S) || InputManager.getInstance().isKeyPressed(KeyEvent.VK_DOWN);
        boolean leftPressed = InputManager.getInstance().isKeyPressed(KeyEvent.VK_A) || InputManager.getInstance().isKeyPressed(KeyEvent.VK_LEFT);
        boolean rightPressed = InputManager.getInstance().isKeyPressed(KeyEvent.VK_D) || InputManager.getInstance().isKeyPressed(KeyEvent.VK_RIGHT);

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

    
}
