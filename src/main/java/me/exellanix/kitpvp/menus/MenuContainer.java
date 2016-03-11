package me.exellanix.kitpvp.menus;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

/**
 * Created by Mac on 3/7/2016.
 */
public class MenuContainer implements Listener {
    private Player player;
    private MenuInventory inventory;

    public MenuContainer(Player player) {
        this.player = player;
        inventory = new MenuInventory();
    }

    public void displayMenu(MenuInventory inv) {
        if (player.getOpenInventory() == null) {
            Inventory inventory = Bukkit.createInventory(player, inv.size(), inv.getTitle());
            inventory.setContents(inv.getItems());
            player.openInventory(inventory);
        } else if (inv.size() > inventory.size()) {
            Inventory inventory = Bukkit.createInventory(player, inv.size(), inv.getTitle());
            inventory.setContents(inv.getItems());
            player.openInventory(inventory);
        }
    }

    public void closeMenu() {
        player.closeInventory();
    }

    @EventHandler
    public void playerClick(InventoryClickEvent event) {
        if (event.getCurrentItem() != null) {
            if (event.getWhoClicked().equals(player)) {
                inventory.getButton(event.getCurrentItem()).onClick();
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
