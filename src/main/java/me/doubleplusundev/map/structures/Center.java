package me.doubleplusundev.map.structures;

public class Center extends Structure {
    public Center() {
        super(StructureType.CENTER);
    }

    static {
        Structure.register(StructureType.CENTER, Center::new);
    }

    @Override
    public void update() {

    }

    @Override
    public void tick(int count) {

    }
    
}
