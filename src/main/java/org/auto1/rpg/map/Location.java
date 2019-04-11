package org.auto1.rpg.map;

import org.auto1.rpg.common.utils.ToStringBuilder;
import org.auto1.rpg.map.character.NonPlayer;

import java.io.Serializable;

public class Location implements Serializable {
    private final Coordinates coordinates;
    private LocationType type; //may change, for example from ENEMY to NPC_DEAD
    private NonPlayer npc;

    public Location(Coordinates coordinates) {
        this.coordinates = coordinates;
        this.type = LocationType.EMPTY;
    }

    public Location(Coordinates coordinates, NonPlayer npc) {
        this.coordinates = coordinates;
        this.type = npc.locationType();
        this.npc = npc;
    }

    public boolean isAnyoneThere() {
        return null != npc && npc.isAlive();
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public NonPlayer getNpc() {
        return npc;
    }

    public LocationType getType() {
        return relevantLocationType();
    }

    public String mapMark() {
        return relevantLocationType().getMapMark();
    }

    public String desc() {
        return relevantLocationType().getDescription();
    }

    private LocationType relevantLocationType() {
        if (null != npc) {
            return npc.locationType();
        }

        return type;
    }

    @Override
    public String toString() {
        return ToStringBuilder.defaultBuilder(this)
                .append("coordinates", coordinates.toString())
                .append("type", type.name())
                .append("mapMark", mapMark())
                .build();
    }
}
