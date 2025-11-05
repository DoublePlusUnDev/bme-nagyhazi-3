package me.doubleplusundev.map.structures;

import java.util.Map;
import java.util.function.Supplier;

public class StructureFactory {
    private static Map<StructureType, Supplier<? extends Structure>> registry = Map.ofEntries(
        Map.entry(StructureType.ROAD, Road::new),
        Map.entry(StructureType.CENTER, Center::new)
    );
    
    public static Structure create(StructureType type) {
        Supplier<? extends Structure> supplier = registry.get(type);
        
        if (supplier == null) {
            throw new IllegalArgumentException("Unregistered structure type: " + type);
        }

        return supplier.get();
    }
}
