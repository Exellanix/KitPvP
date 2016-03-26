package me.exellanix.kitpvp.kit_abilities;

import me.exellanix.kitpvp.KitPvP;
import me.exellanix.kitpvp.config.KitConfiguration;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Mac on 3/6/2016.
 */
public class AxeStrike implements Ability {
    private ItemStack item;
    private List<Action> actions;
    private HashMap<Player, Long> cooldown = new HashMap<>();
    private String name;
    private KitConfiguration config;

    public AxeStrike() {
        setup();
        name = "AXESTRIKE";
    }

    @Override
    public ItemStack getItem() {
        return item;
    }

    @Override
    public void activateAbility(Player player) {
        if (!cooldown.containsKey(player)) {
            int MaxRange = 8;
            for (Block b : player.getLineOfSight((HashSet<Material>) null, MaxRange)) {
                if (b.getType() != Material.AIR) {
                    LightningStrike ls = player.getWorld().strikeLightningEffect(b.getLocation());
                    List<Entity> nbe = ls.getNearbyEntities(3, 12, 3);
                    for (Entity e : nbe) {
                        if (e instanceof Player && (Player) e != player) {
                            ((Player) e).damage((int) getConfig().getSettings().get("damage"));
                        }

                    }
                    cooldown.put(player, System.currentTimeMillis());
                    KitPvP.getSingleton().plugin.getServer().getScheduler().runTaskLater(KitPvP.getSingleton().plugin, () -> {
                        cooldown.remove(player);
                        player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Your axe is ready to strike!");
                    }, (int) getConfig().getSettings().get("cooldown") * 20);

                    return;
                }
            }
        } else {
            long cooldownTime = System.currentTimeMillis() - cooldown.get(player);
            player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You can not use that for another "
                    + ((int) getConfig().getSettings().get("cooldown") - (cooldownTime / 1000))
                    + " seconds!");
        }
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

    @Override
    public KitConfiguration getConfig() {
        return config;
    }

    @Override
    public void setConfig(KitConfiguration config) {
        this.config = config;
    }

    public void setup() {
        ItemStack diamondAxe = new ItemStack(Material.DIAMOND_AXE);
        diamondAxe.addEnchantment(Enchantment.DAMAGE_ALL, 1);
        ItemMeta meta = diamondAxe.getItemMeta();
        meta.setDisplayName(org.bukkit.ChatColor.WHITE + "" + org.bukkit.ChatColor.BOLD + "Thor's Axe");
        diamondAxe.setItemMeta(meta);
        this.item = diamondAxe;

        ArrayList<Action> list = new ArrayList<>();
        list.add(Action.RIGHT_CLICK_AIR);
        list.add(Action.RIGHT_CLICK_BLOCK);
        this.actions = list;

        KitConfiguration config = new KitConfiguration(true, "AxeStrike");
        HashMap<String, Object> values = new HashMap<>();
        values.put("damage", 5);
        values.put("cooldown", 10);
        config.saveDefaultSettings(values);
        this.config = config;
    }
}
