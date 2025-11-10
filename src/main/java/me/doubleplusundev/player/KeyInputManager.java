package me.doubleplusundev.player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class KeyInputManager extends KeyAdapter {    
    Set<Integer> pressedKeys;

    public KeyInputManager() {
        pressedKeys = new HashSet<>();   
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
}  
