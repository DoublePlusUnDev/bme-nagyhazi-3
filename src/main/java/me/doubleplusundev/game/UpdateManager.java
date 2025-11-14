package me.doubleplusundev.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.Timer;

import me.doubleplusundev.util.Config;

/**
 * Used for timing updates and ticks.
 * Ticks are called in order of priority.
 */
public class UpdateManager {
    private final List<IUpdatable> updatables; /** List of updatables that will be invoked by the timer. */
    private final Set<ITickable> tickables; /** List of tickables that will be invoked by the timer in order of priority. */
    private final Timer updateTimer; /** Timer invoking the updatables. */
    private final Timer tickTimer; /** Timer invoking the tickables. */
    private final int updateRate; /** How many updates are invoked in a second. */
    private final int tickRate; /** How many ticks are invoked in a second. */
    private int tickCount; /** How many tick cycles have been invoked so far. */
    
    public UpdateManager() {
        updateRate = Config.getInt("target_fps", 60);
        tickRate = Config.getInt("tick_speed", 20);

        updateTimer = new Timer(1000 / updateRate, e -> update());
        tickTimer = new Timer(1000 / tickRate, e -> tick());

        updatables = new ArrayList<>();
        tickables = new TreeSet<>(new TickableComparator());

        tickCount = 0;
    }

    /**
     * Start the update and tick timers.
     */
    public void start(){
        updateTimer.start();
        tickTimer.start();
    }


    /**
     * Invoke the updatables from the list.
     */
    private void update(){
        for (IUpdatable updatable : updatables){
            updatable.update();
        }
    }

    /**
     * Invoke the tickables from the list in order of priority.
     */
    private void tick() {
        for (ITickable tickable : tickables){
            tickable.tick(tickCount);
        }
        tickCount++;
    }

    /**
     * Register an updatable to be invoked regularly.
     * @param updatable
     */
    public void registerForUpdate(IUpdatable updatable){
        if (!updatables.contains(updatable))
            updatables.add(updatable);
    }

    /**
     * Register a tickable to be invoked regularly.
     * @param updatable
     */
    public void registerForTick(ITickable tickable){
        if (!tickables.contains(tickable))
            tickables.add(tickable);
    }

    /**
     * Length of last update in seconds.
     */
    public double getDeltaTime(){
        return 1.0 / updateRate;
    }

    /**
     * Length of last tick in seconds.
     */
    public double getDeltaTick(){
        return 1.0 / tickRate;
    }
}
