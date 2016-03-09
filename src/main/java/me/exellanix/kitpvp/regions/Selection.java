package me.exellanix.kitpvp.regions;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

/**
 * Created by Mac on 3/7/2016.
 */
public class Selection {
    private Block first;
    private Block second;

    public Block getFirst() {
        return first;
    }

    public Block getSecond() {
        return second;
    }

    public void setFirst(Block first) {
        this.first = first;
    }

    public void setSecond(Block second) {
        this.second = second;
    }

    public boolean isInside(Player player) {
        Location playerL = player.getLocation().getBlock().getLocation();
        if (first.getX() > second.getX()) {
            if (first.getZ() > second.getZ()) {
                if (playerL.getX() <= first.getX() && playerL.getX() >= second.getX()) {
                    if (playerL.getZ() <= first.getZ() && playerL.getZ() >= second.getZ()) {
                        return true;
                    }
                }
            } else {
                if (playerL.getX() <= first.getX() && playerL.getX() >= second.getX()) {
                    if (playerL.getZ() >= first.getZ() && playerL.getZ() <= second.getZ()) {
                        return true;
                    }
                }
            }
        } else {
            if (first.getZ() > second.getZ()) {
                if (playerL.getX() >= first.getX() && playerL.getX() <= second.getX()) {
                    if (playerL.getZ() <= first.getZ() && playerL.getZ() >= second.getZ()) {
                        return true;
                    }
                }
            } else {
                if (playerL.getX() >= first.getX() && playerL.getX() <= second.getX()) {
                    if (playerL.getZ() >= first.getZ() && playerL.getZ() <= second.getZ()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
