package me.exellanix.kitpvp;

import me.exellanix.kitpvp.commands.Repair;
import me.exellanix.kitpvp.external_jars.LoadExternalJar;
import me.exellanix.kitpvp.player.Blood;
import me.exellanix.kitpvp.player.FeatherJump;
import me.exellanix.kitpvp.player.JoinStuff;
import me.exellanix.kitpvp.player.PlayerDeathInv;

import me.exellanix.kitpvp.database.CustomYML;
import me.exellanix.kitpvp.database.Database;
import me.exellanix.kitpvp.kit_abilities.SnowballSwitch;
import me.exellanix.kitpvp.kit_abilities.SoupRegen;
import me.exellanix.kitpvp.kits.Kit;
import me.exellanix.kitpvp.kits.KitManager;
import me.exellanix.kitpvp.kit_abilities.AbilityManager;
import me.exellanix.kitpvp.kit_abilities.AxeStrike;
import me.exellanix.kitpvp.kit_abilities.PyroFire;
import me.exellanix.kitpvp.kit_abilities.RodHook;

import me.exellanix.kitpvp.regions.RegionManager;
import me.exellanix.kitpvp.regions.SpawnRegion;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.logging.Logger;

public class KitPvP extends JavaPlugin {
	
	public static Plugin plugin;
	private static AbilityManager abilityManager;
	private static KitManager kitManager;
	private static RegionManager regionManager;
    private static HashMap<Player, Kit> playerKits;
    private static CustomYML flatStorage;
    private static Database database;
    private static Economy econ;
    private static KitPvP instance;
	
	public void onEnable() {
        if (!setupEconomy()) {
            getLogger().warning("Could not setup the economy!");
            getLogger().warning("Make sure you have an economy plugin installed!");
            plugin.getServer().getPluginManager().disablePlugin(this);
        } else {
            PluginDescriptionFile pdfFile = getDescription();
            Logger logger = Logger.getLogger("Minecraft");

            registerCommands();
            registerEvents();
            saveDefaultConfig();
            generateFood();
            plugin = this;


            abilityManager = new AbilityManager();
            kitManager = new KitManager();
            playerKits = new HashMap<>();
            registerDefaultAbilities();
            regionManager = new RegionManager();
            registerDefaultRegions();

            flatStorage = new CustomYML(this, "playerkits.yml");
            flatStorage.saveDefaultConfig();
            database = new Database(flatStorage);
            instance = this;

            logger.info(pdfFile.getName() + " has been enabled! (V." + pdfFile.getVersion());
        }
	}

	public void onDisable() {
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = Logger.getLogger("Minecraft");

		logger.info(pdfFile.getName() + " has been disabled! (V." + pdfFile.getVersion());

	}

	public void registerCommands() {
		getCommand("kit").setExecutor(new me.exellanix.kitpvp.commands.Kit());
		getCommand("repair").setExecutor(new Repair());
	}
	public void registerEvents() {
		PluginManager pm = getServer().getPluginManager();

		pm.registerEvents(new PlayerDeathInv(), this);
		pm.registerEvents(new me.exellanix.kitpvp.commands.Kit(), this);
		pm.registerEvents(new JoinStuff(), this);
		pm.registerEvents(new FeatherJump(), this);
		pm.registerEvents(new Blood(), this);
	}
	
	public void generateFood() {
		getServer().getScheduler().runTaskTimer(this, new Runnable() {
			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
					p.setFoodLevel(1000000);
				}
			}
		},0, 20);
	}

	public static AbilityManager getAbilityManager() {
		return abilityManager;
	}

	private void registerDefaultAbilities() {
		abilityManager.registerAbility(new AxeStrike());
		abilityManager.registerAbility(new RodHook());
		abilityManager.registerAbility(new PyroFire());
        abilityManager.registerAbility(new SoupRegen());
        abilityManager.registerAbility(new SnowballSwitch());
	}

	public static KitManager getKitManager() {
		return kitManager;
	}

	public static void registerEvent(Listener listener) {
		plugin.getServer().getPluginManager().registerEvents(listener, plugin);
	}

	public static HashMap<Player, Kit> getPlayerKits() {
		return playerKits;
	}

	public static RegionManager getRegionManager() {
		return regionManager;
	}

    private void registerDefaultRegions() {
        regionManager.registerRegion(new SpawnRegion());
    }

    public static Database getPluginDatabase() {
        return database;
    }

    public static Economy getEconomy() {
        return econ;
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static KitPvP getInstance() {
        return instance;
    }
}
