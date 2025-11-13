package me.doubleplusundev.map.worldobject.component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import me.doubleplusundev.game.ITickable;
import me.doubleplusundev.game.TickPriority;
import me.doubleplusundev.map.GameMapHandler;
import me.doubleplusundev.map.worldobject.WorldObject;

public class ActivatorComponent extends Component implements ITickable {
    private transient GameMapHandler gameMapHandler;

    public ActivatorComponent(GameMapHandler gameMapHandler) {
        this.gameMapHandler = gameMapHandler;
    }

    @Override
    public void tick(int count) {
        Queue<WorldObject> toCheck = new LinkedList<>();
        Set<WorldObject> alreadyChecked = new HashSet<>();
        toCheck.add(this.getOwner());

        while (!toCheck.isEmpty()) {
            WorldObject check = toCheck.remove();
            List<WorldObject> neighbours = getNeighbours(check);
            
            for (WorldObject neighbour : neighbours) {
                if (alreadyChecked.contains(neighbour))
                    continue;

                toCheck.add(neighbour);
                alreadyChecked.add(neighbour);
            }

            ActivableComponent activableComponent = check.getComponent(ActivableComponent.class);

            if (activableComponent != null) {
                activableComponent.activate();
            }
        }
    }

    private List<WorldObject> getNeighbours(WorldObject origin) {
        List<WorldObject> neighbours = new ArrayList<>();

        int posX = origin.getX();
        int posY = origin.getY();
        if (0 < posX && gameMapHandler.getWorldObject(posX - 1, posY) != null 
                    && (gameMapHandler.getWorldObject(posX - 1, posY).getComponent(ActivationChannelComponent.class) != null
                    ||  gameMapHandler.getWorldObject(posX - 1, posY).getComponent(ActivableComponent.class) != null)) {
            neighbours.add(gameMapHandler.getWorldObject(posX - 1, posY));
        }

        if (0 < posY && gameMapHandler.getWorldObject(posX, posY - 1) != null 
                    && (gameMapHandler.getWorldObject(posX, posY - 1).getComponent(ActivationChannelComponent.class) != null
                    ||  gameMapHandler.getWorldObject(posX, posY - 1).getComponent(ActivableComponent.class) != null)) {
            neighbours.add(gameMapHandler.getWorldObject(posX, posY - 1));
        }

        if (posX < gameMapHandler.getWidth() - 1 && gameMapHandler.getWorldObject(posX + 1, posY) != null 
                    && (gameMapHandler.getWorldObject(posX + 1, posY).getComponent(ActivationChannelComponent.class) != null
                    ||  gameMapHandler.getWorldObject(posX + 1, posY).getComponent(ActivableComponent.class) != null))
            neighbours.add(gameMapHandler.getWorldObject(posX + 1, posY));

        if (posY < gameMapHandler.getHeight() - 1 && gameMapHandler.getWorldObject(posX, posY + 1) != null 
                    && (gameMapHandler.getWorldObject(posX, posY + 1).getComponent(ActivationChannelComponent.class) != null
                    ||  gameMapHandler.getWorldObject(posX, posY + 1).getComponent(ActivableComponent.class) != null)) {
            neighbours.add(gameMapHandler.getWorldObject(posX, posY + 1));
        }
    
        return neighbours;
    }

    public void loadBack(GameMapHandler gameMapHandler) {
        this.gameMapHandler = gameMapHandler;
    }

    @Override
    public TickPriority getPriority() {
        return TickPriority.ACTIVATE;
    }
}
