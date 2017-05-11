package RayTracing;

public class Camera {
	
	public Vector position, lookat, up;
	//public double pX, pY, pZ; 	// positions
	//public double laX, laY, laZ; // look at positions
	//public int uvX, uvY, uvZ; 	// up vector
	public double sc_dist; 		// screen distance from camera 
	public int sw_from_cam;		// screen width from camera
	public int index;
	
	
	public Vector getPosition() {
		return position;
	}
	public void setPosition(Vector position) {
		this.position = position;
	}
	public Vector getLookat() {
		return lookat;
	}
	public void setLookat(Vector lookat) {
		this.lookat = lookat;
	}
	public Vector getUp() {
		return up;
	}
	public void setUp(Vector up) {
		this.up = up;
	}
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
		return position.x;
	}
	/**
	 * @param pX the pX to set
	 */
	public void setpX(double pX) {
		this.position.x = pX;
	}
	/**
	 * @return the pY
	 */
	public double getpY() {
		return position.y;
	}
	/**
	 * @param pY the pY to set
	 */
	public void setpY(double pY) {
		this.position.y = pY;
	}
	/**
	 * @return the pZ
	 */
	public double getpZ() {
		return position.z;
	}
	/**
	 * @param pZ the pZ to set
	 */
	public void setpZ(double pZ) {
		this.position.z = pZ;
	}
	/**
	 * @return the laX
	 */
	public double getLaX() {
		return lookat.x;
	}
	/**
	 * @param laX the laX to set
	 */
	public void setLaX(double laX) {
		this.lookat.x = laX;
	}
	/**
	 * @return the laY
	 */
	public double getLaY() {
		return lookat.y;
	}
	/**
	 * @param laY the laY to set
	 */
	public void setLaY(double laY) {
		this.lookat.y = laY;
	}
	/**
	 * @return the laZ
	 */
	public double getLaZ() {
		return lookat.z;
	}
	/**
	 * @param laZ the laZ to set
	 */
	public void setLaZ(double laZ) {
		this.lookat.z = laZ;
	}
	/**
	 * @return the uvX
	 */
	public double getUvX() {
		return up.x;
	}
	/**
	 * @param uvX the uvX to set
	 */
	public void setUvX(int uvX) {
		this.up.x = uvX;
	}
	/**
	 * @return the uvY
	 */
	public double getUvY() {
		return up.y;
	}
	/**
	 * @param uvY the uvY to set
	 */
	public void setUvY(int uvY) {
		this.up.y = uvY;
	}
	/**
	 * @return the uvZ
	 */
	public double getUvZ() {
		return up.z;
	}
	/**
	 * @param uvZ the uvZ to set
	 */
	public void setUvZ(int uvZ) {
		this.up.z = uvZ;
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
