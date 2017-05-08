package RayTracing;

public class Sphere {
	public int cx, cy, cz; 	// center positions 
	public int radius; 		// radius
	public int mat_idx;		// material index.
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
	public int getCx() {
		return cx;
	}
	/**
	 * @param cx the cx to set
	 */
	public void setCx(int cx) {
		this.cx = cx;
	}
	/**
	 * @return the cy
	 */
	public int getCy() {
		return cy;
	}
	/**
	 * @param cy the cy to set
	 */
	public void setCy(int cy) {
		this.cy = cy;
	}
	/**
	 * @return the cz
	 */
	public int getCz() {
		return cz;
	}
	/**
	 * @param cz the cz to set
	 */
	public void setCz(int cz) {
		this.cz = cz;
	}
	/**
	 * @return the radius
	 */
	public int getRadius() {
		return radius;
	}
	/**
	 * @param radius the radius to set
	 */
	public void setRadius(int radius) {
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
	
	
}
