package me.doubleplusundev.savegame;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import me.doubleplusundev.map.WorldObject;
import me.doubleplusundev.map.structures.Center;
import me.doubleplusundev.map.structures.Road;

public class WorldObjectAdapter implements JsonSerializer<WorldObject>, JsonDeserializer<Object> {

    @Override
    public JsonElement serialize(WorldObject src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = context.serialize(src).getAsJsonObject();
        if (src instanceof Center) obj.addProperty("wotype", "Center");
        if (src instanceof Road) obj.addProperty("wotype", "Road");
        return obj;
    }

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        String type = obj.get("wotype").getAsString();
        switch (type) {
            case "Center" -> {
                return context.deserialize(json, Center.class);
            }
            case "Road" -> {
                return context.deserialize(json, Road.class);
            }
            default -> throw new RuntimeException("Unknown type:" + type);
        }

    }
    
    
    
}
