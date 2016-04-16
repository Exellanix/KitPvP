package me.exellanix.kitpvp.kit_abilities;

import me.exellanix.kitpvp.Util.AlterItem;
import me.exellanix.kitpvp.config.KitConfiguration;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Mac on 3/6/2016.
 */
public class RodHook implements Ability, Listener {
    private ItemStack item;
    private List<Action> actions;
    private String name;
    private KitConfiguration config;

    public RodHook() {
        setup();
        name = "RODHOOK";
    }

    @Override
    public ItemStack getItem() {
        return item;
    }

    @Override
    public void activateAbility(Player player) {}

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

    @Override
    public KitConfiguration getConfig() {
        return config;
    }

    @Override
    public void setConfig(KitConfiguration config) {
        this.config = config;
    }

    private void setup() {
        ItemStack rod = new ItemStack(Material.FISHING_ROD);
        ItemMeta meta = rod.getItemMeta();
        meta.setDisplayName(org.bukkit.ChatColor.AQUA + "" + org.bukkit.ChatColor.BOLD + "Fish & Sticks");
        rod.setItemMeta(meta);
        this.item = rod;

        List<Action> list = new ArrayList<>();
        list.add(Action.RIGHT_CLICK_BLOCK);
        list.add(Action.RIGHT_CLICK_AIR);
        this.actions = list;

        KitConfiguration config = new KitConfiguration(true, "RodHook");
        HashMap<String, Object> values = new HashMap<>();
        values.put("pull-strength", 1);
        config.saveDefaultSettings(values);
        this.config = config;
    }

    @EventHandler
    public void onPlayerHitFishingrodscorpion(PlayerFishEvent event) {
        Player p = event.getPlayer();
        if (event.getCaught() instanceof Player) {
            Player caught = (Player) event.getCaught();
            if (p != caught) {
                if (AlterItem.itemsEqual(this.item, p.getItemInHand())) {
                    p.sendMessage(ChatColor.GREEN + "GET OVER HERE!");
                    Vector v = p.getLocation().getDirection();
                    v.multiply(-1 * ((double) getConfig().getSettings().get("pull-strength")));
                    v.setY(-1 * ((double) getConfig().getSettings().get("pull-strength")));
                    caught.setVelocity(v);
                }
            }
        }
    }
}
