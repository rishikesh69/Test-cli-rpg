package org.auto1.rpg.map;

import org.auto1.rpg.map.character.Enemy;
import org.auto1.rpg.map.character.NonPlayer;
import org.auto1.rpg.exception.ExplorationException;
import org.auto1.rpg.map.Coordinates;
import org.auto1.rpg.map.Location;
import org.auto1.rpg.common.utils.ToStringBuilder;
import org.auto1.rpg.map.character.EnemyConfiguration;
import org.auto1.rpg.map.realm.RealmConfiguration;

import static org.auto1.rpg.common.utils.CommonUtil.isNotBetweenZeroAndMaxExclusive;
import static org.auto1.rpg.common.utils.CommonUtil.randomIntExclusive;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class World implements Serializable {
    private final String name;
    private final Map<Coordinates, Enemy> enemies;

    //TODO: maybe this should not be an array of arrays at all? maybe it should be a Map? Map<Coordinate, Location> something to think about
    private final Location[][] map;

    public World(RealmConfiguration realmConfiguration) {
        this.name = realmConfiguration.getName();
        this.enemies = new HashMap<>();
        this.map = new Location[realmConfiguration.getRealmSize()][realmConfiguration.getRealmSize()];

        this.initWorld(realmConfiguration.getEnemyConfiguration());
    }

    private void initWorld(List<EnemyConfiguration> enemyConfiguration) {
        initEnemies(enemyConfiguration);
        initMap();
    }

    void initEnemies(List<EnemyConfiguration> enemyConfiguration) {
        enemyConfiguration.stream().map(Enemy::new).forEach(enemy -> {
            Coordinates enemyCoordinates = randomCoordinatesWithoutAnyone();
            enemies.put(enemyCoordinates, enemy);
        });
    }

    void initMap() {
        for (int x = 0; x < map.length; x++) {
            Location[] column = map[x];
            for (int y = 0; y < column.length; y++) {
                Coordinates currentCoordinates = new Coordinates(x, y);
                NonPlayer someone = searchForNpc(currentCoordinates);
                if (null != someone) {
                    map[x][y] = locationWithNpc(currentCoordinates, someone);
                } else {
                    map[x][y] = emptyLocation(currentCoordinates);
                }
            }
        }
    }

    private NonPlayer searchForNpc(Coordinates currentCoordinates) {
            return enemies.get(currentCoordinates);
        
    }

    private Location locationWithNpc(Coordinates coordinates, NonPlayer someone) {
        return new Location(coordinates, someone);
    }

    private Location emptyLocation(Coordinates coordinates) {
        return new Location(coordinates);
    }

    //TODO: think about a better way to do this...
    public Coordinates randomCoordinatesWithoutAnyone() {
        Coordinates coordinates;

        do {
            coordinates = randomCoordinates(map.length);
        } while (someoneIsThere(coordinates));

        return coordinates;
    }

    private boolean someoneIsThere(Coordinates coordinates) {
        return enemies.containsKey(coordinates);
    }

    static Coordinates randomCoordinates(int mapSize) {
        int randomX = randomIntExclusive(mapSize);
        int randomY = randomIntExclusive(mapSize);
        return new Coordinates(randomX, randomY);
    }


    public Location getLocation(Coordinates coordinates) throws ExplorationException {
        return getLocation(coordinates.getX(), coordinates.getY());
    }

    public Location getLocation(int x, int y) throws ExplorationException {
        validateCoordinates(x, y);
        return map[x][y];
    }

    private void validateCoordinates(int x, int y) throws ExplorationException {
        validateCoordinate(x);
        validateCoordinate(y);
    }

    private void validateCoordinate(int index) throws ExplorationException {
        if (isNotBetweenZeroAndMaxExclusive(index, mapSize())) {
            ExplorationException.cannotGo(index);
        }
    }

    public boolean allEnemiesDead() {
        return aliveEnemiesLeft() == 0;
    }

    public long aliveEnemiesLeft() {
        return enemies.values().stream().filter(Enemy::isAlive).count();
    }

    public String getName() {
        return name;
    }

    public Location[][] getMap() {
        return map;
    }

    public Map<Coordinates, Enemy> getEnemies() {
        return enemies;
    }

    public int mapSize() {
        return map.length;
    }

    @Override
    public String toString() {
        return ToStringBuilder.defaultBuilder(this)
                .append("name", name)
                .append("mapSize", mapSize())
                .build();
    }

}
