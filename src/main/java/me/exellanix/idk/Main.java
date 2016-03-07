package me.exellanix.idk;

import java.util.logging.Logger;

import me.exellanix.idk.kit_abilities.AbilityManager;
import me.exellanix.idk.kit_abilities.PyroFire;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import commands.Kit;
import commands.Repair;
import event.inventory.InventoryClick;
import event.player.AxeStrike;
import event.player.Blood;
import event.player.FeatherJump;
import event.player.JoinStuff;
import event.player.PlayerDeathInv;
import event.player.RodHook;
import event.player.SnowballSwitch;
import event.player.SoupRegen;

public class Main extends JavaPlugin {
	
	public static Plugin plugin;
	private static AbilityManager abilityManager;
	
	public void onEnable() {
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = Logger.getLogger("Minecraft");
	
		registerCommands();
		registerEvents();
		saveDefaultConfig();
		generateFood(); 
		plugin = this;

		abilityManager = new AbilityManager();
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

		pm.registerEvents(new InventoryClick(), this);
		//pm.registerEvents(new AxeStrike(), this);
		pm.registerEvents(new SoupRegen(), this);
		pm.registerEvents(new PlayerDeathInv(), this);
		pm.registerEvents(new Kit(), this);
		pm.registerEvents(new JoinStuff(), this);
		//pm.registerEvents(new RodHook(), this);
		pm.registerEvents(new FeatherJump(), this);
		pm.registerEvents(new Blood(), this);
		pm.registerEvents(new SnowballSwitch(), this);
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
		abilityManager.registerAbility(new me.exellanix.idk.kit_abilities.AxeStrike());
		abilityManager.registerAbility(new me.exellanix.idk.kit_abilities.RodHook());
		abilityManager.registerAbility(new PyroFire());
	}
}
