package me.exellanix.kitpvp.event.custom;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

/**
 * Created by Brendan on 5/19/2016.
 */
public class KitPvPDeathEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    private boolean cancelled;

    private final Player victim;

    private final Player killer;

    private boolean tagged;

    public KitPvPDeathEvent(Player victim, Player killer) {
        super(victim != null ? victim : killer);
        this.victim = victim;
        this.killer = killer;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Player getVictim() {
        return victim;
    }

    public Player getKiller() {
        return killer;
    }
}
