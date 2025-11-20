package me.doubleplusundev.game;

/**
 * An interface that allows an object to be registered for receivig ticks.
 * Priority can be set using the TickPriority enum.
 * It's the callers responsibility to handle prioritizing.
 */
public interface ITickable {
    /**
     * Tick method, gets called from the handler.
     */
    public void tick(int count);

    /**
     * Getter for the priority, intended for the tickhandler for ordering calls.
     * @return the priority 
     */
    public default TickPriority getPriority() {
        return TickPriority.DEFAULT;
    }
}
