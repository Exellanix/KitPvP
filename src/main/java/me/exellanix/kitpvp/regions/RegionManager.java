package me.exellanix.kitpvp.regions;

import me.exellanix.kitpvp.KitPvP;
import org.bukkit.event.Listener;

import java.util.ArrayList;

/**
 * Created by Mac on 3/7/2016.
 */
public class RegionManager {
    private ArrayList<Region> registeredRegions;

    public RegionManager() {
        registeredRegions = new ArrayList<>();
    }

    public void registerRegion(Region region) {
        if (!registeredRegions.contains(region)) {
            if (region instanceof Listener) {
                KitPvP.registerEvent((Listener) region);
            }
            registeredRegions.add(region);
        }
    }

    public void removeRegion(Region region) {
        registeredRegions.remove(region);
    }

    public boolean isRegionRegistered(Region region) {
        return registeredRegions.contains(region);
    }
}
