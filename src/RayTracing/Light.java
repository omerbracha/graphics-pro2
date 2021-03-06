package RayTracing;

public class Light {
	public Vector position;
	public double r;	//light color (r)
	public double g;	//light color (g)	
	public double b;	//light color (b)
	public double spec;	// specular intensity
	public double shadow;// shadow intensity
	public int width;	//light width / radius (used for soft shadows)
	public int index;
	
	public Light() {
		this(new Vector(), 0, 0, 0, 0, 0, 0, 0);
	}
	
	public Light(Vector position, double r, double g, double b, int spec, double shadow, int width, int index) {
		super();
		this.position = position;
		this.r = r;
		this.g = g;
		this.b = b;
		this.spec = spec;
		this.shadow = shadow;
		this.width = width;
		this.index = index;
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
	public Vector getPosition() {
		return position;
	}
	public void setPosition(Vector position) {
		this.position = position;
	}
	public double getPx() {
		return position.x;
	}
	public void setPx(double px) {
		this.position.x = px;
	}
	public double getPy() {
		return position.y;
	}
	public void setPy(double py) {
		this.position.y = py;
	}
	public double getPz() {
		return position.z;
	}
	public void setPz(double pz) {
		this.position.z = pz;
	}
	/**
	 * @return the r
	 */
	public double getR() {
		return r;
	}
	/**
	 * @param r the r to set
	 */
	public void setR(double r) {
		this.r = r;
	}
	/**
	 * @return the g
	 */
	public double getG() {
		return g;
	}
	/**
	 * @param g the g to set
	 */
	public void setG(double g) {
		this.g = g;
	}
	/**
	 * @return the b
	 */
	public double getB() {
		return b;
	}
	/**
	 * @param b the b to set
	 */
	public void setB(double b) {
		this.b = b;
	}
	/**
	 * @return the spec
	 */
	public double getSpec() {
		return spec;
	}
	/**
	 * @param spec the spec to set
	 */
	public void setSpec(double spec) {
		this.spec = spec;
	}
	/**
	 * @return the shadow
	 */
	public double getShadow() {
		return shadow;
	}
	/**
	 * @param shadow the shadow to set
	 */
	public void setShadow(double shadow) {
		this.shadow = shadow;
	}
	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
}

