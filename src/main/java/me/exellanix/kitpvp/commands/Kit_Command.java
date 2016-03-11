package me.exellanix.kitpvp.commands;

import me.exellanix.kitpvp.KitPvP;
import me.exellanix.kitpvp.menus.BuyKit;
import me.exellanix.kitpvp.menus.KitSelect;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Kit_Command implements CommandExecutor, Listener {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("kit")) {
			if (sender instanceof Player) {
				// /kit -> 0
				// /kit kitName -> 1
				// /kit kitName <player> -> 2
				Player player = (Player) sender;
				if (args.length == 0 && player.hasPermission("kitpvp.kit.command")) {
					if (!KitPvP.getSingleton().getPlayerKits().containsKey(player) || player.hasPermission("kitpvp.kit.bypass")) {
						KitPvP.getSingleton().registerEvent(new KitSelect(player));
					} else {
						player.sendMessage("You have already chosen your kit!");
					}
					return true;
				} else if (args.length == 1 && player.hasPermission("kitpvp.kit.command")) {
					String kitName = args[0].toLowerCase();
					if (!KitPvP.getSingleton().getPlayerKits().containsKey(player) || player.hasPermission("kitpvp.kit.bypass")) {
						giveKit(player, kitName);
					} else {
						player.sendMessage("You have already chosen your kit!");
					}
					return true;

				} else if (args.length == 2 && player.hasPermission("kitpvp.kit.setothers")) {
                    String kitname = args[0].toLowerCase();
                    Player pl = null;
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (p.getName().equals(args[1])) {
                            pl = p;
                            break;
                        }
                    }

                    if (pl != null) {
                        giveKit(pl, kitname);
                    } else {
                        player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "That player is not online!");
                    }
					return true;
				} else {
                    player.sendMessage(cmd.getPermissionMessage());
                }

			}
		}
		return false;
	}

	// Hotbar Clicks
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (p.getItemInHand().getType() == Material.CHEST
				&& (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR)) {
			if (p.getItemInHand().getItemMeta().getDisplayName().contains("Kit Selector")) {
                KitPvP.getSingleton().registerEvent(new KitSelect(p));
			}
		}
	}

	@EventHandler
	public void onPlayerInteract2(PlayerInteractEvent e) {
		Player p = e.getPlayer();

		if (p.getItemInHand().getType() == Material.ARROW
				&& (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR)) {
			if (p.getItemInHand().getItemMeta().getDisplayName().contains("Warps")) {

				p.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "Work in progress!");
			}
		}
	}

	@EventHandler
	public void onPlayerInteract3(PlayerInteractEvent e) {
		Player p = e.getPlayer();

		if (p.getItemInHand().getType() == Material.DIAMOND_SWORD
				&& (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR)) {
			if (p.getItemInHand().getItemMeta().getDisplayName() != null && p.getItemInHand().getItemMeta().getDisplayName().contains("Duel Arena")) {

				p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Coming soon!");
			}
		} else if (p.getItemInHand().getType() == Material.EMERALD
                && (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR)) {
            if (p.getItemInHand().getItemMeta().getDisplayName() != null && p.getItemInHand().getItemMeta().getDisplayName().contains("Kit Shop")) {
                if (KitPvP.getSingleton().getKitManager().getKitIconsBuy(p).size() != 0) {
                    KitPvP.getSingleton().registerEvent(new BuyKit(p));
                } else {
                    p.sendMessage(ChatColor.BOLD + "You have bought all of the available kits!");
                }
            }
        }
	}

	public void giveKit(Player player, String s) {
		me.exellanix.kitpvp.kits.Kit k = KitPvP.getSingleton().getKitManager().getKitFromString(s.toUpperCase());
		if (k != null) {
            if (KitPvP.getSingleton().getKitManager().hasKit(player, k)) {
                k.equipKit(player);
                KitPvP.getSingleton().getPlayerKits().put(player, k);
                player.sendMessage(ChatColor.BOLD + "You have chosen the kit " + k.getDisplayName() + org.bukkit.ChatColor.WHITE + "" + org.bukkit.ChatColor.BOLD + "!");
            } else {
                player.sendMessage(ChatColor.BOLD + "You need to buy that kit to use it!");
            }
		} else {
			player.sendMessage("That kit doesn't seem to exist.");
		}
	}
}
