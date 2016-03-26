package me.exellanix.kitpvp.Util;

import me.exellanix.kitpvp.KitPvP;
import net.minecraft.server.v1_8_R3.*;

/**
 * Created by Mac on 3/19/2016.
 */
public class CustPlayerConnection extends PlayerConnection {
    public CustPlayerConnection(MinecraftServer minecraftserver, NetworkManager networkmanager, EntityPlayer entityplayer) {
        super(minecraftserver, networkmanager, entityplayer);
        KitPvP.getSingleton().getServer().getScheduler().runTaskTimerAsynchronously(KitPvP.getSingleton(), () -> {
            PacketPlayInKeepAlive in = new PacketPlayInKeepAlive();
            a(in);
        }, 0, 20);
    }

    @Override
    public void sendPacket(Packet packet) {

    }

    @Override
    public void a(PacketPlayInKeepAlive packet) {
        super.a(packet);
    }

    public static void swapConnection(EntityPlayer player) {
        player.playerConnection = new CustPlayerConnection(player.server, new NetworkManager(EnumProtocolDirection.SERVERBOUND), player);
    }
}
