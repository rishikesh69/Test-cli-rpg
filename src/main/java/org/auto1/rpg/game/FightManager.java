package org.auto1.rpg.game;

import org.auto1.rpg.map.character.Character;
import org.auto1.rpg.map.character.NonPlayer;
import org.auto1.rpg.map.character.Player;
import org.auto1.rpg.exception.ShouldNeverHappen;
import org.auto1.rpg.menu.AllMenus;
import org.auto1.rpg.map.Coordinates;
import org.auto1.rpg.map.Location;
import org.auto1.rpg.menu.BeforeFightMenu;
import org.auto1.rpg.menu.FightMenu;
import org.auto1.rpg.menu.item.BeforeFightMenuItem;
import org.auto1.rpg.menu.item.FightMenuItem;

import static org.auto1.rpg.common.utils.ColorFormatter.red;
import static org.auto1.rpg.common.utils.ColorFormatter.yellow;
import static org.auto1.rpg.game.StaticMessages.GET_AWAY_FROM_THE_FIGHT;
import static org.auto1.rpg.menu.item.FightMenuItem.FLEE;

public class FightManager {
    private static final String ATTACK_MESSAGE = "%s attacked for %s damage.";

    private final FightMenu fightMenu;
    private final BeforeFightMenu beforeFightMenu;
    private final Player player;
    private final Coordinates newCoordinates;
    private final NonPlayer npc;

    public FightManager(AllMenus allMenus, Player player, Location fightLocation) {
        this.fightMenu = allMenus.fightMenu();
        this.beforeFightMenu = allMenus.beforeFightMenu();
        this.player = player;
        this.newCoordinates = fightLocation.getCoordinates();
        this.npc = fightLocation.getNpc();
    }

    public void fight() {
        if (userWantsToFight()) {
            startTheBrawl();
        } else {
            beforeFightMenu.showMessage(GET_AWAY_FROM_THE_FIGHT);
        }
    }

    void startTheBrawl() {
        FightMenuItem fightMenuItem = fightMenu.showMenu();
        do {
            switch (fightMenuItem) {
                case ATTACK:
                    attack(player, npc);
                    if (npc.isDead()) {
                        fightMenu.showMessage(player.killed(npc));
                        player.setCoordinates(newCoordinates);
                        fightMenu.showMessage(player.currentStatus());
                        break;
                    }

                    attack(npc, player);
                    fightMenu.showMessage(player.currentStatus());
                    fightMenu.showMessage(npc.currentStatus());
                    break;
                case DEFEND:
                    fightMenu.showMessage(player.armorUp());
                    attack(npc, player);
                    fightMenu.showMessage(player.currentStatus());
                    break;
                case FLEE:
                    fightMenu.showMessage(player.flee());
                    break;
                default:
                    throw new ShouldNeverHappen();
            }

            if (npc.isAlive()){
                fightMenuItem = fightMenu.showMenu();
            }

        } while (fightGoesOn(npc, fightMenuItem));
    }

    boolean userWantsToFight() {
        BeforeFightMenuItem item = beforeFightMenu.showMenu();
        switch (item) {
            case ATTACK:
                return true;
            case FALL_BACK:
                return false;
            default:
                throw new ShouldNeverHappen();
        }
    }

    int attack(Character attacker, Character defender) {
        int damageDealt = attacker.attack(defender);
        showAttackMessage(attacker, damageDealt);
        return damageDealt;
    }

    private void showAttackMessage(Character attacker, int attackedFor) {
        fightMenu.showMessage(String.format(ATTACK_MESSAGE, yellow(attacker.getClass().getSimpleName()), red(String.valueOf(attackedFor))));
    }

    boolean fightGoesOn(NonPlayer npc, FightMenuItem fightMenuItem) {
        return npc.isAlive() && player.isAlive() && null != fightMenuItem && !FLEE.equals(fightMenuItem);
    }
}
