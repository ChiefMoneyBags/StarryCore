package me.chiefbeef.core.utility.netserver;

import org.bukkit.entity.Player;

import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.minecraft.server.v1_15_R1.PacketPlayOutUpdateHealth;

/**
 * lmao bad
 * @author Kevin
 *
 */
public class Aaaaaaaaaa {

	public Aaaaaaaaaa() {
		// TODO Auto-generated constructor stub
	}
	public static class PacketListener {
		
		public void remove(Player player) {
			Channel channel = channel(player);
			channel.eventLoop().submit(() -> {
				channel.pipeline().remove(player.getPlayer().getName());
				return null;
			});
		}
		
		public void inject(Player player) {
			ChannelDuplexHandler duplex = new ChannelDuplexHandler() {
				
				@Override
				public void write(ChannelHandlerContext channelHandlerContext, Object packet, ChannelPromise channelPromise) throws Exception {
					
					if (packet instanceof PacketPlayOutUpdateHealth) {
						/**
						 * Cancel sending packet by returning here
						 */ 
					}
					
					super.write(channelHandlerContext, packet, channelPromise);
				}
				
			};
			channel(player).pipeline().addBefore("packet_handler", player.getName(), duplex);
		}
		
		public static Object craftPlayer(Player player) {
			try {
				return player.getClass().getMethod("getHandle").invoke(player);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		public static Object connection(Player player) {
			return field(craftPlayer(player), "playerConnection");
		}
		
		public static Channel channel(Player player) {
			try {
				return (Channel)Reflect.field(Reflect.field(connection(player), "networkManager"), "channel");
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		public static Object field(Object instance, String field) {
			try {
				return instance.getClass().getField(field).get(instance);
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
}
