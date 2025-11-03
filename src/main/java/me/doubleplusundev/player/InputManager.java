package me.doubleplusundev.player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class InputManager implements KeyListener {
    private static InputManager instance;
    
    Set<Integer> pressedKeys;

    private InputManager() {
        pressedKeys = new HashSet<>();   
    }

    public static InputManager getInstance() {
        if (instance == null)
            instance = new InputManager();
        return instance;
    }

    public boolean isKeyPressed(int keyCode){
        return pressedKeys.contains(keyCode);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        pressedKeys.add(event.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent event) {
        pressedKeys.remove(event.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent event){

    }
}  
