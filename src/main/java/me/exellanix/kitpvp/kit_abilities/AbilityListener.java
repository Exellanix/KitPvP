package me.exellanix.kitpvp.kit_abilities;

import me.exellanix.kitpvp.KitPvP;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by Mac on 3/6/2016.
 */
public class AbilityListener implements Listener {

    @EventHandler
    public void playerUseItem(PlayerInteractEvent e) {
        if (e.getItem() != null) {
            if (KitPvP.getAbilityManager().isAbility(e.getItem())) {
                Ability ability = KitPvP.getAbilityManager().getAbility(e.getItem());
                if (ability.hasAction(e.getAction())) {
                    ability.activateAbility(e.getPlayer());
                }
            }
        }
    }
}
