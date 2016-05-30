package me.exellanix.kitpvp.event.player;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.exellanix.kitpvp.KitPvP;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;

public class Blood implements Listener {

	@EventHandler(priority = EventPriority.MONITOR)
	public void onEntityDamageEvent(EntityDamageByEntityEvent e) {
            if (!e.isCancelled()) {
                final Entity entity = e.getEntity();

                for (Player p : Bukkit.getOnlinePlayers()) {
                    ((CraftPlayer) p).getHandle().playerConnection.sendPacket(getBloodBody(entity));

                }

                final float x = (float) entity.getLocation().getX();
                final float y = (float) entity.getLocation().getY();
                final float z = (float) entity.getLocation().getZ();

                for (int i = 0; i < 10; i++) {

                    KitPvP.getSingleton().plugin.getServer().getScheduler().runTaskLater(KitPvP.getSingleton().plugin, new Runnable() {

                        public void run() {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (!KitPvP.getSingleton().blood.contains(p)) {
                                    ((CraftPlayer) p).getHandle().playerConnection.sendPacket(getBloodGround(x, y, z));
                                }else{
                                    if(p == e.getEntity()) {
                                        return;
                                    }else{
                                        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(getBloodGround(x, y, z));
                                    }
                                }
                            }
                        }
                    }, i * 2);

                }
            }
    }

	public PacketPlayOutWorldParticles getBloodBody(Entity entity) {

		return new PacketPlayOutWorldParticles(EnumParticle.BLOCK_DUST, false, (float) entity.getLocation().getX(),
				(float) entity.getLocation().getY() + 1, (float) entity.getLocation().getZ(), (float) .1, (float) .5,
				(float) .1, (float) .05, 40, 152);

	}

	public PacketPlayOutWorldParticles getBloodGround(float x,float y,float z) {

		return new PacketPlayOutWorldParticles(EnumParticle.BLOCK_DUST, false, x,y,z, (float) .3, (float) 0,
				(float) .3, (float) 0, 20, 152);

	}
}
