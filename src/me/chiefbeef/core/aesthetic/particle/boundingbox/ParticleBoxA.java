package me.chiefbeef.core.aesthetic.particle.boundingbox;

import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

import me.chiefbeef.core.aesthetic.particle.Particles;

public class ParticleBoxA extends BukkitRunnable {

	private Particle e1;
	
	private BoundingBox box;
	
	private World world;
	
	private int
	wait,
	step;
	
	private Vector a, b, c, d, e, f;
	
	double
	spacing,
	
	minx,
	miny,
	minz,
	maxx,
	maxy,
	maxz;
	
	public ParticleBoxA(BoundingBox box, World world, Particle e1) {
		this.spacing = 0.1;
		this.e1 = e1;
		this.box = box;
		this.world = world;
		this.a = box.getMax().add(new Vector(0.1, 0.1, 0.1));
		this.b = a.clone();
		this.c = a.clone();
	}

	//TODO change this shit to >= not >
	@Override
	public void run() {
		if (wait < 8) {
			wait++;
			return;
		}
		boolean progress = true;
		if (step == 0) {
			double edgeA = a.getZ();
			if (edgeA > box.getMinZ()) {
				a.setZ(edgeA - spacing);
				progress = false;
			}
			
			double edgeB = b.getX();
			if (edgeB > box.getMinX()) {
				b.setX(edgeB - spacing);
				progress = false;
			}
			
			double edgeC = c.getY();
			if (edgeC > box.getMinY()) {
				c.setY(edgeC - spacing);
				progress = false;
			}
			
			if (progress) {
				step++;
			}
		} else if (step == 1) {
			if (d == null) d = a.clone();
			if (e == null) e = b.clone();
			if (f == null) f = c.clone();
			
			double edgeA = a.getY();
			if (edgeA > box.getMinY()) {
				a.setY(edgeA - spacing);
				progress = false;
			}
			
			double edgeB = b.getY();
			if (edgeB > box.getMinY()) {
				b.setY(edgeB - spacing);
				progress = false;
			}
			
			double edgeC = c.getZ();
			if (edgeC > box.getMinZ()) {
				c.setZ(edgeC - spacing);
				progress = false;
			}
			
			double edgeD = d.getX();
			if (edgeD > box.getMinX()) {
				d.setX(edgeD - spacing);
				progress = false;
			}
			
			double edgeE = e.getZ();
			if (edgeE > box.getMinZ()) {
				e.setZ(edgeE - spacing);
				progress = false;
			}
			
			double edgeF = f.getX();
			if (edgeF > box.getMinX()) {
				f.setX(edgeF - spacing);
				progress = false;
			}
			
			if (progress) {
				step++;
			}
		} else if (step == 2) {
			double edgeA = a.getX();
			if (edgeA > box.getMinX()) {
				a.setX(edgeA - spacing);
				progress = false;
			}
			
			double edgeB = b.getZ();
			if (edgeB > box.getMinZ()) {
				b.setZ(edgeB - spacing);
				progress = false;
			}
			
			c = null;
			
			double edgeD = d.getY();
			if (edgeD > box.getMinY()) {
				d.setY(edgeD - spacing);
				progress = false;
			}
			
			e = null;
			
			f = null;
			
			if (progress) {
				step++;
			}
		}
		if (a != null) Particles.play(e1, a.toLocation(world), 1);
		if (b != null) Particles.play(e1, b.toLocation(world), 1);
		if (c != null) Particles.play(e1, c.toLocation(world), 1);
		if (d != null) Particles.play(e1, d.toLocation(world), 1);
		if (e != null) Particles.play(e1, e.toLocation(world), 1);
		if (f != null) Particles.play(e1, f.toLocation(world), 1);
		
		if (step == 3) {
			a = box.getMax().add(new Vector(0.1, 0.1, 0.1));
			b = a.clone();
			c = a.clone();
			d = null;
			e = null;
			f = null;
			step = 0;
		}
	}

	
	
}
