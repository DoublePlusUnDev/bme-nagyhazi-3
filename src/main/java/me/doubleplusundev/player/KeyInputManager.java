package me.doubleplusundev.player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

/**
 * Used for accessing the state of keyboard buttons.
 */
public class KeyInputManager extends KeyAdapter {    
    Set<Integer> pressedKeys; /** The set of currently pressed keys. */

    public KeyInputManager() {
        pressedKeys = new HashSet<>();   
    }

    /**
     * Query whether the key correspoding nto the given kecode is pressed down.
     * @param keyCode The keycode of the queried key.
     * @return The state of the key.
     */
    public boolean isKeyPressed(int keyCode){
        return pressedKeys.contains(keyCode);
    }

    /**
     * Keypress event, added to the pressed keys.
     */
    @Override
    public void keyPressed(KeyEvent event) {
        pressedKeys.add(event.getKeyCode());
    }

    /**
     * Key release event, removed from the pressed keys.
     */
    @Override
    public void keyReleased(KeyEvent event) {
        pressedKeys.remove(event.getKeyCode());
    }
}  
