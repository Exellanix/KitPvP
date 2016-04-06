package me.exellanix.kitpvp.database;

import me.exellanix.kitpvp.kits.Kit;
import me.exellanix.kitpvp.stats.PlayerStats;
import org.bukkit.entity.Player;

/**
 * Created by Mac on 3/8/2016.
 */
public interface Database {

    void addPaidKit(Player player, Kit kit);

    void removePaidKit(Player player, Kit kit);

    boolean hasPaidKit(Player player, Kit kit);

    PlayerStats getPlayerStats(Player player);

    void updatePlayerStats(PlayerStats stats, Player player);

    void reloadConfigs();
}
