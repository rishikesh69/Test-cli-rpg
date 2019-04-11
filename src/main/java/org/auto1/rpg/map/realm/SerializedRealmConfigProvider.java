package org.auto1.rpg.map.realm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.auto1.rpg.map.realm.RealmConfigGenerator;
import org.auto1.rpg.menu.RealmConfigurationProvider;
import org.auto1.rpg.game.state.SerializedResourceProvider;
import org.auto1.rpg.map.realm.RealmConfiguration;

import static org.auto1.rpg.common.io.ExternalIO.resourcesPath;

import java.util.List;

public class SerializedRealmConfigProvider extends SerializedResourceProvider<RealmConfiguration> implements RealmConfigurationProvider {
    private static final Logger LOG = LogManager.getLogger(SerializedRealmConfigProvider.class);

    public static final String FILENAME = "realm_configuration.ser";

    @Override
    protected String getFilename() {
        return FILENAME;
    }

    @Override
    protected String basePath() {
        return resourcesPath() + configPath();
    }

    @Override
    protected boolean isLoadExternal() {
        return false;
    }

    @Override
    protected List<RealmConfiguration> handleException(Exception e) {
        //throw new ConfigurationException("Could not load Realm Configuration", e);
        //TODO: decide what is best here.
        //with json an exception might have been a better option, but serialized files are not human readable anyway, so no one will update them
        return rollbackToBuiltInDefault();
    }

    private List<RealmConfiguration> rollbackToBuiltInDefault() {
        LOG.error("could not load realm configuration from a file, rolling back to defaults");
        return RealmConfigGenerator.realms();
    }
}
