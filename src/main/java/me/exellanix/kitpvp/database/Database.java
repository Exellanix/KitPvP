package me.exellanix.kitpvp.database;

import me.exellanix.kitpvp.KitPvP;
import me.exellanix.kitpvp.database.flat.LocalHandler;
import me.exellanix.kitpvp.kits.Kit;
import org.bukkit.entity.Player;

/**
 * Created by Mac on 3/8/2016.
 */
public class Database {
    private CustomYML flat;

    public Database(CustomYML flat) {
        this.flat = flat;
    }

    public void addPaidKit(Player player, Kit kit) {
        if (KitPvP.getSingleton().plugin.getConfig().getBoolean("Use-MySQL")) {

        } else {
            LocalHandler.addPaidKit(player, kit);
        }
    }

    public void removePaidKit(Player player, Kit kit) {
        if (KitPvP.getSingleton().plugin.getConfig().getBoolean("Use-MySQL")) {

        } else {
            LocalHandler.removePaidKit(player, kit);
        }
    }

    public boolean hasPaidKit(Player player, Kit kit) {
        if (KitPvP.getSingleton().plugin.getConfig().getBoolean("Use-MySQL")) {
            return false;
        } else {
            return LocalHandler.hasPaidKit(player, kit);
        }
    }

    public CustomYML getFlatStorage() {
        return flat;
    }
}
