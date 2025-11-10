package me.doubleplusundev.savegame;

public class SaveGameManager {
    private static SaveGameManager instance;

    private SaveGameManager() {
        
    }

    public static SaveGameManager getInstance() {
        return instance;
    }

    public static void setInstance(SaveGameManager mock) {
        SaveGameManager.instance = mock;
    }

    
    
}
