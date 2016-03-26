package me.exellanix.kitpvp.kits;

import me.exellanix.kitpvp.Util.AlterItem;
import me.exellanix.kitpvp.config.KitConfiguration;
import me.exellanix.kitpvp.kit_abilities.Ability;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Created by Mac on 3/7/2016.
 */
public class BasicKit implements Kit {
    private ArrayList<Weapon> weapons;
    private ArrayList<Ability> abilities;
    private ArrayList<String> kitAlias;
    private Armor armor;
    private String name;
    private ItemStack icon;
    private boolean isFree;
    private ItemStack[] inventory;
    private int health;
    private int price;
    private KitConfiguration configuration;

    public BasicKit(ArrayList<Weapon> weapons, ArrayList<Ability> abilities,
                    Armor armor, String name, ItemStack icon, boolean isFree, int health, int price) {
        this.weapons = weapons;
        this.abilities = abilities;
        this.armor = armor;
        this.name = name;
        this.icon = icon;
        this.isFree = isFree;
        inventory = new ItemStack[36];
        this.health = health;
        this.price = price;
        kitAlias = new ArrayList<>();
        kitAlias.add(getName().toUpperCase());
    }

    @Override
    public String getName() {
        char[] array = name.toCharArray();
        String newName = "";
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '§') {
                i += 1;
            } else {
                newName += array[i];
            }
        }
        return newName;
    }

    @Override
    public String getDisplayName() {
        return name;
    }

    @Override
    public Armor getArmor() {
        return armor;
    }

    @Override
    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    @Override
    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    @Override
    public boolean hasWeapon(ItemStack weapon) {
        for (Weapon w : weapons) {
            if (AlterItem.itemsEqual(w.getWeapon(), weapon)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Ability> getAbilities() {
        return abilities;
    }

    @Override
    public void addAbility(Ability ability) {
        if (!abilities.contains(ability)) {
            abilities.add(ability);
        }
    }

    @Override
    public void removeAbility(Ability ability) {
        if (abilities.contains(ability)) {
            abilities.remove(ability);
        }
    }

    @Override
    public boolean hasAbility(Ability ability) {
        return abilities.contains(ability);
    }

    @Override
    public ItemStack getIcon() {
        return icon;
    }

    @Override
    public boolean isFree() {
        return isFree;
    }

    @Override
    public ItemStack[] getInventory() {
        return inventory;
    }

    @Override
    public void setInventory(ItemStack[] inventory) {
        this.inventory = inventory;
    }

    @Override
    public void equipKit(Player player) {
        armor.resetArmor(player);
        Inventory inv = player.getInventory();
        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, inventory[i]);
        }
        player.setMaxHealth(health);
        if (name.contains("Survivor")) {
            player.setHealth(health);
        }
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public ArrayList<String> getKitAlias() {
        return kitAlias;
    }

    @Override
    public void addKitAlias(ArrayList<String> kitAlias) {
        for (String s : kitAlias) {
            if (!this.kitAlias.contains(s)) {
                this.kitAlias.add(s.toUpperCase());
            }
        }
    }

    @Override
    public void addKitAlias(String kitAlias) {
        if (!this.kitAlias.contains(kitAlias)) {
            this.kitAlias.add(kitAlias.toUpperCase());
        }
    }

    @Override
    public void setKitAlias(ArrayList<String> kitAlias) {
        this.kitAlias = kitAlias;
        if (!kitAlias.contains(getName().toUpperCase())) {
            kitAlias.add(getName().toUpperCase());
        }
    }

    @Override
    public boolean hasAlias(String s) {
        return kitAlias.contains(s);
    }

    @Override
    public KitConfiguration getConfig() {
        return configuration;
    }

    @Override
    public void setConfig(KitConfiguration config) {
        configuration = config;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setWeapons(ArrayList<Weapon> weapons) {
        this.weapons = weapons;
    }

    public void setAbilities(ArrayList<Ability> abilities) {
        this.abilities = abilities;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIcon(ItemStack icon) {
        this.icon = icon;
    }

    public void setFree(boolean free) {
        isFree = free;
    }
}
