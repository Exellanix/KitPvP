package me.exellanix.kitpvp.event.player;


import me.exellanix.kitpvp.KitPvP;
import me.exellanix.kitpvp.Util.AlterItem;
import me.exellanix.kitpvp.kits.Kit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Brendan on 4/15/2016.
 */
public class LastKit implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack prevKit = AlterItem.nameItem(Material.NETHER_STAR, ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Play Previous Kit");
        if(AlterItem.itemsEqual(event.getItem(), prevKit)) {
            Kit k = KitPvP.getSingleton().getPlayerPrevKit().get(player);
            if(k != null) {
                k.equipKit(player);
                KitPvP.getSingleton().getPlayerKits().put(player, k);
            }
        }
    }

}
