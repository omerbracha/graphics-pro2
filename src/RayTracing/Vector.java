package RayTracing;


public class Vector {
	public double x, y, z;
	public double size;
	
	public Vector(double x, double y, double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.size = Math.sqrt(x*x + y*y + z*z);
	}
	
	public Vector() {
		this(0,0,0);
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
	
	public Vector mult(double i) {
		Vector V = new Vector();
		V.x = this.x*i;
		V.y = this.y*i;
		V.z = this.z*i;
		return V;
	}
}
