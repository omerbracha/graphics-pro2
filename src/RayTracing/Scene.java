package RayTracing;

import java.util.ArrayList;

public class Scene {
	public Camera cam;
	public MySet mySet;
	public ArrayList<Sphere> spheres;
	public ArrayList<Triangle> triangles;
	public ArrayList<Light> lights;
	public ArrayList<Plane> planes;
	public ArrayList<Material> materials;
	
	private ArrayList<Shape> shapes;
	
	public Scene() {
		super();
		this.mySet = null;
		this.cam = null;
		this.spheres = new ArrayList<Sphere>();
		this.triangles = new ArrayList<Triangle>();
		this.lights = new ArrayList<Light>();
		this.planes = new ArrayList<Plane>();
		this.materials = new ArrayList<Material>();
	}

	/**
	 * @return the cam
	 */
	public Camera getCam() {
		return cam;
	}

	/**
	 * @return the mySet
	 */
	public MySet getMySet() {
		return mySet;
	}

	/**
	 * @param mySet the mySet to set
	 */
	public void setMySet(MySet mySet) {
		this.mySet = mySet;
	}

	/**
	 * @param cam the cam to set
	 */
	public void setCam(Camera cam) {
		this.cam = cam;
	}

	/**
	 * @return the spheres
	 */
	public ArrayList<Sphere> getSpheres() {
		return spheres;
	}

	/**
	 * @return the triangles
	 */
	public ArrayList<Triangle> getTriangles() {
		return triangles;
	}

	/**
	 * @return the lights
	 */
	public ArrayList<Light> getLights() {
		return lights;
	}

	/**
	 * @return the planes
	 */
	public ArrayList<Plane> getPlanes() {
		return planes;
	}

	public void setMaterial(Material mat) {
		this.materials.add(mat);
	}

	/**
	 * @return the materials
	 */
	public ArrayList<Material> getMaterials() {
		return materials;
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}
	
	
	
}
