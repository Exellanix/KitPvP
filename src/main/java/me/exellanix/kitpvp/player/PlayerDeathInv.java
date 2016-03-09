package me.exellanix.kitpvp.player;

import me.exellanix.kitpvp.KitPvP;
import me.exellanix.kitpvp.Util.AlterItem;
import me.exellanix.kitpvp.inventory.DefaultInvConfigurations;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class PlayerDeathInv implements Listener {

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		if (e.getPlayer() instanceof Player) {
			Player player = (Player) e.getPlayer();
			KitPvP.getPlayerKits().remove(player);
			player.setMaxHealth(20);
			DefaultInvConfigurations.useJoinInv(player);
		}
	}
}
