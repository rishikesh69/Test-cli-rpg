package org.auto1.rpg.game.state;

import java.util.Collections;
import java.util.List;

import org.auto1.rpg.exception.ConfigurationException;


/**
 * @author RISHIKESH
 *
 * @param <T>
 */
public interface ResourceProvider<T> {
    List<T> load() throws ConfigurationException;

    void save(List<T> resource) throws ConfigurationException;

    default T loadOne() throws ConfigurationException {
        List<T> load = load();
        if (load.size() > 1) {
            throw new ConfigurationException("Only one entry permitted for this resource");
        }

        return load.get(0);
    }

    default void saveOne(T resource) throws ConfigurationException {
        List<T> listWithOneResource = Collections.singletonList(resource);
        save(listWithOneResource);
    }
}
