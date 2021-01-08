package me.chiefbeef.core.event.channel;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.chiefbeef.core.user.UserCore;

public class PacketPlayInEvent extends Event implements Cancellable {

	private final static HandlerList handlers = new HandlerList();
	
	private final Object packet;
	private final UserCore user;
	private boolean cancelled;
	
	public PacketPlayInEvent(final UserCore user, final Object packet) {
		this.user = user;
		this.packet = packet;
	}
	
	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancelled = cancel;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
	
	public Object getPacket() {
		return packet;
	}

	public UserCore getUser() {
		return user;
	}
}