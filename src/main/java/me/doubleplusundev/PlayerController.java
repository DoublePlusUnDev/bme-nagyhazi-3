package me.doubleplusundev;

import java.awt.event.KeyEvent;

import me.doubleplusundev.util.InputManager;
import me.doubleplusundev.util.Vector2;

public class PlayerController implements IUpdatable {
    private static PlayerController instance;
    
    private Vector2 position;
    private double speed;

    private PlayerController() {
        position = new Vector2();
        speed = (double)Config.getInt("player_speed", 10);
    }

    public static PlayerController getInstance(){
        if (instance == null){
            instance = new PlayerController();
            UpdateManager.getInstance().register(instance);
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

        System.out.println(position + " " + speed);
    }

    
}
