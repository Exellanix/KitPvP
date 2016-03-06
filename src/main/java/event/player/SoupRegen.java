package event.player;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SoupRegen implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (p.getItemInHand().getType() == Material.MUSHROOM_SOUP
				&& (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR)) {
			if (p.getHealth() < 20) {

				if (p.getHealth() + 6 > 20) {
					p.setHealth((20 - p.getHealth()) + p.getHealth());
				} else {
					p.setHealth(p.getHealth() + 6);
				}

				p.getItemInHand().setType(Material.BOWL);
			}
		}

	}
}
