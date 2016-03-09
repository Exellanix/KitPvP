package me.exellanix.kitpvp.menus.button;

import me.exellanix.kitpvp.menus.MenuInventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Mac on 3/8/2016.
 */
public class MenuButton {
    private ItemStack item;
    private int location;
    private MenuInventory menu;

    public MenuButton(MenuInventory menu, ItemStack item) {
        this.menu = menu;
        this.item = item;
        location = -1;
    }

    public ItemStack getItem() {
        return item;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int loc) {
        this.location = loc;
    }

    public void onClick(MenuInventory inventory) {
        menu.getContainer().displayMenu(inventory);
    }

    public void onClick(ButtonAction action) {
        switch (action) {
            case CLOSE_INVENTORY:
                menu.getContainer().closeMenu();
                break;
            default:
        }
    }

    public void onClick() {}
}
