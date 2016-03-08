package me.exellanix.idk.kits;

import me.exellanix.idk.kit_abilities.Ability;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Created by Mac on 3/7/2016.
 */
public class BasicKit implements Kit {
    private ArrayList<ItemStack> weapons;
    private ArrayList<Ability> abilities;
    private ArrayList<String> kitAlias;
    private Armor armor;
    private String name;
    private ItemStack icon;
    private boolean isFree;
    private ItemStack[] inventory;
    private int health;
    private int price;

    public BasicKit(ArrayList<ItemStack> weapons, ArrayList<Ability> abilities,
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
    }

    @Override
    public String getName() {
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
    public ArrayList<ItemStack> getWeapons() {
        return weapons;
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
    public void setKitAlias(ArrayList<String> kitAlias) {
        this.kitAlias = kitAlias;
    }

    @Override
    public boolean hasAlias(String s) {
        return kitAlias.contains(s);
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setWeapons(ArrayList<ItemStack> weapons) {
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
