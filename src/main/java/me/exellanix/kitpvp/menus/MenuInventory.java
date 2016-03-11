package me.exellanix.kitpvp.menus;

import me.exellanix.kitpvp.menus.button.MenuButton;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Created by Mac on 3/8/2016.
 */
public class MenuInventory {
    private int size;
    private String title;
    private ArrayList<MenuButton> buttons;
    private MenuContainer container;

    public MenuInventory(MenuContainer container) {
        this.container = container;
        size = 9;
        title = "";
        buttons = new ArrayList<>();
    }

    public MenuInventory(ArrayList<MenuButton> buttons) {
        size = buttons.size();
        calculateSize();
        title = "";
        buttons = new ArrayList<>();
    }

    public MenuInventory() {
        size = 0;
        title = "";
        buttons = new ArrayList<>();
    }

    public int size() {
        return size;
    }

    public String getTitle() {
        return title;
    }

    public ItemStack[] getItems() {
        ItemStack[] itemStacks = new ItemStack[size];
        for (int i = 0; i < size; i++) {
            if (size <= buttons.size()) {
                if (buttons.get(i).getLocation() < 0) {
                    if (itemStacks[i] == null) {
                        itemStacks[i] = buttons.get(i).getItem();
                    }
                } else {
                    itemStacks[buttons.get(i).getLocation()] = buttons.get(i).getItem();
                }
            }
        }
        return itemStacks;
    }

    public void addButton(MenuButton button) {
        buttons.add(button);
        calculateSize();
    }

    private void calculateSize() {
        int size = 9;
        while (size < this.size || size >= 54) {
            size += 9;
        }
        this.size = size;
    }

    public MenuContainer getContainer() {
        return container;
    }

    public void setContainer(MenuContainer container) {
        this.container = container;
    }

    public MenuButton getButton(ItemStack stack) {
        for (MenuButton button : buttons) {
            if (button.getItem().equals(stack)) {
                return button;
            }
        }
        return null;
    }
}
