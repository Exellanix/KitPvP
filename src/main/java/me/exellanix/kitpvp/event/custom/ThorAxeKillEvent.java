package me.exellanix.kitpvp.event.custom;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

/**
 * Created by Brendan on 5/25/2016.
 */
public class ThorAxeKillEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private boolean cancelled;

    private final Player victim;
    private final Player user;

    private String deathMessage;

    int damage;

    private boolean tagged;

    public ThorAxeKillEvent(Player victim, Player user, String deathMessage, int damage) {
        super(victim != null ? victim : user);
        this.victim = victim;
        this.user = user;
        this.damage = damage;
        this.deathMessage = deathMessage;
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

    public Player getUser() {
        return user;
    }

    public int getDamage() {
        return damage;
    }

    public String getDeathMessage() {
        return deathMessage;
    }

    public void setDeathMessage(String deathMessage) {
        this.deathMessage = deathMessage;
    }
}
