package RayTracing;

//import Jama.Matrix;

public class Ray {
	public double t;		// intersection point
	public Vector p0;		// start point
	public Vector v;		// direction

	public Ray(double t, Vector p0, Vector v) {
		super();
		this.t = t;
		this.p0 = p0;
		this.v = v;
	}

	public Ray() {
		this(Double.POSITIVE_INFINITY, new Vector(), new Vector());
	}



	public double getT() {
		return t;
	}

	public void setT(double t) {
		this.t = t;
	}

	public Vector getP0() {
		return p0;
	}

	public void setP0(Vector p0) {
		this.p0 = p0;
	}

	public Vector getV() {
		return v;
	}

	public void setV(Vector v) {
		this.v = v;
	}

	// create ray with screen parameters
	public void cameraRay(RayTracer tracer, int TopBottom, int leftRight, int i, int j, Vector up, Vector right, Vector down, Vector left, Vector lookAt, Vector topLeft) {

		int n = tracer.scene.mySet.getSS(); // super sampling number
		Camera cam = tracer.scene.cam;
		double t = Double.POSITIVE_INFINITY;
		Vector newP0 = cam.getPosition(); 
		double sw = cam.getSw_from_cam(); // screen width
		double sd = cam.getSc_dist(); // screen distance	
		double pixelWidth =  sw / tracer.imageWidth;
		double pixelHeight = pixelWidth;// sh/tracer.imageHeight;

		// create ray to point in pixel:
		Vector newP = topLeft.add(right.mult( (pixelWidth) * (leftRight + (j/n)) ));
		newP = newP.add(down.mult( (pixelHeight) * (TopBottom + (i/n)) ));
		newP = newP.add(lookAt.mult(sd));
		Vector V = newP.sub(newP0);
		V = V.normalize();
		this.p0 = newP0;
		this.t = t;
		this.v = V;
	}

	public double inter(Sphere sph) { // intersection with sphere
		
		Vector L = sph.getPos().sub(this.p0); // vector to center of sphere
		double t_ca = L.dot(this.v);
		if (t_ca < 0) { // if behind camera
			return -1;
		}

		double d_pow = L.dot(L) - (t_ca*t_ca); 
		if (d_pow > (sph.getRadius()*sph.getRadius())) { // outside of sphere, no intersection
			return -1;
		}
		//intersection, compute distance
		double t_hc = Math.sqrt(sph.getRadius()*sph.getRadius() - d_pow); 	  
		double dist = interDist(t_hc, t_ca);								
		return dist;														
	}

	public double inter(Plane plane) { // intersection with plane
		Vector normal = new Vector (plane.getNx(), plane.getNy(), plane.getNz()); // plane normal
		Vector p0 = this.getP0();
		double t = - ( (p0.dot(normal) - plane.getOffset()) / (this.getV().dot(normal))); // intersection point
		if (t < 0) { // behind camera
			return -1;
		}
		return t;
	}

	public double inter(Triangle trng) { // intersection with triangle
		Vector norm = trng.getNormal(null); // triangle normal
		if(norm.dot(this.getV()) < 0){
			norm = norm.mult(-1);
		}
		
		Plane plane = trngToPlane(trng, norm); // triangle plane
		
		double t = inter(plane); // intersection with plane
		if (t < 0) { // behind camera
			return -1;
		}
		Vector p0 = this.getP0();
		Vector p = this.getV().mult(t).add(p0);

		// compute triangle vectors
		Vector t1 = new Vector(trng.getP0x(), trng.getP0y(), trng.getP0z());
		Vector t2 = new Vector(trng.getP1x(), trng.getP1y(), trng.getP1z());
		Vector t3 = new Vector(trng.getP2x(), trng.getP2y(), trng.getP2z());

		Vector v1 = t1.sub(p0);
		Vector v2 = t2.sub(p0);
		Vector v3 = t3.sub(p0);
		// compute normal to the triangle vectors
		Vector n1 = v2.cross(v1);
		Vector n2 = v3.cross(v2);
		Vector n3 = v1.cross(v3);
		
		// check if intersecting with triangle
		if ( (p.sub(p0).dot(n1) < 0) || (p.sub(p0).dot(n2) < 0) || (p.sub(p0).dot(n3) < 0) ) {
			return -1;
		}

		return t;
	}

	private Plane trngToPlane(Triangle trng, Vector norm) { // find plane consisting triangle
		Plane plane = new Plane();
		double d = - norm.x*trng.getP0x() - norm.y*trng.getP0y() - norm.z*trng.getP0z(); // plane offset

		plane.setNx(norm.x);
		plane.setNy(norm.y);
		plane.setNz(norm.z);
		plane.setOffset(-d);
		plane.setMat_idx(trng.getMat_idx());

		return plane;
	}

	public double interDist(double t_hc, double t_ca) { // find closest intersection point

		double t1 = t_ca - t_hc;
		double t2 = t_ca + t_hc;
		if( (t1 < 0) && (t2 < 0) ){ 				// behind camera 
			return -1; 
		}
		if(t1 < 0){									// only t2 in screen
			return t2;
		}
		return t1;									// t1 is minimal 
	}

	public double inter(Shape sh) { // abstract intersection function
		if(sh.getClass() == Sphere.class) { // if shape is sphere
			return inter( (Sphere) sh);
		}
		if(sh.getClass() == Plane.class) { // if shape is plane
			return inter( (Plane) sh );
		}
		if(sh.getClass() == Triangle.class) { // if shape is triangle
			return inter( (Triangle) sh );
		}

		return 0;

	}

	public Vector getP() { // point of intersection
		Vector ans = this.getP0().add(this.v.mult(this.t));
		return ans;
	}


} // end of ray class
