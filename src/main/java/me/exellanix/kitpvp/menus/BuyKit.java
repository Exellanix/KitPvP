package me.exellanix.kitpvp.menus;

import me.exellanix.kitpvp.KitPvP;
import me.exellanix.kitpvp.kits.Kit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
 * Created by Mac on 3/8/2016.
 */
public class BuyKit implements Listener {
    private Player player;

    public BuyKit(Player player) {
        this.player = player;
        openInventory();
    }

    private void openInventory() {
        int size = KitPvP.getKitManager().getKitIconsBuy(player).size();
        Inventory inv;
        if (size <= 18) {
            inv = Bukkit.createInventory(player, 18, ChatColor.GREEN + "" + ChatColor.BOLD + "Kit Shop");
        } else if (size > 18 && size <= 27) {
            inv = Bukkit.createInventory(player, 27, ChatColor.GREEN + "" + ChatColor.BOLD + "Kit Shop");
        } else if (size > 27 && size <= 36) {
            inv = Bukkit.createInventory(player, 36, ChatColor.GREEN + "" + ChatColor.BOLD + "Kit Shop");
        } else if (size > 36 && size <= 45) {
            inv = Bukkit.createInventory(player, 45, ChatColor.GREEN + "" + ChatColor.BOLD + "Kit Shop");
        } else {
            inv = Bukkit.createInventory(player, 54, ChatColor.GREEN + "" + ChatColor.BOLD + "Kit Shop");
        }

        ArrayList<ItemStack> kits = KitPvP.getKitManager().getKitIconsBuy(player);
        for (int i = 0; i < size; i++) {
            inv.setItem(i, kits.get(i));
        }

        player.openInventory(inv);
    }

    @EventHandler
    public void playerClick(InventoryClickEvent event) {
        if (event.getViewers().contains(player)) {
            ArrayList<ItemStack> kits = KitPvP.getKitManager().getKitIconsBuy(player);
            if (kits.contains(event.getCurrentItem())) {
                MenuSounds.clickButton(player);
                Kit kit = KitPvP.getKitManager().getKitFromIcon(event.getCurrentItem());
                if (KitPvP.getEconomy().hasAccount(player)) {
                    if (KitPvP.getEconomy().getBalance(player) >= kit.getPrice()) {
                        KitPvP.getEconomy().withdrawPlayer(player, kit.getPrice());
                        KitPvP.getPluginDatabase().addPaidKit(player, kit);
                        player.sendMessage(ChatColor.BOLD + "You have bought the " + kit.getName() + "" + ChatColor.WHITE + "" + ChatColor.BOLD + " kit! Equip it in the kit selection menu!");
                    } else {
                        player.sendMessage("You do not have enough money to purchase this kit.");
                    }
                } else {
                    player.sendMessage("An error has occurred during your transaction. Please try again.");
                    KitPvP.plugin.getLogger().warning(player.getName() + " does not have an economy account!");
                    KitPvP.plugin.getLogger().warning("Make sure you have an economy plugin installed!");
                }
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
