package me.doubleplusundev.savegame;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import me.doubleplusundev.game.UpdateManager;
import me.doubleplusundev.map.worldobject.component.BuildingComponent;
import me.doubleplusundev.map.worldobject.component.Component;
import me.doubleplusundev.map.worldobject.component.HarvestableComponent;
import me.doubleplusundev.map.worldobject.component.ProductionComponent;
import me.doubleplusundev.map.worldobject.component.TypeComponent;
import me.doubleplusundev.resource.ResourceManager;

public class ComponentSaveAdapter implements JsonSerializer<Component>, JsonDeserializer<Component> {

    private final ResourceManager resourceManager;
    private final UpdateManager updateManager;

    private static final String COMPONENT_PROPERTY = "component";
    private static final String BUILDING_VALUE = "building";
    private static final String HARVESTABLE_VALUE = "harvestable";
    private static final String PRODUCTION_VALUE = "production";
    private static final String TYPE_VALUE = "type";

    public ComponentSaveAdapter(ResourceManager resourceManager, UpdateManager updateManager) {
        this.resourceManager = resourceManager;
        this.updateManager = updateManager;
    }

    @Override
    public JsonElement serialize(Component src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = context.serialize(src).getAsJsonObject();
        
        if (src instanceof BuildingComponent) object.addProperty(COMPONENT_PROPERTY, BUILDING_VALUE);
        if (src instanceof HarvestableComponent) object.addProperty(COMPONENT_PROPERTY, HARVESTABLE_VALUE);
        if (src instanceof ProductionComponent) object.addProperty(COMPONENT_PROPERTY, PRODUCTION_VALUE);
        if (src instanceof TypeComponent) object.addProperty(COMPONENT_PROPERTY, TYPE_VALUE);

        return object;
    }

    @Override
    public Component deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        String componentType = object.get(COMPONENT_PROPERTY).getAsString();
        
        switch (componentType) {
            case BUILDING_VALUE -> {
                return context.deserialize(json, BuildingComponent.class);
            }
            case HARVESTABLE_VALUE -> {
                HarvestableComponent component = context.deserialize(json, HarvestableComponent.class); 
                component.loadBack(resourceManager);
                return component; 
            }
            case PRODUCTION_VALUE -> {
                ProductionComponent component = context.deserialize(json, ProductionComponent.class);
                component.loadBack(resourceManager);
                updateManager.registerForTick(component);
                return component;
            }
            case TYPE_VALUE -> {
                return context.deserialize(json, TypeComponent.class);
            }
            default -> throw new JsonParseException("Unknown type:" + componentType);
        }
    }  
}
