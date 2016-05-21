package me.exellanix.kitpvp.commands;

import me.exellanix.kitpvp.KitPvP;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Brendan on 4/27/2016.
 */
public class Blood_Command implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equals("blood")) {
            if(sender instanceof Player) {
                Player player = (Player) sender;
                if(player.hasPermission("kitpvp.toggleblood")) {
                    if(args.length == 0) {
                        player.sendMessage(ChatColor.RED + "Usage: /blood [ On | Off ]");
                        return true;
                    }else if(args.length == 1) {
                        if(args[0].equalsIgnoreCase("on")) {
                            if(KitPvP.getSingleton().blood.contains(player)) {
                                player.sendMessage(ChatColor.GREEN + "Your blood is already on!");
                                return true;
                            }
                            KitPvP.getSingleton().blood.add(player);
                            player.sendMessage(ChatColor.GREEN + "Your blood is now on!");
                            return true;
                        }else if(args[0].equalsIgnoreCase("off")) {
                            if(!KitPvP.getSingleton().blood.contains(player)) {
                                player.sendMessage(ChatColor.RED + "Your blood is already off!");
                                return true;
                            }
                            KitPvP.getSingleton().blood.remove(player);
                            player.sendMessage(ChatColor.RED + "Your blood is now off!");
                            return true;
                        }
                    }else{
                        player.sendMessage(ChatColor.RED + "Too many arguments!");
                        return true;
                    }
                }else{
                    player.sendMessage(cmd.getPermissionMessage());
                    return true;
                }
            }else{
                sender.sendMessage(ChatColor.RED + "You can not use this command!");
                return true;
            }
        }
        return false;
    }
}
