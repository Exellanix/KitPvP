package me.exellanix.kitpvp.player;

import me.exellanix.kitpvp.KitPvP;
import me.exellanix.kitpvp.Util.AlterItem;
import me.exellanix.kitpvp.inventory.DefaultInvConfigurations;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
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
        DefaultInvConfigurations.useJoinInv(p);
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

	@EventHandler
	public void playerLeave(PlayerQuitEvent event) {
		KitPvP.getSingleton().getPlayerKits().remove(event.getPlayer());
	}

	@EventHandler
	public void playerKick(PlayerKickEvent event) {
		KitPvP.getSingleton().getPlayerKits().remove(event.getPlayer());
	}
}
