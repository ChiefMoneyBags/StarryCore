package me.chiefbeef.core.aesthetic.sound.elements;

import org.bukkit.scheduler.BukkitRunnable;

import me.chiefbeef.core.user.UserCore;

public class TrackPlayer extends BukkitRunnable {

	
	private Track track;
	private int beat;
	private String
	theme,
	variant;
	
	
	public TrackPlayer(UserCore u) {
		
	}

	public TrackPlayer setTrack(Track track) {
		this.track = track;
		return this;
	}
	
	public void setBeat(int beat) {
		this.beat = beat;
	}
	
	public int getBeat() {
		return beat;
	}
	
	public void setTheme(String theme) {
		
	}
	
	public void setVariant(String variant) {
		
	}
	
	@Override
	public void run() {
		
		
	}

}
