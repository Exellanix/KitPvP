package me.exellanix.kitpvp.kit_abilities;

import me.exellanix.kitpvp.KitPvP;
import me.exellanix.kitpvp.config.KitConfiguration;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.PacketPlayOutBlockChange;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Mac on 3/6/2016.
 */
public class PyroFire implements Ability {
    private ItemStack item;
    private List<Action> actions;
    private HashMap<Player, Long> cooldown;
    private String name;
    private KitConfiguration config;

    public PyroFire() {
        setup();
        cooldown = new HashMap<>();
        name = "PYROFIRE";
    }

    @Override
    public ItemStack getItem() {
        return item;
    }

    @Override
    public void activateAbility(Player player) {
        if (!cooldown.containsKey(player)) {
            Location location = getStartLocation(player.getLocation().getBlock(), 0);

            if (location != null) {
                KitPvP.getSingleton().plugin.getServer().getScheduler().runTaskLater(KitPvP.getSingleton().plugin, () -> {
                    cooldown.remove(player);
                    if (player.getInventory().contains(item)) {
                        player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "You can now use Fire again!");
                    }
                }, (int) getConfig().getSettings().get("cooldown") * 20);
                ArrayList<ArrayList<Block>> list = setupBlocks(location);
                for (int i = 0; i < 5; i++) {
                    final int index = i;
                    KitPvP.getSingleton().plugin.getServer().getScheduler().runTaskLater(KitPvP.getSingleton().plugin, () -> {
                        for (Block b : list.get(index)) {
                            Block temp = getBlock(b, 0);
                            if (temp != null) {
                                if (!cooldown.containsKey(player)) {
                                    cooldown.put(player, System.currentTimeMillis());
                                }
                                setFire(temp, player);
                                sendPacket(temp, 11, player, 55);
                            }
                        }
                    }, i * 2);
                    KitPvP.getSingleton().plugin.getServer().getScheduler().runTaskLater(KitPvP.getSingleton().plugin, () -> {
                        for (Block b : list.get(index)) {
                            Block temp = getBlock(b, 0);
                            if (temp != null) {
                                setFire(temp, player);
                                sendPacket(temp, 0, player, 0);
                            }
                        }
                    }, (i * 2) + 4);
                }

            } else {
                player.sendMessage("Make sure you are near the ground to activate this!");
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


    private void setup() {
        ItemStack item = new ItemStack(Material.BLAZE_POWDER);
        ItemMeta meta1 = item.getItemMeta();
        meta1.setDisplayName(org.bukkit.ChatColor.DARK_RED + "" + org.bukkit.ChatColor.BOLD + "Fire");
        item.setItemMeta(meta1);
        this.item = item;

        List<Action> list = new ArrayList<>();
        list.add(Action.RIGHT_CLICK_AIR);
        list.add(Action.RIGHT_CLICK_BLOCK);
        this.actions = list;

        KitConfiguration config = new KitConfiguration(true, "PyroFire");
        HashMap<String, Object> values = new HashMap<>();
        values.put("damage", 8);
        values.put("cooldown", 10);
        config.saveDefaultSettings(values);
        this.config = config;
    }

    private Location getStartLocation(Block in, int depth) {
        if (depth > 2) {
            return null;
        }
        if (in.getType() != Material.AIR) {
            Location loc = in.getLocation();
            loc.setY(loc.getY() + 1);
            return getStartLocation(loc.getBlock(), depth + 1);
        }
        Location l = in.getLocation();
        l.setY(l.getY() - 1);
        if (l.getBlock().getType() != Material.AIR) {
            return in.getLocation();
        } else {
            return getStartLocation(l.getBlock(), depth + 1);
        }
    }

    private ArrayList<ArrayList<Block>> setupBlocks(Location start) {
        ArrayList<ArrayList<Block>> list = new ArrayList<>();
        Location block = start.getBlock().getLocation();

        ArrayList<Block> first = new ArrayList<>();
        Location l1 = start.getBlock().getLocation();
        l1.setX(block.getX() - 1);
        first.add(l1.getBlock());

        l1.setX(block.getX() + 1);
        first.add(l1.getBlock());

        l1.setX(block.getX());
        l1.setZ(block.getZ() - 1);
        first.add(l1.getBlock());

        l1.setZ(block.getZ() + 1);
        first.add(l1.getBlock());

        list.add(first);

        ArrayList<Block> second = new ArrayList<>();
        Location l2 = start.getBlock().getLocation();
        l2.setX(block.getX() + 1);
        l2.setZ(block.getZ() + 1);
        second.add(l2.getBlock());

        l2 = start.getBlock().getLocation();
        l2.setX(block.getX() - 1);
        l2.setZ(block.getZ() + 1);
        second.add(l2.getBlock());

        l2 = start.getBlock().getLocation();
        l2.setX(block.getX() + 1);
        l2.setZ(block.getZ() - 1);
        second.add(l2.getBlock());

        l2 = start.getBlock().getLocation();
        l2.setX(block.getX() - 1);
        l2.setZ(block.getZ() - 1);
        second.add(l2.getBlock());

        l2 = start.getBlock().getLocation();
        l2.setX(block.getX() + 2);
        for (int i = -1; i <= 1; i++) {
            l2.setZ(block.getZ() + i);
            second.add(l2.getBlock());
        }

        l2 = start.getBlock().getLocation();
        l2.setX(block.getX() - 2);
        for (int i = -1; i <= 1; i++) {
            l2.setZ(block.getZ() + i);
            second.add(l2.getBlock());
        }

        l2 = start.getBlock().getLocation();
        l2.setZ(block.getZ() + 2);
        for (int i = -1; i <= 1; i++) {
            l2.setX(block.getX() + i);
            second.add(l2.getBlock());
        }

        l2 = start.getBlock().getLocation();
        l2.setZ(block.getZ() - 2);
        for (int i = -1; i <= 1; i++) {
            l2.setX(block.getX() + i);
            second.add(l2.getBlock());
        }

        list.add(second);

        ArrayList<Block> third = new ArrayList<>();

        Location l3 = start.getBlock().getLocation();
        l3.setX(block.getX() + 2);
        l3.setZ(block.getZ() + 2);
        third.add(l3.getBlock());

        l3 = start.getBlock().getLocation();
        l3.setX(block.getX() - 2);
        l3.setZ(block.getZ() + 2);
        third.add(l3.getBlock());

        l3 = start.getBlock().getLocation();
        l3.setX(block.getX() + 2);
        l3.setZ(block.getZ() - 2);
        third.add(l3.getBlock());

        l3 = start.getBlock().getLocation();
        l3.setX(block.getX() - 2);
        l3.setZ(block.getZ() - 2);
        third.add(l3.getBlock());

        l3 = start.getBlock().getLocation();
        l3.setX(block.getX() + 3);
        for (int i = -1; i <= 1; i++) {
            l3.setZ(block.getZ() + i);
            third.add(l3.getBlock());
        }

        l3 = start.getBlock().getLocation();
        l3.setX(block.getX() - 3);
        for (int i = -1; i <= 1; i++) {
            l3.setZ(block.getZ() + i);
            third.add(l3.getBlock());
        }

        l3 = start.getBlock().getLocation();
        l3.setZ(block.getZ() + 3);
        for (int i = -1; i <= 1; i++) {
            l3.setX(block.getX() + i);
            third.add(l3.getBlock());
        }

        l3 = start.getBlock().getLocation();
        l3.setZ(block.getZ() - 3);
        for (int i = -1; i <= 1; i++) {
            l3.setX(block.getX() + i);
            third.add(l3.getBlock());
        }

        list.add(third);

        ArrayList<Block> fourth = new ArrayList<>();
        Location l4 = start.getBlock().getLocation();
        l4.setX(block.getX() + 3);
        l4.setZ(block.getZ() + 3);
        fourth.add(l4.getBlock());

        l4 = start.getBlock().getLocation();
        l4.setX(block.getX() + 3);
        l4.setZ(block.getZ() + 2);
        fourth.add(l4.getBlock());

        l4 = start.getBlock().getLocation();
        l4.setX(block.getX() + 2);
        l4.setZ(block.getZ() + 3);
        fourth.add(l4.getBlock());

        l4 = start.getBlock().getLocation();
        l4.setX(block.getX() - 3);
        l4.setZ(block.getZ() + 3);
        fourth.add(l4.getBlock());

        l4 = start.getBlock().getLocation();
        l4.setX(block.getX() - 3);
        l4.setZ(block.getZ() + 2);
        fourth.add(l4.getBlock());

        l4 = start.getBlock().getLocation();
        l4.setX(block.getX() - 2);
        l4.setZ(block.getZ() + 3);
        fourth.add(l4.getBlock());

        l4 = start.getBlock().getLocation();
        l4.setX(block.getX() + 3);
        l4.setZ(block.getZ() - 3);
        fourth.add(l4.getBlock());

        l4 = start.getBlock().getLocation();
        l4.setX(block.getX() + 3);
        l4.setZ(block.getZ() - 2);
        fourth.add(l4.getBlock());

        l4 = start.getBlock().getLocation();
        l4.setX(block.getX() + 2);
        l4.setZ(block.getZ() - 3);
        fourth.add(l4.getBlock());

        l4 = start.getBlock().getLocation();
        l4.setX(block.getX() - 3);
        l4.setZ(block.getZ() - 3);
        fourth.add(l4.getBlock());

        l4 = start.getBlock().getLocation();
        l4.setX(block.getX() - 3);
        l4.setZ(block.getZ() - 2);
        fourth.add(l4.getBlock());

        l4 = start.getBlock().getLocation();
        l4.setX(block.getX() - 2);
        l4.setZ(block.getZ() - 3);
        fourth.add(l4.getBlock());

        l4 = start.getBlock().getLocation();
        l4.setX(block.getX() + 4);
        for (int i = -2; i <= 2; i++) {
            l4.setZ(block.getZ() + i);
            fourth.add(l4.getBlock());
        }

        l4 = start.getBlock().getLocation();
        l4.setX(block.getX() - 4);
        for (int i = -2; i <= 2; i++) {
            l4.setZ(block.getZ() + i);
            fourth.add(l4.getBlock());
        }

        l4 = start.getBlock().getLocation();
        l4.setZ(block.getZ() + 4);
        for (int i = -2; i <= 2; i++) {
            l4.setX(block.getX() + i);
            fourth.add(l4.getBlock());
        }

        l4 = start.getBlock().getLocation();
        l4.setZ(block.getZ() - 4);
        for (int i = -2; i <= 2; i++) {
            l4.setX(block.getX() + i);
            fourth.add(l4.getBlock());
        }

        list.add(fourth);


        ArrayList<Block> fifth = new ArrayList<>();
        Location l5 = start.getBlock().getLocation();
        l5.setX(block.getX() + 4);
        l5.setZ(block.getZ() + 3);
        fifth.add(l5.getBlock());

        l5 = start.getBlock().getLocation();
        l5.setX(block.getX() + 3);
        l5.setZ(block.getZ() + 4);
        fifth.add(l5.getBlock());

        l5 = start.getBlock().getLocation();
        l5.setX(block.getX() - 4);
        l5.setZ(block.getZ() + 3);
        fifth.add(l5.getBlock());

        l5 = start.getBlock().getLocation();
        l5.setX(block.getX() - 3);
        l5.setZ(block.getZ() + 4);
        fifth.add(l5.getBlock());

        l5 = start.getBlock().getLocation();
        l5.setX(block.getX() + 4);
        l5.setZ(block.getZ() - 3);
        fifth.add(l5.getBlock());

        l5 = start.getBlock().getLocation();
        l5.setX(block.getX() + 3);
        l5.setZ(block.getZ() - 4);
        fifth.add(l5.getBlock());

        l5 = start.getBlock().getLocation();
        l5.setX(block.getX() - 4);
        l5.setZ(block.getZ() - 3);
        fifth.add(l5.getBlock());

        l5 = start.getBlock().getLocation();
        l5.setX(block.getX() - 3);
        l5.setZ(block.getZ() - 4);
        fifth.add(l5.getBlock());

        l5 = start.getBlock().getLocation();
        l5.setX(block.getX() + 5);
        for (int i = -2; i <= 2; i++) {
            l5.setZ(block.getZ() + i);
            fifth.add(l5.getBlock());
        }

        l5 = start.getBlock().getLocation();
        l5.setX(block.getX() - 5);
        for (int i = -2; i <= 2; i++) {
            l5.setZ(block.getZ() + i);
            fifth.add(l5.getBlock());
        }

        l5 = start.getBlock().getLocation();
        l5.setZ(block.getZ() + 5);
        for (int i = -2; i <= 2; i++) {
            l5.setX(block.getX() + i);
            fifth.add(l5.getBlock());
        }

        l5 = start.getBlock().getLocation();
        l5.setZ(block.getZ() - 5);
        for (int i = -2; i <= 2; i++) {
            l5.setX(block.getX() + i);
            fifth.add(l5.getBlock());
        }

        list.add(fifth);

        return list;
    }

    private Block getBlock(Block in, int depth) {
        if (depth > 2) {
            return null;
        }
        if (in.getType() != Material.AIR && in.getType() != Material.FIRE) {
            Location loc = in.getLocation();
            loc.setY(loc.getY() + 1);
            return getBlock(loc.getBlock(), depth + 1);
        }
        Location loc = in.getLocation();
        loc.setY(loc.getY() - 1);
        if (loc.getBlock().getType() != Material.AIR && loc.getBlock().getType() != Material.FIRE) {
            return in;
        } else {
            return getBlock(loc.getBlock(), depth + 1);
        }
    }

    private void sendPacket(Block block, int id, Player player, int alternateId) {
        BlockPosition position = new BlockPosition(block.getX(), block.getY(), block.getZ());
        PacketPlayOutBlockChange blockChange1 = new PacketPlayOutBlockChange(((CraftWorld)block.getWorld()).getHandle(), position);
        blockChange1.block = net.minecraft.server.v1_8_R3.Block.getByCombinedId(id);

        PacketPlayOutBlockChange blockChange2 = new PacketPlayOutBlockChange(((CraftWorld)block.getWorld()).getHandle(), position);
        blockChange2.block = net.minecraft.server.v1_8_R3.Block.getByCombinedId(alternateId);

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getWorld().equals(block.getWorld())) {
                if (p.equals(player)) {
                    ((CraftPlayer) p).getHandle().playerConnection.sendPacket(blockChange2);
                } else {
                    ((CraftPlayer) p).getHandle().playerConnection.sendPacket(blockChange1);
                }
            }
        }
    }

    private void setFire(Block b, Player player) {
        for (Entity p : player.getNearbyEntities(20, 20, 20)) {
            if (p instanceof Player && !p.equals(player)) {
                if (p.getLocation().getBlock().equals(b)) {
                    p.setFireTicks((int) getConfig().getSettings().get("damage") * 10);
                }
            }
        }
    }
}
