package me.exellanix.idk.kit_abilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mac on 3/6/2016.
 */
public class SoupRegen implements Ability {
    private ItemStack itemStack;
    private List<Action> actions;
    private String name;

    public SoupRegen() {
        setup();
        name = "SOUPREGEN";
    }

    @Override
    public ItemStack getItem() {
        return itemStack;
    }

    @Override
    public void activateAbility(Player player) {
        if (player.getHealth() < 20) {

            if (player.getHealth() + 6 > 20) {
                player.setHealth((20 - player.getHealth()) + player.getHealth());
            } else {
                player.setHealth(player.getHealth() + 6);
            }

            player.getItemInHand().setType(Material.BOWL);
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

    private void setup() {
        itemStack = new ItemStack(Material.MUSHROOM_SOUP);

        List<Action> list = new ArrayList<>();
        list.add(Action.RIGHT_CLICK_BLOCK);
        list.add(Action.RIGHT_CLICK_AIR);
        actions = list;
    }
}
