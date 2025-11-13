package me.doubleplusundev.map.worldobject.component;

import me.doubleplusundev.game.ITickable;
import me.doubleplusundev.game.TickPriority;

public class ActivableComponent extends Component implements ITickable {
    private boolean active = false;

    public void activate() {
        active = true;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public void tick(int count) {
        active = false;
    }

    @Override
    public TickPriority getPriority() {
        return TickPriority.ACTIVATION_ERASE;
    }
}
