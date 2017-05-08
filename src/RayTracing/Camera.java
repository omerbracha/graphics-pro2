package RayTracing;

public class Camera {

	public double pX, pY, pZ; 	// positions
	public double laX, laY, laZ; // look at positions
	public int uvX, uvY, uvZ; 	// up vector
	public double sc_dist; 		// screen distance from camera 
	public int sw_from_cam;		// screen width from camera
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
	 * @return the pX
	 */
	public double getpX() {
		return pX;
	}
	/**
	 * @param pX the pX to set
	 */
	public void setpX(double pX) {
		this.pX = pX;
	}
	/**
	 * @return the pY
	 */
	public double getpY() {
		return pY;
	}
	/**
	 * @param pY the pY to set
	 */
	public void setpY(double pY) {
		this.pY = pY;
	}
	/**
	 * @return the pZ
	 */
	public double getpZ() {
		return pZ;
	}
	/**
	 * @param pZ the pZ to set
	 */
	public void setpZ(double pZ) {
		this.pZ = pZ;
	}
	/**
	 * @return the laX
	 */
	public double getLaX() {
		return laX;
	}
	/**
	 * @param laX the laX to set
	 */
	public void setLaX(double laX) {
		this.laX = laX;
	}
	/**
	 * @return the laY
	 */
	public double getLaY() {
		return laY;
	}
	/**
	 * @param laY the laY to set
	 */
	public void setLaY(double laY) {
		this.laY = laY;
	}
	/**
	 * @return the laZ
	 */
	public double getLaZ() {
		return laZ;
	}
	/**
	 * @param laZ the laZ to set
	 */
	public void setLaZ(double laZ) {
		this.laZ = laZ;
	}
	/**
	 * @return the uvX
	 */
	public int getUvX() {
		return uvX;
	}
	/**
	 * @param uvX the uvX to set
	 */
	public void setUvX(int uvX) {
		this.uvX = uvX;
	}
	/**
	 * @return the uvY
	 */
	public int getUvY() {
		return uvY;
	}
	/**
	 * @param uvY the uvY to set
	 */
	public void setUvY(int uvY) {
		this.uvY = uvY;
	}
	/**
	 * @return the uvZ
	 */
	public int getUvZ() {
		return uvZ;
	}
	/**
	 * @param uvZ the uvZ to set
	 */
	public void setUvZ(int uvZ) {
		this.uvZ = uvZ;
	}
	/**
	 * @return the sc_dist
	 */
	public double getSc_dist() {
		return sc_dist;
	}
	/**
	 * @param sc_dist the sc_dist to set
	 */
	public void setSc_dist(double sc_dist) {
		this.sc_dist = sc_dist;
	}
	/**
	 * @return the sw_from_cam
	 */
	public int getSw_from_cam() {
		return sw_from_cam;
	}
	/**
	 * @param sw_from_cam the sw_from_cam to set
	 */
	public void setSw_from_cam(int sw_from_cam) {
		this.sw_from_cam = sw_from_cam;
	}
	}
