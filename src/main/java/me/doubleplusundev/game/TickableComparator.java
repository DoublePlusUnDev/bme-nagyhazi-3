package me.doubleplusundev.game;

import java.util.Comparator;

/**
 * Compares two ITickables priority.
 * If the priority is the same, but the objects are not
 * it compares based on the hash of the objects.
 */
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
