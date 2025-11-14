package me.doubleplusundev.map.worldobject.component;

import me.doubleplusundev.game.ITickable;
import me.doubleplusundev.game.TickPriority;

/**
 * A component which gets activated when connected to a source through activation channels.
 * Other componenets may mutate their behaviour based on the activation staus.
 * Must be registered for ACTIVATION_ERASE priority.
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
     * When very low tick priority objects query it may returns incorrect value.
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
     * Registered for ARCITVATION_ERASE, wipes activation before source attempts to reactivate.
     */
    @Override
    public TickPriority getPriority() {
        return TickPriority.ACTIVATION_ERASE;
    }
}
