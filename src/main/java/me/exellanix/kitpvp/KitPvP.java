package me.exellanix.kitpvp;

import com.redstonedgaming.turboprotocol.TurboAPI;
import me.exellanix.kitpvp.commands.*;
import me.exellanix.kitpvp.commands.tabcomplete.KitPvPTab;
import me.exellanix.kitpvp.commands.tabcomplete.KitTab;
import me.exellanix.kitpvp.database.flat.LocalHandler;
import me.exellanix.kitpvp.event.custom.Caller;
import me.exellanix.kitpvp.event.player.Blood;
import me.exellanix.kitpvp.event.player.JoinStuff;
import me.exellanix.kitpvp.event.player.LastKit;
import me.exellanix.kitpvp.event.player.PlayerDeathInv;

import me.exellanix.kitpvp.database.CustomYML;
import me.exellanix.kitpvp.database.Database;
import me.exellanix.kitpvp.kits.Kit;
import me.exellanix.kitpvp.kits.KitManager;
import me.exellanix.kitpvp.kit_abilities.AbilityManager;

import me.exellanix.kitpvp.regions.RegionManager;
import me.exellanix.kitpvp.regions.SpawnRegion;
import me.exellanix.kitpvp.stats.PlayerStats;
import me.exellanix.kitpvp.tasks.HealthCheck;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public class KitPvP extends JavaPlugin implements KitPvPAPI {
	
	public Plugin plugin;
	private AbilityManager abilityManager;
	private KitManager kitManager;
	private RegionManager regionManager;
    private HashMap<Player, Kit> playerKits;
    private HashMap<Player, Kit> playerPrevKit;
    private CustomYML kitConfig;
    private Database database;
    private Economy econ;
    private TurboAPI turboAPI;
    private HealthCheck healthCheck;
    private static KitPvP singleton;
	
	public void onEnable() {
        if (!setupEconomy()) {
            getLogger().warning("Could not setup the economy!");
            getLogger().warning("Make sure you have an economy plugin installed!");
            plugin.getServer().getPluginManager().disablePlugin(this);
        } else if (!setTurboAPI()) {
            getLogger().warning("Failed to find TurboProtocol!");
            getLogger().warning("Make sure it is installed for the plugin to work!");
            getServer().getPluginManager().disablePlugin(this);
        } else {
            singleton = this;
            kitConfig = new CustomYML(this, "kit_config.yml");
            kitConfig.saveDefaultConfig();

            registerCommands();
            registerEvents();
            saveDefaultConfig();
            plugin = this;


            abilityManager = new AbilityManager();
            kitManager = new KitManager();
            playerKits = new HashMap<>();
            playerPrevKit = new HashMap<>();
            regionManager = new RegionManager();
            registerDefaultRegions();

            healthCheck = new HealthCheck();
            getServer().getScheduler().runTaskTimer(this, healthCheck, 0, 1);

            if (KitPvP.getSingleton().plugin.getConfig().getBoolean("Use-MySQL")) {
                // TODO Add support for MySQL
                database = new LocalHandler();
            } else {
                database = new LocalHandler();
            }

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

        getCommand("blood").setExecutor(new Blood_Command());
	}
	public void registerEvents() {
		PluginManager pm = getServer().getPluginManager();

		pm.registerEvents(new PlayerDeathInv(), this);
		pm.registerEvents(new Kit_Command(), this);
		pm.registerEvents(new JoinStuff(), this);
		pm.registerEvents(new Blood(), this);
        pm.registerEvents(new LastKit(), this);
        pm.registerEvents(new Caller(), this);
	}

	public AbilityManager getAbilityManager() {
		return abilityManager;
	}

    @Override
    public PlayerStats getPlayerStats(Player player) {
        return database.getPlayerStats(player);
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

    public HashMap<Player, Kit> getPlayerPrevKit() {
        return playerPrevKit;
    }

    public ArrayList<Player> isDead = new ArrayList<Player>();

    public ArrayList<Player> blood = new ArrayList<Player>();

    public ArrayList<Player> givenKit = new ArrayList<Player>();

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

    private boolean setTurboAPI() {
        RegisteredServiceProvider<TurboAPI> api = getServer().getServicesManager().getRegistration(TurboAPI.class);
        if (api != null) {
            turboAPI = api.getProvider();
            return true;
        } else {
            return false;
        }
    }

    public TurboAPI getTurboAPI() {
        return turboAPI;
    }

    public CustomYML getKitConfig() {
        return kitConfig;
    }

    public HealthCheck getHealthCheck() {
        return healthCheck;
    }

    public static KitPvP getSingleton() {
        return singleton;
    }
}
