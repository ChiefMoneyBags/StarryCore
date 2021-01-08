package me.chiefbeef.core.utility.geometry;

import org.bukkit.Location;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

public class Vectors {
	
    /**
     * Rotates a vector around the X axis at an angle
     * 
     * @param v Starting vector
     * @param angle How much to rotate
     * @return The starting vector rotated
     */
    public static final Vector rotateAroundAxisX(Vector v, double angle) {
        double y, z, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        y = v.getY() * cos - v.getZ() * sin;
        z = v.getY() * sin + v.getZ() * cos;
        return v.setY(y).setZ(z);
    }

    /**
     * Rotates a vector around the Y axis at an angle
     * 
     * @param v Starting vector
     * @param angle How much to rotate
     * @return The starting vector rotated
     */
    public static final Vector rotateAroundAxisY(Vector v, double angle) {
        double x, z, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        x = v.getX() * cos + v.getZ() * sin;
        z = v.getX() * -sin + v.getZ() * cos;
        return v.setX(x).setZ(z);
    }

    /**
     * Rotates a vector around the Z axis at an angle
     * 
     * @param v Starting vector
     * @param angle How much to rotate
     * @return The starting vector rotated
     */
    public static final Vector rotateAroundAxisZ(Vector v, double angle) {
        double x, y, cos, sin;
        cos = Math.cos(angle);
        sin = Math.sin(angle);
        x = v.getX() * cos - v.getY() * sin;
        y = v.getX() * sin + v.getY() * cos;
        return v.setX(x).setY(y);
    }

    /**
     * Rotates a vector around the X, Y, and Z axes
     * 
     * @param v The starting vector
     * @param angleX The change angle on X
     * @param angleY The change angle on Y
     * @param angleZ The change angle on Z
     * @return The starting vector rotated
     */
    public static final Vector rotateVector(Vector v, double angleX, double angleY, double angleZ) {
        rotateAroundAxisX(v, angleX);
        rotateAroundAxisY(v, angleY);
        rotateAroundAxisZ(v, angleZ);
        return v;
    }

    /**
     * Rotate a vector about a location using that location's direction
     *
     * @param v The starting vector
     * @param location The location to rotate around
     * @return The starting vector rotated
     */
    public static final Vector rotateVector(Vector v, Location location) {
        return rotateVector(v, location.getYaw(), location.getPitch());
    }

    /**
     * This handles non-unit vectors, with yaw and pitch instead of X,Y,Z angles.
     *
     * @param v The starting vector
     * @param yawDegrees The yaw offset in degrees
     * @param pitchDegrees The pitch offset in degrees
     * @return The starting vector rotated
     */
    public static final Vector rotateVector(Vector v, float yawDegrees, float pitchDegrees) {
        double yaw = Math.toRadians(-1 * (yawDegrees + 90));
        double pitch = Math.toRadians(-pitchDegrees);

        double cosYaw = Math.cos(yaw);
        double cosPitch = Math.cos(pitch);
        double sinYaw = Math.sin(yaw);
        double sinPitch = Math.sin(pitch);

        double initialX, initialY, initialZ;
        double x, y, z;

        // Z_Axis rotation (Pitch)
        initialX = v.getX();
        initialY = v.getY();
        x = initialX * cosPitch - initialY * sinPitch;
        y = initialX * sinPitch + initialY * cosPitch;

        // Y_Axis rotation (Yaw)
        initialZ = v.getZ();
        initialX = x;
        z = initialZ * cosYaw - initialX * sinYaw;
        x = initialZ * sinYaw + initialX * cosYaw;

        return new Vector(x, y, z);
    }

    /**
     * Gets the angle toward the X axis
     * 
     * @param vector The vector to check
     * @return The angle toward the X axis
     */
    public static final double angleToXAxis(Vector vector) {
        return Math.atan2(vector.getX(), vector.getY());
    }
    
    /**
     * Draws a line perpendicular to the line segment given at the point (check) and returns a scalar
     * which when multiplied by the start of the line will resolve the intersection point of the
     * 2 lines.
     * Will return <0 if the point intersects behind the lines start point.
     * Will return higher than >1 if the point intersects past the lines end point.
     * 
     * @param start Start of the line
     * @param end End of the line
     * @param check Point in space
     * @return The scalar of the intersection.
     */
    public static double perpendicularScalar(Vector start, Vector end, Vector check) {
    	Vector a = check.subtract(start);
    	Vector b = end.subtract(start);
    	return a.dot(b) / b.dot(b);
    }
    
    /**
     * Gets the point on a line closest to another point in space.
     * Caution, the returned point may be outside the bounds of the segment given
     * as the line, when checked, is basically of infinite length. 
     * 
     * @param start Start of the line
     * @param end End of the line
     * @param check Point in space
     * @return The point of intersection
     */
    public static Vector perpendicularPoint(Vector start, Vector end, Vector check) {
    	return start.clone().add(end.subtract(start).multiply(perpendicularScalar(start, end, check)));
    }
    
    /**
     * Check if a line drawn perpendicular from the line given along point 'check' will
     * intersect the line segment given.
     * 
     * @param start Start of the line
     * @param end End of the line
     * @param check Point in space
     * @return True if they intersect
     */
    public static boolean intersectsSegment(Vector start, Vector end, Vector check) {
    	double scalar = perpendicularScalar(start, end, check);
    	return scalar >= 0 && scalar <= 1;
    }
    
    /**
     * Get the intersection point of a line and a box.
     * 
     * @param box The BoundingBox
     * @param start The start of the line
     * @param end The end of the line
     * @return The point where the line intersects the box.
     */
    public static Vector intersectOf(BoundingBox box, Vector start, Vector end) {
    	double fLow = 0;
    	double fHigh = 1;
    	
    	for (int i = 0; i < 3; i++) {
    		double fDimLow, fDimHigh;
    		double a0 = 0, a1 = 0, abl = 0, abm = 0;
        	switch (i) {
        	case 0:
        		a0 = start.getX();
        		a1 = end.getX();
        		abl = box.getMinX();
        		abm = box.getMaxX();
        		break;
        	case 1:
        		a0 = start.getY();
        		a1 = end.getY();
        		abl = box.getMinY();
        		abm = box.getMaxY();
        		break;
        	case 2:
        		a0 = start.getZ();
        		a1 = end.getZ();
        		abl = box.getMinZ();
        		abm = box.getMaxZ();
        		break;
        	}
        	
        	fDimLow = (abl - a0)/(a1 - a0);
        	fDimHigh = (abm - a0)/(a1 - a0);

        	if (fDimHigh < fDimLow) {
        		double placeholder = fDimHigh;
        		fDimHigh = fDimLow;
        		fDimLow = placeholder;
        	}
        	fLow = fDimLow > fLow ? fDimLow : fLow;
        	fHigh = fDimHigh < fHigh ? fDimHigh : fHigh;
    	}
    	return start.add(end.subtract(start).multiply(fLow));
    }
}
