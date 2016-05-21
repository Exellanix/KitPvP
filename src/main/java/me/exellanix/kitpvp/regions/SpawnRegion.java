package me.exellanix.kitpvp.regions;

import me.exellanix.kitpvp.KitPvP;
import me.exellanix.kitpvp.kit_abilities.PlayerActivateAbilityEvent;
import me.exellanix.kitpvp.kits.Kit;
import net.milkbowl.vault.chat.Chat;
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
    private ArrayList<Player> checkedNorth = new ArrayList<Player>();
    private ArrayList<Player> checkedSouth = new ArrayList<Player>();
    private ArrayList<Player> checkedEast = new ArrayList<Player>();
    private ArrayList<Player> checkedWest = new ArrayList<Player>();
    private ArrayList<Player> checkedOutNorth = new ArrayList<Player>();
    private ArrayList<Player> checkedOutSouth = new ArrayList<Player>();
    private ArrayList<Player> checkedOutEast = new ArrayList<Player>();
    private ArrayList<Player> checkedOutWest = new ArrayList<Player>();

    public SpawnRegion() {
        setup();
        border = new Border(selection.getFirst(), selection.getSecond(), 1);
        KitPvP.getSingleton().plugin.getServer().getScheduler().runTaskTimerAsynchronously(KitPvP.getSingleton().plugin, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (isInside(p) && !p.hasPermission("kitpvp.region.bypasskitselect") && p.getGameMode() != GameMode.CREATIVE) {
                    if (border.getNorth().isInside(p)) {
                        if (!KitPvP.getSingleton().getPlayerKits().containsKey(p)) {
                            if(!KitPvP.getSingleton().isDead.contains(p)) {
                                if(!checkedNorth.contains(p)) {
                                    Vector vector = new Vector(0, 1, 1.6);
                                    p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 1, .8f);
                                    p.setVelocity(vector);
                                    p.sendMessage(ChatColor.RED + "Please choose a kit before leaving spawn!");
                                    checkedNorth.add(p);
                                }
                                checkedNorth.add(p);
                            }
                        } else {
                            KitPvP.getSingleton().getServer().getScheduler().runTask(KitPvP.getSingleton(), () -> {
                                if(!checkedOutNorth.contains(p)) {
                                    Vector vector = new Vector(0, .5, -1);
                                    p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1, 1f);
                                    p.setVelocity(vector);
                                    p.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Fight!");
                                    PotionEffect effect = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 255, true, false);
                                    p.addPotionEffect(effect);
                                    checkedOutNorth.add(p);
                                }
                                checkedOutNorth.add(p);
                            });
                        }
                    } else if (border.getSouth().isInside(p)) {
                        if (!KitPvP.getSingleton().getPlayerKits().containsKey(p)) {
                            if(!KitPvP.getSingleton().isDead.contains(p)) {
                                if(!checkedSouth.contains(p)) {
                                    Vector vector = new Vector(0, 1, -1.2);
                                    p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 1, .8f);
                                    p.setVelocity(vector);
                                    p.sendMessage(ChatColor.RED + "Please choose a kit before leaving spawn!");
                                    checkedSouth.add(p);
                                }
                                checkedSouth.add(p);
                            }
                        } else {
                            KitPvP.getSingleton().getServer().getScheduler().runTask(KitPvP.getSingleton(), () -> {
                                if(!checkedOutSouth.contains(p)) {
                                    Vector vector = new Vector(0, .5, 1);
                                    p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1, 1f);
                                    p.setVelocity(vector);
                                    p.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Fight!");
                                    PotionEffect effect = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 255, true, false);
                                    p.addPotionEffect(effect);
                                    checkedOutSouth.add(p);
                                }
                                checkedOutSouth.add(p);
                            });
                        }
                    } else if (border.getEast().isInside(p)) {
                        if (!KitPvP.getSingleton().getPlayerKits().containsKey(p)) {
                            if(!KitPvP.getSingleton().isDead.contains(p)) {
                                if(!checkedEast.contains(p)) {
                                    Vector vector = new Vector(-1.2, 1, 0);
                                    p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 1, .8f);
                                    p.setVelocity(vector);
                                    p.sendMessage(ChatColor.RED + "Please choose a kit before leaving spawn!");
                                    checkedEast.add(p);
                                }
                                checkedEast.add(p);
                            }
                        } else {
                            KitPvP.getSingleton().getServer().getScheduler().runTask(KitPvP.getSingleton(), () -> {
                                if(!checkedOutEast.contains(p)) {
                                    Vector vector = new Vector(1, .5, 0);
                                    p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1, 1f);
                                    p.setVelocity(vector);
                                    p.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Fight!");
                                    PotionEffect effect = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 255, true, false);
                                    p.addPotionEffect(effect);
                                    checkedOutEast.add(p);
                                }
                                checkedOutEast.add(p);
                            });
                        }
                    } else if (border.getWest().isInside(p)) {
                        if (!KitPvP.getSingleton().getPlayerKits().containsKey(p)) {
                            if(!KitPvP.getSingleton().isDead.contains(p)) {
                                if(!checkedWest.contains(p)) {
                                    Vector vector = new Vector(1.2, 1, 0);
                                    p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 1, .8f);
                                    p.setVelocity(vector);
                                    p.sendMessage(ChatColor.RED + "Please choose a kit before leaving spawn!");
                                    checkedWest.add(p);
                                }
                                checkedWest.add(p);
                            }
                        } else {
                            KitPvP.getSingleton().getServer().getScheduler().runTask(KitPvP.getSingleton(), () -> {
                                if(!checkedOutWest.contains(p)) {
                                    Vector vector = new Vector(-1, .5, 0);
                                    p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1, 1f);
                                    p.setVelocity(vector);
                                    p.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Fight!");
                                    PotionEffect effect = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 255, true, false);
                                    p.addPotionEffect(effect);
                                    checkedOutWest.add(p);
                                }
                                checkedOutWest.add(p);
                            });
                        }
                    }
                    KitPvP.getSingleton().getServer().getScheduler().scheduleSyncDelayedTask(KitPvP.getSingleton(), () -> {
                        if(checkedOutNorth.contains(p)) {
                            checkedOutNorth.remove(p);
                        }else if(checkedNorth.contains(p)) {
                            checkedNorth.remove(p);
                        }else if(checkedSouth.contains(p)) {
                            checkedSouth.remove(p);
                        }else if(checkedOutSouth.contains(p)) {
                            checkedOutSouth.remove(p);
                        }else if(checkedEast.contains(p)) {
                            checkedEast.remove(p);
                        }else if(checkedOutEast.contains(p)) {
                            checkedOutEast.remove(p);
                        }else if(checkedWest.contains(p)) {
                            checkedWest.remove(p);
                        }else if(checkedOutWest.contains(p)) {
                            checkedOutWest.remove(p);
                        }
                    },30);
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
