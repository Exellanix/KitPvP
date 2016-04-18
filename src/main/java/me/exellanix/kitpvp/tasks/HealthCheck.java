package me.exellanix.kitpvp.tasks;

import com.redstonedgaming.turboprotocol.packet.Packet;
import me.exellanix.kitpvp.KitPvP;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created by Mac on 4/17/2016.
 */
public class HealthCheck implements Runnable {
    private HashMap<Player, Boolean> update;

    public HealthCheck() {
        update = new HashMap<>();
    }

    @Override
    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            update.putIfAbsent(p, true);
            if (update.get(p)) {
                playSound(p);
            }
        }
    }

    private void playSound(Player player) {
        if (player.getHealth() <= 3) {
            showTint(player);
            update.put(player, false);
            KitPvP.getSingleton().getServer().getScheduler().runTaskLaterAsynchronously(KitPvP.getSingleton(), () -> {
                player.playSound(player.getLocation(), Sound.NOTE_BASS_DRUM, .75f, .7f);
            }, 0);
            KitPvP.getSingleton().getServer().getScheduler().runTaskLaterAsynchronously(KitPvP.getSingleton(), () -> {
                player.playSound(player.getLocation(), Sound.NOTE_BASS_DRUM, .75f, .7f);
            }, 4);
            KitPvP.getSingleton().getServer().getScheduler().runTaskLater(KitPvP.getSingleton(), () -> {
                update.put(player, true);
            }, 12);
        } else if (player.getHealth() <= 6) {
            showTint(player);
            update.put(player, false);
            KitPvP.getSingleton().getServer().getScheduler().runTaskLaterAsynchronously(KitPvP.getSingleton(), () -> {
                player.playSound(player.getLocation(), Sound.NOTE_BASS_DRUM, .5f, .7f);
            }, 0);
            KitPvP.getSingleton().getServer().getScheduler().runTaskLaterAsynchronously(KitPvP.getSingleton(), () -> {
                player.playSound(player.getLocation(), Sound.NOTE_BASS_DRUM, .5f, .7f);
            }, 6);
            KitPvP.getSingleton().getServer().getScheduler().runTaskLater(KitPvP.getSingleton(), () -> {
                update.put(player, true);
            }, 18);
        } else if (player.getHealth() <= 9) {
            showTint(player);
            update.put(player, false);
            KitPvP.getSingleton().getServer().getScheduler().runTaskLaterAsynchronously(KitPvP.getSingleton(), () -> {
                player.playSound(player.getLocation(), Sound.NOTE_BASS_DRUM, .25f, .7f);
            }, 0);
            KitPvP.getSingleton().getServer().getScheduler().runTaskLaterAsynchronously(KitPvP.getSingleton(), () -> {
                player.playSound(player.getLocation(), Sound.NOTE_BASS_DRUM, .25f, .7f);
            }, 8);
            KitPvP.getSingleton().getServer().getScheduler().runTaskLater(KitPvP.getSingleton(), () -> {
                update.put(player, true);
                showTint(player);
            }, 24);
        }
    }

    private void showTint(Player player) {
        if (player.getHealth() <= 3) {
            KitPvP.getSingleton().getTurboAPI().sendBorderTint(player, Packet.BorderTint.DARK);
        } else if (player.getHealth() <= 6) {
            KitPvP.getSingleton().getTurboAPI().sendBorderTint(player, Packet.BorderTint.MEDUIM);
        } else if (player.getHealth() <= 9) {
            KitPvP.getSingleton().getTurboAPI().sendBorderTint(player, Packet.BorderTint.LIGHT);
        } else {
            KitPvP.getSingleton().getTurboAPI().sendBorderTint(player, Packet.BorderTint.OFF);
        }
    }

    public void removePlayer(Player player) {
        update.remove(player);
    }
}
