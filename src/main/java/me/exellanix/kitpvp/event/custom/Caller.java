package me.exellanix.kitpvp.event.custom;

import me.exellanix.kitpvp.KitPvP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Created by Brendan on 5/26/2016.
 */
public class Caller implements Listener {

    //PlayerBowKillEvent caller
    @EventHandler
    public void playerBowKillEvent(EntityDamageByEntityEvent event) {
        if(event.getEntity() instanceof Player) {
            if(event.getDamager() instanceof Player) {
                EntityDamageEvent de = event.getEntity().getLastDamageCause();
                EntityDamageEvent.DamageCause cause = de.getCause();

                if(cause == EntityDamageEvent.DamageCause.PROJECTILE) {
                    Arrow a = (Arrow) event.getDamager();
                    if(a.getShooter() instanceof Player) {
                        Player player = (Player) a.getShooter();
                        String str = ChatColor.DARK_RED + "" + ChatColor.BOLD + "[DEATH] a player has been killed!";
                        KitPvP.getSingleton().getServer().getPluginManager().callEvent(new PlayerBowKillEvent((Player) event.getEntity(), player, str, (int)event.getFinalDamage()));
                        Bukkit.getServer().broadcastMessage(str);
                    }
                }
            }
        }
    }
}
