package me.chiefbeef.core.compatibility;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;

/**
 * The enum constants in CompatBiome are based on the 1.12.2 Biome enum.
 * Forwards compatibility will be required in the future.
 * @author ChiefMoneyBags
 */
public enum CompatBiome {

	BAMBOO_JUNGLE("null", "null", "BAMBOO_JUNGLE",
			Material.JUNGLE_LOG,	(short)3,
			BiomeProperty.JUNGLE, BiomeProperty.MODERATE),
	
	BAMBOO_JUNGLE_HILLS("null", "null", "BAMBOO_JUNGLE_HILLS",
			Material.JUNGLE_LOG, 	(short)3,
			BiomeProperty.JUNGLE, BiomeProperty.MODERATE, BiomeProperty.HILLS),
	
	BEACHES("BEACH", "BEACHES", "BEACH",
			Material.SAND, 			(short)0,
			BiomeProperty.BEACH, BiomeProperty.MODERATE),
	
	BIRCH_FOREST("BIRCH_FOREST", "BIRCH_FOREST", "BIRCH_FOREST",
			Material.BIRCH_LOG, 	(short)2,
			BiomeProperty.FOREST, BiomeProperty.MODERATE),
	
	BIRCH_FOREST_HILLS("BIRCH_FOREST_HILLS", "BIRCH_FOREST_HILLS", "BIRCH_FOREST_HILLS",
			Material.BIRCH_LOG, 	(short)2,
			BiomeProperty.FOREST, BiomeProperty.HILLS, BiomeProperty.MODERATE),
	
	COLD_BEACH("COLD_BEACH", "COLD_BEACH", "SNOWY_BEACH", 
			Material.SAND, 			(short)0,
			BiomeProperty.BEACH, BiomeProperty.FREEZING),
	
	COLD_OCEAN("null", "null", "COLD_OCEAN",
			Material.WATER_BUCKET, 	(short)0,
			BiomeProperty.WATER, BiomeProperty.COLD, BiomeProperty.OCEAN),
	
	DEEP_OCEAN("DEEP_OCEAN", "DEEP_OCEAN", "DEEP_OCEAN", 
			Material.WATER_BUCKET, 	(short)0,
			BiomeProperty.WATER, BiomeProperty.NEUTRAL, BiomeProperty.OCEAN),
	
	DEEP_COLD_OCEAN("null", "null", "DEEP_COLD_OCEAN",
			Material.WATER_BUCKET, 	(short)0,
			BiomeProperty.WATER, BiomeProperty.COLD, BiomeProperty.OCEAN),
	
	DEEP_FROZEN_OCEAN("null", "null", "DEEP_FROZEN_OCEAN",
			Material.WATER_BUCKET, 	(short)0,
			BiomeProperty.WATER, BiomeProperty.FREEZING, BiomeProperty.OCEAN),
	
	DEEP_LUKEWARM_OCEAN("null", "null", "DEEP_LUKEWARM_OCEAN",
			Material.WATER_BUCKET, 	(short)0,
			BiomeProperty.WATER, BiomeProperty.MODERATE, BiomeProperty.OCEAN),
	
	DEEP_WARM_OCEAN("null", "null", "DEEP_WARM_OCEAN",
			Material.WATER_BUCKET, 	(short)0,
			BiomeProperty.WATER, BiomeProperty.MODERATE, BiomeProperty.OCEAN),
	
	DESERT("DESERT", "DESERT", "DESERT",
			Material.SANDSTONE, 	(short)0,
			BiomeProperty.DESERT, BiomeProperty.HOT),
	
	DESERT_HILLS("DESERT_HILLS", "DESERT_HILLS", "DESERT_HILLS",
			Material.SANDSTONE, 	(short)0,
			BiomeProperty.DESERT, BiomeProperty.HOT, BiomeProperty.HILLS, BiomeProperty.HOT),
	
	END_BARRENS("null", "null", "END_BARRENS",
			Material.BLACK_WOOL, 	(short)15,
			BiomeProperty.END, BiomeProperty.NEUTRAL),
	
	END_HIGHLANDS("null", "null", "END_HIGHLANDS",
			Material.BLACK_WOOL, 	(short)15,
			BiomeProperty.END, BiomeProperty.NEUTRAL),
	
	END_MIDLANDS("null", "null", "END_MIDLANDS",
			Material.BLACK_WOOL, 	(short)15,
			BiomeProperty.END, BiomeProperty.NEUTRAL),
	
	EXTREME_HILLS("EXTREME_HILLS", "EXTREME_HILLS", "MOUNTAINS",
			Material.STONE, 		(short)0,
			BiomeProperty.HILLS, BiomeProperty.COLD),
	
	EXTREME_HILLS_WITH_TREES("EXTREME_HILLS_PLUS", "EXTREME_HILLS_WITH_TREES", "WOODED_MOUNTAINS",
			Material.STONE, 		(short)0,
			BiomeProperty.HILLS, BiomeProperty.COLD),
	
	FOREST("FOREST", "FOREST", "FOREST",
			Material.OAK_LOG,		(short)0,
			BiomeProperty.FOREST, BiomeProperty.MODERATE),
	
	FOREST_HILLS("FOREST_HILLS", "FOREST_HILLS", "WOODED_HILLS",
			Material.OAK_LOG, 		(short)0,
			BiomeProperty.FOREST, BiomeProperty.HILLS, BiomeProperty.MODERATE),
	
	FROZEN_OCEAN("FROZEN_OCEAN", "FROZEN_OCEAN", "FROZEN_OCEAN",
			Material.ICE, 			(short)0,
			BiomeProperty.WATER, BiomeProperty.FREEZING, BiomeProperty.OCEAN),
	
	FROZEN_RIVER("FROZEN_RIVER", "FROZEN_RIVER", "FROZEN_RIVER",
			Material.ICE, 			(short)0,
			BiomeProperty.WATER, BiomeProperty.FREEZING),
	
	HELL("HELL", "HELL", "NETHER",
			Material.NETHERRACK, 	(short)0,
			BiomeProperty.SPECIAL, BiomeProperty.HOT),
	
	ICE_FLATS("ICE_PLAINS", "ICE_FLATS", "SNOWY_TUNDRA",
			Material.ICE, 			(short)0,
			BiomeProperty.PLAINS, BiomeProperty.FREEZING),
	
	ICE_MOUNTAINS("ICE_MOUNTAINS", "ICE_MOUNTAINS", "SNOWY_MOUNTAINS",
			Material.ICE, 			(short)0,
			BiomeProperty.MOUNTAINS, BiomeProperty.FREEZING),
	
	JUNGLE("JUNGLE", "JUNGLE", "JUNGLE",
			Material.JUNGLE_LOG, 	(short)3,
			BiomeProperty.JUNGLE, BiomeProperty.MODERATE),
	
	JUNGLE_EDGE("JUNGLE_EDGE", "JUNGLE_EDGE", "JUNGLE_EDGE",
			Material.JUNGLE_LOG,	(short)3,
			BiomeProperty.JUNGLE, BiomeProperty.MODERATE),
	
	JUNGLE_HILLS("JUNGLE_HILLS", "JUNGLE_HILLS", "JUNGLE_HILLS",
			Material.JUNGLE_LOG, 	(short)3,
			BiomeProperty.JUNGLE, BiomeProperty.HILLS, BiomeProperty.MODERATE),
	
	LUKEWARM_OCEAN("null", "null", "LUKEWARM_OCEAN",
			Material.WATER_BUCKET, 	(short)0,
			BiomeProperty.WATER, BiomeProperty.OCEAN, BiomeProperty.MODERATE),
	
	MESA("MESA", "MESA", "BADLANDS",
			Material.CLAY, 			(short)0,
			BiomeProperty.MESA, BiomeProperty.HOT),
	
	MESA_CLEAR_ROCK("MESA_PLATEAU_FOREST", "MESA_CLEAR_ROCK", "BADLANDS_PLATEAU",
			Material.CLAY, 			(short)0,
			BiomeProperty.MESA, BiomeProperty.FOREST, BiomeProperty.HOT),
	
	MESA_ROCK("MESA_PLATEAU", "MESA_ROCK", "WOODED_BADLANDS_PLATEAU",
			Material.CLAY, 			(short)0,
			BiomeProperty.MESA, BiomeProperty.HILLS, BiomeProperty.HOT),
	
	MUSHROOM_ISLAND("MUSHROOM_ISLAND", "MUSHROOM_ISLAND", "MUSHROOM_FIELDS",
			Material.MYCELIUM, 		(short)0,
			BiomeProperty.MUSHROOM, BiomeProperty.MODERATE),
	
	MUSHROOM_ISLAND_SHORE("MUSHROOM_SHORE", "MUSHROOM_ISLAND_SHORE", "MUSHROOM_FIELD_SHORE",
			Material.MYCELIUM, 		(short)0,
			BiomeProperty.MUSHROOM, BiomeProperty.MODERATE),
	
	MUTATED_BIRCH_FOREST("BIRCH_FOREST_MOUNTAINS", "MUTATED_BIRCH_FOREST", "TALL_BIRCH_FOREST",
			Material.BIRCH_LOG, 	(short)2,
			BiomeProperty.FOREST, BiomeProperty.MOUNTAINS, BiomeProperty.MODERATE),
	
	MUTATED_BIRCH_FOREST_HILLS("BIRCH_FOREST_HILLS_MOUNTAINS", "MUTATED_BIRCH_FOREST_HILLS", "TALL_BIRCH_HILLS",
			Material.BIRCH_LOG, 	(short)2,
			BiomeProperty.FOREST, BiomeProperty.HILLS, BiomeProperty.MOUNTAINS, BiomeProperty.MODERATE),
	
	MUTATED_DESERT("DESERT_MOUNTAINS", "MUTATED_DESERT", "DESERT_LAKES",
			Material.SAND, 			(short)0,
			BiomeProperty.MOUNTAINS, BiomeProperty.DESERT, BiomeProperty.HOT),
	
	MUTATED_EXTREME_HILLS("EXTREME_HILLS_MOUNTAINS", "MUTATED_EXTREME_HILLS", "GRAVELLY_MOUNTAINS",
			Material.STONE, 		(short)0,
			BiomeProperty.HILLS, BiomeProperty.MOUNTAINS, BiomeProperty.COLD),
	
	MUTATED_EXTREME_HILLS_WITH_TREES("EXTREME_HILLS_PLUS_MOUNTAINS", "MUTATED_EXTREME_HILLS_WITH_TREES", "MODIFIED_GRAVELLY_MOUNTAINS",
			Material.STONE, 		(short)0,
			BiomeProperty.HILLS, BiomeProperty.MOUNTAINS, BiomeProperty.COLD),
	
	MUTATED_FOREST("FLOWER_FOREST", "MUTATED_FOREST", "FLOWER_FOREST",
			Material.SUNFLOWER, 	(short)0,
			BiomeProperty.PLAINS, BiomeProperty.FOREST, BiomeProperty.MODERATE),
	
	MUTATED_ICE_FLATS("ICE_PLAINS_SPIKES", "MUTATED_ICE_FLATS", "ICE_SPIKES",
			Material.PACKED_ICE, 	(short)0,
			BiomeProperty.PLAINS, BiomeProperty.FREEZING),
	
	MUTATED_JUNGLE("JUNGLE_MOUNTAINS", "MUTATED_JUNGLE", "MODIFIED_JUNGLE",
			Material.JUNGLE_LOG, 	(short)3,
			BiomeProperty.JUNGLE, BiomeProperty.MOUNTAINS, BiomeProperty.MODERATE),
	
	MUTATED_JUNGLE_EDGE("JUNGLE_EDGE_MOUNTAINS", "MUTATED_JUNGLE_EDGE", "MODIFIED_JUNGLE_EDGE",
			Material.JUNGLE_LOG, 	(short)3,
			BiomeProperty.JUNGLE, BiomeProperty.MOUNTAINS, BiomeProperty.MODERATE),
	
	MUTATED_MESA("MESA_BRYCE", "MUTATED_MESA", "ERODED_BADLANDS",
			Material.CLAY, 			(short)0,
			BiomeProperty.MESA, BiomeProperty.MOUNTAINS, BiomeProperty.HOT),
	
	MUTATED_MESA_CLEAR_ROCK("MESA_PLATEAU_FOREST_MOUNTAINS", "MUTATED_MESA_CLEAR_ROCK", "MODIFIED_BADLANDS_PLATEAU",
			Material.CLAY, 			(short)0,
			BiomeProperty.MESA, BiomeProperty.MOUNTAINS, BiomeProperty.HOT),
	
	MUTATED_MESA_ROCK("MESA_PLATEAU_MOUNTAINS", "MUTATED_MESA_ROCK", "MODIFIED_WOODED_BADLANDS_PLATEAU",
			Material.CLAY, 			(short)0,
			BiomeProperty.MESA, BiomeProperty.MOUNTAINS, BiomeProperty.HOT),
	
	MUTATED_PLAINS("SUNFLOWER_PLAINS", "MUTATED_PLAINS", "SUNFLOWER_PLAINS",
			Material.SUNFLOWER, 	(short)0,
			BiomeProperty.PLAINS, BiomeProperty.MODERATE),
	
	MUTATED_REDWOOD_TAIGA("MEGA_TAIGA", "MUTATED_REDWOOD_TAIGA", "GIANT_SPRUCE_TAIGA",
			Material.SNOW_BLOCK, 	(short)0,
			BiomeProperty.TAIGA, BiomeProperty.COLD),
	
	MUTATED_REDWOOD_TAIGA_HILLS("MEGA_TAIGA_HILLS", "MUTATED_REDWOOD_TAIGA_HILLS", "GIANT_SPRUCE_TAIGA_HILLS",
			Material.SNOW_BLOCK, 	(short)0,
			BiomeProperty.TAIGA, BiomeProperty.HILLS, BiomeProperty.COLD),
	
	MUTATED_ROOFED_FOREST("ROOFED_FOREST_MOUNTAINS", "MUTATED_ROOFED_FOREST", "DARK_FOREST_HILLS",
			Material.OAK_LOG, 		(short)0,
			BiomeProperty.FOREST, BiomeProperty.MOUNTAINS, BiomeProperty.MODERATE),
	
	MUTATED_SAVANNA("SAVANNA_MOUNTAINS", "MUTATED_SAVANNA", "SHATTERED_SAVANNA",
			Material.ACACIA_LOG, 	(short)0,
			BiomeProperty.SAVANNAH, BiomeProperty.MOUNTAINS, BiomeProperty.HOT),
	
	MUTATED_SAVANNA_ROCK("SAVANNA_PLATEAU_MOUNTAINS", "MUTATED_SAVANNA_ROCK", "SHATTERED_SAVANNA_PLATEAU",
			Material.ACACIA_LOG, 	(short)0,
			BiomeProperty.SAVANNAH, BiomeProperty.MOUNTAINS, BiomeProperty.HOT),
	
	MUTATED_SWAMPLAND("SWAMPLAND_MOUNTAINS", "MUTATED_SWAMPLAND", "SWAMP_HILLS",
			Material.SLIME_BLOCK, 	(short)0,
			BiomeProperty.SWAMPLAND, BiomeProperty.MOUNTAINS, BiomeProperty.HOT),
	
	MUTATED_TAIGA("TAIGA_MOUNTAINS", "MUTATED_TAIGA", "TAIGA_MOUNTAINS",
			Material.SNOW_BLOCK, 	(short)0,
			BiomeProperty.TAIGA, BiomeProperty.MOUNTAINS, BiomeProperty.COLD),
	
	MUTATED_TAIGA_COLD("COLD_TAIGA_MOUNTAINS", "MUTATED_TAIGA_COLD", "SNOWY_TAIGA_MOUNTAINS",
			Material.SNOW_BLOCK, 	(short)0,
			BiomeProperty.TAIGA, BiomeProperty.MOUNTAINS, BiomeProperty.FREEZING),
	
	OCEAN("OCEAN", "OCEAN", "OCEAN",
			Material.WATER_BUCKET, 	(short)0,
			BiomeProperty.WATER, BiomeProperty.NEUTRAL, BiomeProperty.OCEAN),
	
	PLAINS("PLAINS", "PLAINS", "PLAINS",
			Material.GRASS, 		(short)0,
			BiomeProperty.PLAINS, BiomeProperty.MODERATE),
	
	REDWOOD_TAIGA("MEGA_SPRUCE_TAIGA", "REDWOOD_TAIGA", "GIANT_TREE_TAIGA",
			Material.SNOW_BLOCK, 	(short)0,
			BiomeProperty.TAIGA, BiomeProperty.COLD),
	
	REDWOOD_TAIGA_HILLS("MEGA_SPRUCE_TAIGA_HILLS", "REDWOOD_TAIGA_HILLS", "GIANT_TREE_TAIGA_HILLS",
			Material.SNOW_BLOCK, 	(short)0,
			BiomeProperty.TAIGA, BiomeProperty.HILLS, BiomeProperty.COLD),
	
	RIVER("RIVER", "RIVER", "RIVER",
			Material.WATER_BUCKET, 	(short)0,
			BiomeProperty.WATER, BiomeProperty.MODERATE),
	
	ROOFED_FOREST("ROOFED_FOREST", "ROOFED_FOREST", "DARK_FOREST",
			Material.OAK_LOG, 		(short)0,
			BiomeProperty.FOREST, BiomeProperty.MODERATE),
	
	SAVANNA("SAVANNA", "SAVANNA", "SAVANNA",
			Material.ACACIA_LOG, 	(short)0,
			BiomeProperty.SAVANNAH, BiomeProperty.HOT),
	
	SAVANNA_ROCK("SAVANNA_PLATEAU", "SAVANNA_ROCK", "SAVANNA_PLATEAU",
			Material.ACACIA_LOG, 	(short)0,
			BiomeProperty.SAVANNAH, BiomeProperty.HILLS),
	
	SKY("SKY", "SKY", "SKY",
			Material.WHITE_WOOL, 	(short)0,
			BiomeProperty.SPECIAL, BiomeProperty.MODERATE),
	
	SMALLER_EXTREME_HILLS("SMALL_MOUNTAINS", "SMALLER_EXTREME_HILLS", "MOUNTAIN_EDGE",
			Material.STONE, 		(short)0,
			BiomeProperty.MOUNTAINS),
	
	SMALL_END_ISLANDS("null", "null", "SMALL_END_ISLANDS",
			Material.BLACK_WOOL, 	(short)15,
			BiomeProperty.END, BiomeProperty.NEUTRAL),
	
	STONE_BEACH("STONE_BEACH", "STONE_BEACH", "STONE_SHORE",
			Material.GRAVEL, 		(short)0,
			BiomeProperty.BEACH, BiomeProperty.COLD), 
	
	SWAMPLAND("SWAMPLAND", "SWAMPLAND", "SWAMP",
			Material.SLIME_BLOCK, 	(short)0,
			BiomeProperty.SWAMPLAND, BiomeProperty.MODERATE),
	
	TAIGA("TAIGA", "TAIGA", "TAIGA",
			Material.SNOW_BLOCK, 	(short)0,
			BiomeProperty.TAIGA, BiomeProperty.COLD),
	
	TAIGA_COLD("COLD_TAIGA", "TAIGA_COLD", "SNOWY_TAIGA",
			Material.SNOW_BLOCK,	(short)0,
			BiomeProperty.TAIGA, BiomeProperty.FREEZING),
	
	TAIGA_COLD_HILLS("COLD_TAIGA_HILLS", "TAIGA_COLD_HILLS", "SNOWY_TAIGA_HILLS",
			Material.SNOW_BLOCK, 	(short)0,
			BiomeProperty.TAIGA, BiomeProperty.HILLS, BiomeProperty.FREEZING), 
	
	TAIGA_HILLS("TAIGA_HILLS", "TAIGA_HILLS", "TAIGA_HILLS",
			Material.SNOW_BLOCK, 	(short)0,
			BiomeProperty.TAIGA, BiomeProperty.HILLS, BiomeProperty.COLD),
	
	THE_VOID("THE_VOID", "THE_VOID", "THE_VOID",
			Material.BLACK_WOOL, 	(short)15,
			BiomeProperty.SPECIAL, BiomeProperty.NEUTRAL),
	
	THE_END("THE_END", "THE_END", "THE_END",
			Material.BLACK_WOOL, 	(short)15,
			BiomeProperty.END, BiomeProperty.NEUTRAL),
	
	WARM_OCEAN("null", "null", "WARM_OCEAN",
			Material.WATER_BUCKET, 	(short)0,
			BiomeProperty.MODERATE, BiomeProperty.WATER, BiomeProperty.OCEAN);
	
	
	//Stahp changing it  >:(
	private String nameOne;
	private String nameTwo;
	private String nameThree;
	private Material mat;
	private short dat;
	private List<BiomeProperty> sort;
	
	private CompatBiome(String nameOne, String nameTwo, String nameThree, Material mat, short dat, BiomeProperty... sort) {
		this.nameOne = nameOne;
		this.nameTwo = nameTwo;
		this.nameThree = nameThree;
		this.mat = mat;
		this.dat = dat;
		this.sort = Arrays.asList(sort);
	}
	
	public String getNameOne() {
		return nameOne;
	}
	
	public String getNameTwo() {
		return nameTwo;
	}
	
	public String getNameThree() {
		return nameThree;
	}
	
	public Material getMaterial() {
		return mat;
	}
	
	public short getData() {
		return dat;
	}
	
	public List<BiomeProperty> getProperties() {
		return sort;
	}
	
	public boolean hasProperty(BiomeProperty sort) {
		return this.sort != null ? this.sort.contains(sort) : false;
	}
	
	
	
	
	private static Map<String, CompatBiome> one = new HashMap<>();
	private static Map<String, CompatBiome> two = new HashMap<>();
	private static Map<String, CompatBiome> three = new HashMap<>();
	
	static {
		for (CompatBiome b: CompatBiome.values()) {
			one.put(b.getNameOne(), b);
			two.put(b.getNameTwo(), b);
			three.put(b.getNameThree(), b);
		}
	}
	
	public static CompatBiome getCompatBiome(String label) {
		String l = label.toUpperCase();
		try {
			if (one.containsKey(l)) {
				return one.get(l);
			} else if (two.containsKey(l)) {
				return two.get(l);
			} else if (three.containsKey(l)) {
				return three.get(l);
			}
		} catch (Exception e1) {}
		return null;
	}
	
	public static Biome resolveBiome(String label) {
		String l = label.toUpperCase();
		try {
			return Biome.valueOf(l);
		} catch (Exception e1) {}
		try {
			CompatBiome b = CompatBiome.getCompatBiome(label);
			try {
				return Biome.valueOf(b.getNameOne());
			} catch (Exception e1) {}
			try {
				return Biome.valueOf(b.getNameTwo());
			} catch (Exception e1) {}
			try {
				return Biome.valueOf(b.getNameThree());
			} catch (Exception e1) {}
			
		} catch (Exception e1) {}
		return null;
	}
	
	public static ItemStack getBiomeDisplay(Biome biome) {
		CompatBiome b = CompatBiome.getCompatBiome(biome.toString());
		if (b == null) {
			return null;
		}
		ItemStack it = new ItemStack(b.mat);
		return it;
	}
	
	public static List<BiomeProperty> getRegionProperties() {
		return Arrays.asList(BiomeProperty.BEACH, BiomeProperty.FOREST, BiomeProperty.DESERT, BiomeProperty.JUNGLE, BiomeProperty.TAIGA, BiomeProperty.SWAMPLAND,
				BiomeProperty.WATER, BiomeProperty.MESA, BiomeProperty.MUSHROOM, BiomeProperty.SAVANNAH, BiomeProperty.SPECIAL, BiomeProperty.PLAINS);
	}
	
	public static List<BiomeProperty> getTempuratureProperties() {
		return Arrays.asList(BiomeProperty.HOT, BiomeProperty.MODERATE, BiomeProperty.COLD, BiomeProperty.FREEZING, BiomeProperty.NEUTRAL);
	}
	
	public static List<BiomeProperty> getTerrainProperties() {
		return Arrays.asList(BiomeProperty.MOUNTAINS, BiomeProperty.HILLS, BiomeProperty.BARREN, BiomeProperty.TREES, BiomeProperty.LUSH);
	}
	
	public static enum BiomeProperty {
		HOT,
		MODERATE,
		COLD,
		FREEZING,
		NEUTRAL,
		
		BEACH,
		DESERT,
		SWAMPLAND,
		PLAINS,
		WATER,
		OCEAN,
		TAIGA,
		FOREST,
		JUNGLE,
		MESA,
		MUSHROOM,
		SAVANNAH,
		END,
		SPECIAL,
		
		MOUNTAINS,
		HILLS,
		BARREN,
		TREES,
		LUSH;
	}

	
}
