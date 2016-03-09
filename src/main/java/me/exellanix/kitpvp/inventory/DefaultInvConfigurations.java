package me.exellanix.kitpvp.inventory;

import me.exellanix.kitpvp.Util.AlterItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/**
 * Created by Mac on 3/8/2016.
 */
public class DefaultInvConfigurations {
    public static void useJoinInv(Player player) {
        Inventory inv = player.getInventory();
        inv.clear();
        ItemStack air = new ItemStack(Material.AIR);
        ((PlayerInventory) inv).setHelmet(air);
        ((PlayerInventory) inv).setChestplate(air);
        ((PlayerInventory) inv).setLeggings(air);
        ((PlayerInventory) inv).setBoots(air);

        ItemStack chest = AlterItem.nameItem(Material.CHEST, ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Kit Selector");
        inv.setItem(0, chest);
        ItemStack arrow = AlterItem.nameItem(Material.ARROW, ChatColor.YELLOW + "" + ChatColor.BOLD + "Warps");
        inv.setItem(7, arrow);
        ItemStack hub = AlterItem.nameItem(Material.WATCH, ChatColor.GOLD + "" + ChatColor.BOLD + "Hub");
        inv.setItem(4, hub);
        ItemStack duel = AlterItem.nameItem(Material.DIAMOND_SWORD, ChatColor.AQUA + "" + ChatColor.BOLD + "Duel Arena");
        inv.setItem(8, duel);
        ItemStack shop = AlterItem.nameItem(Material.EMERALD, ChatColor.GREEN + "" + ChatColor.BOLD + "Kit Shop");
        inv.setItem(1, shop);
    }
}
