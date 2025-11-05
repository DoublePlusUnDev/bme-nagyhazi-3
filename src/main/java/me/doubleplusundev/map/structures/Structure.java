package me.doubleplusundev.map.structures;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import me.doubleplusundev.map.WorldObject;

public abstract  class Structure extends WorldObject {
    protected StructureType type;
    protected static Map<StructureType, Supplier<? extends Structure>> registry = new HashMap();

    protected Structure(StructureType type) {
        this.type = type;
    }

    public StructureType getType() {
        return type;
    }

    protected static void register(StructureType type, Supplier<? extends Structure> creator) {
        registry.put(type, creator);
        System.out.println("reg" + type.toString());
    }

    public static Structure create(StructureType type) {
        Supplier<? extends Structure> supplier = registry.get(type);

        for (StructureType t : registry.keySet()) {
            System.out.println(t.toString());
        }

        return supplier.get();
    }
}
