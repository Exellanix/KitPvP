package me.exellanix.kitpvp.event.region;

import me.exellanix.kitpvp.KitPvP;
import me.exellanix.kitpvp.regions.Region;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;

/**
 * Created by Mac on 3/15/2016.
 */
public class RegionListener implements Listener {

    @EventHandler
    public void regionDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            for (Region r : KitPvP.getSingleton().getRegionManager().getRegisteredRegions()) {
                if (r.isInside(player)) {
                    if (!r.isDamageAllowed(event.getCause())) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void shootBow(EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player) {
            for (Region r : KitPvP.getSingleton().getRegionManager().getRegisteredRegions()) {
                if (r.isInside((Player) event.getEntity())) {
                    if (!r.isDamageAllowed(EntityDamageEvent.DamageCause.PROJECTILE)) {
                        event.getBow().setDurability((short)0);
                        event.setCancelled(true);
                        return;
                    }
                }
            }
        }
    }
}
