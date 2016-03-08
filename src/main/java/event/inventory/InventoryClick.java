package event.inventory;

import me.exellanix.idk.kits.DefaultKits;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryClick implements Listener {

	// DefaultKits
	/*@EventHandler
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
			DefaultKits.pvpKit(player);
			break;
		case 1:
			DefaultKits.archerKit(player);
			break;
		case 2:
			DefaultKits.thorKit(player);
			break;
		case 3:
			DefaultKits.kangarooKit(player);
			break;
		case 4:
			DefaultKits.switcherKit(player);
			break;
		case 5:
			DefaultKits.fishermanKit(player);
			break;
		case 6:
			DefaultKits.potmasterKit(player);
			break;
		case 7:
			DefaultKits.survivorKit(player);
			break;
		case 8:
			DefaultKits.pyromancerKit(player);
			break;
		case 9:
			DefaultKits.recrafterKit(player);
			break;
		default:
			break;
		}
		event.setCancelled(true);
		player.closeInventory();
	}*/
}