package me.exellanix.kitpvp.database.flat;

import me.exellanix.kitpvp.KitPvP;
import me.exellanix.kitpvp.database.CustomYML;
import me.exellanix.kitpvp.database.Database;
import me.exellanix.kitpvp.kits.Kit;
import me.exellanix.kitpvp.stats.PlayerStats;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Mac on 3/8/2016.
 */
public class LocalHandler implements Database {
    private CustomYML playerKitData;
    private CustomYML playerStatsData;
    
    public LocalHandler() {
        playerKitData = new CustomYML(KitPvP.getSingleton(), "playerkits.yml");
        playerKitData.saveDefaultConfig();
        playerStatsData = new CustomYML(KitPvP.getSingleton(), "playerstats.yml");
        playerStatsData.saveDefaultConfig();
    }

    public void addPaidKit(Player player, Kit kit) {
        addPlayer(player);
        ArrayList<String> kitNames = new ArrayList<>(playerKitData.getConfig().getStringList("players." + player.getUniqueId().toString() + ".kits"));
        kitNames.add(kit.getName());
        playerKitData.getConfig().set("players." + player.getUniqueId().toString() + ".kits", kitNames);
        playerKitData.saveConfig();
    }

    public void removePaidKit(Player player, Kit kit) {
        addPlayer(player);
        ArrayList<String> kitNames = new ArrayList<>(playerKitData.getConfig().getStringList("players." + player.getUniqueId().toString() + ".kits"));
        if (kitNames.contains(kit.getName())) {
            kitNames.remove(kit.getName());
        }
        playerKitData.getConfig().set("players." + player.getUniqueId().toString() + ".kits", kitNames);
        playerKitData.saveConfig();
    }

    public boolean hasPaidKit(Player player, Kit kit) {
        addPlayer(player);
        ArrayList<String> kitNames = new ArrayList<>(playerKitData.getConfig().getStringList("players." + player.getUniqueId().toString() + ".kits"));
        return kitNames.contains(kit.getName());
    }

    @Override
    public PlayerStats getPlayerStats(Player player) {
        verifyPlayerData(player);
        PlayerStats stats = new PlayerStats(playerStatsData.getConfig().getInt("players." + player.getUniqueId().toString() + ".totalDeaths"), playerStatsData.getConfig().getInt("players." + player.getUniqueId().toString() + ".totalKills"), new HashMap<>(), new HashMap<>());
        for (Kit k : KitPvP.getSingleton().getKitManager().getRegisteredKits()) {
            stats.getKitKills().put(k.getName(), playerStatsData.getConfig().getInt("players." + player.getUniqueId().toString() + ".kitKills." + k.getName()));
        }
        for (Kit k : KitPvP.getSingleton().getKitManager().getRegisteredKits()) {
            stats.getKitDeaths().put(k.getName(), playerStatsData.getConfig().getInt("players." + player.getUniqueId().toString() + ".kitDeaths." + k.getName()));
        }
        return stats;
    }

    @Override
    public void updatePlayerStats(PlayerStats stats, Player player) {
        verifyPlayerData(player);
        playerStatsData.getConfig().set("players." + player.getUniqueId().toString() + ".totalKills", stats.getTotalKills());
        playerStatsData.getConfig().set("players." + player.getUniqueId().toString() + ".totalDeaths", stats.getTotalDeaths());
        for (String kit: stats.getKitKills().keySet()) {
            playerStatsData.getConfig().set("players." + player.getUniqueId().toString() + ".kitKills." + kit, stats.getKitKills().get(kit));
        }
        for (String kit: stats.getKitDeaths().keySet()) {
            playerStatsData.getConfig().set("players." + player.getUniqueId().toString() + ".kitDeaths." + kit, stats.getKitDeaths().get(kit));
        }
        playerStatsData.saveConfig();
    }

    private void verifyPlayerData(Player player) {
        if (!playerStatsData.getConfig().isSet("players." + player.getUniqueId().toString())) {
            playerStatsData.getConfig().createSection("players." + player.getUniqueId().toString() + ".totalKills");
            playerStatsData.getConfig().set("players." + player.getUniqueId().toString() + ".totalKills", 0);
            playerStatsData.getConfig().createSection("players." + player.getUniqueId().toString() + ".totalDeaths");
            playerStatsData.getConfig().set("players." + player.getUniqueId().toString() + ".totalDeaths", 0);
        }
        for (Kit k : KitPvP.getSingleton().getKitManager().getRegisteredKits()) {
            if (!playerStatsData.getConfig().isSet("players." + player.getUniqueId().toString() + ".kitKills." + k.getName())) {
                playerStatsData.getConfig().set("players." + player.getUniqueId().toString() + ".kitKills." + k.getName(), 0);
            }
        }
        for (Kit k : KitPvP.getSingleton().getKitManager().getRegisteredKits()) {
            if (!playerStatsData.getConfig().isSet("players." + player.getUniqueId().toString() + ".kitDeaths." + k.getName())) {
                playerStatsData.getConfig().set("players." + player.getUniqueId().toString() + ".kitDeaths." + k.getName(), 0);
            }
        }
        playerStatsData.saveConfig();
    }

    @Override
    public void reloadConfigs() {
        playerStatsData.reloadConfig();
        playerKitData.reloadConfig();
    }

    private boolean playerExists(Player player) {
        if(playerKitData.getConfig().isSet("players." + player.getUniqueId().toString() + ".kits")) {
            return true;
        }
        return false;
    }

    private void addPlayer(Player player) {
        if (!playerExists(player)) {
            playerKitData.getConfig().createSection("players." + player.getUniqueId().toString() + ".kits");
            playerKitData.getConfig().set("players." + player.getUniqueId().toString() + ".kits", new ArrayList<String>());
            playerKitData.saveConfig();
        }
    }
}
