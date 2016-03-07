package me.exellanix.idk.kit_abilities;

import me.exellanix.idk.Main;
import me.exellanix.idk.Util.AlterItem;
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
        Main.plugin.getServer().getPluginManager().registerEvents(listener, Main.plugin);
    }

    public void registerAbility(Ability ability) {
        if (ability instanceof Listener) {
            Main.plugin.getServer().getPluginManager().registerEvents((Listener) ability, Main.plugin);
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
