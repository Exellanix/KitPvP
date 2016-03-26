package me.exellanix.kitpvp.kits;

import me.exellanix.kitpvp.KitPvP;
import me.exellanix.kitpvp.Util.AlterItem;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

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

    @EventHandler
    public void moveWeapon(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            if (KitPvP.getSingleton().getPlayerKits().containsKey(event.getWhoClicked())) {
                Kit k = KitPvP.getSingleton().getPlayerKits().get(event.getWhoClicked());
                for (ItemStack weapon : k.getWeapons()) {
                    if (AlterItem.itemsEqual(event.getCurrentItem(), weapon)) {
                        event.setCancelled(true);
                        event.getWhoClicked().sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You cannot move your weapon.");
                        return;
                    }
                }
            }
        }
    }
}
