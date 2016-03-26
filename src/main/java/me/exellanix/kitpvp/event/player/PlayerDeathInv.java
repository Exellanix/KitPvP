package me.exellanix.kitpvp.event.player;

import com.google.common.base.Charsets;
import com.google.common.base.Function;
import com.mojang.authlib.GameProfile;
import com.sun.javafx.scene.text.HitInfo;
import me.exellanix.kitpvp.KitPvP;
import me.exellanix.kitpvp.Util.CustPlayerConnection;
import me.exellanix.kitpvp.player.inventory.DefaultInvConfigurations;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftArrow;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.util.CraftChatMessage;
import org.bukkit.entity.*;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PlayerDeathInv implements Listener {
	private ArrayList<CraftPlayer> npcs;
    private ArrayList<Player> dead;

	public PlayerDeathInv() {
		npcs = new ArrayList<>();
        dead = new ArrayList<>();
	}

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		Player player = e.getPlayer();
		KitPvP.getSingleton().getPlayerKits().remove(player);
		player.setMaxHealth(20);
		DefaultInvConfigurations.useJoinInv(player);
	}

	@EventHandler
	public void entityDamagebyEntity(EntityDamageEvent event) {
        if (event instanceof EntityDamageByEntityEvent) {
            if (((EntityDamageByEntityEvent) event).getDamager() instanceof Player) {
                if (dead.contains(((EntityDamageByEntityEvent) event).getDamager())) {
                    event.setCancelled(true);
                }
            }
        }
        if (event.getEntity() instanceof Player) {
            if (!npcs.contains((CraftPlayer) event.getEntity())) {
                if (event.getEntity() instanceof Player) {
                    Player damaged = (Player) event.getEntity();
                    if (damaged.getHealth() <= event.getFinalDamage()) {
                        event.setDamage(0);
                        KitPvP.getSingleton().getPlayerKits().remove(event.getEntity());
                        damaged.setHealth(damaged.getMaxHealth());
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (p != damaged) {
                                p.hidePlayer(damaged);
                                KitPvP.getSingleton().getServer().getScheduler().runTaskLater(KitPvP.getSingleton(), () -> {
                                    p.showPlayer(damaged);
                                }, 60);
                            }
                        }
                        if (event instanceof EntityDamageByEntityEvent) {
                            spawnFakePlayer(damaged, ((EntityDamageByEntityEvent)event).getDamager(), event.getCause());
                        } else {
                            spawnFakePlayer(damaged, null, event.getCause());
                        }
                        DefaultInvConfigurations.useSpectateInv(damaged);
                        for (int i = 3; i > 0; i--) {
                            final int index = i;
                            KitPvP.getSingleton().getServer().getScheduler().runTaskLater(KitPvP.getSingleton(), () -> {
                                ((CraftPlayer) damaged).getHandle().playerConnection.sendPacket(new PacketPlayOutTitle(0, 22, 0));
                                ((CraftPlayer) damaged).getHandle().playerConnection.sendPacket(new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, new ChatComponentText(ChatColor.RED + "Respawning in " + index)));
                            }, (3 - i) * 20);
                        }
                        KitPvP.getSingleton().getServer().getScheduler().runTaskLater(KitPvP.getSingleton(), () -> {
                            damaged.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 1, true, false));
                        }, 50);
                        KitPvP.getSingleton().getServer().getScheduler().runTaskLater(KitPvP.getSingleton(), () -> {
                            damaged.teleport(damaged.getWorld().getSpawnLocation());
                            DefaultInvConfigurations.useJoinInv(damaged);
                        }, 60);
                    }
                }
            }
        }
	}

	public void spawnFakePlayer(Player toFake, Entity hitter, EntityDamageEvent.DamageCause cause) {
		EntityPlayer original = ((CraftPlayer)toFake).getHandle();
		EntityPlayer human = new EntityPlayer(original.server, original.u(), newGameProfile(original.getProfile()), new PlayerInteractManager(original.world));
        CustPlayerConnection.swapConnection(human);
        human.setLocation(original.locX, original.locY, original.locZ, original.yaw, original.pitch);
        original.getWorld().addEntity(human);
        CraftPlayer newPlayer = new CraftPlayer(((CraftServer)toFake.getServer()), human);
        npcs.add(newPlayer);
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p != newPlayer && p != toFake) {
                ((CraftPlayer)p).getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, human));
                ((CraftPlayer)p).getHandle().playerConnection.sendPacket(new PacketPlayOutNamedEntitySpawn(human));
                ((CraftPlayer)p).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityEquipment(human.getId(), 0, original.getEquipment(0)));
                ((CraftPlayer)p).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityEquipment(human.getId(), 1, original.getEquipment(1)));
                ((CraftPlayer)p).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityEquipment(human.getId(), 2, original.getEquipment(2)));
                ((CraftPlayer)p).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityEquipment(human.getId(), 3, original.getEquipment(3)));
                ((CraftPlayer)p).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityEquipment(human.getId(), 4, original.getEquipment(4)));
            }
        }

        KitPvP.getSingleton().getServer().getScheduler().runTaskLater(KitPvP.getSingleton(), () -> {
            if (hitter != null) {
                if (cause == EntityDamageEvent.DamageCause.CONTACT) {
                    newPlayer.setLastDamageCause(createEntityDamageEvent(EntityDamageEvent.DamageCause.CONTACT, newPlayer, hitter));
                    human.combatTracker.a(DamageSource.CACTUS, 0, 0);
                    newPlayer.damage(newPlayer.getHealth(), hitter);
                    createDeathEvent(newPlayer);
                } else if (cause == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
                    if (hitter instanceof Player) {
                        newPlayer.setLastDamageCause(createEntityDamageEvent(EntityDamageEvent.DamageCause.ENTITY_ATTACK, newPlayer, hitter));
                        human.combatTracker.a(DamageSource.playerAttack(((CraftPlayer)hitter).getHandle()), 0, 0);
                        newPlayer.damage(newPlayer.getHealth(), hitter);
                        createDeathEvent(newPlayer);
                    } else {
                        newPlayer.setLastDamageCause(createEntityDamageEvent(EntityDamageEvent.DamageCause.ENTITY_ATTACK, newPlayer, hitter));
                        human.combatTracker.a(DamageSource.mobAttack(((CraftLivingEntity) hitter).getHandle()), 0, 0);
                        newPlayer.damage(newPlayer.getHealth(), hitter);
                        createDeathEvent(newPlayer);
                    }
                } else if (cause == EntityDamageEvent.DamageCause.PROJECTILE) {
                    if (hitter instanceof Projectile) {
                        human.combatTracker.a(DamageSource.arrow(((CraftArrow)hitter).getHandle(), ((CraftEntity)(((Projectile) hitter).getShooter())).getHandle()), 0, 0);
                        newPlayer.setLastDamageCause(createEntityDamageEvent(EntityDamageEvent.DamageCause.PROJECTILE, newPlayer, hitter));
                        newPlayer.damage(newPlayer.getHealth(), hitter);
                        createDeathEvent(newPlayer);
                    }
                }
            } else {
                if (cause == EntityDamageEvent.DamageCause.DROWNING) {
                    newPlayer.setLastDamageCause(createDamageEvent(EntityDamageEvent.DamageCause.DROWNING, newPlayer));
                    human.combatTracker.a(DamageSource.DROWN, 0, 0);
                    newPlayer.damage(newPlayer.getHealth());
                    createDeathEvent(newPlayer);
                } else if (cause == EntityDamageEvent.DamageCause.FALL) {
                    newPlayer.setLastDamageCause(createDamageEvent(EntityDamageEvent.DamageCause.FALL, newPlayer));
                    human.combatTracker.a(DamageSource.FALL, 0, 0);
                    newPlayer.damage(newPlayer.getHealth());
                    createDeathEvent(newPlayer);
                } else if (cause == EntityDamageEvent.DamageCause.SUFFOCATION) {
                    newPlayer.setLastDamageCause(createDamageEvent(EntityDamageEvent.DamageCause.SUFFOCATION, newPlayer));
                    human.combatTracker.a(DamageSource.STUCK, 0, 0);
                    newPlayer.damage(newPlayer.getHealth());
                    createDeathEvent(newPlayer);
                } else if (cause == EntityDamageEvent.DamageCause.FIRE) {
                    newPlayer.setLastDamageCause(createDamageEvent(EntityDamageEvent.DamageCause.FIRE, newPlayer));
                    human.combatTracker.a(DamageSource.FIRE, 0, 0);
                    newPlayer.damage(newPlayer.getHealth());
                    createDeathEvent(newPlayer);
                } else if (cause == EntityDamageEvent.DamageCause.FIRE_TICK) {
                    newPlayer.setLastDamageCause(createDamageEvent(EntityDamageEvent.DamageCause.FIRE_TICK, newPlayer));
                    human.combatTracker.a(DamageSource.FIRE, 0, 0);
                    newPlayer.damage(newPlayer.getHealth());
                    createDeathEvent(newPlayer);
                } else if (cause == EntityDamageEvent.DamageCause.FALLING_BLOCK) {
                    newPlayer.setLastDamageCause(createDamageEvent(EntityDamageEvent.DamageCause.FALLING_BLOCK, newPlayer));
                    human.combatTracker.a(DamageSource.FALLING_BLOCK, 0, 0);
                    newPlayer.damage(newPlayer.getHealth());
                    createDeathEvent(newPlayer);
                }
            }
            human.setHealth(0);
        }, 1);

		KitPvP.getSingleton().getServer().getScheduler().runTaskLater(KitPvP.getSingleton(), () -> {
			human.getWorld().removeEntity(human);
            for (Player p : Bukkit.getOnlinePlayers()) {
                ((CraftPlayer)p).getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, human));
                ((CraftPlayer)p).getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, original));
                ((CraftPlayer)p).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityDestroy(human.getId()));
            }
            npcs.remove(newPlayer);
		}, 20);
	}

    private GameProfile newGameProfile(GameProfile player) {
        UUID offline = java.util.UUID.nameUUIDFromBytes(("OfflinePlayerFake:" + player.getName()).getBytes(Charsets.UTF_8));
        GameProfile newP = new GameProfile(offline, player.getName());
        try {
            Field f = newP.getClass().getDeclaredField("properties");
            Field other = player.getClass().getDeclaredField("properties");
            f.setAccessible(true);
            other.setAccessible(true);
            f.set(newP, other.get(player));
            f.setAccessible(false);
            other.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            KitPvP.getSingleton().getLogger().info(e.getMessage());
        }
        return newP;
    }

    private EntityDamageEvent createDamageEvent(EntityDamageEvent.DamageCause cause, Player player) {
        HashMap<EntityDamageEvent.DamageModifier, Double> map = new HashMap<>();
        map.put(EntityDamageEvent.DamageModifier.BASE, 0.0);
        HashMap<EntityDamageEvent.DamageModifier, Function<Double, Double>> map2 = new HashMap<>();
        map2.put(EntityDamageEvent.DamageModifier.BASE, new Function<Double, Double>() {
            @Nullable
            @Override
            public Double apply(@Nullable Double aDouble) {
                return 0.0;
            }
        });
        EntityDamageEvent event = new EntityDamageEvent(player, cause, map, map2);
        player.setLastDamageCause(event);
        return event;
    }

    private EntityDamageByEntityEvent createEntityDamageEvent(EntityDamageEvent.DamageCause cause, Player player, Entity damager) {
        HashMap<EntityDamageEvent.DamageModifier, Double> map = new HashMap<>();
        map.put(EntityDamageEvent.DamageModifier.BASE, 0.0);
        HashMap<EntityDamageEvent.DamageModifier, Function<Double, Double>> map2 = new HashMap<>();
        map2.put(EntityDamageEvent.DamageModifier.BASE, new Function<Double, Double>() {
            @Nullable
            @Override
            public Double apply(@Nullable Double aDouble) {
                return 0.0;
            }
        });
        EntityDamageByEntityEvent event = new EntityDamageByEntityEvent(damager, player, cause, map, map2);
        player.setLastDamageCause(event);
        return event;
    }

    private EntityDeathEvent createDeathEvent(Player player) {
        String deathmessage = ((CraftPlayer)player).getHandle().bs().b().c();
        PlayerDeathEvent event = new PlayerDeathEvent(player, null, 0, deathmessage);
        KitPvP.getSingleton().getServer().getPluginManager().callEvent(event);
        String deathMessage = event.getDeathMessage();
        if(deathMessage != null && deathMessage.length() > 0 && ((CraftPlayer)player).getHandle().world.getGameRules().getBoolean("showDeathMessages")) {
            if(deathMessage.equals(deathmessage)) {
                ((CraftPlayer)player).getHandle().server.getPlayerList().sendMessage(((CraftPlayer)player).getHandle().bs().b());
            } else {
                ((CraftPlayer)player).getHandle().server.getPlayerList().sendMessage(CraftChatMessage.fromString(deathMessage));
            }
        }
        return event;
    }
}
