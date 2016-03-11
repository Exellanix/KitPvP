package me.exellanix.kitpvp.kit_abilities;

import me.exellanix.kitpvp.config.KitConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by Mac on 3/6/2016.
 */
public interface Ability {
    ItemStack getItem();

    void activateAbility(Player player);

    List<Action> getActions();

    boolean hasAction(Action action);

    String getName();

    KitConfiguration getConfig();

    void setConfig(KitConfiguration config);
}
