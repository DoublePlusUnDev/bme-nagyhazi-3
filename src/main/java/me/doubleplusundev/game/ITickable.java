package me.doubleplusundev.game;

public interface ITickable {
    public void tick(int count);

    public default TickPriority getPriority() {
        return TickPriority.DEFAULT;
    }
}
