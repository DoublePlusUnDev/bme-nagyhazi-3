package me.doubleplusundev.game;

/*
 * An interface that allows an object to be registered for receivig updates.
 */
public interface IUpdatable {
    /**
     * Update method, gets called from the handler.
     */
    public void update();
}
