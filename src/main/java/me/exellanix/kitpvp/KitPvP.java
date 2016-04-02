package me.exellanix.kitpvp;

import me.exellanix.kitpvp.commands.KitPvP_Command;
import me.exellanix.kitpvp.commands.Kit_Command;
import me.exellanix.kitpvp.commands.Repair_Command;
import me.exellanix.kitpvp.commands.Soup_Command;
import me.exellanix.kitpvp.commands.tabcomplete.KitPvPTab;
import me.exellanix.kitpvp.commands.tabcomplete.KitTab;
import me.exellanix.kitpvp.event.player.Blood;
import me.exellanix.kitpvp.event.player.JoinStuff;
import me.exellanix.kitpvp.event.player.PlayerDeathInv;

import me.exellanix.kitpvp.database.CustomYML;
import me.exellanix.kitpvp.database.Database;
import me.exellanix.kitpvp.kits.Kit;
import me.exellanix.kitpvp.kits.KitManager;
import me.exellanix.kitpvp.kit_abilities.AbilityManager;

import me.exellanix.kitpvp.regions.RegionManager;
import me.exellanix.kitpvp.regions.SpawnRegion;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class KitPvP extends JavaPlugin implements KitPvPAPI {
	
	public Plugin plugin;
	private AbilityManager abilityManager;
	private KitManager kitManager;
	private RegionManager regionManager;
    private HashMap<Player, Kit> playerKits;
    private CustomYML flatStorage;
    private CustomYML kitConfig;
    private Database database;
    private Economy econ;
    private static KitPvP singleton;
	
	public void onEnable() {
        if (!setupEconomy()) {
            getLogger().warning("Could not setup the economy!");
            getLogger().warning("Make sure you have an economy plugin installed!");
            plugin.getServer().getPluginManager().disablePlugin(this);
        } else {
            singleton = this;
            kitConfig = new CustomYML(this, "kit_config.yml");
            kitConfig.saveDefaultConfig();

            PluginDescriptionFile pdfFile = getDescription();

            registerCommands();
            registerEvents();
            saveDefaultConfig();
            //generateFood();
            plugin = this;


            abilityManager = new AbilityManager();
            kitManager = new KitManager();
            playerKits = new HashMap<>();
            regionManager = new RegionManager();
            registerDefaultRegions();

            flatStorage = new CustomYML(this, "playerkits.yml");
            flatStorage.saveDefaultConfig();
            database = new Database(flatStorage);

            getServer().getServicesManager().register(KitPvPAPI.class, this, this, ServicePriority.Normal);

            getLogger().info("Enabled!");
        }
	}

	public void onDisable() {
		getLogger().info("Disabled.");
	}

	public void registerCommands() {
		getCommand("kit").setExecutor(new Kit_Command());
        getCommand("kit").setTabCompleter(new KitTab());

		getCommand("repair").setExecutor(new Repair_Command());

        getCommand("kitpvp").setExecutor(new KitPvP_Command());
        getCommand("kitpvp").setTabCompleter(new KitPvPTab());

        getCommand("soup").setExecutor(new Soup_Command());
	}
	public void registerEvents() {
		PluginManager pm = getServer().getPluginManager();

		pm.registerEvents(new PlayerDeathInv(), this);
		pm.registerEvents(new Kit_Command(), this);
		pm.registerEvents(new JoinStuff(), this);
		pm.registerEvents(new Blood(), this);
	}

    // TODO Remove if FoodLevelChangeEvent works
	/*public void generateFood() {
		getServer().getScheduler().runTaskTimer(this, new Runnable() {
			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
					p.setFoodLevel(1000000);
				}
			}
		},0, 20);
	}*/

	public AbilityManager getAbilityManager() {
		return abilityManager;
	}

	public KitManager getKitManager() {
		return kitManager;
	}

	public void registerEvent(Listener listener) {
		plugin.getServer().getPluginManager().registerEvents(listener, plugin);
	}

	public HashMap<Player, Kit> getPlayerKits() {
		return playerKits;
	}

    public void setPlayerKits(HashMap<Player, Kit> playerKits) {
        this.playerKits = playerKits;
    }

	public RegionManager getRegionManager() {
		return regionManager;
	}

    private void registerDefaultRegions() {
        regionManager.registerRegion(new SpawnRegion());
    }

    public Database getPluginDatabase() {
        return database;
    }

    public Economy getEconomy() {
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

    public CustomYML getKitConfig() {
        return kitConfig;
    }

    public static KitPvP getSingleton() {
        return singleton;
    }
}
