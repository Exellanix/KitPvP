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
        KitPvP.getSingleton().getServer().getPluginManager().registerEvents(listener, KitPvP.getSingleton());
        registerDefaultAbilities();
    }

    public void registerAbility(Ability ability) {
        if (ability instanceof Listener) {
            KitPvP.getSingleton().plugin.getServer().getPluginManager().registerEvents((Listener) ability, KitPvP.getSingleton());
        }
        registeredAbilities.add(ability);
    }

    public void unregisterAbility(Ability ability) {
        registeredAbilities.remove(ability);
    }

    public void unregisterAllAbilities() {
        registeredAbilities = new ArrayList<>();
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

    public Ability getAbility(String name) {
        for (Ability a : registeredAbilities) {
            if (a.getName().equals(name.toUpperCase())) {
                return a;
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

    public void registerDefaultAbilities() {
        registerAbility(new PyroFire());
        registerAbility(new SoupRegen());
        registerAbility(new SnowballSwitch());
        registerAbility(new AxeStrike());
        registerAbility(new RodHook());
    }
}
