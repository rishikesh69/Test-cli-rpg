package org.auto1.rpg.map.legend;

import org.auto1.rpg.map.LocationType;

import java.util.Arrays;
import java.util.stream.Collectors;

public class LegendBuilder extends OutputBuilderBase {
    private LegendBuilder() {
    }

    public static String buildLegend() {
        return new LegendBuilder().legend();
    }

    public String legend() {
        String sb = Arrays.stream(LocationType.values()).map(locationType -> toLegend(locationType) + "\n").collect(Collectors.joining());
        return sb;
    }

    private String toLegend(LocationType locationType) {
        return formatMapMark(locationType.getMapMark()) + " = " + locationType.getDescription();
    }

    private String formatMapMark(String mapMark) {
        return formatString("'" + mapMark + "'");
    }
}
