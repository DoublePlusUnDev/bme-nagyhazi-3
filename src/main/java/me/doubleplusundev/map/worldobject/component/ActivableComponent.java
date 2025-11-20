package me.doubleplusundev.map.worldobject.component;

import me.doubleplusundev.game.ITickable;
import me.doubleplusundev.game.TickPriority;

/**
 * A component which gets activated when connected to a source through activation channels.
 * Other componenets may act differently based on the activation status.
 * Must be registered for ACTIVATION_ERASE priority, to clear activation before each reactivation cycle.
 */
public class ActivableComponent extends Component implements ITickable {
    private boolean active = false; /** Activation status */

    /**
     * Set status to active.
     */
    public void activate() {
        active = true;
    }

    /**
     * Returns whether the object is active.
     * When tickables with the wrong priority query it, it may return incorrect value.
     * Make sure to read it either before ACTIVATION_ERASE or after ACTIVATE priority.
     * @return Activation status.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Wipes activation status, before source reactivates instances.
     */
    @Override
    public void tick(int count) {
        active = false;
    }

    /**
     * Registered for ACTIVATION_ERASE, so it wipes activation before source attempts to reactivate.
     * If not registered and once activated it won't ever lose it's activation state even if source is disconnected. 
     */
    @Override
    public TickPriority getPriority() {
        return TickPriority.ACTIVATION_ERASE;
    }
}
