package me.exellanix.kitpvp.menus;

import me.exellanix.kitpvp.KitPvP;
import me.exellanix.kitpvp.kits.Kit;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Created by Mac on 3/7/2016.
 */
public class KitSelect implements Listener {
    private Player player;

    public KitSelect(Player player) {
        this.player = player;
        openInventory();
    }

    private void openInventory() {
        int size = KitPvP.getKitManager().getKitIcons(player).size();
        Inventory inv;
        if (size <= 18) {
            inv = Bukkit.createInventory(player, 18, ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Kits");
        } else if (size > 18 && size <= 27) {
            inv = Bukkit.createInventory(player, 27, ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Kits");
        } else if (size > 27 && size <= 36) {
            inv = Bukkit.createInventory(player, 36, ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Kits");
        } else if (size > 36 && size <= 45) {
            inv = Bukkit.createInventory(player, 45, ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Kits");
        } else {
            inv = Bukkit.createInventory(player, 54, ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Kits");
        }

        ArrayList<ItemStack> kits = KitPvP.getKitManager().getKitIcons(player);
        for (int i = 0; i < size; i++) {
            inv.setItem(i, kits.get(i));
        }

        player.openInventory(inv);
    }

    @EventHandler
    public void playerClick(InventoryClickEvent event) {
        if (event.getViewers().contains(player)) {
            ArrayList<ItemStack> kits = KitPvP.getKitManager().getKitIcons(player);
            if (kits.contains(event.getCurrentItem())) {
                Kit kit = KitPvP.getKitManager().getKitFromIcon(event.getCurrentItem());
                kit.equipKit(player);
                event.setCancelled(true);
                player.closeInventory();
                HandlerList.unregisterAll(this);
            }
        }
    }

    @EventHandler
    public void close(InventoryCloseEvent event) {
        if (event.getPlayer().equals(player)) {
            HandlerList.unregisterAll(this);
        }
    }
}
