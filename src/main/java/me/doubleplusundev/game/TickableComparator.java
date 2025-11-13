package me.doubleplusundev.game;

import java.util.Comparator;

public class TickableComparator implements Comparator<ITickable> {

    @Override
    public int compare(ITickable tickable1, ITickable tickable2) {
        if (tickable1 == tickable2)
            return 0;

        int priorityCompare = tickable1.getPriority().compareTo(tickable2.getPriority());
    
        if (priorityCompare != 0)
            return priorityCompare;

        return Integer.compare(System.identityHashCode(tickable1), System.identityHashCode(tickable2));
    }
    
    
}
