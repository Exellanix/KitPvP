package me.exellanix.kitpvp.kit_abilities;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mac on 3/7/2016.
 */
public class SnowballSwitch implements Ability, Listener {
    private ItemStack item;
    private List<Action> actions;
    private String name;

    public SnowballSwitch() {
        setup();
        name = "SNOWBALLSWITCH";
    }

    @Override
    public ItemStack getItem() {
        return item;
    }

    @Override
    public void activateAbility(Player player) {

    }

    @Override
    public List<Action> getActions() {
        return actions;
    }

    @Override
    public boolean hasAction(Action action) {
        return actions.contains(action);
    }

    @Override
    public String getName() {
        return name;
    }

    private void setup() {
        ItemStack snowball = new ItemStack(Material.SNOW_BALL, 16);
        ItemMeta meta = snowball.getItemMeta();
        meta.setDisplayName(org.bukkit.ChatColor.WHITE + "" + org.bukkit.ChatColor.BOLD + "Switcher");
        snowball.setItemMeta(meta);
        item = snowball;

        List<Action> list = new ArrayList<>();
        list.add(Action.RIGHT_CLICK_AIR);
        list.add(Action.RIGHT_CLICK_BLOCK);
        actions = list;
    }

    @EventHandler
    public void EntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            Player p = (Player) event.getEntity();

            if (event.getDamager() instanceof Snowball) {
                Snowball d = (Snowball) event.getDamager();

                if (d.getShooter() instanceof Player) {
                    Player shooter = (Player) d.getShooter();

                    Location shooterl = shooter.getLocation();
                    shooter.teleport(p.getLocation());
                    p.teleport(shooterl);
                }
            }
        }
    }
}
