package me.chiefbeef.core.compatibility;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import me.chiefbeef.core.utility.Console;

/**
 * The enum constants in CompatMaterial are based on the 1.15.2 Material enum.
 * @author ChiefMoneyBags
 */
public enum CompatMaterial {
	
	ACACIA_BOAT(
			BlockSound.ITEM,
			null, (short) 0),
	ACACIA_BUTTON(
			BlockSound.WOOD),
	ACACIA_DOOR(
			BlockSound.WOOD,
			null, (short) 0),
	ACACIA_FENCE(
			BlockSound.WOOD,
			null, (short) 0),
	ACACIA_FENCE_GATE(
			BlockSound.WOOD,
			null, (short) 0),
	ACACIA_LEAVES(
			BlockSound.LEAVES,
			"LEAVES2", (short) 0),
	//?
	ACACIA_LOG(
			BlockSound.WOOD,
			"LOG2", (short) 0),
	ACACIA_PLANKS(
			BlockSound.WOOD,
			"PLANKS", (short) 4),
	ACACIA_PRESSURE_PLATE(
			BlockSound.WOOD),
	ACACIA_SAPLING(
			BlockSound.LEAVES,
			"SAPLING", (short) 4),
	ACACIA_SIGN(
			BlockSound.WOOD),
	ACACIA_SLAB(
			BlockSound.WOOD,
			"WOODEN_SLAB", (short) 4),
	ACACIA_STAIRS(
			BlockSound.WOOD,
			null, (short) 0),
	ACACIA_TRAPDOOR(
			BlockSound.WOOD),
	ACACIA_WALL_SIGN(
			BlockSound.WOOD),
	//?
	ACACIA_WOOD(
			BlockSound.WOOD),
	ACTIVATOR_RAIL(
			BlockSound.METAL,
			null, (short) 0),
	AIR(
			BlockSound.NONE,
			null, (short) 0),
	ALLIUM(
			BlockSound.CROPS,
			"RED_FLOWER", (short) 2),
	ANDESITE(
			BlockSound.STONE,
			"STONE", (short) 5),
	ANDESITE_SLAB(
			BlockSound.STONE),
	ANDESITE_STAIRS(
			BlockSound.STONE),
	ANDESITE_WALL(
			BlockSound.STONE),
	ANVIL(
			BlockSound.ANVIL,
			null, (short) 0),
	APPLE(
			BlockSound.ITEM,
			null, (short) 0),
	ARMOR_STAND(
			BlockSound.WOOD,
			null, (short) 0),
	ARROW(
			BlockSound.ITEM,
			null, (short) 0),
	ATTACHED_MELON_STEM(
			BlockSound.WOOD),
	ATTACHED_PUMPKIN_STEM(
			BlockSound.WOOD),
	AZURE_BLUET(
			BlockSound.CROPS,
			"RED_FLOWER", (short) 3),
	BAKED_POTATO(
			BlockSound.ITEM,
			null, (short) 0),
	BAMBOO(
			BlockSound.BAMBOO,
			null, (short) 0),
	BAMBOO_SAPLING(
			BlockSound.INCOMPLETE,
			null, (short) 0),
	BARREL(
			BlockSound.WOOD,
			null, (short) 0),
	BARRIER(
			BlockSound.STONE,
			null, (short) 0),
	BAT_SPAWN_EGG(
			BlockSound.ITEM,
			"SPAWN_EGG", (short) 65),
	BEACON(
			BlockSound.STONE,
			null, (short) 0),
	BEDROCK(
			BlockSound.STONE,
			null, (short) 0),
	BEEF(
			BlockSound.ITEM,
			null, (short) 0),
	//?
	BEEHIVE(
			BlockSound.WOOD,
			null, (short) 0),
	BEETROOT(
			BlockSound.ITEM,
			null, (short) 0),
	BEETROOTS(
			BlockSound.CROPS,
			null, (short) 0),
	BEETROOT_SEEDS(
			BlockSound.ITEM,
			null, (short) 0),
	BEETROOT_SOUP(
			BlockSound.ITEM,
			null, (short) 0),
	BEE_NEST(
			BlockSound.WOOD,
			null, (short) 0),
	BEE_SPAWN_EGG(
			BlockSound.ITEM,
			null, (short) 0),
	BELL(
			BlockSound.METAL,
			null, (short) 0),
	BIRCH_BOAT(
			BlockSound.ITEM),
	BIRCH_BUTTON(
			BlockSound.WOOD),
	BIRCH_DOOR(
			BlockSound.WOOD),
	BIRCH_FENCE(
			BlockSound.WOOD),
	BIRCH_FENCE_GATE(
			BlockSound.WOOD),
	BIRCH_LEAVES(
			BlockSound.LEAVES),
	BIRCH_LOG(
			BlockSound.WOOD),
	BIRCH_PLANKS(
			BlockSound.WOOD),
	BIRCH_PRESSURE_PLATE(
			BlockSound.WOOD),
	BIRCH_SAPLING(
			BlockSound.CROPS),
	BIRCH_SIGN(
			BlockSound.WOOD),
	BIRCH_SLAB(
			BlockSound.WOOD),
	BIRCH_STAIRS(
			BlockSound.WOOD),
	BIRCH_TRAPDOOR(
			BlockSound.WOOD),
	BIRCH_WALL_SIGN(
			BlockSound.WOOD),
	BIRCH_WOOD(
			BlockSound.WOOD),
	BLACK_BANNER(
			BlockSound.WOOL),
	BLACK_BED(
			BlockSound.WOOD),
	BLACK_CARPET(
			BlockSound.WOOL),
	BLACK_CONCRETE(
			BlockSound.STONE),
	BLACK_CONCRETE_POWDER(
			BlockSound.SAND),
	BLACK_DYE(
			BlockSound.ITEM),
	BLACK_GLAZED_TERRACOTTA(
			BlockSound.STONE),
	BLACK_SHULKER_BOX(
			BlockSound.STONE),
	BLACK_STAINED_GLASS(
			BlockSound.GLASS,
			"STAINED_GLASS", (short) 15),
	BLACK_STAINED_GLASS_PANE(
			BlockSound.GLASS,
			"STAINED_GLASS_PANE", (short) 15),
	BLACK_TERRACOTTA(
			BlockSound.STONE),
	BLACK_WALL_BANNER(
			BlockSound.WOOL),
	BLACK_WOOL(
			BlockSound.WOOL),
	BLAST_FURNACE(
			BlockSound.STONE),
	BLAZE_POWDER(
			BlockSound.ITEM),
	BLAZE_ROD(
			BlockSound.ITEM),
	BLAZE_SPAWN_EGG(
			BlockSound.ITEM),
	BLUE_BANNER(
			BlockSound.WOOL),
	BLUE_BED(
			BlockSound.WOOD),
	BLUE_CARPET(
			BlockSound.WOOL),
	BLUE_CONCRETE(
			BlockSound.STONE),
	BLUE_CONCRETE_POWDER(
			BlockSound.SAND),
	BLUE_DYE(
			BlockSound.ITEM),
	BLUE_GLAZED_TERRACOTTA(
			BlockSound.STONE),
	BLUE_ICE(
			BlockSound.GLASS),
	BLUE_ORCHID(
			BlockSound.CROPS),
	BLUE_SHULKER_BOX(
			BlockSound.STONE),
	BLUE_STAINED_GLASS(
			BlockSound.GLASS,
			"STAINED_GLASS", (short) 11),
	BLUE_STAINED_GLASS_PANE(
			BlockSound.GLASS,
			"STAINED_GLASS_PANE", (short) 11),
	BLUE_TERRACOTTA(
			BlockSound.STONE),
	BLUE_WALL_BANNER(
			BlockSound.WOOL),
	BLUE_WOOL(
			BlockSound.WOOL),
	BONE(
			BlockSound.ITEM),
	BONE_BLOCK(
			BlockSound.WOOD),
	BONE_MEAL(
			BlockSound.ITEM),
	BOOK(
			BlockSound.ITEM),
	BOOKSHELF(
			BlockSound.WOOD),
	BOW(
			BlockSound.ITEM),
	BOWL(
			BlockSound.ITEM),
	BRAIN_CORAL(
			BlockSound.CORAL),
	BRAIN_CORAL_BLOCK(
			BlockSound.CORAL),
	BRAIN_CORAL_FAN(
			BlockSound.CORAL),
	BRAIN_CORAL_WALL_FAN(
			BlockSound.CORAL),
	BREAD(
			BlockSound.ITEM),
	BREWING_STAND(
			BlockSound.INCOMPLETE),
	BRICK(
			BlockSound.ITEM),
	BRICKS(
			BlockSound.STONE,
			"BRICK_BLOCK", (short)0),
	BRICK_SLAB(
			BlockSound.STONE),
	BRICK_STAIRS(
			BlockSound.STONE),
	BRICK_WALL(
			BlockSound.STONE),
	BROWN_BANNER(
			BlockSound.WOOL),
	BROWN_BED(
			BlockSound.WOOD),
	BROWN_CARPET(
			BlockSound.WOOL),
	BROWN_CONCRETE(
			BlockSound.STONE),
	BROWN_CONCRETE_POWDER(
			BlockSound.SAND),
	BROWN_DYE(
			BlockSound.ITEM),
	BROWN_GLAZED_TERRACOTTA(
			BlockSound.STONE),
	BROWN_MUSHROOM(
			BlockSound.CROPS),
	BROWN_MUSHROOM_BLOCK(
			BlockSound.WOOD),
	BROWN_SHULKER_BOX(
			BlockSound.STONE),
	BROWN_STAINED_GLASS(
			BlockSound.GLASS,
			"STAINED_GLASS", (short) 12),
	BROWN_STAINED_GLASS_PANE(
			BlockSound.GLASS,
			"STAINED_GLASS_PANE", (short) 12),
	BROWN_TERRACOTTA(
			BlockSound.STONE),
	BROWN_WALL_BANNER(
			BlockSound.WOOL),
	BROWN_WOOL(
			BlockSound.WOOL),
	//?
	BUBBLE_COLUMN(
			BlockSound.NONE),
	BUBBLE_CORAL(
			BlockSound.CORAL),
	BUBBLE_CORAL_BLOCK(
			BlockSound.CORAL),
	BUBBLE_CORAL_FAN(
			BlockSound.CORAL),
	BUBBLE_CORAL_WALL_FAN(
			BlockSound.CORAL),
	BUCKET(
			BlockSound.ITEM),
	CACTUS(
			BlockSound.WOOL),
	CAKE(
			BlockSound.INCOMPLETE),
	//?
	CAMPFIRE(
			BlockSound.STONE),
	CARROT(
			BlockSound.ITEM),
	CARROTS(
			BlockSound.CROPS),
	CARROT_ON_A_STICK(
			BlockSound.ITEM),
	CARTOGRAPHY_TABLE(
			BlockSound.WOOD),
	CARVED_PUMPKIN(
			BlockSound.WOOD),
	CAT_SPAWN_EGG(
			BlockSound.ITEM),
	CAULDRON(
			BlockSound.INCOMPLETE),
	CAVE_AIR(
			BlockSound.NONE),
	CAVE_SPIDER_SPAWN_EGG(
			BlockSound.ITEM),
	CHAINMAIL_BOOTS(
			BlockSound.ITEM),
	CHAINMAIL_CHESTPLATE(
			BlockSound.ITEM),
	CHAINMAIL_HELMET(
			BlockSound.ITEM),
	CHAINMAIL_LEGGINGS(
			BlockSound.ITEM),
	CHAIN_COMMAND_BLOCK(
			BlockSound.STONE),
	CHARCOAL(
			BlockSound.ITEM),
	CHEST(
			BlockSound.WOOD),
	CHEST_MINECART(
			BlockSound.ITEM),
	CHICKEN(
			BlockSound.ITEM),
	CHICKEN_SPAWN_EGG(
			BlockSound.ITEM),
	CHIPPED_ANVIL(
			BlockSound.ANVIL),
	CHISELED_QUARTZ_BLOCK(
			BlockSound.STONE),
	CHISELED_RED_SANDSTONE(
			BlockSound.STONE),
	CHISELED_SANDSTONE(
			BlockSound.STONE),
	CHISELED_STONE_BRICKS(
			BlockSound.STONE),
	CHORUS_FLOWER(
			BlockSound.INCOMPLETE),
	CHORUS_FRUIT(
			BlockSound.INCOMPLETE),
	CHORUS_PLANT(
			BlockSound.INCOMPLETE),
	CLAY(
			BlockSound.INCOMPLETE),
	CLAY_BALL(
			BlockSound.ITEM),
	CLOCK(
			BlockSound.ITEM),
	COAL(
			BlockSound.ITEM),
	COAL_BLOCK(
			BlockSound.STONE),
	COAL_ORE(
			BlockSound.STONE),
	COARSE_DIRT(
			BlockSound.INCOMPLETE),
	COBBLESTONE(
			BlockSound.STONE),
	COBBLESTONE_SLAB(
			BlockSound.STONE),
	COBBLESTONE_STAIRS(
			BlockSound.STONE),
	COBBLESTONE_WALL(
			BlockSound.STONE),
	COBWEB(
			BlockSound.INCOMPLETE),
	/// FLIP?
	COCOA(
			BlockSound.ITEM),
	COCOA_BEANS(
			BlockSound.WOOD),
	///
	COD(
			BlockSound.ITEM),
	COD_BUCKET(
			BlockSound.ITEM),
	COD_SPAWN_EGG(
			BlockSound.ITEM),
	COMMAND_BLOCK(
			BlockSound.STONE),
	COMMAND_BLOCK_MINECART(
			BlockSound.ITEM),
	COMPARATOR(
			BlockSound.STONE),
	COMPASS(
			BlockSound.ITEM),
	COMPOSTER(
			BlockSound.WOOD),
	CONDUIT(
			BlockSound.STONE),
	COOKED_BEEF(
			BlockSound.ITEM),
	COOKED_CHICKEN(
			BlockSound.ITEM),
	COOKED_COD(
			BlockSound.ITEM),
	COOKED_MUTTON(
			BlockSound.ITEM),
	COOKED_PORKCHOP(
			BlockSound.ITEM),
	COOKED_RABBIT(
			BlockSound.ITEM),
	COOKED_SALMON(
			BlockSound.ITEM),
	COOKIE(
			BlockSound.ITEM),
	CORNFLOWER(
			BlockSound.CROPS),
	COW_SPAWN_EGG(
			BlockSound.ITEM),
	CRACKED_STONE_BRICKS(
			BlockSound.STONE),
	CRAFTING_TABLE(
			BlockSound.WOOD),
	CREEPER_BANNER_PATTERN(
			BlockSound.WOOD),
	CREEPER_HEAD(
			BlockSound.WOOD),
	CREEPER_SPAWN_EGG(
			BlockSound.ITEM),
	CREEPER_WALL_HEAD(
			BlockSound.WOOD),
	CROSSBOW(
			BlockSound.ITEM),
	CUT_RED_SANDSTONE(
			BlockSound.STONE),
	CUT_RED_SANDSTONE_SLAB(
			BlockSound.STONE),
	CUT_SANDSTONE(
			BlockSound.STONE),
	CUT_SANDSTONE_SLAB(
			BlockSound.STONE),
	CYAN_BANNER(
			BlockSound.WOOL),
	CYAN_BED(
			BlockSound.WOOD),
	CYAN_CARPET(
			BlockSound.WOOL),	
	CYAN_CONCRETE(
			BlockSound.STONE),
	CYAN_CONCRETE_POWDER(
			BlockSound.SAND),
	CYAN_DYE(
			BlockSound.ITEM),
	CYAN_GLAZED_TERRACOTTA(
			BlockSound.STONE),
	CYAN_SHULKER_BOX(
			BlockSound.STONE),
	CYAN_STAINED_GLASS(
			BlockSound.GLASS,
			"STAINED_GLASS", (short) 9),
	CYAN_STAINED_GLASS_PANE(
			BlockSound.GLASS,
			"STAINED_GLASS_PANE", (short) 9),
	CYAN_TERRACOTTA(
			BlockSound.STONE),
	CYAN_WALL_BANNER(
			BlockSound.WOOL),
	CYAN_WOOL(
			BlockSound.WOOL),
	DAMAGED_ANVIL(
			BlockSound.ANVIL),
	DANDELION(
			BlockSound.CROPS),
	DARK_OAK_BOAT(
			BlockSound.ITEM),
	DARK_OAK_BUTTON(
			BlockSound.WOOD),
	DARK_OAK_DOOR(
			BlockSound.WOOD),
	DARK_OAK_FENCE(
			BlockSound.WOOD),
	DARK_OAK_FENCE_GATE(
			BlockSound.WOOD),
	DARK_OAK_LEAVES(
			BlockSound.LEAVES),
	DARK_OAK_LOG(
			BlockSound.WOOD),
	DARK_OAK_PLANKS(
			BlockSound.WOOD),
	DARK_OAK_PRESSURE_PLATE(
			BlockSound.WOOD),
	DARK_OAK_SAPLING(
			BlockSound.CROPS),
	DARK_OAK_SIGN(
			BlockSound.WOOD),
	DARK_OAK_SLAB(
			BlockSound.WOOD),
	DARK_OAK_STAIRS(
			BlockSound.WOOD),
	DARK_OAK_TRAPDOOR(
			BlockSound.WOOD),
	DARK_OAK_WALL_SIGN(
			BlockSound.WOOD),
	DARK_OAK_WOOD(
			BlockSound.WOOD),
	DARK_PRISMARINE(
			BlockSound.STONE),
	DARK_PRISMARINE_SLAB(
			BlockSound.STONE),
	DARK_PRISMARINE_STAIRS(
			BlockSound.STONE),
	DAYLIGHT_DETECTOR(
			BlockSound.STONE),
	DEAD_BRAIN_CORAL(
			BlockSound.CORAL),
	DEAD_BRAIN_CORAL_BLOCK(
			BlockSound.CORAL),
	DEAD_BRAIN_CORAL_FAN(
			BlockSound.CORAL),
	DEAD_BRAIN_CORAL_WALL_FAN(
			BlockSound.CORAL),
	DEAD_BUBBLE_CORAL(
			BlockSound.CORAL),
	DEAD_BUBBLE_CORAL_BLOCK(
			BlockSound.CORAL),
	DEAD_BUBBLE_CORAL_FAN(
			BlockSound.CORAL),
	DEAD_BUBBLE_CORAL_WALL_FAN(
			BlockSound.CORAL),
	DEAD_BUSH(
			BlockSound.CORAL),
	DEAD_FIRE_CORAL(
			BlockSound.CORAL),
	DEAD_FIRE_CORAL_BLOCK(
			BlockSound.CORAL),
	DEAD_FIRE_CORAL_FAN(
			BlockSound.CORAL),
	DEAD_FIRE_CORAL_WALL_FAN(
			BlockSound.CORAL),
	DEAD_HORN_CORAL(
			BlockSound.CORAL),
	DEAD_HORN_CORAL_BLOCK(
			BlockSound.CORAL),
	DEAD_HORN_CORAL_FAN(
			BlockSound.CORAL),
	DEAD_HORN_CORAL_WALL_FAN(
			BlockSound.CORAL),
	DEAD_TUBE_CORAL(
			BlockSound.CORAL),
	DEAD_TUBE_CORAL_BLOCK(
			BlockSound.CORAL),
	DEAD_TUBE_CORAL_FAN(
			BlockSound.CORAL),
	DEAD_TUBE_CORAL_WALL_FAN(
			BlockSound.CORAL),
	DEBUG_STICK(
			BlockSound.ITEM),
	DETECTOR_RAIL(
			BlockSound.METAL),
	DIAMOND(
			BlockSound.ITEM),
	DIAMOND_AXE(
			BlockSound.ITEM),
	DIAMOND_BLOCK(
			BlockSound.STONE),
	DIAMOND_BOOTS(
			BlockSound.ITEM),
	DIAMOND_CHESTPLATE(
			BlockSound.ITEM),
	DIAMOND_HELMET(
			BlockSound.ITEM),
	DIAMOND_HOE(
			BlockSound.ITEM),
	DIAMOND_HORSE_ARMOR(
			BlockSound.ITEM),
	DIAMOND_LEGGINGS(
			BlockSound.ITEM),
	DIAMOND_ORE(
			BlockSound.STONE),
	DIAMOND_PICKAXE(
			BlockSound.ITEM),
	DIAMOND_SHOVEL(
			BlockSound.ITEM),
	DIAMOND_SWORD(
			BlockSound.ITEM),
	DIORITE(
			BlockSound.STONE),
	DIORITE_SLAB(
			BlockSound.STONE),
	DIORITE_STAIRS(
			BlockSound.STONE),
	DIORITE_WALL(
			BlockSound.STONE),
	DIRT(
			BlockSound.INCOMPLETE),
	DISPENSER(
			BlockSound.STONE),
	DOLPHIN_SPAWN_EGG(
			BlockSound.ITEM),
	DONKEY_SPAWN_EGG(
			BlockSound.ITEM),
	//?
	DRAGON_BREATH(
			BlockSound.ITEM),
	DRAGON_EGG(
			BlockSound.INCOMPLETE),
	DRAGON_HEAD(
			BlockSound.INCOMPLETE),
	DRAGON_WALL_HEAD(
			BlockSound.INCOMPLETE),
	DRIED_KELP(
			BlockSound.ITEM),
	DRIED_KELP_BLOCK(
			BlockSound.INCOMPLETE),
	DROPPER(
			BlockSound.STONE),
	DROWNED_SPAWN_EGG(
			BlockSound.ITEM),
	EGG(
			BlockSound.ITEM),
	ELDER_GUARDIAN_SPAWN_EGG(
			BlockSound.ITEM),
	ELYTRA(
			BlockSound.ITEM),
	EMERALD(
			BlockSound.ITEM),
	EMERALD_BLOCK(
			BlockSound.STONE),
	EMERALD_ORE(
			BlockSound.STONE),
	ENCHANTED_BOOK(
			BlockSound.ITEM),
	ENCHANTED_GOLDEN_APPLE(
			BlockSound.ITEM),
	ENCHANTING_TABLE(
			BlockSound.STONE),
	ENDERMAN_SPAWN_EGG(
			BlockSound.ITEM),
	ENDERMITE_SPAWN_EGG(
			BlockSound.ITEM),
	ENDER_CHEST(
			BlockSound.WOOD),
	ENDER_EYE(
			BlockSound.STONE),
	ENDER_PEARL(
			BlockSound.ITEM),
	END_CRYSTAL(
			BlockSound.GLASS),
	END_GATEWAY(
			BlockSound.NONE),
	END_PORTAL(
			BlockSound.NONE),
	END_PORTAL_FRAME(
			BlockSound.STONE),
	END_ROD(
			BlockSound.INCOMPLETE),
	END_STONE(
			BlockSound.STONE),
	END_STONE_BRICKS(
			BlockSound.STONE),
	END_STONE_BRICK_SLAB(
			BlockSound.STONE),
	END_STONE_BRICK_STAIRS(
			BlockSound.STONE),
	END_STONE_BRICK_WALL(
			BlockSound.STONE),
	EVOKER_SPAWN_EGG(
			BlockSound.ITEM),
	EXPERIENCE_BOTTLE(
			BlockSound.ITEM),
	FARMLAND(
			BlockSound.INCOMPLETE),
	FEATHER(
			BlockSound.ITEM),
	FERMENTED_SPIDER_EYE(
			BlockSound.ITEM),
	FERN(
			BlockSound.CROPS),
	FILLED_MAP(
			BlockSound.ITEM),
	FIRE(
			BlockSound.NONE),
	FIREWORK_ROCKET(
			BlockSound.ITEM),
	FIREWORK_STAR(
			BlockSound.ITEM),
	FIRE_CHARGE(
			BlockSound.ITEM),
	FIRE_CORAL(
			BlockSound.CORAL),
	FIRE_CORAL_BLOCK(
			BlockSound.CORAL),
	FIRE_CORAL_FAN(
			BlockSound.CORAL),
	FIRE_CORAL_WALL_FAN(
			BlockSound.CORAL),
	FISHING_ROD(
			BlockSound.ITEM),
	FLETCHING_TABLE(
			BlockSound.WOOD),
	FLINT(
			BlockSound.ITEM),
	FLINT_AND_STEEL(
			BlockSound.ITEM),
	FLOWER_BANNER_PATTERN(
			BlockSound.WOOL),
	FLOWER_POT(
			BlockSound.INCOMPLETE),
	FOX_SPAWN_EGG(
			BlockSound.ITEM),
	FROSTED_ICE(
			BlockSound.GLASS),
	FURNACE(
			BlockSound.STONE),
	FURNACE_MINECART(
			BlockSound.ITEM),
	GHAST_SPAWN_EGG(
			BlockSound.ITEM),
	GHAST_TEAR(
			BlockSound.ITEM),
	GLASS(
			BlockSound.GLASS),
	GLASS_BOTTLE(
			BlockSound.ITEM),
	GLASS_PANE(
			BlockSound.GLASS),
	GLISTERING_MELON_SLICE(
			BlockSound.ITEM),
	GLOBE_BANNER_PATTERN(
			BlockSound.WOOL),
	GLOWSTONE(
			BlockSound.GLASS),
	GLOWSTONE_DUST(
			BlockSound.ITEM),
	GOLDEN_APPLE(
			BlockSound.ITEM),
	GOLDEN_AXE(
			BlockSound.ITEM),
	GOLDEN_BOOTS(
			BlockSound.ITEM),
	GOLDEN_CARROT(
			BlockSound.ITEM),
	GOLDEN_CHESTPLATE(
			BlockSound.ITEM),
	GOLDEN_HELMET(
			BlockSound.ITEM),
	GOLDEN_HOE(
			BlockSound.ITEM),
	GOLDEN_HORSE_ARMOR(
			BlockSound.ITEM),
	GOLDEN_LEGGINGS(
			BlockSound.ITEM),
	GOLDEN_PICKAXE(
			BlockSound.ITEM),
	GOLDEN_SHOVEL(
			BlockSound.ITEM),
	GOLDEN_SWORD(
			BlockSound.ITEM),
	GOLD_BLOCK(
			BlockSound.METAL),
	GOLD_INGOT(
			BlockSound.ITEM),
	GOLD_NUGGET(
			BlockSound.ITEM),
	GOLD_ORE(
			BlockSound.STONE),
	GRANITE(
			BlockSound.STONE),
	GRANITE_SLAB(
			BlockSound.STONE),
	GRANITE_STAIRS(
			BlockSound.STONE),
	GRANITE_WALL(
			BlockSound.STONE),
	GRASS(
			BlockSound.CROPS),
	GRASS_BLOCK(
			BlockSound.GRASS),
	GRASS_PATH(
			BlockSound.INCOMPLETE),
	GRAVEL(
			BlockSound.GRAVEL),
	GRAY_BANNER(
			BlockSound.WOOL),
	GRAY_BED(
			BlockSound.WOOD),
	GRAY_CARPET(
			BlockSound.WOOL),
	GRAY_CONCRETE(
			BlockSound.STONE),
	GRAY_CONCRETE_POWDER(
			BlockSound.SAND),
	GRAY_DYE(
			BlockSound.ITEM),
	GRAY_GLAZED_TERRACOTTA(
			BlockSound.STONE),
	GRAY_SHULKER_BOX(
			BlockSound.STONE),
	GRAY_STAINED_GLASS(
			BlockSound.GLASS,
			"STAINED_GLASS", (short) 7),
	GRAY_STAINED_GLASS_PANE(
			BlockSound.GLASS,
			"STAINED_GLASS_PANE", (short) 7),
	GRAY_TERRACOTTA(
			BlockSound.STONE),
	GRAY_WALL_BANNER(
			BlockSound.WOOD),
	GRAY_WOOL(
			BlockSound.WOOL),
	GREEN_BANNER(
			BlockSound.WOOL),
	GREEN_BED(
			BlockSound.WOOD),
	GREEN_CARPET(
			BlockSound.WOOL),
	GREEN_CONCRETE(
			BlockSound.STONE),
	GREEN_CONCRETE_POWDER(
			BlockSound.SAND),
	GREEN_DYE(
			BlockSound.ITEM),
	GREEN_GLAZED_TERRACOTTA(
			BlockSound.STONE),
	GREEN_SHULKER_BOX(
			BlockSound.STONE),
	GREEN_STAINED_GLASS(
			BlockSound.GLASS,
			"STAINED_GLASS", (short) 13),
	GREEN_STAINED_GLASS_PANE(
			BlockSound.GLASS,
			"STAINED_GLASS_PANE", (short) 13),
	GREEN_TERRACOTTA(
			BlockSound.STONE),
	GREEN_WALL_BANNER(
			BlockSound.WOOL),
	GREEN_WOOL(
			BlockSound.WOOL),
	GRINDSTONE(
			BlockSound.STONE),
	GUARDIAN_SPAWN_EGG(
			BlockSound.ITEM),
	GUNPOWDER(
			BlockSound.ITEM),
	HAY_BLOCK(
			BlockSound.CROPS),
	HEART_OF_THE_SEA(
			BlockSound.ITEM),
	HEAVY_WEIGHTED_PRESSURE_PLATE(
			BlockSound.METAL),
	HONEYCOMB(
			BlockSound.ITEM),
	HONEYCOMB_BLOCK(
			BlockSound.HONEY),
	HONEY_BLOCK(
			BlockSound.HONEY),
	HONEY_BOTTLE(
			BlockSound.ITEM),
	HOPPER(
			BlockSound.METAL),
	HOPPER_MINECART(
			BlockSound.ITEM),
	HORN_CORAL(
			BlockSound.CORAL),
	HORN_CORAL_BLOCK(
			BlockSound.CORAL),
	HORN_CORAL_FAN(
			BlockSound.CORAL),
	HORN_CORAL_WALL_FAN(
			BlockSound.CORAL),
	HORSE_SPAWN_EGG(
			BlockSound.ITEM),
	HUSK_SPAWN_EGG(
			BlockSound.ITEM),
	ICE(
			BlockSound.GLASS),
	INFESTED_CHISELED_STONE_BRICKS(
			BlockSound.STONE),
	INFESTED_COBBLESTONE(
			BlockSound.STONE),
	INFESTED_CRACKED_STONE_BRICKS(
			BlockSound.STONE),
	INFESTED_MOSSY_STONE_BRICKS(
			BlockSound.STONE),
	INFESTED_STONE(
			BlockSound.STONE),
	INFESTED_STONE_BRICKS(
			BlockSound.STONE),
	INK_SAC(
			BlockSound.ITEM),
	IRON_AXE(
			BlockSound.ITEM),
	IRON_BARS(
			BlockSound.METAL),
	IRON_BLOCK(
			BlockSound.METAL),
	IRON_BOOTS(
			BlockSound.ITEM),
	IRON_CHESTPLATE(
			BlockSound.ITEM),
	IRON_DOOR(
			BlockSound.METAL),
	IRON_HELMET(
			BlockSound.ITEM),
	IRON_HOE(
			BlockSound.ITEM),
	IRON_HORSE_ARMOR(
			BlockSound.ITEM),
	IRON_INGOT(
			BlockSound.ITEM),
	IRON_LEGGINGS(
			BlockSound.ITEM),
	IRON_NUGGET(
			BlockSound.ITEM),
	IRON_ORE(
			BlockSound.STONE),
	IRON_PICKAXE(
			BlockSound.ITEM),
	IRON_SHOVEL(
			BlockSound.ITEM),
	IRON_SWORD(
			BlockSound.ITEM),
	IRON_TRAPDOOR(
			BlockSound.METAL),
	ITEM_FRAME(
			BlockSound.WOOD),
	JACK_O_LANTERN(
			BlockSound.WOOD),
	//? wat
	JIGSAW(
			BlockSound.INCOMPLETE),
	JUKEBOX(
			BlockSound.WOOD),
	JUNGLE_BOAT(
			BlockSound.ITEM),
	JUNGLE_BUTTON(
			BlockSound.WOOD),
	JUNGLE_DOOR(
			BlockSound.WOOD),
	JUNGLE_FENCE(
			BlockSound.WOOD),
	JUNGLE_FENCE_GATE(
			BlockSound.WOOD),
	JUNGLE_LEAVES(
			BlockSound.LEAVES),
	JUNGLE_LOG(
			BlockSound.WOOD),
	JUNGLE_PLANKS(
			BlockSound.WOOD),
	JUNGLE_PRESSURE_PLATE(
			BlockSound.WOOD),
	JUNGLE_SAPLING(
			BlockSound.CROPS),
	JUNGLE_SIGN(
			BlockSound.WOOD),
	JUNGLE_SLAB(
			BlockSound.WOOD),
	JUNGLE_STAIRS(
			BlockSound.WOOD),
	JUNGLE_TRAPDOOR(
			BlockSound.WOOD),
	JUNGLE_WALL_SIGN(
			BlockSound.WOOD),
	JUNGLE_WOOD(
			BlockSound.WOOD),
	KELP(
			BlockSound.ITEM),
	KELP_PLANT(
			BlockSound.INCOMPLETE),
	KNOWLEDGE_BOOK(
			BlockSound.ITEM),
	LADDER(
			BlockSound.LADDER),
	LANTERN(
			BlockSound.METAL),
	LAPIS_BLOCK(
			BlockSound.STONE),
	LAPIS_LAZULI(
			BlockSound.ITEM),
	LAPIS_ORE(
			BlockSound.STONE),
	LARGE_FERN(
			BlockSound.CROPS),
	LAVA(
			BlockSound.NONE),
	LAVA_BUCKET(
			BlockSound.ITEM),
	LEAD(
			BlockSound.ITEM),
	LEATHER(
			BlockSound.ITEM),
	LEATHER_BOOTS(
			BlockSound.ITEM),
	LEATHER_CHESTPLATE(
			BlockSound.ITEM),
	LEATHER_HELMET(
			BlockSound.ITEM),
	LEATHER_HORSE_ARMOR(
			BlockSound.ITEM),
	LEATHER_LEGGINGS(
			BlockSound.ITEM),
	LECTERN(
			BlockSound.WOOD),
	LEVER(
			BlockSound.STONE),
	LIGHT_BLUE_BANNER(
			BlockSound.WOOL),
	LIGHT_BLUE_BED(
			BlockSound.WOOD),
	LIGHT_BLUE_CARPET(
			BlockSound.WOOL),
	LIGHT_BLUE_CONCRETE(
			BlockSound.STONE),
	LIGHT_BLUE_CONCRETE_POWDER(
			BlockSound.SAND),
	LIGHT_BLUE_DYE(
			BlockSound.ITEM),
	LIGHT_BLUE_GLAZED_TERRACOTTA(
			BlockSound.STONE),
	LIGHT_BLUE_SHULKER_BOX(
			BlockSound.STONE),
	LIGHT_BLUE_STAINED_GLASS(
			BlockSound.GLASS,
			"STAINED_GLASS", (short) 3),
	LIGHT_BLUE_STAINED_GLASS_PANE(
			BlockSound.GLASS,
			"STAINED_GLASS_PANE", (short) 3),
	LIGHT_BLUE_TERRACOTTA(
			BlockSound.STONE),
	LIGHT_BLUE_WALL_BANNER(
			BlockSound.WOOL),
	LIGHT_BLUE_WOOL(
			BlockSound.WOOL),
	LIGHT_GRAY_BANNER(
			BlockSound.WOOL),
	LIGHT_GRAY_BED(
			BlockSound.WOOD),
	LIGHT_GRAY_CARPET(
			BlockSound.WOOL),
	LIGHT_GRAY_CONCRETE(
			BlockSound.STONE),
	LIGHT_GRAY_CONCRETE_POWDER(
			BlockSound.SAND),
	LIGHT_GRAY_DYE(
			BlockSound.ITEM),
	LIGHT_GRAY_GLAZED_TERRACOTTA(
			BlockSound.STONE),
	LIGHT_GRAY_SHULKER_BOX(
			BlockSound.STONE),
	LIGHT_GRAY_STAINED_GLASS(
			BlockSound.GLASS,
			"STAINED_GLASS", (short) 8),
	LIGHT_GRAY_STAINED_GLASS_PANE(
			BlockSound.GLASS,
			"STAINED_GLASS_PANE", (short) 8),
	LIGHT_GRAY_TERRACOTTA(
			BlockSound.STONE),
	LIGHT_GRAY_WALL_BANNER(
			BlockSound.WOOL),
	LIGHT_GRAY_WOOL(
			BlockSound.WOOL),
	LIGHT_WEIGHTED_PRESSURE_PLATE(
			BlockSound.METAL),
	LILAC(
			BlockSound.CROPS),
	LILY_OF_THE_VALLEY(
			BlockSound.CROPS),
	LILY_PAD(
			BlockSound.CROPS),
	LIME_BANNER(
			BlockSound.WOOL),
	LIME_BED(
			BlockSound.WOOD),
	LIME_CARPET(
			BlockSound.WOOL),
	LIME_CONCRETE(
			BlockSound.STONE),
	LIME_CONCRETE_POWDER(
			BlockSound.SAND),
	LIME_DYE(
			BlockSound.ITEM),
	LIME_GLAZED_TERRACOTTA(
			BlockSound.STONE),
	LIME_SHULKER_BOX(
			BlockSound.STONE),
	LIME_STAINED_GLASS(
			BlockSound.GLASS,
			"STAINED_GLASS", (short) 5),
	LIME_STAINED_GLASS_PANE(
			BlockSound.GLASS,
			"STAINED_GLASS_PANE", (short) 5),
	LIME_TERRACOTTA(
			BlockSound.STONE),
	LIME_WALL_BANNER(
			BlockSound.WOOL),
	LIME_WOOL(
			BlockSound.WOOL),
	LINGERING_POTION(
			BlockSound.ITEM),
	LLAMA_SPAWN_EGG(
			BlockSound.ITEM),
	LOOM(
			BlockSound.WOOD),
	MAGENTA_BANNER(
			BlockSound.WOOL),
	MAGENTA_BED(
			BlockSound.WOOD),
	MAGENTA_CARPET(
			BlockSound.WOOL),
	MAGENTA_CONCRETE(
			BlockSound.STONE),
	MAGENTA_CONCRETE_POWDER(
			BlockSound.SAND),
	MAGENTA_DYE(
			BlockSound.ITEM),
	MAGENTA_GLAZED_TERRACOTTA(
			BlockSound.STONE),
	MAGENTA_SHULKER_BOX(
			BlockSound.STONE),
	MAGENTA_STAINED_GLASS(
			BlockSound.GLASS,
			"STAINED_GLASS", (short) 2),
	MAGENTA_STAINED_GLASS_PANE(
			BlockSound.GLASS,
			"STAINED_GLASS_PANE", (short) 2),
	MAGENTA_TERRACOTTA(
			BlockSound.STONE),
	MAGENTA_WALL_BANNER(
			BlockSound.WOOL),
	MAGENTA_WOOL(
			BlockSound.WOOL),
	MAGMA_BLOCK(
			BlockSound.STONE),
	MAGMA_CREAM(
			BlockSound.ITEM),
	MAGMA_CUBE_SPAWN_EGG(
			BlockSound.ITEM),
	MAP(
			BlockSound.ITEM),
	MELON(
			BlockSound.WOOD),
	MELON_SEEDS(
			BlockSound.ITEM),
	MELON_SLICE(
			BlockSound.ITEM),
	MELON_STEM(
			BlockSound.WOOD),
	MILK_BUCKET(
			BlockSound.ITEM),
	MINECART(
			BlockSound.ITEM),
	MOJANG_BANNER_PATTERN(
			BlockSound.WOOL),
	MOOSHROOM_SPAWN_EGG(
			BlockSound.ITEM),
	MOSSY_COBBLESTONE(
			BlockSound.STONE),
	MOSSY_COBBLESTONE_SLAB(
			BlockSound.STONE),
	MOSSY_COBBLESTONE_STAIRS(
			BlockSound.STONE),
	MOSSY_COBBLESTONE_WALL(
			BlockSound.STONE),
	MOSSY_STONE_BRICKS(
			BlockSound.STONE),
	MOSSY_STONE_BRICK_SLAB(
			BlockSound.STONE),
	MOSSY_STONE_BRICK_STAIRS(
			BlockSound.STONE),
	MOSSY_STONE_BRICK_WALL(
			BlockSound.STONE),
	MOVING_PISTON(
			BlockSound.STONE),
	MULE_SPAWN_EGG(
			BlockSound.ITEM),
	MUSHROOM_STEM(
			BlockSound.WOOD),
	MUSHROOM_STEW(
			BlockSound.ITEM),
	MUSIC_DISC_11(
			BlockSound.ITEM),
	MUSIC_DISC_13(
			BlockSound.ITEM),
	MUSIC_DISC_BLOCKS(
			BlockSound.ITEM),
	MUSIC_DISC_CAT(
			BlockSound.ITEM),
	MUSIC_DISC_CHIRP(
			BlockSound.ITEM),
	MUSIC_DISC_FAR(
			BlockSound.ITEM),
	MUSIC_DISC_MALL(
			BlockSound.ITEM),
	MUSIC_DISC_MELLOHI(
			BlockSound.ITEM),
	MUSIC_DISC_STAL(
			BlockSound.ITEM),
	MUSIC_DISC_STRAD(
			BlockSound.ITEM),
	MUSIC_DISC_WAIT(
			BlockSound.ITEM),
	MUSIC_DISC_WARD(
			BlockSound.ITEM),
	MUTTON(
			BlockSound.ITEM),
	MYCELIUM(
			BlockSound.INCOMPLETE),
	NAME_TAG(
			BlockSound.ITEM),
	NAUTILUS_SHELL(
			BlockSound.ITEM),
	NETHERRACK(
			BlockSound.STONE),
	NETHER_BRICK(
			BlockSound.ITEM),
	NETHER_BRICKS(
			BlockSound.STONE),
	NETHER_BRICK_FENCE(
			BlockSound.STONE),
	NETHER_BRICK_SLAB(
			BlockSound.STONE),
	NETHER_BRICK_STAIRS(
			BlockSound.STONE),
	NETHER_BRICK_WALL(
			BlockSound.STONE),
	NETHER_PORTAL(
			BlockSound.NONE),
	NETHER_QUARTZ_ORE(
			BlockSound.STONE),
	NETHER_STAR(
			BlockSound.NONE),
	NETHER_WART(
			BlockSound.CROPS),
	NETHER_WART_BLOCK(
			BlockSound.WOOD),
	NOTE_BLOCK(
			BlockSound.WOOD),
	OAK_BOAT(
			BlockSound.ITEM),
	OAK_BUTTON(
			BlockSound.WOOD),
	OAK_DOOR(
			BlockSound.WOOD),
	OAK_FENCE(
			BlockSound.WOOD),
	OAK_FENCE_GATE(
			BlockSound.WOOD),
	OAK_LEAVES(
			BlockSound.LEAVES),
	OAK_LOG(
			BlockSound.WOOD),
	OAK_PLANKS(
			BlockSound.WOOD),
	OAK_PRESSURE_PLATE(
			BlockSound.WOOD),
	OAK_SAPLING(
			BlockSound.CROPS),
	OAK_SIGN(
			BlockSound.WOOD),
	OAK_SLAB(
			BlockSound.WOOD),
	OAK_STAIRS(
			BlockSound.WOOD),
	OAK_TRAPDOOR(
			BlockSound.WOOD),
	OAK_WALL_SIGN(
			BlockSound.WOOD),
	OAK_WOOD(
			BlockSound.WOOD),
	OBSERVER(
			BlockSound.STONE),
	OBSIDIAN(
			BlockSound.STONE),
	OCELOT_SPAWN_EGG(
			BlockSound.ITEM),
	ORANGE_BANNER(
			BlockSound.WOOL),
	ORANGE_BED(
			BlockSound.WOOD),
	ORANGE_CARPET(
			BlockSound.WOOL),
	ORANGE_CONCRETE(
			BlockSound.STONE),
	ORANGE_CONCRETE_POWDER(
			BlockSound.SAND),
	ORANGE_DYE(
			BlockSound.ITEM),
	ORANGE_GLAZED_TERRACOTTA(
			BlockSound.STONE),
	ORANGE_SHULKER_BOX(
			BlockSound.STONE),
	ORANGE_STAINED_GLASS(
			BlockSound.GLASS,
			"STAINED_GLASS", (short) 1),
	ORANGE_STAINED_GLASS_PANE(
			BlockSound.GLASS,
			"STAINED_GLASS_PANE", (short) 1),
	ORANGE_TERRACOTTA(
			BlockSound.STONE),
	ORANGE_TULIP(
			BlockSound.CROPS),
	ORANGE_WALL_BANNER(
			BlockSound.WOOL),
	ORANGE_WOOL(
			BlockSound.WOOL),
	OXEYE_DAISY(
			BlockSound.CROPS),
	PACKED_ICE(
			BlockSound.GLASS),
	PAINTING(
			BlockSound.WOOD),
	PANDA_SPAWN_EGG(
			BlockSound.ITEM),
	PAPER(
			BlockSound.ITEM),
	PARROT_SPAWN_EGG(
			BlockSound.ITEM),
	PEONY(
			BlockSound.CROPS),
	PETRIFIED_OAK_SLAB(
			BlockSound.WOOD),
	PHANTOM_MEMBRANE(
			BlockSound.ITEM),
	PHANTOM_SPAWN_EGG(
			BlockSound.ITEM),
	PIG_SPAWN_EGG(
			BlockSound.ITEM),
	PILLAGER_SPAWN_EGG(
			BlockSound.ITEM),
	PINK_BANNER(
			BlockSound.WOOL),
	PINK_BED(
			BlockSound.WOOD),
	PINK_CARPET(
			BlockSound.WOOL),
	PINK_CONCRETE(
			BlockSound.STONE),
	PINK_CONCRETE_POWDER(
			BlockSound.SAND),
	PINK_DYE(
			BlockSound.ITEM),
	PINK_GLAZED_TERRACOTTA(
			BlockSound.STONE),
	PINK_SHULKER_BOX(
			BlockSound.STONE),
	PINK_STAINED_GLASS(
			BlockSound.GLASS,
			"STAINED_GLASS", (short) 6),
	PINK_STAINED_GLASS_PANE(
			BlockSound.GLASS,
			"STAINED_GLASS_PANE", (short) 6),
	PINK_TERRACOTTA(
			BlockSound.STONE),
	PINK_TULIP(
			BlockSound.CROPS),
	PINK_WALL_BANNER(
			BlockSound.WOOL),
	PINK_WOOL(
			BlockSound.WOOL),
	PISTON(
			BlockSound.STONE),
	PISTON_HEAD(
			BlockSound.WOOD),
	PLAYER_HEAD(
			BlockSound.WOOD),
	PLAYER_WALL_HEAD(
			BlockSound.WOOD),
	PODZOL(
			BlockSound.INCOMPLETE),
	POISONOUS_POTATO(
			BlockSound.ITEM),
	POLAR_BEAR_SPAWN_EGG(
			BlockSound.ITEM),
	POLISHED_ANDESITE(
			BlockSound.STONE),
	POLISHED_ANDESITE_SLAB(
			BlockSound.STONE),
	POLISHED_ANDESITE_STAIRS(
			BlockSound.STONE),
	POLISHED_DIORITE(
			BlockSound.STONE),
	POLISHED_DIORITE_SLAB(
			BlockSound.STONE),
	POLISHED_DIORITE_STAIRS(
			BlockSound.STONE),
	POLISHED_GRANITE(
			BlockSound.STONE),
	POLISHED_GRANITE_SLAB(
			BlockSound.STONE),
	POLISHED_GRANITE_STAIRS(
			BlockSound.STONE),
	POPPED_CHORUS_FRUIT(
			BlockSound.ITEM),
	POPPY(
			BlockSound.CROPS),
	PORKCHOP(
			BlockSound.ITEM),
	POTATO(
			BlockSound.ITEM),
	POTATOES(
			BlockSound.CROPS),
	POTION(
			BlockSound.ITEM),
	POTTED_ACACIA_SAPLING(
			BlockSound.INCOMPLETE),
	POTTED_ALLIUM(
			BlockSound.INCOMPLETE),
	POTTED_AZURE_BLUET(
			BlockSound.INCOMPLETE),
	POTTED_BAMBOO(
			BlockSound.INCOMPLETE),
	POTTED_BIRCH_SAPLING(
			BlockSound.INCOMPLETE),
	POTTED_BLUE_ORCHID(
			BlockSound.INCOMPLETE),
	POTTED_BROWN_MUSHROOM(
			BlockSound.INCOMPLETE),
	POTTED_CACTUS(
			BlockSound.INCOMPLETE),
	POTTED_CORNFLOWER(
			BlockSound.INCOMPLETE),
	POTTED_DANDELION(
			BlockSound.INCOMPLETE),
	POTTED_DARK_OAK_SAPLING(
			BlockSound.INCOMPLETE),
	POTTED_DEAD_BUSH(
			BlockSound.INCOMPLETE),
	POTTED_FERN(
			BlockSound.INCOMPLETE),
	POTTED_JUNGLE_SAPLING(
			BlockSound.INCOMPLETE),
	POTTED_LILY_OF_THE_VALLEY(
			BlockSound.INCOMPLETE),
	POTTED_OAK_SAPLING(
			BlockSound.INCOMPLETE),
	POTTED_ORANGE_TULIP(
			BlockSound.INCOMPLETE),
	POTTED_OXEYE_DAISY(
			BlockSound.INCOMPLETE),
	POTTED_PINK_TULIP(
			BlockSound.INCOMPLETE),
	POTTED_POPPY(
			BlockSound.INCOMPLETE),
	POTTED_RED_MUSHROOM(
			BlockSound.INCOMPLETE),
	POTTED_RED_TULIP(
			BlockSound.INCOMPLETE),
	POTTED_SPRUCE_SAPLING(
			BlockSound.INCOMPLETE),
	POTTED_WHITE_TULIP(
			BlockSound.INCOMPLETE),
	POTTED_WITHER_ROSE(
			BlockSound.INCOMPLETE),
	POWERED_RAIL(
			BlockSound.WOOD),
	PRISMARINE(
			BlockSound.STONE),
	PRISMARINE_BRICKS(
			BlockSound.STONE),
	PRISMARINE_BRICK_SLAB(
			BlockSound.STONE),
	PRISMARINE_BRICK_STAIRS(
			BlockSound.STONE),
	PRISMARINE_CRYSTALS(
			BlockSound.ITEM),
	PRISMARINE_SHARD(
			BlockSound.ITEM),
	PRISMARINE_SLAB(
			BlockSound.STONE),
	PRISMARINE_STAIRS(
			BlockSound.STONE),
	PRISMARINE_WALL(
			BlockSound.STONE),
	PUFFERFISH(
			BlockSound.ITEM),
	PUFFERFISH_BUCKET(
			BlockSound.ITEM),
	PUFFERFISH_SPAWN_EGG(
			BlockSound.ITEM),
	PUMPKIN(
			BlockSound.WOOD),
	PUMPKIN_PIE(
			BlockSound.ITEM),
	PUMPKIN_SEEDS(
			BlockSound.ITEM),
	PUMPKIN_STEM(
			BlockSound.WOOD),
	PURPLE_BANNER(
			BlockSound.WOOL),
	PURPLE_BED(
			BlockSound.WOOD),
	PURPLE_CARPET(
			BlockSound.WOOL),
	PURPLE_CONCRETE(
			BlockSound.STONE),
	PURPLE_CONCRETE_POWDER(
			BlockSound.SAND),
	PURPLE_DYE(
			BlockSound.ITEM),
	PURPLE_GLAZED_TERRACOTTA(
			BlockSound.STONE),
	PURPLE_SHULKER_BOX(
			BlockSound.STONE),
	PURPLE_STAINED_GLASS(
			BlockSound.GLASS,
			"STAINED_GLASS", (short) 10),
	PURPLE_STAINED_GLASS_PANE(
			BlockSound.GLASS,
			"STAINED_GLASS_PANE", (short) 10),
	PURPLE_TERRACOTTA(
			BlockSound.STONE),
	PURPLE_WALL_BANNER(
			BlockSound.WOOL),
	PURPLE_WOOL(
			BlockSound.WOOL),
	PURPUR_BLOCK(
			BlockSound.STONE),
	PURPUR_PILLAR(
			BlockSound.STONE),
	PURPUR_SLAB(
			BlockSound.STONE),
	PURPUR_STAIRS(
			BlockSound.STONE),
	QUARTZ(
			BlockSound.STONE),
	QUARTZ_BLOCK(
			BlockSound.STONE),
	QUARTZ_PILLAR(
			BlockSound.STONE),
	QUARTZ_SLAB(
			BlockSound.STONE),
	QUARTZ_STAIRS(
			BlockSound.STONE),
	RABBIT(
			BlockSound.ITEM),
	RABBIT_FOOT(
			BlockSound.ITEM),
	RABBIT_HIDE(
			BlockSound.ITEM),
	RABBIT_SPAWN_EGG(
			BlockSound.ITEM),
	RABBIT_STEW(
			BlockSound.ITEM),
	RAIL(
			BlockSound.METAL),
	RAVAGER_SPAWN_EGG(
			BlockSound.ITEM),
	REDSTONE(
			BlockSound.ITEM),
	REDSTONE_BLOCK(
			BlockSound.STONE),
	REDSTONE_LAMP(
			BlockSound.GLASS),
	REDSTONE_ORE(
			BlockSound.STONE),
	REDSTONE_TORCH(
			BlockSound.WOOD),
	REDSTONE_WALL_TORCH(
			BlockSound.WOOD),
	REDSTONE_WIRE(
			BlockSound.INCOMPLETE),
	RED_BANNER(
			BlockSound.WOOL),
	RED_BED(
			BlockSound.WOOD),
	RED_CARPET(
			BlockSound.WOOL),
	RED_CONCRETE(
			BlockSound.STONE),
	RED_CONCRETE_POWDER(
			BlockSound.SAND),
	RED_DYE(
			BlockSound.ITEM),
	RED_GLAZED_TERRACOTTA(
			BlockSound.STONE),
	RED_MUSHROOM(
			BlockSound.CROPS),
	RED_MUSHROOM_BLOCK(
			BlockSound.WOOD),
	RED_NETHER_BRICKS(
			BlockSound.ITEM),
	RED_NETHER_BRICK_SLAB(
			BlockSound.STONE),
	RED_NETHER_BRICK_STAIRS(
			BlockSound.STONE),
	RED_NETHER_BRICK_WALL(
			BlockSound.STONE),
	RED_SAND(
			BlockSound.SAND),
	RED_SANDSTONE(
			BlockSound.STONE),
	RED_SANDSTONE_SLAB(
			BlockSound.STONE),
	RED_SANDSTONE_STAIRS(
			BlockSound.STONE),
	RED_SANDSTONE_WALL(
			BlockSound.STONE),
	RED_SHULKER_BOX(
			BlockSound.STONE),
	RED_STAINED_GLASS(
			BlockSound.GLASS,
			"STAINED_GLASS", (short) 14),
	RED_STAINED_GLASS_PANE(
			BlockSound.GLASS,
			"STAINED_GLASS_PANE", (short) 14),
	RED_TERRACOTTA(
			BlockSound.STONE),
	RED_TULIP(
			BlockSound.CROPS),
	RED_WALL_BANNER(
			BlockSound.WOOL),
	RED_WOOL(
			BlockSound.WOOL),
	REPEATER(
			BlockSound.STONE),
	REPEATING_COMMAND_BLOCK(
			BlockSound.STONE),
	ROSE_BUSH(
			BlockSound.CROPS),
	ROTTEN_FLESH(
			BlockSound.ITEM),
	SADDLE(
			BlockSound.ITEM),
	SALMON(
			BlockSound.ITEM),
	SALMON_BUCKET(
			BlockSound.ITEM),
	SALMON_SPAWN_EGG(
			BlockSound.ITEM),
	SAND(
			BlockSound.SAND),
	SANDSTONE(
			BlockSound.STONE),
	SANDSTONE_SLAB(
			BlockSound.STONE),
	SANDSTONE_STAIRS(
			BlockSound.STONE),
	SANDSTONE_WALL(
			BlockSound.STONE),
	SCAFFOLDING(
			BlockSound.INCOMPLETE),
	SCUTE(
			BlockSound.ITEM),
	SEAGRASS(
			BlockSound.INCOMPLETE),
	SEA_LANTERN(
			BlockSound.GLASS),
	SEA_PICKLE(
			BlockSound.INCOMPLETE),
	SHEARS(
			BlockSound.ITEM),
	SHEEP_SPAWN_EGG(
			BlockSound.ITEM),
	SHIELD(
			BlockSound.ITEM),
	SHULKER_BOX(
			BlockSound.STONE),
	SHULKER_SHELL(
			BlockSound.ITEM),
	SHULKER_SPAWN_EGG(
			BlockSound.ITEM),
	SILVERFISH_SPAWN_EGG(
			BlockSound.ITEM),
	SKELETON_HORSE_SPAWN_EGG(
			BlockSound.ITEM),
	SKELETON_SKULL(
			BlockSound.WOOD),
	SKELETON_SPAWN_EGG(
			BlockSound.ITEM),
	SKELETON_WALL_SKULL(
			BlockSound.WOOD),
	SKULL_BANNER_PATTERN(
			BlockSound.WOOL),
	SLIME_BALL(
			BlockSound.ITEM),
	SLIME_BLOCK(
			BlockSound.SLIME),
	SLIME_SPAWN_EGG(
			BlockSound.ITEM),
	SMITHING_TABLE(
			BlockSound.METAL),
	SMOKER(
			BlockSound.STONE),
	SMOOTH_QUARTZ(
			BlockSound.STONE),
	SMOOTH_QUARTZ_SLAB(
			BlockSound.STONE),
	SMOOTH_QUARTZ_STAIRS(
			BlockSound.STONE),
	SMOOTH_RED_SANDSTONE(
			BlockSound.STONE),
	SMOOTH_RED_SANDSTONE_SLAB(
			BlockSound.STONE),
	SMOOTH_RED_SANDSTONE_STAIRS(
			BlockSound.STONE),
	SMOOTH_SANDSTONE(
			BlockSound.STONE),
	SMOOTH_SANDSTONE_SLAB(
			BlockSound.STONE),
	SMOOTH_SANDSTONE_STAIRS(
			BlockSound.STONE),
	SMOOTH_STONE(
			BlockSound.STONE),
	SMOOTH_STONE_SLAB(
			BlockSound.STONE),
	SNOW(
			BlockSound.INCOMPLETE),
	SNOWBALL(
			BlockSound.ITEM),
	SNOW_BLOCK(
			BlockSound.INCOMPLETE),
	SOUL_SAND(
			BlockSound.SAND),
	SPAWNER(
			BlockSound.METAL),
	SPECTRAL_ARROW(
			BlockSound.ITEM),
	SPIDER_EYE(
			BlockSound.ITEM),
	SPIDER_SPAWN_EGG(
			BlockSound.ITEM),
	SPLASH_POTION(
			BlockSound.ITEM),
	SPONGE(
			BlockSound.INCOMPLETE),
	SPRUCE_BOAT(
			BlockSound.ITEM),
	SPRUCE_BUTTON(
			BlockSound.WOOD),
	SPRUCE_DOOR(
			BlockSound.WOOD),
	SPRUCE_FENCE(
			BlockSound.WOOD),
	SPRUCE_FENCE_GATE(
			BlockSound.WOOD),
	SPRUCE_LEAVES(
			BlockSound.LEAVES),
	SPRUCE_LOG(
			BlockSound.WOOD),
	SPRUCE_PLANKS(
			BlockSound.WOOD),
	SPRUCE_PRESSURE_PLATE(
			BlockSound.WOOD),
	SPRUCE_SAPLING(
			BlockSound.CROPS),
	SPRUCE_SIGN(
			BlockSound.WOOD),
	SPRUCE_SLAB(
			BlockSound.WOOD),
	SPRUCE_STAIRS(
			BlockSound.WOOD),
	SPRUCE_TRAPDOOR(
			BlockSound.WOOD),
	SPRUCE_WALL_SIGN(
			BlockSound.WOOD),
	SPRUCE_WOOD(
			BlockSound.WOOD),
	SQUID_SPAWN_EGG(
			BlockSound.ITEM),
	STICK(
			BlockSound.ITEM),
	STICKY_PISTON(
			BlockSound.STONE),
	STONE(
			BlockSound.STONE),
	STONECUTTER(
			BlockSound.STONE),
	STONE_AXE(
			BlockSound.ITEM),
	STONE_BRICKS(
			BlockSound.STONE),
	STONE_BRICK_SLAB(
			BlockSound.STONE),
	STONE_BRICK_STAIRS(
			BlockSound.STONE),
	STONE_BRICK_WALL(
			BlockSound.STONE),
	STONE_BUTTON(
			BlockSound.STONE),
	STONE_HOE(
			BlockSound.ITEM),
	STONE_PICKAXE(
			BlockSound.ITEM),
	STONE_PRESSURE_PLATE(
			BlockSound.STONE),
	STONE_SHOVEL(
			BlockSound.ITEM),
	STONE_SLAB(
			BlockSound.STONE),
	STONE_STAIRS(
			BlockSound.STONE),
	STONE_SWORD(
			BlockSound.ITEM),
	STRAY_SPAWN_EGG(
			BlockSound.ITEM),
	STRING(
			BlockSound.INCOMPLETE),
	STRIPPED_ACACIA_LOG(
			BlockSound.WOOD),
	STRIPPED_ACACIA_WOOD(
			BlockSound.WOOD),
	STRIPPED_BIRCH_LOG(
			BlockSound.WOOD),
	STRIPPED_BIRCH_WOOD(
			BlockSound.WOOD),
	STRIPPED_DARK_OAK_LOG(
			BlockSound.WOOD),
	STRIPPED_DARK_OAK_WOOD(
			BlockSound.WOOD),
	STRIPPED_JUNGLE_LOG(
			BlockSound.WOOD),
	STRIPPED_JUNGLE_WOOD(
			BlockSound.WOOD),
	STRIPPED_OAK_LOG(
			BlockSound.WOOD),
	STRIPPED_OAK_WOOD(
			BlockSound.WOOD),
	STRIPPED_SPRUCE_LOG(
			BlockSound.WOOD),
	STRIPPED_SPRUCE_WOOD(
			BlockSound.WOOD),
	STRUCTURE_BLOCK(
			BlockSound.STONE),
	STRUCTURE_VOID(
			BlockSound.NONE),
	SUGAR(
			BlockSound.ITEM),
	SUGAR_CANE(
			BlockSound.INCOMPLETE),
	SUNFLOWER(
			BlockSound.CROPS),
	SUSPICIOUS_STEW(
			BlockSound.ITEM),
	SWEET_BERRIES(
			BlockSound.SWEET_BERRY),
	SWEET_BERRY_BUSH(
			BlockSound.SWEET_BERRY),
	TALL_GRASS(
			BlockSound.CROPS),
	TALL_SEAGRASS(
			BlockSound.INCOMPLETE),
	TERRACOTTA(
			BlockSound.STONE),
	TIPPED_ARROW(
			BlockSound.ITEM),
	TNT(
			BlockSound.CROPS),
	TNT_MINECART(
			BlockSound.ITEM),
	TORCH(
			BlockSound.WOOD),
	TOTEM_OF_UNDYING(
			BlockSound.ITEM),
	TRADER_LLAMA_SPAWN_EGG(
			BlockSound.ITEM),
	TRAPPED_CHEST(
			BlockSound.WOOD),
	TRIDENT(
			BlockSound.ITEM),
	TRIPWIRE(
			BlockSound.INCOMPLETE),
	TRIPWIRE_HOOK(
			BlockSound.WOOD),
	TROPICAL_FISH(
			BlockSound.ITEM),
	TROPICAL_FISH_BUCKET(
			BlockSound.ITEM),
	TROPICAL_FISH_SPAWN_EGG(
			BlockSound.ITEM),
	TUBE_CORAL(
			BlockSound.CORAL),
	TUBE_CORAL_BLOCK(
			BlockSound.CORAL),
	TUBE_CORAL_FAN(
			BlockSound.CORAL),
	TUBE_CORAL_WALL_FAN(
			BlockSound.CORAL),
	TURTLE_EGG(
			BlockSound.INCOMPLETE),
	TURTLE_HELMET(
			BlockSound.ITEM),
	TURTLE_SPAWN_EGG(
			BlockSound.ITEM),
	VEX_SPAWN_EGG(
			BlockSound.ITEM),
	VILLAGER_SPAWN_EGG(
			BlockSound.ITEM),
	VINDICATOR_SPAWN_EGG(
			BlockSound.ITEM),
	VINE(
			BlockSound.CROPS),
	VOID_AIR(
			BlockSound.NONE),
	WALL_TORCH(
			BlockSound.WOOD),
	WANDERING_TRADER_SPAWN_EGG(
			BlockSound.ITEM),
	WATER(
			BlockSound.NONE),
	WATER_BUCKET(	
			BlockSound.ITEM),
	WET_SPONGE(
			BlockSound.INCOMPLETE),
	WHEAT(
			BlockSound.CROPS),
	WHEAT_SEEDS(
			BlockSound.ITEM),
	WHITE_BANNER(
			BlockSound.WOOL),
	WHITE_BED(
			BlockSound.WOOD),
	WHITE_CARPET(
			BlockSound.WOOL),
	WHITE_CONCRETE(
			BlockSound.STONE),
	WHITE_CONCRETE_POWDER(
			BlockSound.SAND),
	WHITE_DYE(
			BlockSound.ITEM),
	WHITE_GLAZED_TERRACOTTA(
			BlockSound.STONE),
	WHITE_SHULKER_BOX(
			BlockSound.STONE),
	WHITE_STAINED_GLASS(
			BlockSound.GLASS,
			"STAINED_GLASS", (short) 0),
	WHITE_STAINED_GLASS_PANE(
			BlockSound.GLASS,
			"STAINED_GLASS_PANE", (short) 0),
	WHITE_TERRACOTTA(
			BlockSound.STONE),
	WHITE_TULIP(
			BlockSound.CROPS),
	WHITE_WALL_BANNER(
			BlockSound.WOOL),
	WHITE_WOOL(
			BlockSound.WOOL),
	WITCH_SPAWN_EGG(
			BlockSound.ITEM),
	WITHER_ROSE(
			BlockSound.CROPS),
	WITHER_SKELETON_SKULL(
			BlockSound.WOOD),
	WITHER_SKELETON_SPAWN_EGG(
			BlockSound.ITEM),
	WITHER_SKELETON_WALL_SKULL(
			BlockSound.WOOL),
	WOLF_SPAWN_EGG(
			BlockSound.ITEM),
	WOODEN_AXE(
			BlockSound.ITEM),
	WOODEN_HOE(
			BlockSound.ITEM),
	WOODEN_PICKAXE(
			BlockSound.ITEM),
	WOODEN_SHOVEL(
			BlockSound.ITEM),
	WOODEN_SWORD(
			BlockSound.ITEM),
	WRITABLE_BOOK(
			BlockSound.ITEM),
	WRITTEN_BOOK(
			BlockSound.ITEM),
	YELLOW_BANNER(
			BlockSound.WOOL),
	YELLOW_BED(
			BlockSound.WOOD),
	YELLOW_CARPET(
			BlockSound.WOOL),
	YELLOW_CONCRETE(
			BlockSound.STONE),
	YELLOW_CONCRETE_POWDER(
			BlockSound.SAND),
	YELLOW_DYE(
			BlockSound.ITEM),
	YELLOW_GLAZED_TERRACOTTA(
			BlockSound.STONE),
	YELLOW_SHULKER_BOX(
			BlockSound.STONE),
	YELLOW_STAINED_GLASS(
			BlockSound.GLASS,
			"STAINED_GLASS", (short) 4),
	YELLOW_STAINED_GLASS_PANE(
			BlockSound.GLASS,
			"STAINED_GLASS_PANE", (short) 4),
	YELLOW_TERRACOTTA(
			BlockSound.STONE),
	YELLOW_WALL_BANNER(
			BlockSound.WOOL),
	YELLOW_WOOL(
			BlockSound.WOOL),
	ZOMBIE_HEAD(
			BlockSound.WOOD),
	ZOMBIE_HORSE_SPAWN_EGG(
			BlockSound.ITEM),
	ZOMBIE_PIGMAN_SPAWN_EGG(
			BlockSound.ITEM),
	ZOMBIE_SPAWN_EGG(
			BlockSound.ITEM),
	ZOMBIE_VILLAGER_SPAWN_EGG(
			BlockSound.ITEM),
	ZOMBIE_WALL_HEAD(
			BlockSound.WOOD);
	
	private static Map<Material, CompatMaterial> newMap = new HashMap<>();
	private static Map<Material, Map<Short, CompatMaterial>> legacyMap = new HashMap<>();
	
	static {
		for (CompatMaterial cmat: CompatMaterial.values()) {
			cmat.resolve();
		}
	}
	
	@SuppressWarnings("deprecation")
	public static CompatMaterial of(ItemStack item) {
		if (newMap.containsKey(item.getType())) {
			return newMap.get(item.getType());
		}
		if (legacyMap.containsKey(item.getType())) {
			return legacyMap.get(item.getType()).get(item.getDurability());
		}
		return null;
	}
	
	@SuppressWarnings({ "deprecation" })
	public static CompatMaterial of(Block block) {
		if (newMap.containsKey(block.getType())) {
			return newMap.get(block.getType());
		}
		if (legacyMap.containsKey(block.getType())) {
			return legacyMap.get(block.getType()).get((short)block.getData());
		}
		return null;
	}
	
	public static CompatMaterial of(Material mat, short data) {
		if (newMap.containsKey(mat)) {
			return newMap.get(mat);
		}
		if (legacyMap.containsKey(mat)) {
			return legacyMap.get(mat).get(data);
		}
		return null;
	}
	
	public static boolean isAir(Material mat) {
		CompatMaterial cmat = CompatMaterial.of(mat, (short)0);
		return cmat == CompatMaterial.AIR || cmat == VOID_AIR || cmat == CompatMaterial.CAVE_AIR;
	}
	
	
	
	private final String legacy; 
	private final short data;
	private boolean usingLegacy = false;
	
	private BlockSound soundType;
	private Material mat;
	
	private CompatMaterial(BlockSound sound) {
		this.legacy = this.toString();
		this.data = 0;
	}
	
	private CompatMaterial(BlockSound soundType, String legacy, short data) {
		this.soundType = soundType;
		this.legacy = legacy;
		this.data = data;
	}
	
	private void resolve() {
		//Console.debug("Resolving: " + this.toString());
		try {
			this.mat = Material.valueOf(this.toString());
			newMap.put(this.mat, this);
		} catch (Exception e) {
			try {
				this.mat = Material.valueOf(this.legacy);
				usingLegacy = true;
				Map<Short, CompatMaterial> dataMap = legacyMap.getOrDefault(this.mat, new HashMap<>());
				dataMap.put(this.data, this);
				legacyMap.put(this.mat, dataMap);
			} catch (Exception e1) {
				e.printStackTrace();
			}
		}
	}
	
	public BlockSound getBlockSound() {
		return soundType;
	}
	
	public Material asMaterial() {
		return this.mat;
	}
	
	@SuppressWarnings("deprecation")
	public void applyMaterial(ItemStack item) {
		item.setType(mat);
		if (usingLegacy) {
			item.setDurability(data);
		}
	}
	
	public String getLegacyName() {
		return legacy;
	}
	
	public short getLegacyData() {
		return data;
	}
	
	
	
	@SuppressWarnings("serial")
	public static enum BlockSound {
		
		ANVIL(new HashMap<SoundType, CompatSound>() {{
			put(SoundType.HIT, CompatSound.BLOCK_ANVIL_HIT);
			put(SoundType.STEP, CompatSound.BLOCK_ANVIL_STEP);
			put(SoundType.BREAK, CompatSound.BLOCK_ANVIL_BREAK);
			put(SoundType.PLACE, CompatSound.BLOCK_ANVIL_PLACE);
		}}),
		
		BAMBOO(new HashMap<SoundType, CompatSound>() {{
			put(SoundType.HIT, CompatSound.BLOCK_BAMBOO_HIT);
			put(SoundType.STEP, CompatSound.BLOCK_BAMBOO_STEP);
			put(SoundType.BREAK, CompatSound.BLOCK_BAMBOO_BREAK);
			put(SoundType.PLACE, CompatSound.BLOCK_BAMBOO_PLACE);
		}}),
		
		CORAL(new HashMap<SoundType, CompatSound>() {{
			put(SoundType.HIT, CompatSound.BLOCK_CORAL_BLOCK_HIT);
			put(SoundType.STEP, CompatSound.BLOCK_CORAL_BLOCK_STEP);
			put(SoundType.BREAK, CompatSound.BLOCK_CORAL_BLOCK_BREAK);
			put(SoundType.PLACE, CompatSound.BLOCK_CORAL_BLOCK_PLACE);
		}}),
		
		CROPS(new HashMap<SoundType, CompatSound>() {{
			put(SoundType.HIT, CompatSound.BLOCK_STONE_HIT);
			put(SoundType.STEP, CompatSound.BLOCK_GRASS_STEP);
			put(SoundType.BREAK, CompatSound.BLOCK_CROP_BREAK);
			put(SoundType.PLACE, CompatSound.ITEM_CROP_PLANT);
		}}),
		
		GLASS(new HashMap<SoundType, CompatSound>() {{
			put(SoundType.HIT, CompatSound.BLOCK_GLASS_HIT);
			put(SoundType.STEP, CompatSound.BLOCK_GLASS_STEP);
			put(SoundType.BREAK, CompatSound.BLOCK_GLASS_BREAK);
			put(SoundType.PLACE, CompatSound.BLOCK_GLASS_PLACE);
		}}),
		
		GRASS(new HashMap<SoundType, CompatSound>() {{
			put(SoundType.HIT, CompatSound.BLOCK_GRASS_HIT);
			put(SoundType.STEP, CompatSound.BLOCK_GRASS_STEP);
			put(SoundType.BREAK, CompatSound.BLOCK_GRASS_BREAK);
			put(SoundType.PLACE, CompatSound.BLOCK_GRASS_PLACE);
		}}),
		
		GRAVEL(new HashMap<SoundType, CompatSound>() {{
			put(SoundType.HIT, CompatSound.BLOCK_GRAVEL_HIT);
			put(SoundType.STEP, CompatSound.BLOCK_GRAVEL_STEP);
			put(SoundType.BREAK, CompatSound.BLOCK_GRAVEL_BREAK);
			put(SoundType.PLACE, CompatSound.BLOCK_GRAVEL_PLACE);
		}}),
		
		HONEY(new HashMap<SoundType, CompatSound>() {{
			put(SoundType.HIT, CompatSound.BLOCK_HONEY_BLOCK_HIT);
			put(SoundType.STEP, CompatSound.BLOCK_HONEY_BLOCK_STEP);
			put(SoundType.BREAK, CompatSound.BLOCK_HONEY_BLOCK_BREAK);
			put(SoundType.PLACE, CompatSound.BLOCK_HONEY_BLOCK_PLACE);
		}}),
		
		LEAVES(new HashMap<SoundType, CompatSound>() {{
			put(SoundType.HIT, CompatSound.BLOCK_GRASS_HIT);
			put(SoundType.STEP, CompatSound.BLOCK_GRASS_STEP);
			put(SoundType.BREAK, CompatSound.BLOCK_GRASS_BREAK);
			put(SoundType.PLACE, CompatSound.BLOCK_GRASS_PLACE);
		}}),
		
		METAL(new HashMap<SoundType, CompatSound>() {{
			put(SoundType.HIT, CompatSound.BLOCK_METAL_HIT);
			put(SoundType.STEP, CompatSound.BLOCK_METAL_STEP);
			put(SoundType.BREAK, CompatSound.BLOCK_METAL_BREAK);
			put(SoundType.PLACE, CompatSound.BLOCK_METAL_PLACE);
		}}),
		
		SAND(new HashMap<SoundType, CompatSound>() {{
			put(SoundType.HIT, CompatSound.BLOCK_SAND_HIT);
			put(SoundType.STEP, CompatSound.BLOCK_SAND_STEP);
			put(SoundType.BREAK, CompatSound.BLOCK_SAND_BREAK);
			put(SoundType.PLACE, CompatSound.BLOCK_SAND_PLACE);
		}}),
		
		SLIME(new HashMap<SoundType, CompatSound>() {{
			put(SoundType.HIT, CompatSound.BLOCK_SLIME_BLOCK_HIT);
			put(SoundType.STEP, CompatSound.BLOCK_SLIME_BLOCK_STEP);
			put(SoundType.BREAK, CompatSound.BLOCK_SLIME_BLOCK_BREAK);
			put(SoundType.PLACE, CompatSound.BLOCK_SLIME_BLOCK_PLACE);
		}}),
		
		STONE(new HashMap<SoundType, CompatSound>() {{
			put(SoundType.HIT, CompatSound.BLOCK_STONE_HIT);
			put(SoundType.STEP, CompatSound.BLOCK_STONE_STEP);
			put(SoundType.BREAK, CompatSound.BLOCK_STONE_BREAK);
			put(SoundType.PLACE, CompatSound.BLOCK_STONE_PLACE);
		}}),
		
		WOOD(new HashMap<SoundType, CompatSound>() {{
			put(SoundType.HIT, CompatSound.BLOCK_WOOD_HIT);
			put(SoundType.STEP, CompatSound.BLOCK_WOOD_STEP);
			put(SoundType.BREAK, CompatSound.BLOCK_WOOD_BREAK);
			put(SoundType.PLACE, CompatSound.BLOCK_WOOD_PLACE);
		}}),
		
		WOOL(new HashMap<SoundType, CompatSound>() {{
			put(SoundType.HIT, CompatSound.BLOCK_WOOL_HIT);
			put(SoundType.STEP, CompatSound.BLOCK_WOOL_STEP);
			put(SoundType.BREAK, CompatSound.BLOCK_WOOL_BREAK);
			put(SoundType.PLACE, CompatSound.BLOCK_WOOL_PLACE);
		}}),
		
		SWEET_BERRY,
		LADDER,
		
		ITEM,
		NONE,
		INCOMPLETE;
		
		private Map<SoundType, CompatSound> sounds;
		
		private BlockSound(Map<SoundType, CompatSound> sounds) {
			this.sounds = sounds;
		}
		
		private BlockSound() {
			this.sounds = new HashMap<>();
		}
		
		public CompatSound getSoundType(SoundType type) {
			return sounds.getOrDefault(type, BlockSound.STONE.getSoundType(type));
		}
		
	}
	
	public static enum SoundType {
		HIT,
		STEP,
		PLACE,
		BREAK;
	}
	
}
