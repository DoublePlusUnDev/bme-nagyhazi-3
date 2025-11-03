
package me.doubleplusundev;

import me.doubleplusundev.game.UpdateManager;
import me.doubleplusundev.player.PlayerController;
import me.doubleplusundev.ui.UIHandler;

public class Main {
    public static void main(String[] args) {
        UIHandler.getInstance().initialize();
        UpdateManager.getInstance().start();
        PlayerController.getInstance();
        
    }
}