package me.exellanix.kitpvp.kits;

import org.bukkit.inventory.ItemStack;

/**
 * Created by Mac on 3/26/2016.
 */
public class Weapon {
    private boolean requip;
    private int location;
    private ItemStack weapon;

    public Weapon(boolean requip, int location, ItemStack weapon) {
        this.requip = requip;
        this.location = location;
        this.weapon = weapon;
    }

    public boolean isRequip() {
        return requip;
    }

    public int getLocation() {
        return location;
    }

    public ItemStack getWeapon() {
        return weapon;
    }
}
