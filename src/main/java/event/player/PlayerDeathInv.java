package event.player;

import me.exellanix.idk.Util.AlterItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerDeathInv implements Listener {

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		if (e.getPlayer() instanceof Player) {
			Player player = (Player) e.getPlayer();
			PlayerInventory inventory = player.getInventory();
			inventory.clear();
			player.setMaxHealth(20);
			ItemStack air = new ItemStack(Material.AIR);
			inventory.setHelmet(air);
			inventory.setChestplate(air);
			inventory.setLeggings(air);
			inventory.setBoots(air);
			


			ItemStack chest = AlterItem.nameItem(Material.CHEST, ChatColor.GREEN + "" + ChatColor.BOLD + "Kit Selector");
			inventory.setItem(0, chest);
			ItemStack arrow = AlterItem.nameItem(Material.ARROW, ChatColor.YELLOW + "" + ChatColor.BOLD + "Warps");
			inventory.setItem(7, arrow);
			ItemStack hub = AlterItem.nameItem(Material.WATCH, ChatColor.GOLD + "" + ChatColor.BOLD + "Hub");
			inventory.setItem(4, hub);
			ItemStack duel = AlterItem.nameItem(Material.DIAMOND_SWORD, ChatColor.AQUA + "" + ChatColor.BOLD + "Duel Arena");
			inventory.setItem(8, duel);


		}
	}
}
