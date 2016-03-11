package me.exellanix.kitpvp.Util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;

/**
 * Created by Mac on 3/6/2016.
 */
public class AlterItem {

    public static ItemStack nameItem(ItemStack item, String name) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);

        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack nameItem(Material item, String name) {
        return nameItem(new ItemStack(item), name);
    }

    public static boolean itemsEqual(ItemStack first, ItemStack second) {
        if (first != null && second != null) {
            if (first.getType() == second.getType()) {
                if (first.hasItemMeta() && second.hasItemMeta()) {
                    if (first.getItemMeta().equals(second.getItemMeta())) {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    public static void addLore(ItemStack item, ArrayList<String> lore) {
        ItemMeta meta = item.getItemMeta();
        meta.setLore(lore);
        item.setItemMeta(meta);
    }

    public static ItemStack copyItem(ItemStack item) {
        ItemStack itemStack = new ItemStack(item.getType(), item.getAmount(), item.getDurability());
        ItemMeta meta = item.getItemMeta();
        itemStack.setItemMeta(meta);
        MaterialData data = item.getData();
        itemStack.setData(data);
        itemStack.setAmount(item.getAmount());
        return itemStack;
    }

    public static void addPrice(ItemStack item, int price) {
        ItemMeta meta = item.getItemMeta();
        String name = meta.getDisplayName();
        name += "    " + ChatColor.GREEN + "" + ChatColor.BOLD +  "$" + price;
        meta.setDisplayName(name);
        item.setItemMeta(meta);
    }

    public static void removePrice(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        String name = meta.getDisplayName();
        name = name.substring(0, name.indexOf("    "));
        meta.setDisplayName(name);
        item.setItemMeta(meta);
    }

    public static String prettifyTypeName(String type) {
        String[] array = type.split("_");
        for (int i = 0; i < array.length; i++) {
            String newString = "";
            newString += array[i].charAt(0);
            newString += array[i].substring(1).toLowerCase();
            array[i] = newString;
        }
        String out = "";
        for (int i = 0; i < array.length; i++) {
            out += array[i] + " ";
        }
        return out.trim();
    }
}
