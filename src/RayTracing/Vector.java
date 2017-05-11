package RayTracing;

import javax.print.attribute.standard.MediaSize.Other;

public class Vector {
	public double x, y, z;
	public double size;
	
	public Vector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.size = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z,  2));
	}
	
	public Vector() {
		new Vector(0,0,0);
	}
	
	public double dot(Vector other) {
		return (this.x*other.x + this.y*other.y + this.z*other.z);
	}
	
	public Vector cross(Vector other) {
		Vector vec = new Vector();
		vec.x = this.y*other.z - this.z*other.y;
		vec.y = this.z*other.x - this.x*other.z;
		vec.z = this.x*other.y - this.y*other.x;
		return vec;
	}
	
	public Vector add(Vector other) {
		Vector vec = new Vector();
		vec.x = this.x + other.x;
		vec.y = this.y + other.y;
		vec.z = this.z + other.z;
		return vec;
	}
	
	public Vector sub(Vector other) {
		Vector vec = new Vector();
		vec.x = this.x - other.x;
		vec.y = this.y - other.y;
		vec.z = this.z - other.z;
		return vec;
	}
	
	public void mult(int i) {
		this.x = this.x*i;
		this.y = this.y*i;
		this.z = this.z*i;
	}
}
