package me.exellanix.kitpvp.kit_abilities;

import me.exellanix.kitpvp.KitPvP;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by Mac on 3/6/2016.
 */
public class AbilityListener implements Listener {

    @EventHandler
    public void playerUseItem(PlayerInteractEvent e) {
        if (e.getItem() != null) {
            if (KitPvP.getSingleton().getAbilityManager().isAbility(e.getItem())) {
                Ability ability = KitPvP.getSingleton().getAbilityManager().getAbility(e.getItem());
                if (ability.hasAction(e.getAction())) {
                    PlayerActivateAbilityEvent event = new PlayerActivateAbilityEvent(e.getPlayer(), ability);
                    Bukkit.getServer().getPluginManager().callEvent(event);
                    if (!event.isCancelled()) {
                        ability.activateAbility(e.getPlayer());
                    } else {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void throwOutItem(PlayerDropItemEvent event) {
        if (event.getItemDrop() != null) {
            if (KitPvP.getSingleton().getAbilityManager().isAbility(event.getItemDrop().getItemStack())) {
                event.setCancelled(true);
                if (!KitPvP.getSingleton().getPlayerKits().get(event.getPlayer()).getWeapons().contains(event.getItemDrop().getItemStack())) {
                    event.getPlayer().sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You cannot drop this item.");
                }
            }
        }
    }
}
