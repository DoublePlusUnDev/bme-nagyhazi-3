package me.doubleplusundev.map.structures;

import me.doubleplusundev.map.GameMapHandler;

public class Center extends Structure {
    public Center(int xPos, int yPos, GameMapHandler gameMapHandler) {
        super(xPos, yPos, StructureType.CENTER, gameMapHandler);
    }
}
