package me.chiefbeef.core.utility.netserver;

import io.netty.channel.Channel;
import me.chiefbeef.core.user.UserCore;

public class Packet extends Reflect {

	public static void sendPacket(UserCore u, Object packet, String name) {
		try {
			Object connection = connection(u);
			connection.getClass().getMethod("sendPacket", nmsClass("Packet")).invoke(connection, packet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Object connection(UserCore u) {
		return Reflect.field(craftPlayer(u), "playerConnection");
	}
	
	public static Channel channel(UserCore u) {
		try {
			return (Channel)Reflect.field(Reflect.field(connection(u), "networkManager"), "channel");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
