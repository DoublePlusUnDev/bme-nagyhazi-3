package me.doubleplusundev.map.tiles;

/**
 * Type of a map tile.
 * Each gridspace is occupied by exactly one tile.
 * Map tiles do not have an internal state or methods, they're purely an enum.
 */
public enum TileType {
    GRASS,
    LAKE,
    ROCK,
    SAND,
    SEA_DEEP,
    SEA_SHORE,
    SNOW,
}
