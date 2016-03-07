package event.player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.exellanix.kitpvp.Main;
import net.md_5.bungee.api.ChatColor;

public class AxeStrike implements Listener {

	private HashMap<Player, Long> cooldown = new HashMap<>();

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if ((p.getItemInHand().getType() == Material.DIAMOND_AXE
				&& p.getItemInHand().getItemMeta().getDisplayName().contains("Thor's Axe"))
				&& (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR)) {
			if (!cooldown.containsKey(p)) {
				activateLightning(p);

			} else {
				long cooldownTime = System.currentTimeMillis() - cooldown.get(p);
				if (cooldownTime >= Main.plugin.getConfig().getInt("kits.thor.axe-cooldown") * 1000) {
					activateLightning(p);
				} else {
					p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You can not use that for another "
							+ (Main.plugin.getConfig().getInt("kits.thor.axe-cooldown") - (cooldownTime / 1000))
							+ " seconds!");
				}
			}

		}
	}

	public void activateLightning(final Player p) {

		int MaxRange = 8;
		HashSet<Material> transparentBlocks = new HashSet<>();
		transparentBlocks.add(Material.AIR);
		for (Block b : p.getLineOfSight((HashSet<Material>) null, MaxRange)) {
			if (b.getType() != Material.AIR) {
				LightningStrike ls = p.getWorld().strikeLightningEffect(b.getLocation());
				List<Entity> nbe = ls.getNearbyEntities(3, 12, 3);
				for (Entity e : nbe) {
					if (e instanceof Player && (Player) e != p) {
						((Player) e).damage(5);

					}

				}
				cooldown.put(p, System.currentTimeMillis());
				Main.plugin.getServer().getScheduler().runTaskLater(Main.plugin, new Runnable() {
					@Override
					public void run() {
						p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Your axe is ready to strike!");
					}
				}, 400);

				return;
			}
		}

	}

}
