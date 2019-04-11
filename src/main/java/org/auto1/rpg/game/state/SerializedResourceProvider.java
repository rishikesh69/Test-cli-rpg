package org.auto1.rpg.game.state;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.auto1.rpg.common.io.ExternalIO;
import org.auto1.rpg.common.io.InternalIO;
import org.auto1.rpg.exception.ConfigurationException;
import org.auto1.rpg.game.state.ResourceProvider;

import static org.auto1.rpg.console.ui.CliMessageConstants.EMPTY_CONFIGURATION;
import static org.auto1.rpg.console.ui.CliMessageConstants.FAIL_LOADING_RESOURCES;
import static org.auto1.rpg.console.ui.CliMessageConstants.RESOURCE_NOT_SAVED;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public abstract class SerializedResourceProvider<T> implements ResourceProvider<T> {
    private static final Logger LOG = LogManager.getLogger(SerializedResourceProvider.class);

    private static final String PATH_TO_CONFIG_FILES = "config";
    private static final String PATH_TO_SAVE_FILES = "saves";

    @SuppressWarnings("unchecked")
    public List<T> load() throws ConfigurationException {
        LOG.debug("Trying to load resources from a file");

        try (ObjectInputStream objectInputStream = getObjectInputStream()) {
            List<T> resources = (List<T>) objectInputStream.readObject();
            if (null == resources || resources.isEmpty()) {
                throw new ConfigurationException(EMPTY_CONFIGURATION);
            }

            return resources;
        } catch (Exception e) {
            LOG.error(FAIL_LOADING_RESOURCES + getFilename());
            return handleException(e);
        }
    }

    private ObjectInputStream getObjectInputStream() throws IOException {
        if (isLoadExternal()) {
            return ExternalIO.objectInputStream(basePath(), getFilename());
        } else {
            return InternalIO.objectInputStream(basePath(), getFilename());
        }
    }

    @Override
    public void save(List<T> resources) throws ConfigurationException {
        LOG.debug("Trying to save resources to a file");

        try (ObjectOutputStream objectOutputStream = ExternalIO.objectOutputStream(basePath(), getFilename())) {
            objectOutputStream.writeObject(resources);
            LOG.debug("resources saving finished with a success");
        } catch (Exception e) {
            throw new ConfigurationException(RESOURCE_NOT_SAVED, e);
        }
    }


    protected abstract List<T> handleException(Exception e) throws ConfigurationException;

    protected abstract String getFilename();

    protected abstract String basePath();

    protected abstract boolean isLoadExternal();

    public static String configPath() {
        return PATH_TO_CONFIG_FILES;
    }

    public static String savePath() {
        return PATH_TO_SAVE_FILES;
    }
}
