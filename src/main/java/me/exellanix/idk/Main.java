package me.exellanix.idk;

import commands.Kit;
import commands.Repair;
import event.inventory.InventoryClick;
import event.player.Blood;
import event.player.FeatherJump;
import event.player.JoinStuff;
import event.player.PlayerDeathInv;
import me.exellanix.idk.kit_abilities.*;
import me.exellanix.idk.kits.KitManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.logging.Logger;

public class Main extends JavaPlugin {
	
	public static Plugin plugin;
	private static AbilityManager abilityManager;
	private static KitManager kitManager;
    private static HashMap<Player, Kit> playerKits;
	
	public void onEnable() {
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
		logger.info(pdfFile.getName() + " has been enabled! (V." + pdfFile.getVersion());
	}

	public void onDisable() {
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = Logger.getLogger("Minecraft");

		logger.info(pdfFile.getName() + " has been disabled! (V." + pdfFile.getVersion());

	}

	public void registerCommands() {
		getCommand("kit").setExecutor(new Kit());
		getCommand("repair").setExecutor(new Repair());
	}
	public void registerEvents() {
		PluginManager pm = getServer().getPluginManager();

		//pm.registerEvents(new InventoryClick(), this);
		pm.registerEvents(new PlayerDeathInv(), this);
		pm.registerEvents(new Kit(), this);
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
}
