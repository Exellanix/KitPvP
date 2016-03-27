package me.exellanix.kitpvp.commands;

import me.exellanix.kitpvp.KitPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Brendan on 3/26/2016.
 */
public class Soup_Command implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(cmd.getName().equalsIgnoreCase("soup")) {
            if(sender instanceof Player) {
                Player player = (Player) sender;
                if(player.hasPermission("kitpvp.soup")) {
                    if(args.length == 0) {

                        player.addPotionEffect((new PotionEffect(PotionEffectType.BLINDNESS, 100, 100)));
                        player.addPotionEffect((new PotionEffect(PotionEffectType.CONFUSION, 100, 1)));
                        player.addPotionEffect((new PotionEffect(PotionEffectType.SLOW, 100, 255)));
                        player.sendMessage(ChatColor.AQUA + "Refilling...");
                        int openSlots = openSlots(player);
                        while (KitPvP.getSingleton().getEconomy().getBalance(player) <= ((double) openSlots) * .5) {
                            openSlots--;
                        }
                        if (openSlots > 0) {
                            delay(player, openSlots);
                            player.sendMessage(ChatColor.GREEN + "You have refilled " + openSlots + " soups!");
                            KitPvP.getSingleton().getEconomy().withdrawPlayer(player, ((double) openSlots) * .5);
                        } else {
                            player.sendMessage(ChatColor.RED + "You do not have enough money!");
                        }
                    }else if(args.length == 1) {
                        try {
                            int amount = Integer.parseInt(args[0]);
                            int openSlots = openSlots(player);
                            while (KitPvP.getSingleton().getEconomy().getBalance(player) <= ((double) amount) * .5) {
                                amount--;
                            }
                            if(amount <= openSlots) {
                                KitPvP.getSingleton().getEconomy().withdrawPlayer(player, ((double) amount) * .5);
                            }else{
                                KitPvP.getSingleton().getEconomy().withdrawPlayer(player, ((double) openSlots) * .5);
                            }
                            delay(player, amount);
                            if(Integer.parseInt(args[0]) > amount) {
                                player.sendMessage(ChatColor.RED + "You could only afford " + (amount <= openSlots ?amount : openSlots) + " soups!");
                            }
                            player.sendMessage(ChatColor.GREEN + "Refilled " + amount + " soups!");
                        }catch(NumberFormatException e) {
                            player.sendMessage(ChatColor.RED + "Please enter a number!");
                        }
                    }
                    return true;
                }
            }else{
                sender.sendMessage(ChatColor.RED + "Only players can execute this command!");
                return true;
            }
        }

        return false;
    }

      public void delay(final Player soup, final int amount) {
        Bukkit.getServer().getScheduler().runTaskLater(KitPvP.getSingleton(), () -> {
                int refilled = 0;
                for(int i = 0; i < 36; i++) {
                    if(refilled == amount) {
                        break;
                    }
                    if(soup.getInventory().getItem(i) == null) {

                        Inventory inv = soup.getInventory();
                        ItemStack mushroomSoup = new ItemStack(Material.MUSHROOM_SOUP);

                        inv.addItem(mushroomSoup);
                        refilled++;

                    }
                }

                soup.sendMessage(ChatColor.GREEN + "Done!");
        }, 100L);
    }

    public int openSlots(Player soup) {
        int slots = 0;
        for (int i = 0; i < 36; i++) {
            if (soup.getInventory().getItem(i) == null) {
                slots++;

            }
        }
        return slots;
    }
}
