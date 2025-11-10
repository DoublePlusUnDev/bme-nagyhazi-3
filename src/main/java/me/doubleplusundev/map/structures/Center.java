package me.doubleplusundev.map.structures;

import me.doubleplusundev.map.GameMap;

public class Center extends Structure {
    public Center(int xPos, int yPos, GameMap gameMap) {
        super(xPos, yPos, StructureType.CENTER, gameMap);
    }
}
