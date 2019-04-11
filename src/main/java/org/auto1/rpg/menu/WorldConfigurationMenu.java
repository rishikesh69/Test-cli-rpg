package org.auto1.rpg.menu;

import org.auto1.rpg.map.realm.RealmConfiguration;

import java.util.List;

public interface WorldConfigurationMenu {
    RealmConfiguration chooseConfiguration(String realmQuestion, List<RealmConfiguration> realmConfigs);

    void confirmRealmConfiguration(String realmConfirmationMessage);
}
