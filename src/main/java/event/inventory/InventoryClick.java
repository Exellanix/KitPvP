package event.inventory;

import me.exellanix.kitpvp.Kits;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryClick implements Listener {

	// Kits
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Inventory inv = event.getInventory();
		if (!inv.getTitle().equals(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Kits")) {
			return;
		}

		if (!(event.getWhoClicked() instanceof Player))
			return;

		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		switch (slot) {
		case 0:
			Kits.pvpKit(player);
			break;
		case 1:
			Kits.archerKit(player);
			break;
		case 2:
			Kits.thorKit(player);
			break;
		case 3:
			Kits.kangarooKit(player);
			break;
		case 4:
			Kits.switcherKit(player);
			break;
		case 5:
			Kits.fishermanKit(player);
			break;
		case 6:
			Kits.potmasterKit(player);
			break;
		case 7:
			Kits.survivorKit(player);
			break;
		case 8:
			Kits.pyromancerKit(player);
			break;
		case 9:
			Kits.recrafterKit(player);
			break;
		default:
			break;
		}
		event.setCancelled(true);
		player.closeInventory();
	}
}