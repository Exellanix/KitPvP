package commands;

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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import event.inventory.InventoryClick;
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

					Inventory inv = Bukkit.createInventory(null, 18,
							ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Kits");

					// PvP
					ItemStack kitPvpSword = nameItem(Material.DIAMOND_SWORD,
							ChatColor.AQUA + "" + ChatColor.BOLD + "PvP");
					inv.setItem(0, kitPvpSword);
					// Archer
					ItemStack kitArcherBow = nameItem(Material.BOW,
							ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Archer");
					inv.setItem(1, kitArcherBow);
					// Zeus
					ItemStack kitZeusAxe = nameItem(Material.DIAMOND_AXE,
							ChatColor.YELLOW + "" + ChatColor.BOLD + "Thor");
					inv.setItem(2, kitZeusAxe);

					// Kangaroo
					ItemStack kitJumperFeather = nameItem(Material.FIREWORK,
							ChatColor.RED + "" + ChatColor.BOLD + "Kangaroo");
					inv.setItem(3, kitJumperFeather);
					// Switcher
					ItemStack kitSwitcherSnowball = nameItem(Material.SNOW_BALL,
							ChatColor.WHITE + "" + ChatColor.BOLD + "Switcher");
					inv.setItem(4, kitSwitcherSnowball);
					// Fisherman
					ItemStack kitFishermanRod = nameItem(Material.FISHING_ROD,
							ChatColor.GRAY + "" + ChatColor.BOLD + "Fisherman");
					inv.setItem(5, kitFishermanRod);
					// Pot Master
					ItemStack kitPotMaster = nameItem(Material.POTION,
							ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Pot Master");
					inv.setItem(6, kitPotMaster);
					// Survivor
					ItemStack kitSurvivor = nameItem(Material.APPLE,
							ChatColor.GREEN + "" + ChatColor.BOLD + "Survivor");
					inv.setItem(7, kitSurvivor);
					// Pyromancer 
					ItemStack kitPyromancer = nameItem(Material.BLAZE_POWDER,
							ChatColor.DARK_RED + "" + ChatColor.BOLD + "Pyromancer");
					inv.setItem(8, kitPyromancer);
					// re-crafter
					ItemStack kitrecrafter = nameItem(Material.BOWL,
							ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "Re-crafter");
					inv.setItem(9, kitrecrafter);

					player.openInventory(inv);

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

	private ItemStack nameItem(ItemStack item, String name) {
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);

		item.setItemMeta(meta);
		return item;
	}

	private ItemStack nameItem(Material item, String name) {
		return nameItem(new ItemStack(item), name);
	}

	// Hotbar Clicks

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (p.getItemInHand().getType() == Material.CHEST
				&& (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR)) {
			if (p.getItemInHand().getItemMeta().getDisplayName().contains("Kit Selector")) {

				p.chat("/kits");
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
			if (p.getItemInHand().getItemMeta().getDisplayName().contains("Duel Arena")) {

				p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Coming soon!");
			}
		}
	}

	public void giveKit(Player player, String s) {
		switch (s) {
		case "pvp":
			InventoryClick.pvpKit(player);
			break;
		case "archer":
			InventoryClick.archerKit(player);
			break;
		case "zeus":
			InventoryClick.thorKit(player);
			break;
		case "kangaroo":
			InventoryClick.kangarooKit(player);
			break;
		case "switcher":
			InventoryClick.switcherKit(player);
			break;
		case "fisherman":
			InventoryClick.fishermanKit(player);
			break;
		case "potmaster":
			InventoryClick.potmasterKit(player);
			break;
		case "survivor":
			InventoryClick.survivorKit(player);
			break;
		case "pyromancer":
			InventoryClick.pyromancerKit(player);
		case "recrafter":
			InventoryClick.recrafterKit(player);
			break;
		default:
			player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "That kit does not exist!");
			break;
		}
	}
}
