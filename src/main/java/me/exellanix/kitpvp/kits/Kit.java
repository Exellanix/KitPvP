package me.exellanix.kitpvp.kits;

import me.exellanix.kitpvp.config.KitConfiguration;
import me.exellanix.kitpvp.kit_abilities.Ability;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Created by Mac on 3/7/2016.
 */
public interface Kit {
    String getName();

    String getDisplayName();

    Armor getArmor();

    void setArmor(Armor armor);

    ArrayList<Weapon> getWeapons();

    boolean hasWeapon(ItemStack weapon);

    ArrayList<Ability> getAbilities();

    void addAbility(Ability ability);

    void removeAbility(Ability ability);

    boolean hasAbility(Ability ability);

    ItemStack getIcon();

    boolean isFree();

    ItemStack[] getInventory();

    void setInventory(ItemStack[] inventory);

    void equipKit(Player player);

    int getHealth();

    int getPrice();

    ArrayList<String> getKitAlias();

    void addKitAlias(ArrayList<String> kitAlias);

    void addKitAlias(String kitAlias);

    void setKitAlias(ArrayList<String> kitAlias);

    boolean hasAlias(String s);

    KitConfiguration getConfig();

    void setConfig(KitConfiguration config);
}
