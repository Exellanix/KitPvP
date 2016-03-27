package me.exellanix.kitpvp.kits;

import me.exellanix.kitpvp.KitPvP;
import me.exellanix.kitpvp.Util.AlterItem;
import me.exellanix.kitpvp.config.KitConfiguration;
import me.exellanix.kitpvp.kit_abilities.Ability;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Mac on 3/6/2016.
 */
public class DefaultKits {
    private static int switchSnowball = -1;

    public static ArrayList<Kit> getDefaultKits() {
        ArrayList<Kit> kits = new ArrayList<>();
        kits.add(pvpKit());
        kits.add(archerKit());
        kits.add(thorKit());
        kits.add(kangarooKit());
        kits.add(switcherKit());
        kits.add(fishermanKit());
        kits.add(potmasterKit());
        kits.add(survivorKit());
        kits.add(pyromancerKit());
        kits.add(recrafterKit());
        return kits;
    }

    public static BasicKit pvpKit() {

        ItemStack[] inventory = new ItemStack[36];

        ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD);
        for (int i = 1; i < 36; i++) {
            inventory[i] = new ItemStack(Material.MUSHROOM_SOUP, 1);
        }

        ItemStack ironHelmet = new ItemStack(Material.IRON_HELMET);
        ItemStack ironChestplate = new ItemStack(Material.IRON_CHESTPLATE);
        ItemStack ironLeggings = new ItemStack(Material.IRON_LEGGINGS);
        ItemStack ironBoots = new ItemStack(Material.IRON_BOOTS);

        Armor armor = new Armor();
        armor.setHelmet(ironHelmet);
        armor.setChestplate(ironChestplate);
        armor.setLeggings(ironLeggings);
        armor.setBoots(ironBoots);

        inventory[0] = diamondSword;

        ArrayList<Weapon> weapons = new ArrayList<>();
        weapons.add(new Weapon(true, 0, diamondSword));

        ArrayList<Ability> abilities = new ArrayList<>();
        Ability soup = KitPvP.getSingleton().getAbilityManager().getAbility("SOUPREGEN");
        if (soup != null) {
            abilities.add(soup);
        }

        ArrayList<String> nameAlias = new ArrayList<>();
        nameAlias.add("PVP");

        ItemStack icon = AlterItem.nameItem(Material.DIAMOND_SWORD,
                ChatColor.AQUA + "" + ChatColor.BOLD + "PvP");

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "With your wits and piercing sword, you");
        lore.add(ChatColor.GRAY + "will exterminate all of your enemies!");
        AlterItem.addLore(icon, lore);

        BasicKit pvp = new BasicKit(weapons, abilities, armor, org.bukkit.ChatColor.AQUA
                + "" + org.bukkit.ChatColor.BOLD + "PvP", icon, true, 20, 0);
        pvp.setInventory(inventory);
        pvp.addKitAlias(nameAlias);

        return pvp;
    }

    public static BasicKit archerKit() {

        ItemStack[] inv = new ItemStack[36];

        ItemStack woodSword = new ItemStack(Material.WOOD_SWORD);

        for (int i = 2; i < 36; i++) {
            inv[i] = new ItemStack(Material.MUSHROOM_SOUP, 1);
        }

        ItemStack arrow = new ItemStack(Material.ARROW, 1);
        AlterItem.nameItem(arrow, ChatColor.AQUA + "" + ChatColor.BOLD + "Arrow o' Doom");
        ArrayList<String> arrowLore = new ArrayList<>();
        arrowLore.add("");
        arrowLore.add(ChatColor.GRAY + "Nothing to see here...");
        AlterItem.addLore(arrow, arrowLore);
        inv[17] = arrow;

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

        Armor armor = new Armor(chainHelmet, chainChestplate, chainLeggings, chainBoots);

        ArrayList<Ability> abilities = new ArrayList<>();
        Ability soup = KitPvP.getSingleton().getAbilityManager().getAbility("SOUPREGEN");
        if (soup != null) {
            abilities.add(soup);
        }
        ArrayList<Weapon> weapons = new ArrayList<>();
        weapons.add(new Weapon(true, 0, woodSword));
        weapons.add(new Weapon(true, 1, bow));
        weapons.add(new Weapon(false, 17, arrow));
        inv[0] = woodSword;
        inv[1] = bow;

        String name = org.bukkit.ChatColor.DARK_AQUA + "" + org.bukkit.ChatColor.BOLD + "Archer";

        ItemStack icon = AlterItem.nameItem(Material.BOW,
                ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Archer");

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "With your powerful bow and infinite arrows your");
        lore.add(ChatColor.GRAY + "enemies will never have the chance to hit you!");
        AlterItem.addLore(icon, lore);

        ArrayList<String> nameAlias = new ArrayList<>();
        nameAlias.add("ARCHER");
        nameAlias.add("BOW");

        BasicKit kit = new BasicKit(weapons, abilities, armor, name, icon, true, 20, 0);
        kit.setInventory(inv);
        kit.addKitAlias(nameAlias);

        return kit;
    }

    public static BasicKit thorKit() {
        ItemStack[] inv = new ItemStack[36];

        for (int i = 1; i < 36; i++) {
            inv[i] = new ItemStack(Material.MUSHROOM_SOUP, 1);
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
        meta.setDisplayName(org.bukkit.ChatColor.WHITE + "" + org.bukkit.ChatColor.BOLD + "Thor's Axe");
        diamondAxe.setItemMeta(meta);

        ItemMeta meta1 = leatherHelmet.getItemMeta();
        meta1.setDisplayName(org.bukkit.ChatColor.WHITE + "" + org.bukkit.ChatColor.BOLD + "Thor's Hair");
        leatherHelmet.setItemMeta(meta1);

        ItemMeta meta2 = ironChestplate.getItemMeta();
        meta2.setDisplayName(org.bukkit.ChatColor.WHITE + "" + org.bukkit.ChatColor.BOLD + "Thor's Cloak");
        ironChestplate.setItemMeta(meta2);

        ItemMeta meta3 = ironLeggings.getItemMeta();
        meta3.setDisplayName(org.bukkit.ChatColor.WHITE + "" + org.bukkit.ChatColor.BOLD + "Thor's Cloak");
        ironLeggings.setItemMeta(meta3);

        ItemMeta meta4 = leatherBoots.getItemMeta();
        meta4.setDisplayName(org.bukkit.ChatColor.WHITE + "" + org.bukkit.ChatColor.BOLD + "Thor's Feet");
        leatherBoots.setItemMeta(meta4);

        Armor armor = new Armor(leatherHelmet, ironChestplate, ironLeggings, leatherBoots);

        ArrayList<Ability> abilities = new ArrayList<>();
        Ability soup = KitPvP.getSingleton().getAbilityManager().getAbility("SOUPREGEN");
        if (soup != null) {
            abilities.add(soup);
        }
        Ability axeStrike = KitPvP.getSingleton().getAbilityManager().getAbility("AXESTRIKE");
        if (axeStrike != null) {
            abilities.add(axeStrike);
        }
        ArrayList<Weapon> weapons = new ArrayList<>();
        weapons.add(new Weapon(true, 0, diamondAxe));
        inv[0] = diamondAxe;

        String name = ChatColor.YELLOW + "" + ChatColor.BOLD + "Thor";

        ItemStack icon = AlterItem.nameItem(Material.DIAMOND_AXE,
                ChatColor.YELLOW + "" + ChatColor.BOLD + "Thor");

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Call upon the wrath of the Gods and");
        lore.add(ChatColor.GRAY + "rain down lightning on your enemies!");
        lore.add("");
        lore.add(ChatColor.GRAY + "Kit Info:");
        lore.add(ChatColor.GRAY + "  - Right click the axe to summon");
        lore.add(ChatColor.GRAY + "    lightning where you are looking.");
        AlterItem.addLore(icon, lore);

        ArrayList<String> nameAlias = new ArrayList<>();
        nameAlias.add("THOR");

        BasicKit kit = new BasicKit(weapons, abilities, armor, name, icon, false, 20, 250);
        kit.setInventory(inv);
        kit.addKitAlias(nameAlias);

        return kit;
    }

    public static BasicKit kangarooKit() {
        ItemStack[] inv = new ItemStack[36];

        ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD);

        for (int i = 2; i < 36; i++) {
            inv[i] = new ItemStack(Material.MUSHROOM_SOUP, 1);
        }
        ItemStack ironHelmet = new ItemStack(Material.IRON_HELMET);
        ItemStack ironChestplate = new ItemStack(Material.IRON_CHESTPLATE);
        ItemStack ironLeggings = new ItemStack(Material.IRON_LEGGINGS);
        ItemStack ironBoots = new ItemStack(Material.IRON_BOOTS);
        ItemStack feather = new ItemStack(Material.FIREWORK);

        Armor armor = new Armor(ironHelmet, ironChestplate, ironLeggings, ironBoots);

        ArrayList<Ability> abilities = new ArrayList<>();
        Ability soup = KitPvP.getSingleton().getAbilityManager().getAbility("SOUPREGEN");
        if (soup != null) {
            abilities.add(soup);
        }
        ArrayList<Weapon> weapons = new ArrayList<>();
        weapons.add(new Weapon(true, 0, diamondSword));
        inv[0] = diamondSword;
        inv[1] = feather;

        String name = ChatColor.RED + "" + ChatColor.BOLD + "Kangaroo";

        ItemStack icon = AlterItem.nameItem(Material.FIREWORK,
                ChatColor.RED + "" + ChatColor.BOLD + "Kangaroo");

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "With a powerful jump like this,");
        lore.add(ChatColor.GRAY + "no one can compete with you!");
        AlterItem.addLore(icon, lore);

        ArrayList<String> nameAlias = new ArrayList<>();
        nameAlias.add("KANGAROO");
        nameAlias.add("KANG");

        BasicKit kit = new BasicKit(weapons, abilities, armor, name, icon, false, 20, 150);
        kit.setInventory(inv);
        kit.addKitAlias(nameAlias);

        return kit;
    }

    public static BasicKit switcherKit() {
        ItemStack[] inv = new ItemStack[36];

        ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD);

        for (int i = 2; i < 36; i++) {
            inv[i] = new ItemStack(Material.MUSHROOM_SOUP, 1);
        }
        ItemStack ironHelmet = new ItemStack(Material.IRON_HELMET);
        ItemStack ironChestplate = new ItemStack(Material.IRON_CHESTPLATE);
        ItemStack ironLeggings = new ItemStack(Material.IRON_LEGGINGS);
        ItemStack ironBoots = new ItemStack(Material.IRON_BOOTS);
        ItemStack snowball = new ItemStack(Material.SNOW_BALL, 16);

        ItemMeta meta = snowball.getItemMeta();
        meta.setDisplayName(org.bukkit.ChatColor.WHITE + "" + org.bukkit.ChatColor.BOLD + "Switcher");
        snowball.setItemMeta(meta);

        Armor armor = new Armor(ironHelmet, ironChestplate, ironLeggings, ironBoots);

        ArrayList<Ability> abilities = new ArrayList<>();
        Ability soup = KitPvP.getSingleton().getAbilityManager().getAbility("SOUPREGEN");
        if (soup != null) {
            abilities.add(soup);
        }
        Ability snowballA = KitPvP.getSingleton().getAbilityManager().getAbility("SNOWBALLSWITCHER");
        if (snowballA != null) {
            abilities.add(snowballA);
        }
        ArrayList<Weapon> weapons = new ArrayList<>();
        weapons.add(new Weapon(true, 0, diamondSword));
        inv[0] = diamondSword;
        inv[1] = snowball;

        String name = ChatColor.WHITE + "" + ChatColor.BOLD + "Switcher";

        ItemStack icon = AlterItem.nameItem(Material.SNOW_BALL,
                ChatColor.WHITE + "" + ChatColor.BOLD + "Switcher");

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Confuse your enemy by switching places with him,");
        lore.add(ChatColor.GRAY + "then destroy him while he is incapacitated!");
        lore.add("");
        lore.add(ChatColor.GRAY + "Kit Info:");
        lore.add(ChatColor.GRAY + "  - Receive a snowball every 8 seconds with");
        lore.add(ChatColor.GRAY + "    a maximum of 16.");
        AlterItem.addLore(icon, lore);

        ArrayList<String> nameAlias = new ArrayList<>();
        nameAlias.add("SWITCHER");
        nameAlias.add("SWITCH");

        BasicKit kit = new BasicKit(weapons, abilities, armor, name, icon, false, 20, 300);
        kit.setInventory(inv);
        kit.addKitAlias(nameAlias);

        KitConfiguration config = new KitConfiguration(false, "Switcher");
        HashMap<String, Object> values = new HashMap<>();
        values.put("refill", 8);
        config.saveDefaultSettings(values);
        kit.setConfig(config);

        if (switchSnowball != -1) {
            KitPvP.getSingleton().getServer().getScheduler().cancelTask(switchSnowball);
        }

        switchSnowball = KitPvP.getSingleton().getServer().getScheduler().runTaskTimerAsynchronously(KitPvP.getSingleton(), () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (KitPvP.getSingleton().getPlayerKits().containsKey(p)) {
                    if (KitPvP.getSingleton().getPlayerKits().get(p).equals(kit)) {
                        int amount = 0;
                        int location = -1;
                        int index = 0;
                        for (ItemStack itemStack : p.getInventory().getContents()) {
                            if (AlterItem.itemsEqual(itemStack, snowball)) {
                                amount += itemStack.getAmount();
                                if (location == -1) {
                                    location = index;
                                }
                            }
                            index++;
                        }
                        if (amount < 16 && amount > 0) {
                            ItemStack stack = p.getInventory().getItem(location);
                            stack.setAmount(stack.getAmount() + 1);
                            p.getInventory().setItem(location, stack);
                            p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1, 1);
                        } else if (amount == 0) {
                            for (int i = 0; i < 36; i++) {
                                if (p.getInventory().getItem(i) == null) {
                                    ItemStack snowB = AlterItem.copyItem(snowball);
                                    snowB.setAmount(1);
                                    p.getInventory().setItem(i, snowB);
                                    p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1, 1);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }, 0, ((int) kit.getConfig().getSettings().get("refill")) * 20).getTaskId();

        return kit;
    }

    public static BasicKit fishermanKit() {
        ItemStack[] inv = new ItemStack[36];

        ItemStack diamondSword = new ItemStack(Material.IRON_SWORD);

        for (int i = 2; i < 36; i++) {
            inv[i] = new ItemStack(Material.MUSHROOM_SOUP, 1);
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
        meta7.setDisplayName(org.bukkit.ChatColor.AQUA + "" + org.bukkit.ChatColor.BOLD + "Fish Knife");
        diamondSword.setItemMeta(meta7);

        ItemMeta meta = rod.getItemMeta();
        meta.setDisplayName(org.bukkit.ChatColor.AQUA + "" + org.bukkit.ChatColor.BOLD + "Fish & Sticks");
        rod.setItemMeta(meta);

        ItemMeta meta4 = ironHelmet.getItemMeta();
        meta4.setDisplayName(org.bukkit.ChatColor.GRAY + "" + org.bukkit.ChatColor.BOLD + "Fisherman's Cap");
        ironHelmet.setItemMeta(meta4);

        LeatherArmorMeta im = (LeatherArmorMeta) ironHelmet.getItemMeta();
        im.setColor(Color.GRAY);
        ironHelmet.setItemMeta(im);

        ItemMeta meta1 = ironChestplate.getItemMeta();
        meta1.setDisplayName(org.bukkit.ChatColor.GRAY + "" + org.bukkit.ChatColor.BOLD + "Fisherman's Jacket");
        ironChestplate.setItemMeta(meta1);

        ItemMeta meta2 = ironLeggings.getItemMeta();
        meta2.setDisplayName(org.bukkit.ChatColor.GRAY + "" + org.bukkit.ChatColor.BOLD + "Fisherman's Pants");
        ironLeggings.setItemMeta(meta2);

        ItemMeta meta3 = ironBoots.getItemMeta();
        meta3.setDisplayName(org.bukkit.ChatColor.GRAY + "" + org.bukkit.ChatColor.BOLD + "Fisherman's Boots");
        ironBoots.setItemMeta(meta3);

        Armor armor = new Armor(ironHelmet, ironChestplate, ironLeggings, ironBoots);

        ArrayList<Ability> abilities = new ArrayList<>();
        Ability soup = KitPvP.getSingleton().getAbilityManager().getAbility("SOUPREGEN");
        if (soup != null) {
            abilities.add(soup);
        }
        ArrayList<Weapon> weapons = new ArrayList<>();
        weapons.add(new Weapon(true, 0, diamondSword));
        weapons.add(new Weapon(true, 1, rod));
        inv[0] = diamondSword;
        inv[1] = rod;

        String name = ChatColor.GRAY + "" + ChatColor.BOLD + "Fisherman";

        ItemStack icon = AlterItem.nameItem(Material.FISHING_ROD,
                ChatColor.GRAY + "" + ChatColor.BOLD + "Fisherman");

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Your enemy trying to escape your wrath?");
        lore.add(ChatColor.GRAY + "Snatch them back with the specially crafted");
        lore.add(ChatColor.GRAY + "fishing rod!");
        AlterItem.addLore(icon, lore);

        ArrayList<String> nameAlias = new ArrayList<>();
        nameAlias.add("FISHERMAN");
        nameAlias.add("FISHER");

        BasicKit kit = new BasicKit(weapons, abilities, armor, name, icon, false, 20, 275);
        kit.setInventory(inv);
        kit.addKitAlias(nameAlias);

        return kit;
    }

    public static BasicKit potmasterKit() {
        ItemStack[] inv = new ItemStack[36];

        ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD);
        Potion pot = new Potion(PotionType.INSTANT_HEAL, 2);
        pot.setSplash(true);
        for (int i = 1; i < 36; i++) {
            inv[i] = pot.toItemStack(1);

        }

        ItemStack ironHelmet = new ItemStack(Material.IRON_HELMET);
        ItemStack ironChestplate = new ItemStack(Material.IRON_CHESTPLATE);
        ItemStack ironLeggings = new ItemStack(Material.IRON_LEGGINGS);
        ItemStack ironBoots = new ItemStack(Material.IRON_BOOTS);

        ironHelmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        ironChestplate.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        ironLeggings.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        ironBoots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);

        Armor armor = new Armor(ironHelmet, ironChestplate, ironLeggings, ironBoots);

        ArrayList<Ability> abilities = new ArrayList<>();
        ArrayList<Weapon> weapons = new ArrayList<>();
        weapons.add(new Weapon(true, 0, diamondSword));
        inv[0] = diamondSword;

        String name = ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Pot-Master";

        ItemStack icon = AlterItem.nameItem(Material.POTION,
                ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Pot Master");

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Heal yourself with potions, but be careful");
        lore.add(ChatColor.GRAY + "where you throw them, you don't want to help");
        lore.add(ChatColor.GRAY + "the other players!");
        AlterItem.addLore(icon, lore);

        ArrayList<String> nameAlias = new ArrayList<>();
        nameAlias.add("POTMASTER");
        nameAlias.add("POT");

        BasicKit kit = new BasicKit(weapons, abilities, armor, name, icon, true, 20, 0);
        kit.setInventory(inv);
        kit.addKitAlias(nameAlias);

        return kit;
    }

    public static BasicKit survivorKit() {
        ItemStack[] inv = new ItemStack[36];

        ItemStack sword = new ItemStack(Material.IRON_AXE);
        ItemStack rod = new ItemStack(Material.FISHING_ROD);
        ItemStack ironHelmet = new ItemStack(Material.IRON_HELMET);
        ItemStack ironChestplate = new ItemStack(Material.IRON_CHESTPLATE);
        ItemStack ironLeggings = new ItemStack(Material.IRON_LEGGINGS);
        ItemStack ironBoots = new ItemStack(Material.IRON_BOOTS);

        sword.addEnchantment(Enchantment.DAMAGE_ALL, 2);

        ItemMeta meta = sword.getItemMeta();
        meta.setDisplayName(org.bukkit.ChatColor.BLUE + "" + org.bukkit.ChatColor.BOLD + "Hachet");
        sword.setItemMeta(meta);

        ItemMeta meta1 = rod.getItemMeta();
        meta1.setDisplayName(org.bukkit.ChatColor.BLUE + "" + org.bukkit.ChatColor.BOLD + "Old Fishing Pole");
        rod.setItemMeta(meta1);

        Armor armor = new Armor(ironHelmet, ironChestplate, ironLeggings, ironBoots);

        ArrayList<Ability> abilities = new ArrayList<>();
        ArrayList<Weapon> weapons = new ArrayList<>();
        weapons.add(new Weapon(true, 0, sword));
        weapons.add(new Weapon(true, 1, rod));
        inv[0] = sword;
        inv[1] = rod;

        String name = ChatColor.GREEN + "" + ChatColor.BOLD + "Survivor";

        ItemStack icon = AlterItem.nameItem(Material.APPLE,
                ChatColor.GREEN + "" + ChatColor.BOLD + "Survivor");

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "With 100 points of health, taking you");
        lore.add(ChatColor.GRAY + "down won't be easy!");
        AlterItem.addLore(icon, lore);

        ArrayList<String> nameAlias = new ArrayList<>();
        nameAlias.add("SURVIVOR");
        nameAlias.add("SURVIVE");

        BasicKit kit = new BasicKit(weapons, abilities, armor, name, icon, true, 100, 0);
        kit.setInventory(inv);
        kit.addKitAlias(nameAlias);

        return kit;
    }

    public static BasicKit pyromancerKit() {
        ItemStack[] inv = new ItemStack[36];

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
        meta1.setDisplayName(org.bukkit.ChatColor.DARK_RED + "" + org.bukkit.ChatColor.BOLD + "Fire");
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

        Armor armor = new Armor(helmet, chestplate, leggings, boots);

        ArrayList<Ability> abilities = new ArrayList<>();
        Ability soup = KitPvP.getSingleton().getAbilityManager().getAbility("SOUPREGEN");
        if (soup != null) {
            abilities.add(soup);
        }

        ArrayList<Weapon> weapons = new ArrayList<>();
        weapons.add(new Weapon(true, 0, sword));
        inv[0] = sword;
        inv[1] = item;

        String name = ChatColor.DARK_RED + "" + ChatColor.BOLD + "Pyromancer";

        for (int i = 2; i < 36; i++) {
            inv[i] = new ItemStack(Material.MUSHROOM_SOUP, 1);
        }

        ItemStack icon = AlterItem.nameItem(Material.BLAZE_POWDER,
                ChatColor.DARK_RED + "" + ChatColor.BOLD + "Pyromancer");

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Take out your enemies with ");
        lore.add(ChatColor.GRAY + "a burst of scalding lava!");
        lore.add("");
        lore.add(ChatColor.GRAY + "Kit Info:");
        lore.add(ChatColor.GRAY + "  - Right click the blaze powder to");
        lore.add(ChatColor.GRAY + "    spawn scalding lava around you.");
        AlterItem.addLore(icon, lore);

        ArrayList<String> nameAlias = new ArrayList<>();
        nameAlias.add("PYROMANCER");
        nameAlias.add("PYRO");

        BasicKit kit = new BasicKit(weapons, abilities, armor, name, icon, false, 20, 225);
        kit.setInventory(inv);
        kit.addKitAlias(nameAlias);

        return kit;
    }

    public static BasicKit recrafterKit() {
        ItemStack[] inv = new ItemStack[36];

        for (int i = 1; i < 36; i++) {
            inv[i] = new ItemStack(Material.MUSHROOM_SOUP, 1);
        }

        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        ItemStack helmet = new ItemStack(Material.IRON_HELMET);
        ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
        ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS);
        ItemStack boots = new ItemStack(Material.IRON_BOOTS);

        inv[9] = new ItemStack(Material.BOWL, 64);
        inv[10] = new ItemStack(Material.BROWN_MUSHROOM, 64);
        inv[11] = new ItemStack(Material.RED_MUSHROOM, 64);

        ArrayList<Ability> abilities = new ArrayList<>();
        Ability soup = KitPvP.getSingleton().getAbilityManager().getAbility("SOUPREGEN");
        if (soup != null) {
            abilities.add(soup);
        }
        ArrayList<Weapon> weapons = new ArrayList<>();
        weapons.add(new Weapon(true, 0, sword));
        inv[0] = sword;

        Armor armor = new Armor(helmet, chestplate, leggings, boots);

        String name = ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Re-crafter";

        ItemStack icon = AlterItem.nameItem(Material.BOWL,
                ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "Re-crafter");

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Did someone call for more soup?");
        lore.add(ChatColor.GRAY + "Craft up an extra batch as fast as you can!");
        lore.add("");
        lore.add(ChatColor.GRAY + "Kit Info:");
        lore.add(ChatColor.GRAY + "  - Receive 64 extra bowls and mushrooms");
        lore.add(ChatColor.GRAY + "    to craft more mushroom soup.");
        AlterItem.addLore(icon, lore);

        ArrayList<String> nameAlias = new ArrayList<>();
        nameAlias.add("RECRAFTER");
        nameAlias.add("RECRAFT");
        nameAlias.add("RCRAFT");

        BasicKit kit = new BasicKit(weapons, abilities, armor, name, icon, false, 20, 100);
        kit.setInventory(inv);
        kit.addKitAlias(nameAlias);

        return kit;
    }
}
