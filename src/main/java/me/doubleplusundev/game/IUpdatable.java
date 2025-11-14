package me.doubleplusundev.game;

/*
 * An interface that can be registered for receivig ticks.
 */
public interface IUpdatable {
    /**
     * Update method, gets called from the handler.
     */
    public void update();
}
