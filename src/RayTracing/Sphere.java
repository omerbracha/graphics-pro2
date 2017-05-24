package RayTracing;

public class Sphere extends Shape{
	//TODO check xyz int or double
	public double cx, cy, cz; 	// center positions 
	public double radius; 		// radius
	//public int mat_idx;		// material index.
	public int index;
	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}
	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	/**
	 * @return the cx
	 */
	public double getCx() {
		return cx;
	}
	/**
	 * @param cx the cx to set
	 */
	public void setCx(double cx) {
		this.cx = cx;
	}
	/**
	 * @return the cy
	 */
	public double getCy() {
		return cy;
	}
	/**
	 * @param cy the cy to set
	 */
	public void setCy(double cy) {
		this.cy = cy;
	}
	/**
	 * @return the cz
	 */
	public double getCz() {
		return cz;
	}
	/**
	 * @param cz the cz to set
	 */
	public void setCz(double cz) {
		this.cz = cz;
	}
	/**
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}
	/**
	 * @param radius the radius to set
	 */
	public void setRadius(double radius) {
		this.radius = radius;
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
	public Vector getPos() {
		return new Vector(getCx(), getCy(), getCz());
	}
	public Vector getNormal(Vector p) {
		Vector n = p.sub(this.getPos());
		n = n.normalize();
		return n;
	}
	
	
}
