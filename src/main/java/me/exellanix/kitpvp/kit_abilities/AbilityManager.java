package me.exellanix.kitpvp.kit_abilities;

import me.exellanix.kitpvp.KitPvP;
import me.exellanix.kitpvp.Util.AlterItem;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Created by Mac on 3/6/2016.
 */
public class AbilityManager {
    private ArrayList<Ability> registeredAbilities;
    private AbilityListener listener;

    public AbilityManager() {
        registeredAbilities = new ArrayList<>();
        listener = new AbilityListener();
        KitPvP.plugin.getServer().getPluginManager().registerEvents(listener, KitPvP.plugin);
    }

    public void registerAbility(Ability ability) {
        if (ability instanceof Listener) {
            KitPvP.plugin.getServer().getPluginManager().registerEvents((Listener) ability, KitPvP.plugin);
        }
        registeredAbilities.add(ability);
    }

    public void unregisterAbility(Ability ability) {
        registeredAbilities.remove(ability);
    }

    public ArrayList<Ability> getAbilities() {
        return registeredAbilities;
    }

    public Ability getAbility(ItemStack item) {
        if (isAbility(item)) {
            for (Ability a : registeredAbilities) {
                if (AlterItem.itemsEqual(a.getItem(), item)) {
                    return a;
                }
            }
        }
        return null;
    }

    public boolean isAbility(ItemStack item) {
        for (Ability a : registeredAbilities) {
            if (AlterItem.itemsEqual(a.getItem(), item)) {
                return true;
            }
        }
        return false;
    }
}
