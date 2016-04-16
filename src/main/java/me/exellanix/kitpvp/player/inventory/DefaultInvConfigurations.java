package me.exellanix.kitpvp.player.inventory;

import me.exellanix.kitpvp.KitPvP;
import me.exellanix.kitpvp.Util.AlterItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Team;

/**
 * Created by Mac on 3/8/2016.
 */
public class DefaultInvConfigurations {
    public static void useJoinInv(Player player) {

        if (player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
            player.removePotionEffect(PotionEffectType.INVISIBILITY);
        }

        player.setAllowFlight(false);
        player.setFlying(false);
        Inventory inv = player.getInventory();
        inv.clear();
        ItemStack air = new ItemStack(Material.AIR);
        ((PlayerInventory) inv).setHelmet(air);
        ((PlayerInventory) inv).setChestplate(air);
        ((PlayerInventory) inv).setLeggings(air);
        ((PlayerInventory) inv).setBoots(air);

        ItemStack chest = AlterItem.nameItem(Material.CHEST, ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Kit Selector");
        inv.setItem(0, chest);
        ItemStack star= AlterItem.nameItem(Material.NETHER_STAR, ChatColor.YELLOW + "" + ChatColor.BOLD + "Warps");
        inv.setItem(7, star);
        ItemStack hub = AlterItem.nameItem(Material.WATCH, ChatColor.GOLD + "" + ChatColor.BOLD + "Hub");
        inv.setItem(4, hub);
        if(KitPvP.getSingleton().getPlayerPrevKit().get(player) != null) {
            ItemStack prevKit = AlterItem.nameItem(Material.DOUBLE_PLANT, ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Play Previous Kit");
            inv.setItem(2, prevKit);
        }
        ItemStack skullItem = new ItemStack(Material.SKULL_ITEM, 1, (byte)3);
        SkullMeta  meta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.SKULL_ITEM);
        meta.setOwner(player.getName());
        skullItem.setItemMeta(meta);
        ItemStack skull = AlterItem.nameItem(skullItem, ChatColor.AQUA + "" + ChatColor.BOLD + "Profile");
        inv.setItem(8, skull);
        ItemStack shop = AlterItem.nameItem(Material.EMERALD, ChatColor.GREEN + "" + ChatColor.BOLD + "Kit Shop");
        inv.setItem(1, shop);
    }

    public static void useSpectateInv(Player player) {
        PotionEffect invisible = new PotionEffect(PotionEffectType.INVISIBILITY, 1000000000, 255, true, false);

        player.setAllowFlight(true);
        player.setFlying(true);
        player.addPotionEffect(invisible);
        player.getInventory().clear();
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
    }
}
