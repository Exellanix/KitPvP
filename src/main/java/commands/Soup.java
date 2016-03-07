package commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.exellanix.kitpvp.Main;

public class Soup implements CommandExecutor {


	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("soup")) {
			if(sender instanceof Player) {
				if(p.hasPermission("kitpvp.soup")) {
					
				}else{
					p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You don't have permission!");
				}
				
			}else{
				p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Only players can do this!");
			}
		}
		
		
		return false;
	}
	
	public void delay(final Player p) {
		Bukkit.getServer().getScheduler().runTaskLater(Main.plugin, new Runnable() {
			public void run() {
			}
		}, 100L);
	}

}
