package me.doubleplusundev.game;

import java.util.Comparator;

public class TickableComparator implements Comparator<ITickable> {

    @Override
    public int compare(ITickable tickable1, ITickable tickable2) {
        return tickable1.getPriority().compareTo(tickable2.getPriority());
    }
    
    
}
