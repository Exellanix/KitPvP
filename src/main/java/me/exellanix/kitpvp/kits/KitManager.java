package me.exellanix.kitpvp.kits;

import me.exellanix.kitpvp.KitPvP;
import me.exellanix.kitpvp.Util.AlterItem;
import me.exellanix.kitpvp.external_jars.LoadExternalJar;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Created by Mac on 3/7/2016.
 */
public class KitManager {
    private ArrayList<Kit> registeredKits;

    public KitManager() {
        registeredKits = new ArrayList<>();
        registerDefaultKits();
        KitPvP.getSingleton().registerEvent(new KitListener());
        KitPvP.getSingleton().plugin.getServer().getScheduler().runTaskTimerAsynchronously(KitPvP.getSingleton().plugin, LoadExternalJar::loadJars, 0, 10);
    }

    public ArrayList<Kit> getRegisteredKits() {
        return registeredKits;
    }

    public void registerKit(Kit kit) {
        for (Kit k : new ArrayList<>(registeredKits)) {
            if (kit.getName().equals(k.getName())) {
                unregisterKit(k);
            }
        }
        registeredKits.add(kit);
    }

    public void unregisterAllKits() {
        registeredKits = new ArrayList<>();
    }

    public boolean isRegistered(Kit kit) {
        return registeredKits.contains(kit);
    }

    public void unregisterKit(Kit kit) {
        registeredKits.remove(kit);
    }

    public boolean hasKit(Player player, Kit kit) {
        // TODO add support for paid kits
        if (kit.isFree()) {
            return true;
        } else {
            return KitPvP.getSingleton().getPluginDatabase().hasPaidKit(player, kit);
        }
    }

    public ArrayList<ItemStack> getKitIconsOwn(Player player) {
        ArrayList<ItemStack> icons = new ArrayList<>();
        for (Kit k : registeredKits) {
            if (hasKit(player, k)) {
                icons.add(k.getIcon());
            }
        }
        return icons;
    }

    public ArrayList<ItemStack> getKitIconsBuy(Player player) {
        ArrayList<ItemStack> icons = new ArrayList<>();
        for (Kit k : registeredKits) {
            if (!hasKit(player, k)) {
                icons.add(k.getIcon());
            }
        }
        return icons;
    }

    public Kit getKitFromIcon(ItemStack icon) {
        for (Kit k : registeredKits) {
            if (AlterItem.itemsEqual(icon, k.getIcon())) {
                return k;
            }
        }
        return null;
    }

    public Kit getKitFromString(String name) {
        for (Kit k : registeredKits) {
            if (k.hasAlias(name)) {
                return k;
            }
        }
        return null;
    }

    public void registerDefaultKits() {
        for (Kit k : DefaultKits.getDefaultKits()) {
            registeredKits.add(k);
        }
    }
}
