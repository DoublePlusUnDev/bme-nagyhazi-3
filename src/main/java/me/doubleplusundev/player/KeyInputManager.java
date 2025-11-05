package me.doubleplusundev.player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;

public class KeyInputManager implements KeyListener {
    private static KeyInputManager instance;
    
    Set<Integer> pressedKeys;

    private KeyInputManager() {
        pressedKeys = new HashSet<>();   
    }

    public static KeyInputManager getInstance() {
        if (instance == null)
            instance = new KeyInputManager();
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
