package event.inventory;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

public class InventoryClick implements Listener {

	// Kits
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Inventory inv = event.getInventory();
		if (!inv.getTitle().equals(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Kits")) {
			return;
		}

		if (!(event.getWhoClicked() instanceof Player))
			return;

		Player player = (Player) event.getWhoClicked();
		int slot = event.getSlot();
		switch (slot) {
		case 0:
			InventoryClick.pvpKit(player);
			break;
		case 1:
			InventoryClick.archerKit(player);
			break;
		case 2:
			InventoryClick.thorKit(player);
			break;
		case 3:
			InventoryClick.kangarooKit(player);
			break;
		case 4:
			InventoryClick.switcherKit(player);
			break;
		case 5:
			InventoryClick.fishermanKit(player);
			break;
		case 6:
			InventoryClick.potmasterKit(player);
			break;
		case 7:
			InventoryClick.survivorKit(player);
			break;
		case 8:
			InventoryClick.pyromancerKit(player);
			break;
		case 9:
			InventoryClick.recrafterKit(player);
			break;
		default:
			break;
		}
		event.setCancelled(true);
		player.closeInventory();
	}

	public static void pvpKit(Player player) {

		PlayerInventory inventory = player.getInventory();
		// clears player inventory
		inventory.clear();
		player.setMaxHealth(20);

		ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD);
		for (int i = 1; i < 36; i++) {
			inventory.setItem(i, new ItemStack(Material.MUSHROOM_SOUP, 1));
		}

		ItemStack ironHelmet = new ItemStack(Material.IRON_HELMET);
		ItemStack ironChestplate = new ItemStack(Material.IRON_CHESTPLATE);
		ItemStack ironLeggings = new ItemStack(Material.IRON_LEGGINGS);
		ItemStack ironBoots = new ItemStack(Material.IRON_BOOTS);

		inventory.addItem(diamondSword);
		inventory.setHelmet(ironHelmet);
		inventory.setChestplate(ironChestplate);
		inventory.setLeggings(ironLeggings);
		inventory.setBoots(ironBoots);

		player.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "You have chosen the kit " + ChatColor.AQUA
				+ "" + ChatColor.BOLD + "PvP!");
	}

	public static void archerKit(Player player) {

		PlayerInventory inv = player.getInventory();
		inv.clear();
		player.setMaxHealth(20);

		ItemStack woodSword = new ItemStack(Material.WOOD_SWORD);

		for (int i = 2; i < 36; i++) {
			inv.setItem(i, new ItemStack(Material.MUSHROOM_SOUP, 1));
		}
		inv.setItem(17, new ItemStack(Material.ARROW, 1));
		ItemStack chainHelmet = new ItemStack(Material.CHAINMAIL_HELMET);
		ItemStack chainChestplate = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		ItemStack chainLeggings = new ItemStack(Material.CHAINMAIL_LEGGINGS);
		ItemStack chainBoots = new ItemStack(Material.CHAINMAIL_BOOTS);
		ItemStack bow = new ItemStack(Material.BOW);

		woodSword.addEnchantment(Enchantment.DAMAGE_ALL, 2);
		woodSword.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		chainHelmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		chainChestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		chainLeggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		chainBoots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		bow.addEnchantment(Enchantment.ARROW_DAMAGE, 3);
		bow.addEnchantment(Enchantment.ARROW_KNOCKBACK, 1);

		inv.addItem(woodSword, bow);
		inv.setHelmet(chainHelmet);
		inv.setChestplate(chainChestplate);
		inv.setLeggings(chainLeggings);
		inv.setBoots(chainBoots);

		player.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "You have chosen the kit "
				+ ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Archer!");
	}

	public static void thorKit(Player player) {
		PlayerInventory inventory1 = player.getInventory();
		// clears player inventory
		inventory1.clear();
		player.setMaxHealth(20);

		for (int i = 1; i < 36; i++) {
			inventory1.setItem(i, new ItemStack(Material.MUSHROOM_SOUP, 1));
		}

		ItemStack diamondAxe = new ItemStack(Material.DIAMOND_AXE);

		ItemStack leatherHelmet = new ItemStack(Material.LEATHER_HELMET);
		ItemStack ironChestplate = new ItemStack(Material.IRON_CHESTPLATE);
		ItemStack ironLeggings = new ItemStack(Material.IRON_LEGGINGS);
		ItemStack leatherBoots = new ItemStack(Material.LEATHER_BOOTS);

		LeatherArmorMeta im = (LeatherArmorMeta) leatherHelmet.getItemMeta();
		im.setColor(Color.WHITE);
		leatherHelmet.setItemMeta(im);

		LeatherArmorMeta im1 = (LeatherArmorMeta) leatherBoots.getItemMeta();
		im1.setColor(Color.WHITE);
		leatherBoots.setItemMeta(im1);

		diamondAxe.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		leatherHelmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		leatherBoots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);

		leatherHelmet.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		leatherBoots.addUnsafeEnchantment(Enchantment.DURABILITY, 10);

		ItemMeta meta = diamondAxe.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Thor's Axe");
		diamondAxe.setItemMeta(meta);

		ItemMeta meta1 = leatherHelmet.getItemMeta();
		meta1.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Thor's Hair");
		leatherHelmet.setItemMeta(meta1);

		ItemMeta meta2 = ironChestplate.getItemMeta();
		meta2.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Thor's Cloak");
		ironChestplate.setItemMeta(meta2);

		ItemMeta meta3 = ironLeggings.getItemMeta();
		meta3.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Thor's Cloak");
		ironLeggings.setItemMeta(meta3);

		ItemMeta meta4 = leatherBoots.getItemMeta();
		meta4.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Thor's Feet");
		leatherBoots.setItemMeta(meta4);

		inventory1.addItem(diamondAxe);
		inventory1.setHelmet(leatherHelmet);
		inventory1.setChestplate(ironChestplate);
		inventory1.setLeggings(ironLeggings);
		inventory1.setBoots(leatherBoots);
		player.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "You have chosen the kit " + ChatColor.YELLOW
				+ "" + ChatColor.BOLD + "Thor!");

	}

	public static void kangarooKit(Player player) {
		PlayerInventory inventory2 = player.getInventory();
		// clears player inventory
		inventory2.clear();
		player.setMaxHealth(20);

		ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD);

		for (int i = 2; i < 36; i++) {
			inventory2.setItem(i, new ItemStack(Material.MUSHROOM_SOUP, 1));
		}
		ItemStack ironHelmet = new ItemStack(Material.IRON_HELMET);
		ItemStack ironChestplate = new ItemStack(Material.IRON_CHESTPLATE);
		ItemStack ironLeggings = new ItemStack(Material.IRON_LEGGINGS);
		ItemStack ironBoots = new ItemStack(Material.IRON_BOOTS);
		ItemStack feather = new ItemStack(Material.FIREWORK);

		inventory2.addItem(diamondSword, feather);
		inventory2.setHelmet(ironHelmet);
		inventory2.setChestplate(ironChestplate);
		inventory2.setLeggings(ironLeggings);
		inventory2.setBoots(ironBoots);

		player.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "You have chosen the kit " + ChatColor.RED + ""
				+ ChatColor.BOLD + "Kangaroo!");
	}

	public static void switcherKit(Player player) {
		PlayerInventory inventory = player.getInventory();
		// clears player inventory
		inventory.clear();
		player.setMaxHealth(20);

		ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD);

		for (int i = 2; i < 36; i++) {
			inventory.setItem(i, new ItemStack(Material.MUSHROOM_SOUP, 1));
		}
		ItemStack ironHelmet = new ItemStack(Material.IRON_HELMET);
		ItemStack ironChestplate = new ItemStack(Material.IRON_CHESTPLATE);
		ItemStack ironLeggings = new ItemStack(Material.IRON_LEGGINGS);
		ItemStack ironBoots = new ItemStack(Material.IRON_BOOTS);
		ItemStack snowball = new ItemStack(Material.SNOW_BALL, 16);

		ItemMeta meta = snowball.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Switcher");
		snowball.setItemMeta(meta);

		inventory.addItem(diamondSword, snowball);
		inventory.setHelmet(ironHelmet);
		inventory.setChestplate(ironChestplate);
		inventory.setLeggings(ironLeggings);
		inventory.setBoots(ironBoots);

		player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "You have chosen the kit " + ChatColor.WHITE + ""
				+ ChatColor.BOLD + "Switcher!");

	}

	public static void fishermanKit(Player player) {
		PlayerInventory inventory = player.getInventory();
		// clears player inventory
		inventory.clear();
		player.setMaxHealth(20);

		ItemStack diamondSword = new ItemStack(Material.IRON_SWORD);

		for (int i = 2; i < 36; i++) {
			inventory.setItem(i, new ItemStack(Material.MUSHROOM_SOUP, 1));
		}
		ItemStack ironHelmet = new ItemStack(Material.LEATHER_HELMET);
		ItemStack ironChestplate = new ItemStack(Material.GOLD_CHESTPLATE);
		ItemStack ironLeggings = new ItemStack(Material.GOLD_LEGGINGS);
		ItemStack ironBoots = new ItemStack(Material.GOLD_BOOTS);
		ItemStack rod = new ItemStack(Material.FISHING_ROD);

		diamondSword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		ironHelmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		ironHelmet.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		ironChestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		ironLeggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		ironBoots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);

		ItemMeta meta7 = diamondSword.getItemMeta();
		meta7.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Fish Knife");
		diamondSword.setItemMeta(meta7);

		ItemMeta meta = rod.getItemMeta();
		meta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Fish & Sticks");
		rod.setItemMeta(meta);

		ItemMeta meta4 = ironHelmet.getItemMeta();
		meta4.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Fisherman's Cap");
		ironHelmet.setItemMeta(meta4);

		LeatherArmorMeta im = (LeatherArmorMeta) ironHelmet.getItemMeta();
		im.setColor(Color.GRAY);
		ironHelmet.setItemMeta(im);

		ItemMeta meta1 = ironChestplate.getItemMeta();
		meta1.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Fisherman's Jacket");
		ironChestplate.setItemMeta(meta1);

		ItemMeta meta2 = ironLeggings.getItemMeta();
		meta2.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Fisherman's Pants");
		ironLeggings.setItemMeta(meta2);

		ItemMeta meta3 = ironBoots.getItemMeta();
		meta3.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Fisherman's Boots");
		ironBoots.setItemMeta(meta3);

		inventory.addItem(diamondSword, rod);
		inventory.setHelmet(ironHelmet);
		inventory.setChestplate(ironChestplate);
		inventory.setLeggings(ironLeggings);
		inventory.setBoots(ironBoots);

		player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "You have chosen the kit " + ChatColor.GRAY + ""
				+ ChatColor.BOLD + "Fisherman!");
	}

	public static void potmasterKit(Player player) {
		PlayerInventory inventory = player.getInventory();
		// clears player inventory
		inventory.clear();
		player.setMaxHealth(20);

		ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD);
		Potion pot = new Potion(PotionType.INSTANT_HEAL, 2);
		pot.setSplash(true);
		for (int i = 1; i < 36; i++) {
			inventory.setItem(i, pot.toItemStack(1));

		}

		ItemStack ironHelmet = new ItemStack(Material.IRON_HELMET);
		ItemStack ironChestplate = new ItemStack(Material.IRON_CHESTPLATE);
		ItemStack ironLeggings = new ItemStack(Material.IRON_LEGGINGS);
		ItemStack ironBoots = new ItemStack(Material.IRON_BOOTS);

		ironHelmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		ironChestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		ironLeggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		ironBoots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);

		inventory.addItem(diamondSword);
		inventory.setHelmet(ironHelmet);
		inventory.setChestplate(ironChestplate);
		inventory.setLeggings(ironLeggings);
		inventory.setBoots(ironBoots);

		player.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "You have chosen the kit "
				+ ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Pot Master!");
	}

	public static void survivorKit(Player player) {
		PlayerInventory inventory = player.getInventory();
		// clears player inventory
		inventory.clear();

		ItemStack sword = new ItemStack(Material.IRON_AXE);
		ItemStack rod = new ItemStack(Material.FISHING_ROD);
		ItemStack ironHelmet = new ItemStack(Material.IRON_HELMET);
		ItemStack ironChestplate = new ItemStack(Material.IRON_CHESTPLATE);
		ItemStack ironLeggings = new ItemStack(Material.IRON_LEGGINGS);
		ItemStack ironBoots = new ItemStack(Material.IRON_BOOTS);

		sword.addEnchantment(Enchantment.DAMAGE_ALL, 2);

		ItemMeta meta = sword.getItemMeta();
		meta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Hachet");
		sword.setItemMeta(meta);

		ItemMeta meta1 = rod.getItemMeta();
		meta1.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Old Fishing Pole");
		rod.setItemMeta(meta1);

		inventory.addItem(sword, rod);
		inventory.setHelmet(ironHelmet);
		inventory.setChestplate(ironChestplate);
		inventory.setLeggings(ironLeggings);
		inventory.setBoots(ironBoots);

		player.setMaxHealth(100);
		player.setHealth(100);

		player.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "You have chosen the kit " + ChatColor.GREEN
				+ "" + ChatColor.BOLD + "Survivor!");

	}

	public static void pyromancerKit(Player player) {
		PlayerInventory inventory = player.getInventory();
		// clears player inventory
		inventory.clear();

		ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
		ItemStack item = new ItemStack(Material.BLAZE_POWDER);
		ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
		ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);

		helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		chestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		leggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);

		helmet.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		chestplate.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		leggings.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		boots.addUnsafeEnchantment(Enchantment.DURABILITY, 10);

		ItemMeta meta1 = item.getItemMeta();
		meta1.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Fire");
		item.setItemMeta(meta1);

		LeatherArmorMeta im = (LeatherArmorMeta) helmet.getItemMeta();
		im.setColor(Color.RED);
		helmet.setItemMeta(im);

		LeatherArmorMeta im1 = (LeatherArmorMeta) chestplate.getItemMeta();
		im1.setColor(Color.RED);
		chestplate.setItemMeta(im1);

		LeatherArmorMeta im2 = (LeatherArmorMeta) leggings.getItemMeta();
		im2.setColor(Color.RED);
		leggings.setItemMeta(im2);

		LeatherArmorMeta im3 = (LeatherArmorMeta) boots.getItemMeta();
		im3.setColor(Color.RED);
		boots.setItemMeta(im3);

		inventory.addItem(sword, item);
		inventory.setHelmet(helmet);
		inventory.setChestplate(chestplate);
		inventory.setLeggings(leggings);
		inventory.setBoots(boots);

		for (int i = 2; i < 36; i++) {
			inventory.setItem(i, new ItemStack(Material.MUSHROOM_SOUP, 1));
		}

		player.setMaxHealth(20);
		player.setHealth(20);

		player.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "You have chosen the kit " + ChatColor.DARK_RED
				+ "" + ChatColor.BOLD + "Pyromancer!");

	}

	public static void recrafterKit(Player player) {
		PlayerInventory inventory = player.getInventory();
		// clears player inventory
		inventory.clear();

		for (int i = 1; i < 36; i++) {
			inventory.setItem(i, new ItemStack(Material.MUSHROOM_SOUP, 1));
		}

		ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
		ItemStack helmet = new ItemStack(Material.IRON_HELMET);
		ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
		ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS);
		ItemStack boots = new ItemStack(Material.IRON_BOOTS);

		inventory.setItem(9, new ItemStack(Material.BOWL, 64));
		inventory.setItem(10, new ItemStack(Material.BROWN_MUSHROOM, 64));
		inventory.setItem(11, new ItemStack(Material.RED_MUSHROOM, 64));

		inventory.addItem(sword);
		inventory.setHelmet(helmet);
		inventory.setChestplate(chestplate);
		inventory.setLeggings(leggings);
		inventory.setBoots(boots);

		player.setMaxHealth(20);
		player.setHealth(20);

		player.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "You have chosen the kit "
				+ ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Re-crafter!");

	}

}