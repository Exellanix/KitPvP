package event.player;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class SnowballSwitch implements Listener {

	@EventHandler
	public void EntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player) {
			Player p = (Player) event.getEntity();

			if (event.getDamager() instanceof Snowball) {
				Snowball d = (Snowball) event.getDamager();

				if (d.getShooter() instanceof Player) {
					Player shooter = (Player) d.getShooter();

					if (shooter.getItemInHand().getItemMeta().getDisplayName().contains("Switcher")) {
						Location shooterl = shooter.getLocation();
						shooter.teleport(p.getLocation());
						p.teleport(shooterl);

					}

				}

			}

		}
	}
}
