package me.exellanix.kitpvp.menus;

import me.exellanix.kitpvp.KitPvP;
import me.exellanix.kitpvp.Util.AlterItem;
import me.exellanix.kitpvp.kits.Kit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
 * Created by Mac on 3/10/2016.
 */
public class KitConfirm implements Listener {
    private Player player;
    private Kit kit;
    private ItemStack confirm;
    private ItemStack deny;
    private ItemStack icon;

    public KitConfirm(Player player, Kit kit) {
        this.player = player;
        this.kit = kit;
        setup();
        openInventory();
    }

    @EventHandler
    public void playerClick(InventoryClickEvent event) {
        if (event.getWhoClicked().equals(player)) {
            if (event.getCurrentItem() != null) {
                event.setCancelled(true);
                if (AlterItem.itemsEqual(confirm, event.getCurrentItem())) {
                    MenuSounds.clickButton(player);
                    buyKit();
                    HandlerList.unregisterAll(this);
                    player.closeInventory();
                } else if (AlterItem.itemsEqual(deny, event.getCurrentItem())) {
                    MenuSounds.clickButton(player);
                    HandlerList.unregisterAll(this);
                    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You have cancelled purchasing " + kit.getDisplayName() + ".");
                    KitPvP.getSingleton().registerEvent(new BuyKit(player));
                }
            }
        }
    }

    @EventHandler
    public void close(InventoryCloseEvent event) {
        if (event.getPlayer().equals(player)) {
            HandlerList.unregisterAll(this);
            player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You have cancelled purchasing " + kit.getDisplayName() + ".");
        }
    }

    private void openInventory() {
        Inventory inv = Bukkit.createInventory(player, 9, ChatColor.RED + "" + ChatColor.BOLD + "Confirm Selection");
        for (int i = 0; i < 4; i++) {
            inv.setItem(i, confirm);
        }
        inv.setItem(4, icon);
        for (int i = 5; i < 9; i++) {
            inv.setItem(i, deny);
        }
        player.openInventory(inv);
    }

    private void setup() {
        ItemStack confirm = new ItemStack(Material.STAINED_CLAY, 1, (short) 5);
        AlterItem.nameItem(confirm, ChatColor.GREEN + "Confirm selection");
        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Left click to purchase " + kit.getName() + ChatColor.GRAY + " for $" + kit.getPrice() + ".");
        AlterItem.addLore(confirm, lore);
        this.confirm = confirm;

        ItemStack deny = new ItemStack(Material.STAINED_CLAY, 1, (short) 14);
        AlterItem.nameItem(deny, ChatColor.GREEN + "Cancel selection");
        ArrayList<String> lore1 = new ArrayList<>();
        lore1.add("");
        lore1.add(ChatColor.GRAY + "Left click to cancel purchasing " + kit.getDisplayName() + ChatColor.GRAY + ".");
        AlterItem.addLore(deny, lore1);
        this.deny = deny;

        ItemStack icon = AlterItem.copyItem(kit.getIcon());
        AlterItem.addPrice(icon, kit.getPrice());
        this.icon = icon;
    }

    private void buyKit() {
        if (KitPvP.getSingleton().getEconomy().hasAccount(player)) {
            if (KitPvP.getSingleton().getEconomy().getBalance(player) >= kit.getPrice()) {
                KitPvP.getSingleton().getEconomy().withdrawPlayer(player, kit.getPrice());
                KitPvP.getSingleton().getPluginDatabase().addPaidKit(player, kit);
                player.sendMessage(ChatColor.BOLD + "You have bought the " + kit.getDisplayName() + "" + ChatColor.WHITE + "" + ChatColor.BOLD + " kit! Equip it in the kit selection menu!");
            } else {
                player.sendMessage("You do not have enough money to purchase this kit.");
            }
        } else {
            player.sendMessage("An error has occurred during your transaction. Please try again.");
            KitPvP.getSingleton().plugin.getLogger().warning(player.getName() + " does not have an economy account!");
            KitPvP.getSingleton().plugin.getLogger().warning("Make sure you have an economy plugin installed!");
        }
    }
}
