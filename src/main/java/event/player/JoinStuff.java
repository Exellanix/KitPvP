package event.player;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class JoinStuff implements Listener {
	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		Location loc = (Location) p.getWorld().getSpawnLocation();
		p.teleport(loc);
		Inventory inv = p.getInventory();
		inv.clear();
		p.setMaxHealth(20);
		setupScoreboard(p);

		ItemStack air = new ItemStack(Material.AIR);
		((PlayerInventory) inv).setHelmet(air);
		((PlayerInventory) inv).setChestplate(air);
		((PlayerInventory) inv).setLeggings(air);
		((PlayerInventory) inv).setBoots(air);

		ItemStack chest = nameItem(Material.CHEST, ChatColor.GREEN + "" + ChatColor.BOLD + "Kit Selector");
		inv.setItem(0, chest);
		ItemStack arrow = nameItem(Material.ARROW, ChatColor.YELLOW + "" + ChatColor.BOLD + "Warps");
		inv.setItem(7, arrow);
		ItemStack hub = nameItem(Material.WATCH, ChatColor.GOLD + "" + ChatColor.BOLD + "Hub");
		inv.setItem(4, hub);
		ItemStack duel = nameItem(Material.DIAMOND_SWORD, ChatColor.AQUA + "" + ChatColor.BOLD + "Duel Arena");
		inv.setItem(8, duel);

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

	public void setupScoreboard(Player p) {

		Scoreboard sb = p.getScoreboard();
		if (sb.getObjective(ChatColor.AQUA + "" + ChatColor.BOLD + "Health") == null) {
			Objective o = sb.registerNewObjective(ChatColor.AQUA + "" + ChatColor.BOLD + "Health", "health");
			o.setDisplaySlot(DisplaySlot.BELOW_NAME);
		}

	}

	@EventHandler
	public void onPlayerJoinEvent1(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Welcome, "
				+ ChatColor.DARK_GREEN + "" + ChatColor.BOLD + p.getName());
		p.sendMessage(" "); 
		p.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "-" + ChatColor.YELLOW + "" + ChatColor.BOLD + "-"
				+ ChatColor.AQUA + "" + ChatColor.BOLD + "-" + ChatColor.YELLOW + "" + ChatColor.BOLD + "-"
				+ ChatColor.AQUA + "" + ChatColor.BOLD + "=" + ChatColor.YELLOW + "" + ChatColor.BOLD + "N"
				+ ChatColor.AQUA + "" + ChatColor.BOLD + "E" + ChatColor.YELLOW + "" + ChatColor.BOLD + "W"
				+ ChatColor.AQUA + "" + ChatColor.BOLD + "S" + " " + ChatColor.YELLOW + "" + ChatColor.BOLD + "A"
				+ ChatColor.AQUA + "" + ChatColor.BOLD + "N" + ChatColor.YELLOW + "" + ChatColor.BOLD + "D" + " "
				+ ChatColor.AQUA + "" + ChatColor.BOLD + "E" + ChatColor.YELLOW + "" + ChatColor.BOLD + "V"
				+ ChatColor.AQUA + "" + ChatColor.BOLD + "E" + ChatColor.YELLOW + "" + ChatColor.BOLD + "N"
				+ ChatColor.AQUA + "" + ChatColor.BOLD + "T" + ChatColor.YELLOW + "" + ChatColor.BOLD + "S"
				+ ChatColor.AQUA + "" + ChatColor.BOLD + "=" + ChatColor.YELLOW + "" + ChatColor.BOLD + "-"
				+ ChatColor.AQUA + "" + ChatColor.BOLD + "-" + ChatColor.YELLOW + "" + ChatColor.BOLD + "-"
				+ ChatColor.AQUA + "" + ChatColor.BOLD + "-");
		p.sendMessage(" ");
		p.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD
				+ "ExellPvP is still in development, expect bugs and major non-workingness!");
		p.sendMessage(" ");
		p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD
				+ "Added kits, Pyromancer and Re-crafter!");
		p.sendMessage(" ");
		p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD
				+ "Pyromancers ability is not done yet! Expect it to be done in a few days!");
		p.chat("/stats");
		p.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Enjoy your stay!");
		p.sendMessage(" ");
	}

}
