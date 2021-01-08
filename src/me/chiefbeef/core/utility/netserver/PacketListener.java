package me.chiefbeef.core.utility.netserver;

import org.bukkit.Bukkit;

import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import me.chiefbeef.core.StarryCore;
import me.chiefbeef.core.event.channel.PacketPlayInEvent;
import me.chiefbeef.core.event.channel.PacketPlayOutEvent;
import me.chiefbeef.core.user.UserCore;

public class PacketListener {
	
	public static void remove(final UserCore user) {
		Channel channel = Packet.channel(user);
		channel.eventLoop().submit(() -> {
			channel.pipeline().remove(user.getPlayer().getName());
			return null;
		});
	}
	
	public static void inject(final UserCore user) {
		ChannelDuplexHandler duplex = new ChannelDuplexHandler() {
			
			@Override
			public void channelRead(ChannelHandlerContext channelHandlerContext, Object packet) throws Exception {
				PacketPlayInEvent e = new PacketPlayInEvent(user, packet);
				try {
					Bukkit.getScheduler().runTask(StarryCore.getInstance(), () -> Bukkit.getPluginManager().callEvent(e));
					if (e.isCancelled())
						return;
				} catch (IllegalStateException e1) {}
				super.channelRead(channelHandlerContext, packet);
			}
			
			@Override
			public void write(ChannelHandlerContext channelHandlerContext, Object packet, ChannelPromise channelPromise) throws Exception {
				PacketPlayOutEvent e = new PacketPlayOutEvent(user, packet);
				try {
					Bukkit.getScheduler().runTask(StarryCore.getInstance(), () -> Bukkit.getPluginManager().callEvent(e));
					if (e.isCancelled())
						return;
				} catch (IllegalStateException e1) {}
				super.write(channelHandlerContext, packet, channelPromise);
			}
		};
		Packet.channel(user).pipeline().addBefore("packet_handler", user.getPlayer().getName(), duplex);
	}
	
	
	
}
