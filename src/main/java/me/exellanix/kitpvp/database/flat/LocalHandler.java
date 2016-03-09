package me.exellanix.kitpvp.database.flat;

import me.exellanix.kitpvp.KitPvP;
import me.exellanix.kitpvp.kits.Kit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by Mac on 3/8/2016.
 */
public class LocalHandler {

    public static void addPaidKit(Player player, Kit kit) {
        addPlayer(player);
        ArrayList<String> kitNames = new ArrayList<>(KitPvP.getPluginDatabase().getFlatStorage().getConfig().getStringList("players." + player.getUniqueId().toString() + ".kits"));
        kitNames.add(kit.getName());
        KitPvP.getPluginDatabase().getFlatStorage().getConfig().set("players." + player.getUniqueId().toString() + ".kits", kitNames);
        KitPvP.getPluginDatabase().getFlatStorage().saveConfig();
    }

    public static void removePaidKit(Player player, Kit kit) {
        addPlayer(player);
        ArrayList<String> kitNames = new ArrayList<>(KitPvP.getPluginDatabase().getFlatStorage().getConfig().getStringList("players." + player.getUniqueId().toString() + ".kits"));
        if (kitNames.contains(kit.getName())) {
            kitNames.remove(kit.getName());
        }
        KitPvP.getPluginDatabase().getFlatStorage().getConfig().set("players." + player.getUniqueId().toString() + ".kits", kitNames);
        KitPvP.getPluginDatabase().getFlatStorage().saveConfig();
    }

    public static boolean hasPaidKit(Player player, Kit kit) {
        addPlayer(player);
        ArrayList<String> kitNames = new ArrayList<>(KitPvP.getPluginDatabase().getFlatStorage().getConfig().getStringList("players." + player.getUniqueId().toString() + ".kits"));
        return kitNames.contains(kit.getName());
    }

    private static boolean playerExists(Player player) {
        if(KitPvP.getPluginDatabase().getFlatStorage().getConfig().isSet("players." + player.getUniqueId().toString() + ".kits")) {
            return true;
        }
        return false;
    }

    private static void addPlayer(Player player) {
        if (!playerExists(player)) {
            KitPvP.getPluginDatabase().getFlatStorage().getConfig().createSection("players." + player.getUniqueId().toString() + ".kits");
            KitPvP.getPluginDatabase().getFlatStorage().getConfig().set("players." + player.getUniqueId().toString() + ".kits", new ArrayList<String>());
            KitPvP.getPluginDatabase().getFlatStorage().saveConfig();
        }
    }
}