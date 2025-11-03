package me.doubleplusundev;

import me.doubleplusundev.util.Vector2;

public class PlayerController implements IUpdatable {
    private static PlayerController instance;
    
    Vector2 position;

    private PlayerController() {
        
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
