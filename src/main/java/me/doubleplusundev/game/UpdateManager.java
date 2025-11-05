package me.doubleplusundev.game;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import me.doubleplusundev.util.Config;

//implement proper deltatime later
public class UpdateManager {
    private static UpdateManager instance;
    private final List<IUpdatable> updatables;
    private final List<ITickable> tickables;
    private final Timer updateTimer;
    private final Timer tickTimer;
    int updateRate;
    int tickRate;
    int tickCount;
    
    
    private UpdateManager(){
        updateRate = Config.getInt("target_fps", 60);
        tickRate = Config.getInt("tick_speed", 20);

        updateTimer = new Timer(1000 / updateRate, e -> update());
        tickTimer = new Timer(1000 / tickRate, e -> tick());

        updatables = new ArrayList<>();
        tickables = new ArrayList<>();

        tickCount = 0;
    }

    public static UpdateManager getInstance(){
        if (instance == null){
            instance = new UpdateManager();
        }
        return instance;
    }

    public void start(){
        updateTimer.start();
        tickTimer.start();
    }

    private void update(){
        for (IUpdatable updatable : updatables){
            updatable.update();
        }
    }

    private void tick(){
        for (ITickable tickable : tickables){
            tickable.tick(tickCount);
        }
        tickCount++;
    }

    public void register(IUpdatable updatable){
        if (!updatables.contains(updatable))
            updatables.add(updatable);
    }

    public void register(ITickable tickable){
        if (!tickables.contains(tickable))
            tickables.add(tickable);
    }

    public double getDeltaTime(){
        return 1.0 / updateRate;
    }

    public double getDeltaTick(){
        return 1.0 / tickRate;
    }
}
