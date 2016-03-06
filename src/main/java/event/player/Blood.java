package event.player;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.exellanix.idk.Main;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;

public class Blood implements Listener {

	@EventHandler
	public void onEntityDamageEvent(EntityDamageByEntityEvent e) {
		final Entity entity = e.getEntity();

		for (Player p : Bukkit.getOnlinePlayers()) {
			((CraftPlayer) p).getHandle().playerConnection.sendPacket(getBloodBody(entity));

		}
		
		final float x = (float) entity.getLocation().getX();
		final float y = (float) entity.getLocation().getY();
		final float z = (float) entity.getLocation().getZ();
		
		for (int i = 0; i < 10; i++) {

			Main.plugin.getServer().getScheduler().runTaskLater(Main.plugin, new Runnable() {

				public void run() {
					for (Player p : Bukkit.getOnlinePlayers()) {
						((CraftPlayer) p).getHandle().playerConnection.sendPacket(getBloodGround(x,y,z));
					}
				}
			}, i * 2);

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
