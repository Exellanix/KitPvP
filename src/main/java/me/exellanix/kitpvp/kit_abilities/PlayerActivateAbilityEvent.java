package me.exellanix.kitpvp.kit_abilities;

import me.exellanix.kitpvp.KitPvP;
import me.exellanix.kitpvp.kits.Kit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

/**
 * Created by Mac on 3/7/2016.
 */
public class PlayerActivateAbilityEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;
    private Ability ability;

    public PlayerActivateAbilityEvent(Player player, Ability ability) {
        super(player);
        this.ability = ability;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        cancelled = b;
    }

    public Ability getAbility() {
        return ability;
    }

    public Kit getKit() {
        return KitPvP.getPlayerKits().get(getPlayer());
    }
}
