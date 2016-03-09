package me.exellanix.kitpvp.menus;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 * Created by Mac on 3/8/2016.
 */
public class MenuSounds {
    public static void clickButton(Player player) {
        player.playSound(player.getLocation(), Sound.CLICK, 1f, 1.2f);
    }
}
