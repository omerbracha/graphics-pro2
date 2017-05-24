package RayTracing;

public abstract class Shape {
	public double x,y,z;
	public Vector position;
	public int mat_idx; //material index
	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}
	/**
	 * @return the z
	 */
	public double getZ() {
		return z;
	}
	/**
	 * @param z the z to set
	 */
	public void setZ(double z) {
		this.z = z;
	}
	/**
	 * @return the position
	 */
	public Vector getPos() {
		return position;
	}
	public Shape(double x, double y, double z, Vector position, int mat_idx) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.position = position;
		this.mat_idx = mat_idx;
	}
	
	public Shape(){
		this(0,0,0,null,0);
	}
	
	/**
	 * @param position the position to set
	 */
	public void setPosition(Vector position) {
		this.position = position;
	}
	/**
	 * @return the mat_idx
	 */
	public int getMat_idx() {
		return mat_idx;
	}
	/**
	 * @param mat_idx the mat_idx to set
	 */
	public void setMat_idx(int mat_idx) {
		this.mat_idx = mat_idx;
	}
	public void setIndex(int cnt_sph) {
		// TODO 
		
	}
	public void setCx(double parseDouble) {
		// TODO Auto-generated method stub
		
	}
	public void setCy(double parseDouble) {
		// TODO Auto-generated method stub
		
	}
	public void setCz(double parseDouble) {
		// TODO Auto-generated method stub
		
	}
	public void setRadius(double parseDouble) {
		// TODO Auto-generated method stub
		
	}
	public void setNx(double parseDouble) {
		// TODO Auto-generated method stub
		
	}
	public void setNy(double parseDouble) {
		// TODO Auto-generated method stub
		
	}
	public void setNz(double parseDouble) {
		// TODO Auto-generated method stub
		
	}
	public void setOffset(double parseDouble){
		
	}
	public void setP0x(double parseDouble) {
		// TODO Auto-generated method stub
		
	}
	public void setP0y(double parseDouble) {
		// TODO Auto-generated method stub
		
	}
	public void setP0z(double parseDouble) {
		// TODO Auto-generated method stub
		
	}
	public void setP1x(double parseDouble) {
		// TODO Auto-generated method stub
		
	}
	public void setP1y(double parseDouble) {
		// TODO Auto-generated method stub
		
	}
	public void setP1z(double parseDouble) {
		// TODO Auto-generated method stub
		
	}
	public void setP2x(double parseDouble) {
		// TODO Auto-generated method stub
		
	}
	public void setP2y(double parseDouble) {
		// TODO Auto-generated method stub
		
	}
	public void setP2z(double parseDouble) {
		// TODO Auto-generated method stub
		
	}
	public Vector getNormal() {
		if (this.getClass() == Sphere.class) {
			Sphere sph = (Sphere) this;
			return sph.getNormal();
		}
		if (this.getClass() == Triangle.class) {
			Triangle trn = (Triangle) this;
			return trn.getNormal();
		}
		if (this.getClass() == Plane.class) {
			Plane pln = (Plane) this;
			return pln.getNormal();
		}
		return null;
	}
	public Vector getR(Vector endPoint, Light licht) {
		Vector d = endPoint.sub(licht.getPosition());
		Vector n = this.getNormal(endPoint);
		Vector r = d.sub(n.mult(2 * d.dot(n)));
		return r;
	}
	
}
