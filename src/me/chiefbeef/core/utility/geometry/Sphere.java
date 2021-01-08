package me.chiefbeef.core.utility.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;


public final class Sphere {
	
	private final static Map<Integer, Sphere> cache = new HashMap<>();
	
	/**
	 * It is best to pre generate sphere sizes you need on startup so they will be ready during gameplay.
	 * Large sizes will cause lag if generated during gameplay.
	 */
	public static void preGenerate(int radiusMin, int radiusMax) {
		for (int i = radiusMin; radiusMin <= radiusMax; radiusMin++) {
			if (!cache.containsKey(i)) {
				new Sphere(null, i);
			}
		}
	}
	
	public static void preGenerate(int... sizes) {
		for (int i: sizes) {
			if (!cache.containsKey(i)) {
				new Sphere(null, i);
			}
		}
	}

	
	
	/**
	 * Instance
	 */
	
	private final int radius;
	
	private List<Location>
	locations = new LinkedList<>(),
	perimeter = new LinkedList<>();
	
	private List<Block>
	locationsActual,
	perimeterActual;
	
	private int
	offsetX,
	offsetY,
	offsetZ;
	
	private World world;
	
	/**
	 * Private constructor for template creation.
	 */
	private Sphere(List<Location> locations, List<Location> perimeter, int radius) {
		this.locations = locations;
		this.perimeter = perimeter;
		this.radius = radius;
	}
	
	/**
	 * Used to apply an offset to copies of a template sphere. 
	 */
	public void setCenter(Location center) {
		this.locationsActual = null;
		this.perimeterActual = null;
		this.offsetX = center.getBlockX();
		this.offsetY = center.getBlockY();
		this.offsetZ = center.getBlockZ();
		this.world = center.getWorld();
	}
	
	/**
	 * Initial method to generate a template sphere at the center of the world.
	 */
	private void generate() {
		final Location center = new Location(Bukkit.getWorlds().get(0), 0.5, 0.5, 0.5);
		final Set<Block> temp = new HashSet<>();
		for (int x = -radius; x <= radius; x++) {
			for (int y = -radius; y <= radius; y++) {
				for (int z = -radius; z <= radius; z++) {
					Location loc = center.clone().add(x, y, z);
					if (loc.distance(center) <= radius) {
						locations.add(loc);
						temp.add(loc.getBlock());
					}
				}
			}
		}
		
		final List<BlockFace> faces = Arrays.asList(BlockFace.UP, BlockFace.DOWN, BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST);
		blocks:
		for (Location loc: locations) {
			for (BlockFace face: faces) {
				if (!temp.contains(loc.getBlock().getRelative(face))) {
					perimeter.add(loc);
					continue blocks;
				}
			}
		}
		this.createTemplate();
	}
	
	/**
	 * Create a template for this sphere size.
	 */
	private void createTemplate() {
		cache.put(radius, new Sphere(locations, perimeter, radius));
	}
	
	
	/**
	 * public constructor.
	 */
	public Sphere(Location center, int radius) {
		this.radius = radius;
		if (!cache.containsKey(radius)) {
			this.generate();
		}
		if (center == null) {
			return;
		}
		Sphere cached = cache.get(radius);
		this.locations = cached.locations;
		this.perimeter = cached.perimeter;
		this.setCenter(center);
	}
	
	/**
	 * Get all blocks of a solid sphere.
	 * @return All blocks that this sphere is composed of.
	 */
	public List<Block> getBlocks() {
		if (locationsActual == null) {
			locationsActual = new ArrayList<>();
			for (Location loc: locations) {
				locationsActual.add(world.getBlockAt(loc.getBlockX() + offsetX, loc.getBlockY() + offsetY, loc.getBlockZ() + offsetZ));
			}
		}
		return locationsActual;
	}
	
	/**
	 * Get all perimeter blocks of a hollow sphere.
	 * @return All blocks that make up the outer perimeter of this sphere.
	 */
	public List<Block> getPerimeterBlocks() {
		if (perimeterActual == null) {
			perimeterActual = new ArrayList<>();
			for (Location loc: perimeter) {
				perimeterActual.add(world.getBlockAt(loc.getBlockX() + offsetX, loc.getBlockY() + offsetY, loc.getBlockZ() + offsetZ));
			}
		}
		return perimeterActual;
	}
}