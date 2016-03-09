package me.exellanix.kitpvp.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.exellanix.kitpvp.KitPvP;

public class Repair implements CommandExecutor {
	private ArrayList<Material> repairable = new ArrayList<>();

	public Repair() {
		repairable.add(Material.DIAMOND_AXE);
		repairable.add(Material.IRON_AXE);
		repairable.add(Material.WOOD_AXE);
		repairable.add(Material.GOLD_AXE);
		repairable.add(Material.STONE_AXE);

		repairable.add(Material.DIAMOND_SWORD);
		repairable.add(Material.IRON_SWORD);
		repairable.add(Material.WOOD_SWORD);
		repairable.add(Material.GOLD_SWORD);
		repairable.add(Material.STONE_SWORD);

		repairable.add(Material.IRON_HELMET);
		repairable.add(Material.GOLD_HELMET);
		repairable.add(Material.LEATHER_HELMET);
		repairable.add(Material.CHAINMAIL_HELMET);
		repairable.add(Material.DIAMOND_HELMET);

		repairable.add(Material.IRON_CHESTPLATE);
		repairable.add(Material.GOLD_CHESTPLATE);
		repairable.add(Material.LEATHER_CHESTPLATE);
		repairable.add(Material.CHAINMAIL_CHESTPLATE);
		repairable.add(Material.DIAMOND_CHESTPLATE);

		repairable.add(Material.IRON_CHESTPLATE);
		repairable.add(Material.GOLD_CHESTPLATE);
		repairable.add(Material.LEATHER_CHESTPLATE);
		repairable.add(Material.CHAINMAIL_CHESTPLATE);
		repairable.add(Material.DIAMOND_CHESTPLATE);

		repairable.add(Material.IRON_LEGGINGS);
		repairable.add(Material.GOLD_LEGGINGS);
		repairable.add(Material.LEATHER_LEGGINGS);
		repairable.add(Material.CHAINMAIL_LEGGINGS);
		repairable.add(Material.DIAMOND_LEGGINGS);

		repairable.add(Material.IRON_BOOTS);
		repairable.add(Material.GOLD_BOOTS);
		repairable.add(Material.LEATHER_BOOTS);
		repairable.add(Material.CHAINMAIL_BOOTS);
		repairable.add(Material.DIAMOND_BOOTS);

		repairable.add(Material.IRON_HOE);
		repairable.add(Material.GOLD_HOE);
		repairable.add(Material.WOOD_HOE);
		repairable.add(Material.STONE_HOE);
		repairable.add(Material.DIAMOND_HOE);

		repairable.add(Material.IRON_SPADE);
		repairable.add(Material.GOLD_SPADE);
		repairable.add(Material.WOOD_SPADE);
		repairable.add(Material.STONE_SPADE);
		repairable.add(Material.DIAMOND_SPADE);

		repairable.add(Material.BOW);
		repairable.add(Material.FISHING_ROD);
		repairable.add(Material.FLINT_AND_STEEL);
		repairable.add(Material.SHEARS);
		repairable.add(Material.DIAMOND_SPADE);
	}

	public void repairAll(Player p) {
		for (int i = 0; i < p.getInventory().getSize(); i++) {
			if (repairable.contains(p.getInventory().getItem(i).getType())) {
				ItemStack item = p.getInventory().getItem(i);
				item.setDurability((short) 0);
				p.getInventory().setItem(i, item);
				ItemStack boots = p.getInventory().getBoots();
				ItemStack chestplate = p.getInventory().getChestplate();
				ItemStack leggings = p.getInventory().getLeggings();
				ItemStack helmet = p.getInventory().getHelmet();
				boots.setDurability((short) 0);
				chestplate.setDurability((short) 0);
				leggings.setDurability((short) 0);
				helmet.setDurability((short) 0);

			}
		}

	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("repair")) {
				p.addPotionEffect((new PotionEffect(PotionEffectType.BLINDNESS, 100, 100)));
				p.addPotionEffect((new PotionEffect(PotionEffectType.CONFUSION, 100, 1)));
				p.addPotionEffect((new PotionEffect(PotionEffectType.SLOW, 100, 255)));
				p.sendMessage(ChatColor.AQUA + "Repairing...");
				delay(p);

				return true;
			}
		}
		return false;
	}

	public void delay(final Player p) {
		Bukkit.getServer().getScheduler().runTaskLater(KitPvP.plugin, new Runnable() {
			public void run() {
				repairAll(p);
				p.sendMessage(ChatColor.GREEN + "Repaired!");
			}
		}, 100L);
	}

}
