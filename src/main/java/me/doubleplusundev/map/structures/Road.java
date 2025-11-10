package me.doubleplusundev.map.structures;

import me.doubleplusundev.map.GameMap;

public class Road extends Structure {
    public Road(int xPos, int yPos, GameMap gameMap) {
        super(xPos, yPos, StructureType.ROAD, gameMap);
    }
}
