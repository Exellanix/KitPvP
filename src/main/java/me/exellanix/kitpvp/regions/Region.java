package me.exellanix.kitpvp.regions;

import me.exellanix.kitpvp.kits.Kit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by Mac on 3/7/2016.
 */
public interface Region {
    Selection getSelection();

    ArrayList<Kit> getEnabledKits();

    void addKit(Kit kit);

    void removeKit(Kit kit);

    boolean hasKit(Kit kit);

    boolean isInside(Player player);
}
