package me.chiefbeef.core.utility;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

public class Blocks {
	public static Vector blockFaceCenter(Block b, BlockFace face) {
		Location center = b.getLocation().add(0.5, 0, 0.5);
		
		switch (face) {
		case DOWN:
			return center.subtract(0, 0.5, 0).toVector();
		case EAST:
			return center.add(0.5, 0 , 0).toVector();
		case NORTH:
			return center.subtract(0, 0, 0.5).toVector();
		case SOUTH:
			return center.add(0, 0, 0.5).toVector();
		case UP:
			return center.add(0, 0.5, 0).toVector();
		case WEST:
			return center.subtract(0.5, 0, 0).toVector();
		default:
			Console.generateException("Some rand never went back to finish this method and figure out what the blockface (" + face.toString() + ") does.");
			break;
		}
		return center.toVector();
	}
	
	//TODO this may be broken idk. this doesn't seem right, "x <= maxX" vs "x < maxX". May add blocks outside of BoundingBox
	/**
	* I added a special check that ensures the bounding box overlaps the block being checked as a safety measure
	* Only issue would be checking a couple blocks that don't need to be checked.
	*/
	public static Block[] of(BoundingBox box, World world) {
		Block
			min = box.getMin().toLocation(world).getBlock(), max = box.getMax().toLocation(world).getBlock();
		
		double 
			maxX = max.getX(), maxY = max.getY(), maxZ = max.getZ(),
			minX = min.getX(), minY = min.getY(), minZ = min.getZ();
		
		List<Block>
			blocks = new ArrayList<>();
		
		for (double x = minX; x <= maxX; x++) {
			for (double y = minY; y <= maxY; y++) {
				for (double z = minZ; z <= maxZ; z++) {
					Block check = world.getBlockAt(new Location(world, x, y, z));
					if (box.overlaps(check.getBoundingBox())) blocks.add(check);
				}	
			}	
		}
		return blocks.toArray(new Block[blocks.size()]);
	}
	
	public static Location center(Block b) {
		return b.getLocation().add(0.5, 0.5, 0.5);
	}
	
	//TODO fix illegal BlockFaces (Up / Down)
	public static Material convertTo(Material mat, BlockFace face) {
		switch (mat) {
		case TORCH:
			if (isWall(face)) 
				return Material.WALL_TORCH;
			return Material.TORCH;
		case REDSTONE_TORCH:
			if (isWall(face)) 
				return Material.REDSTONE_WALL_TORCH;
			return Material.REDSTONE_TORCH;
		default:
			return mat;
		}
	}
	
	public static boolean isWall(BlockFace face) {
		return (face == BlockFace.EAST || face == BlockFace.NORTH || face == BlockFace.SOUTH || face == BlockFace.WEST);
	}
	
	
	
    public static Vector blockFaceNormal(Vector location, Block b, BlockFace face) {
    	Vector center = blockFaceCenter(b, face);
    	switch (face) {
		case UP:
		case DOWN:
			return center.clone().setX(location.getX()).setZ(location.getZ());
		case NORTH:
		case SOUTH:
			return center.clone().setX(location.getX()).setY(location.getY());
		case EAST:
		case WEST:
			return center.clone().setY(location.getY()).setZ(location.getZ());
		default:
			Console.generateException("Some rand never went back to finish this method and figure out what the blockface (" + face.toString() + ") does.");
			return null;
		}
    }
	
    public static Vector moveLeft(Vector point, BlockFace face, double distance) {
    	if (distance < 0) {
    		Console.generateException("Distance cannot be negetive!");
    		return null;
    	}
    	switch (face) {
		case UP:
		case DOWN:
			return point.clone().add(new Vector(-distance, 0, 0));
		case NORTH:
			return point.clone().add(new Vector(distance, 0, 0));
		case SOUTH:
			return point.clone().add(new Vector(-distance, 0, 0));
		case EAST:
			return point.clone().add(new Vector(0, 0, distance));
		case WEST:
			return point.clone().add(new Vector(0, 0, -distance));
		default:
			Console.generateException("Some rand never went back to finish this method and figure out what the blockface (" + face.toString() + ") does.");
			return null;
		}
    }
    
	public static enum BlockFaceDirection {
		UP,
		DOWN,
		LEFT,
		RIGHT,
		
		UP_LEFT,
		UP_RIGHT,
		DOWN_LEFT,
		DOWN_RIGHT;
	}
}
