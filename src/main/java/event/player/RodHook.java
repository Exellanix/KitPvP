package event.player;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.util.Vector;

public class RodHook implements Listener {

	@EventHandler
	public void onPlayerHitFishingrodscorpion(final PlayerFishEvent event) {
		final Player p = event.getPlayer();
		if (event.getCaught() instanceof Player) {
			final Player caught = (Player) event.getCaught();
			if (p != caught) {
				if (p.getItemInHand().getType() == Material.FISHING_ROD
						&& p.getItemInHand().getItemMeta().getDisplayName().contains("Fish & Sticks")) {
					p.sendMessage(ChatColor.GREEN + "GET OVER HERE!");
					Vector v = p.getLocation().getDirection();
					v.multiply(-.25);
					v.setY(.25);
					caught.setVelocity(v);
				}
			}
		}
	}

	{
	}
}


