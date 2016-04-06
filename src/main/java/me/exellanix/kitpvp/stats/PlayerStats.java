package me.exellanix.kitpvp.stats;

import java.util.HashMap;

/**
 * Created by Mac on 4/5/2016.
 */
public class PlayerStats {
    private int totalKills;
    private HashMap<String, Integer> kitKills;
    private HashMap<String, Integer> kitDeaths;
    private int totalDeaths;

    public PlayerStats(int deaths, int totalKills, HashMap<String, Integer> kitKills, HashMap<String, Integer> kitDeaths) {
        this.totalDeaths = deaths;
        this.totalKills = totalKills;
        this.kitKills = kitKills;
        this.kitDeaths = kitDeaths;
    }

    public int getTotalKills() {
        return totalKills;
    }

    public void setTotalKills(int totalKills) {
        this.totalKills = totalKills;
    }

    public HashMap<String, Integer> getKitKills() {
        return kitKills;
    }

    public int getKitKills(String kitName) {
        return kitKills.get(kitName) != null ? kitKills.get(kitName) : -1;
    }

    public void setKitKills(HashMap<String, Integer> kitKills) {
        this.kitKills = kitKills;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(int deaths) {
        this.totalDeaths = deaths;
    }

    public HashMap<String, Integer> getKitDeaths() {
        return kitDeaths;
    }

    public void setKitDeaths(HashMap<String, Integer> kitDeaths) {
        this.kitDeaths = kitDeaths;
    }
}
