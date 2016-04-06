package me.exellanix.kitpvp.commands;

import me.exellanix.kitpvp.KitPvP;
import me.exellanix.kitpvp.external_jars.LoadExternalJar;
import me.exellanix.kitpvp.kits.Kit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by Mac on 3/9/2016.
 */
public class KitPvP_Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("kitpvp.kitpvp.command")) {
                String subCommand = null;
                String[] subArgs;
                if (strings.length > 0) {
                    subCommand = strings[0];
                    if (strings.length > 1) {
                        subArgs = new String[strings.length - 1];
                        for (int i = 1; i < strings.length; i++) {
                            subArgs[i - 1] = strings[i];
                        }
                    }
                }
                if (subCommand != null) {
                    switch (subCommand) {
                        case "reload":
                            reloadCommand(player, command);
                            break;
                        case "help":
                            helpCommand(player, command);
                            break;
                        default:
                    }
                } else {
                    helpCommand(player, command);
                }
            } else {
                player.sendMessage(command.getPermissionMessage());
            }
            return true;
        }
        return false;
    }

    private void helpCommand(Player player, Command command) {
        ArrayList<String> out = new ArrayList<>();
        out.add(ChatColor.GOLD + "" + ChatColor.BOLD + "<------ KitPvP Commands ------>");
        if (player.hasPermission("kitpvp.kitpvp.reload")) {
            out.add(ChatColor.RED + "/kitpvp reload " + ChatColor.WHITE + "Allows you to reload the plugin.");
        }
        if (out.size() > 1) {
            for (String s : out) {
                player.sendMessage(s);
            }
        } else {
            player.sendMessage(command.getPermissionMessage());
        }
    }

    private void reloadCommand(Player player, Command command) {
        if (player.hasPermission("kitpvp.kitpvp.reload")) {
            KitPvP.getSingleton().getLogger().info("Reloading the plugin...");
            KitPvP.getSingleton().getLogger().info("Saving config files...");
            KitPvP.getSingleton().reloadConfig();
            KitPvP.getSingleton().getKitConfig().reloadConfig();
            KitPvP.getSingleton().getPluginDatabase().reloadConfigs();
            KitPvP.getSingleton().getLogger().info("Reloading abilities and kits...");
            KitPvP.getSingleton().getKitManager().unregisterAllKits();
            KitPvP.getSingleton().getAbilityManager().unregisterAllAbilities();
            KitPvP.getSingleton().getAbilityManager().registerDefaultAbilities();
            KitPvP.getSingleton().getKitManager().registerDefaultKits();
            KitPvP.getSingleton().getKitManager().reloadPlayerKits();
            LoadExternalJar.reloadJars();
            KitPvP.getSingleton().getLogger().info("Done.");
            player.sendMessage("The plugin has been reloaded!");
        } else {
            player.sendMessage(command.getPermissionMessage());
        }
    }
}
