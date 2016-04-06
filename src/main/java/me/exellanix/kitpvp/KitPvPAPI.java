package me.exellanix.kitpvp;

import me.exellanix.kitpvp.kit_abilities.AbilityManager;
import me.exellanix.kitpvp.kits.Kit;
import me.exellanix.kitpvp.kits.KitManager;
import me.exellanix.kitpvp.regions.RegionManager;
import me.exellanix.kitpvp.stats.PlayerStats;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created by Mac on 4/2/2016.
 */
public interface KitPvPAPI {
    RegionManager getRegionManager();

    HashMap<Player, Kit> getPlayerKits();

    KitManager getKitManager();

    AbilityManager getAbilityManager();

    PlayerStats getPlayerStats(Player player);
}
