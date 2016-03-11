package me.exellanix.kitpvp.config;

import me.exellanix.kitpvp.KitPvP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mac on 3/9/2016.
 */
public class KitConfiguration {
    private String section;
    private String name;

    public KitConfiguration(boolean isAbility, String name) {
        if (isAbility) {
            this.section = "ability";
        } else {
            this.section = "kit";
        }
        this.name = name;
    }

    public void saveDefaultSettings(Map<String, Object> settings) {
        boolean save = false;
        if (!KitPvP.getSingleton().getKitConfig().getConfig().isSet(section + "." + name)) {
            KitPvP.getSingleton().getKitConfig().getConfig().createSection(section + "." + name);
            save = true;
        }
        for (String s : settings.keySet()) {
            if (!KitPvP.getSingleton().getKitConfig().getConfig().isSet(section + "." + name + "." + s)) {
                KitPvP.getSingleton().getKitConfig().getConfig().createSection(section + "." + name + "." + s);
                KitPvP.getSingleton().getKitConfig().getConfig().set(section + "." + name + "." + s, settings.get(s));
                save = true;
            }
        }
        if (save) {
            KitPvP.getSingleton().getKitConfig().saveConfig();
        }
    }

    public void saveSettings(Map<String, Object> settings) {
        saveDefaultSettings(settings);
        for (String s : settings.keySet()) {
            KitPvP.getSingleton().getKitConfig().getConfig().set(section + "." + name + "." + s, settings.get(s));
        }
        KitPvP.getSingleton().getKitConfig().saveConfig();
    }

    public Map<String, Object> getSettings() {
        if (!KitPvP.getSingleton().getKitConfig().getConfig().isSet(section + "." + name)) {
            KitPvP.getSingleton().getLogger().warning("Could not get the config section for " + section + "." + name);
            return null;
        } else {
            HashMap<String, Object> values = new HashMap<>();
            ArrayList<String> names = new ArrayList<>(KitPvP.getSingleton().getKitConfig().getConfig().getConfigurationSection(section + "." + name).getKeys(false));
            for (String s : names) {
                values.put(s, KitPvP.getSingleton().getKitConfig().getConfig().get(section + "." + name + "." + s));
            }
            return values;
        }
    }
}
