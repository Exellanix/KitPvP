package me.exellanix.kitpvp.kits;

import me.exellanix.kitpvp.kit_abilities.Ability;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Created by Mac on 3/7/2016.
 */
public interface Kit {
    String getName();

    Armor getArmor();

    void setArmor(Armor armor);

    ArrayList<ItemStack> getWeapons();

    ArrayList<Ability> getAbilities();

    void addAbility(Ability ability);

    void removeAbility(Ability ability);

    ItemStack getIcon();

    boolean isFree();

    ItemStack[] getInventory();

    void setInventory(ItemStack[] inventory);

    void equipKit(Player player);

    int getHealth();

    int getPrice();

    public ArrayList<String> getKitAlias();

    public void setKitAlias(ArrayList<String> kitAlias);

    public boolean hasAlias(String s);
}
