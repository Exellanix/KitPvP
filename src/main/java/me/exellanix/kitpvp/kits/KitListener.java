package me.exellanix.kitpvp.kits;

import me.exellanix.kitpvp.KitPvP;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

/**
 * Created by Mac on 3/7/2016.
 */
public class KitListener implements Listener {
    @EventHandler
    public void throwOutItem(PlayerDropItemEvent event) {
        if (event.getItemDrop() != null) {
            Kit kit = KitPvP.getSingleton().getPlayerKits().get(event.getPlayer());
            if (kit != null) {
                if (kit.hasWeapon(event.getItemDrop().getItemStack())) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You cannot drop your weapon.");
                }
            }
        }
    }
}
