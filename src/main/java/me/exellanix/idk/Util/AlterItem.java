package me.exellanix.idk.Util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
}
