package me.chiefbeef.core.aesthetic.particle;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;

public class Particles {
	
	/**
	 * Spawn particles at a for a world.
	 */
	public static void play(Particle particle, Location loc, int amount) {
		if (particle == null) particle = Particle.VILLAGER_HAPPY;
		loc.getWorld().spawnParticle(particle, loc, amount);
	}
	
	/**
	 * Spawn particles for a player
	 */
	public static void play(Particle particle, Location loc, int amount, Player p) {
		if (particle == null) particle = Particle.VILLAGER_HAPPY;
		p.spawnParticle(particle, loc, amount);
		
	}
	
	/**
	 * Spawn particles that require data for a world
	 */
	public static void play(Particle particle, Location loc, int amount, BlockData data) {
		if (particle == null) particle = Particle.VILLAGER_HAPPY;
		loc.getWorld().spawnParticle(Particle.BLOCK_CRACK, loc, amount, data);
	}
	
	/**
	 * Spawn particles that require data for a player
	 */
	public static void play(Particle particle, Location loc, int amount, BlockData data, Player p) {
		if (particle == null) particle = Particle.VILLAGER_HAPPY;
		p.spawnParticle(particle, loc, amount, data);
		
	}
	
	/**
	 * <p>
	 * This embedded enumeration contains different pattern sizes for easy implementation by
	 * other plugins without needing to understand any mathematics.
	 * Recommended size - MEDIUM
	 * </p>
	 * 
	 * @author MoneyBags
	 */
	public static enum PatternSize {
		HUGE,
		LARGE,
		MEDIUM,
		SMALL,
		MICRO;
	}
	
	/**
	 * <p>
	 * This embedded enumeration contains different preset particle densities for easy implementation by
	 * other plugins without needing to understand any mathematics.
	 * Recommended density - MEDIUM
	 * </p>
	 * 
	 * @author MoneyBags
	 */
	public static enum ParticleDensity {
		OVER_9000,
		HIGH,
		MEDIUM,
		LOW,
		MINIMAL;
	}
}
