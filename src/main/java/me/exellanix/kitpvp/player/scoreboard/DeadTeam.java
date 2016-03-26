package me.exellanix.kitpvp.player.scoreboard;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/**
 * Created by Mac on 3/20/2016.
 */
public class DeadTeam {
    public static void registerDeadTeam(Scoreboard main) {
        Team dead = main.getTeam("DeadPlayers");
        if (dead == null) {
            dead = main.registerNewTeam("DeadPlayers");
        }
        dead.setCanSeeFriendlyInvisibles(true);
        dead.setAllowFriendlyFire(false);
        dead.setSuffix(ChatColor.GRAY + "" + ChatColor.BOLD + " Dead");
    }

    public static void addPlayer(Player player) {
        Scoreboard main = player.getScoreboard();
        registerDeadTeam(main);
        Team t = main.getEntryTeam(player.getName());
        if (t == null) {
            main.getTeam("DeadPlayers").addEntry(player.getName());
        } else {
            t.setCanSeeFriendlyInvisibles(true);
        }
    }

    public static void removePlayer(Player player) {
        Scoreboard main = player.getScoreboard();
        if (main.getEntryTeam(player.getName()).getName().equals("DeadPlayers")) {
            main.getEntryTeam(player.getName()).removeEntry(player.getName());
        }
    }
}
