package me.exellanix.kitpvp.regions;

import me.exellanix.kitpvp.KitPvP;
import me.exellanix.kitpvp.kit_abilities.PlayerActivateAbilityEvent;
import me.exellanix.kitpvp.kits.Kit;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;

/**
 * Created by Mac on 3/7/2016.
 */
public class SpawnRegion implements Region, Listener {
    private Selection selection;
    private ArrayList<Kit> enabledKits;
    private Border border;
    private ArrayList<EntityDamageEvent.DamageCause> damageFlags;

    public SpawnRegion() {
        setup();
        border = new Border(selection.getFirst(), selection.getSecond(), 1);
        KitPvP.getSingleton().plugin.getServer().getScheduler().runTaskTimerAsynchronously(KitPvP.getSingleton().plugin, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (isInside(p) && !p.hasPermission("kitpvp.region.bypasskitselect") && p.getGameMode() != GameMode.CREATIVE) {
                    if (border.getNorth().isInside(p)) {
                        if (!KitPvP.getSingleton().getPlayerKits().containsKey(p)) {
                            Vector vector = new Vector(0, 1, 1.2);
                            p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 1, .8f);
                            p.setVelocity(vector);
                            p.sendMessage("Please choose a kit before leaving spawn!");
                        } else {
                            KitPvP.getSingleton().getServer().getScheduler().runTask(KitPvP.getSingleton(), () -> {
                                Vector vector = new Vector(0, .5, -1);
                                p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1, 1f);
                                p.setVelocity(vector);
                                p.sendMessage(ChatColor.BOLD + "Fight!");
                                PotionEffect effect = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 255, true, false);
                                p.addPotionEffect(effect);
                            });
                        }
                    } else if (border.getSouth().isInside(p)) {
                        if (!KitPvP.getSingleton().getPlayerKits().containsKey(p)) {
                            Vector vector = new Vector(0, 1, -1.2);
                            p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 1, .8f);
                            p.setVelocity(vector);
                            p.sendMessage("Please choose a kit before leaving spawn!");
                        } else {
                            KitPvP.getSingleton().getServer().getScheduler().runTask(KitPvP.getSingleton(), () -> {
                                Vector vector = new Vector(0, .5, 1);
                                p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1, 1f);
                                p.setVelocity(vector);
                                p.sendMessage(ChatColor.BOLD + "Fight!");
                                PotionEffect effect = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 255, true, false);
                                p.addPotionEffect(effect);
                            });
                        }
                    } else if (border.getEast().isInside(p)) {
                        if (!KitPvP.getSingleton().getPlayerKits().containsKey(p)) {
                            Vector vector = new Vector(-1.2, 1, 0);
                            p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 1, .8f);
                            p.setVelocity(vector);
                            p.sendMessage("Please choose a kit before leaving spawn!");
                        } else {
                            KitPvP.getSingleton().getServer().getScheduler().runTask(KitPvP.getSingleton(), () -> {
                                Vector vector = new Vector(1, .5, 0);
                                p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1, 1f);
                                p.setVelocity(vector);
                                p.sendMessage(ChatColor.BOLD + "Fight!");
                                PotionEffect effect = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 255, true, false);
                                p.addPotionEffect(effect);
                            });
                        }
                    } else if (border.getWest().isInside(p)) {
                        if (!KitPvP.getSingleton().getPlayerKits().containsKey(p)) {
                            Vector vector = new Vector(1.2, 1, 0);
                            p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 1, .8f);
                            p.setVelocity(vector);
                            p.sendMessage("Please choose a kit before leaving spawn!");
                        } else {
                            KitPvP.getSingleton().getServer().getScheduler().runTask(KitPvP.getSingleton(), () -> {
                                Vector vector = new Vector(-1, .5, 0);
                                p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1, 1f);
                                p.setVelocity(vector);
                                p.sendMessage(ChatColor.BOLD + "Fight!");
                                PotionEffect effect = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 255, true, false);
                                p.addPotionEffect(effect);
                            });
                        }
                    }
                }
            }
        }, 0, 2);
    }

    @Override
    public Selection getSelection() {
        return selection;
    }

    @Override
    public ArrayList<Kit> getEnabledKits() {
        return enabledKits;
    }

    @Override
    public void addKit(Kit kit) {
        if (!enabledKits.contains(kit)) {
            enabledKits.add(kit);
        }
    }

    @Override
    public void removeKit(Kit kit) {
        enabledKits.remove(kit);
    }

    @Override
    public boolean hasKit(Kit kit) {
        return enabledKits.contains(kit);
    }

    @Override
    public boolean isInside(Player player) {
        return selection.isInside(player);
    }

    @Override
    public String getName() {
        return "Spawn";
    }

    @Override
    public ArrayList<EntityDamageEvent.DamageCause> getDamageFlags() {
        return damageFlags;
    }

    @Override
    public boolean isDamageAllowed(EntityDamageEvent.DamageCause damage) {
        return !damageFlags.contains(damage);
    }

    @EventHandler
    public void ability(PlayerActivateAbilityEvent event) {
        if (isInside(event.getPlayer())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You cannot use your kit inside spawn.");
        }
    }

    private void setup() {
        selection = new Selection();
        selection.setFirst(new Location(Bukkit.getWorld("world"), -10, 15, 372).getBlock());
        selection.setSecond(new Location(Bukkit.getWorld("world"), -54, 15, 328).getBlock());
        enabledKits = new ArrayList<>();
        damageFlags = new ArrayList<>();
        damageFlags.add(EntityDamageEvent.DamageCause.CONTACT);
        damageFlags.add(EntityDamageEvent.DamageCause.ENTITY_ATTACK);
        damageFlags.add(EntityDamageEvent.DamageCause.FALL);
        damageFlags.add(EntityDamageEvent.DamageCause.CUSTOM);
        damageFlags.add(EntityDamageEvent.DamageCause.PROJECTILE);
        damageFlags.add(EntityDamageEvent.DamageCause.STARVATION);
    }
}
