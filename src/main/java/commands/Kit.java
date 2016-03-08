package commands;

import me.exellanix.idk.Main;
import me.exellanix.idk.kits.DefaultKits;
import me.exellanix.idk.menus.KitSelect;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import net.md_5.bungee.api.ChatColor;

public class Kit implements CommandExecutor, Listener {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("kit")) {
			if (sender instanceof Player) {
				// /kit -> 0
				// /kit kitName -> 1
				// /kit kitName <player> -> 2
				Player player = (Player) sender;
				if (args.length == 0) {
					Main.registerEvent(new KitSelect(player));
					return true;
				} else if (args.length == 1) {
					String kitName = args[0].toLowerCase();
					giveKit(player, kitName);
					return true;

				} else if (args.length == 2) {
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
                Main.registerEvent(new KitSelect(p));
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
		}
	}

	public void giveKit(Player player, String s) {
		me.exellanix.idk.kits.Kit k = Main.getKitManager().getKitFromString(s.toUpperCase());
		if (k != null) {
			k.equipKit(player);
		} else {
			player.sendMessage("That kit doesn't seem to exist.");
		}
	}
}
