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

	public void cameraRay(RayTracer tracer, int TopBottom, int leftRight, int i, int j) {
		// p0 = position 
		// t = inf
		
		int n = tracer.scene.mySet.getSS(); // super sampling number
		Camera cam = tracer.scene.cam;

		double t = Double.POSITIVE_INFINITY;
		Vector p0 = cam.getPosition();
		double sw = cam.getSw_from_cam();
		double sd = cam.getSc_dist();
		double sh = (sw/tracer.imageWidth) * tracer.imageHeight;
		
//		
//		Vector lookat = cam.getLookat().sub(p0);
//		lookat = lookat.normalize();
//		double cosy = Math.cos(lookat.y);
//		double siny = Math.sin(lookat.y);
//		double cosx = Math.cos(lookat.x);
//		double sinx = Math.sin(lookat.x);
//		double cosz = Math.cos(lookat.z);
//		double sinz = Math.sin(lookat.z);
//		
//		//double[][] m = {{cosy*cosz,cosy*sinz,siny},{-sinx*siny*cosz+cosx*sinz,sinx*siny*sinz+cosz*cosx,sinx*cosy},{-cosz*cosx*siny-sinx*sinz,cosx*sinz*siny-sinx*cosz,cosx*cosy}};
//		double[][] m = {{cosy,0,siny},{-sinx*siny,cosx,sinx*cosy},{-cosx*siny,-sinx,cosx*cosy}}; 
//		Matrix mat = new Matrix(m);
//		Matrix inverse = mat.inverse();
//		Vector v1 = new Vector(1,0,0);
//		Vector v2 = new Vector(0,1,0);
//		Vector v3 = new Vector(0,0,1);
//		Vector Vx1 = vecTimesMat(v1, m);
//		Vector Vy1 = vecTimesMat(v2, m);
//		Vector Vz1 = vecTimesMat(v3, m);
//		
//		double Px = - (sw/2) + ( (sw/tracer.imageWidth) * (leftRight + (j/n)) );
//		double Py = - (sh/2) + ( (sh/tracer.imageHeight) * (TopBottom + (i/n)) );
//		
//		//double Px = cam.getpX() - (sw/2) + ( (sw/tracer.imageWidth) * (leftRight + (j/n)) );
//		//double Py = cam.getpY() - (sh/2) + ( (sh/tracer.imageHeight) * (TopBottom + (i/n)) );
//		double Pz = cam.getSc_dist();
//		
//		Vector v = new Vector(Px,Py,Pz);
//		
//		double Vx = v.dot(Vx1);
//		double Vy = v.dot(Vy1);
//		double Vz = v.dot(Vz1);

		

		/*//////////////from ozeri slide//////////////
		
		Vector normal = p0.sub(cam.getLookat());
		normal = normal.normalize();
		Vector u = cam.getUp().cross(normal);
		u = u.normalize();
		Vector v = normal.cross(u);
		
		Vector c = p0.sub(normal.mult(sd));
		Vector l = c.sub(u.mult(sw/2)).sub(v.mult(sh/2));
		Vector V = l.add(u.mult((sw/tracer.imageWidth) * (leftRight + (j/n)))).add(v.mult((sh/tracer.imageHeight) * (TopBottom + (i/n))));
		*/
		
		
		
		///////////////////with semi lookat//////////////////
		
		Vector lookAt = cam.getLookat();
		lookAt.x = lookAt.x - p0.x;
		lookAt.y = lookAt.y - p0.y;
		lookAt.z = lookAt.z - p0.z;
		lookAt = lookAt.normalize();
		double lookX = lookAt.x*sd;
		double lookY = lookAt.y*sd;
		double lookZ = lookAt.z;
		
		double Px = lookX + cam.getpX() - (sw/2) + ( (sw/tracer.imageWidth) * (leftRight + (j/n)) );
		double Py = lookY + cam.getpY() - (sh/2) + ( (sh/tracer.imageHeight) * (TopBottom + (i/n)) );
		double Pz = lookZ;
		double Vx = Px - p0.x;
		double Vy = Py - p0.y;
		double Vz = Pz;
		
		
		/*/////////without lookat///////////////////
		
		double Px = cam.getpX() - (sw/2) + ( (sw/tracer.imageWidth) * (leftRight + (j/n)) );
		double Py = cam.getpY() - (sh/2) + ( (sh/tracer.imageHeight) * (TopBottom + (i/n)) );
		double Pz = cam.getSc_dist();
		double Vx = Px - p0.x;
		double Vy = Py - p0.y;
		double Vz = Pz;
		*/
		//Vector V = new Vector(Vx, Vy, Vz);
		//V = V.normalize();
		//double[][] inv1 = inverse.getArray();
		//V = vecTimesMat(V, inv1);
		
		double size = Math.sqrt(Vx*Vx + Vy*Vy + Vz*Vz);
		Vector V = new Vector(Vx/size, Vy/size, Vz/size);

		this.p0 = p0;
		this.t = t;
		this.v = V;
	}
	
	private Vector vecTimesMat(Vector v1, double[][] m) {
		double vx = v1.x*m[0][0] + v1.y*m[1][0] + v1.z*m[2][0];
		double vy = v1.x*m[0][1] + v1.y*m[1][1] + v1.z*m[2][1];
		double vz = v1.x*m[0][2] + v1.y*m[1][2] + v1.z*m[2][2];
		Vector v = new Vector(vx,vy,vz);
		return v;
	}

	public double interAlgebraic(Sphere sph) {
		double a = 1;
		double b = 2 * this.getV().dot(this.getP0().sub(sph.getPos()));
		double c = this.getV().dot(this.getV()) - sph.getRadius() * sph.getRadius();
		double disc =  b*b - 4*a*c;
		if(disc < 0 ) {
			return -1; // no intersaction
		}
		double t1 = ( -b + Math.sqrt(disc)) / 2;
		double t2 = ( -b - Math.sqrt(disc)) / 2;
		
		return interDist(t1, t2);
		
	}
	public double inter(Sphere sph) {
		Vector L = sph.getPos().sub(this.p0);
		double t_ca = L.dot(this.v);
		if (t_ca < 0) {
			return -1;
		}
		
		double d_pow = L.dot(L) - (t_ca*t_ca); 
		if (d_pow > (sph.getRadius()*sph.getRadius())) {
			return -1;
		}
		
		double t_hc = Math.sqrt(sph.getRadius()*sph.getRadius() - d_pow); 	// never gets here. 
		double dist = interDist(t_hc, t_ca);								// 
		return dist;														// 
	}
	
	public double inter(Plane plane) {
		Vector normal = new Vector (plane.getNx(), plane.getNy(), plane.getNz());
		Vector p0 = this.getP0();
		double t = - ( (p0.dot(normal) - plane.getOffset()) / (this.getV().dot(normal)));
		if (t < 0) {
			return -1;
		}
		return t;
	}
	
	public double inter(Triangle trng) {
		Vector norm = trng.getNormal();
		Plane plane = trngToPlane(trng, norm);
		
		double t = inter(plane);
		if (t < 0) {
			return -1;
		}
		Vector p0 = this.getP0();
		Vector p = this.getV().mult(t).add(p0);
		
		Vector t1 = new Vector(trng.getP0x(), trng.getP0y(), trng.getP0z());
		Vector t2 = new Vector(trng.getP1x(), trng.getP1y(), trng.getP1z());
		Vector t3 = new Vector(trng.getP2x(), trng.getP2y(), trng.getP2z());
		
		Vector v1 = t1.sub(p0);
		Vector v2 = t2.sub(p0);
		Vector v3 = t3.sub(p0);
		
		Vector n1 = v2.cross(v1);
		Vector n2 = v3.cross(v2);
		Vector n3 = v1.cross(v3);
		
		if ( (p.sub(p0).dot(n1) < 0) || (p.sub(p0).dot(n2) < 0) || (p.sub(p0).dot(n3) < 0) ) {
			return -1;
		}
		
		return t;
	}
	
	private Plane trngToPlane(Triangle trng, Vector norm) {
		Plane plane = new Plane();
		double d = - norm.x*trng.getP0x() - norm.y*trng.getP0y() - norm.z*trng.getP0z();
		
		plane.setNx(norm.x);
		plane.setNy(norm.y);
		plane.setNz(norm.z);
		plane.setOffset(-d);
		plane.setMat_idx(trng.getMat_idx());
		
		return plane;
	}

//	private Vector triangleToNorm(Triangle trng) {
//		Vector ab = new Vector(trng.getP1x()-trng.getP0x(), trng.getP1y()-trng.getP0y(), trng.getP1z()-trng.getP0z());
//		Vector ac = new Vector(trng.getP2x()-trng.getP0x(), trng.getP2y()-trng.getP0y(), trng.getP2z()-trng.getP0z());
//		ab.x = ab.x/ab.size;
//		ab.y = ab.y/ab.size;
//		ab.z = ab.z/ab.size;
//		ac.x = ac.x/ac.size;
//		ac.y = ac.y/ac.size;
//		ac.z = ac.z/ac.size;
//		Vector norm = ab.cross(ac);
//		return norm;
//	}

	public double interDist(double t_hc, double t_ca) {
		
		/*
		Vector V = this.p0.sub(sph.getPos());
		double b = 2 * this.v.dot(V);
		double c = ((V.size)*(v.size)) - ( sph.getRadius() * sph.getRadius() ) ;
		double t1 = (-b + Math.sqrt(b*b - 4*c))/2;
		double t2 = (-b - Math.sqrt(b*b - 4*c))/2;
		*/
		
		double t1 = t_ca - t_hc;
		double t2 = t_ca + t_hc;
		
		if( (t1 < 0) && (t2 < 0) ){ 				// behind camera 
			return -1; 
		}
		if(t1 < 0){									// only 2 in
			return t2;
		}
		if(t2<0){									// only 1 in 
			return t1;
		}
		if(t2<t1){									// 2 is minimal 
			return t2;
		}
		return t1;									// 1 is minimal 
	}

	public double inter(Shape sh) {
		if(sh.getClass() == Sphere.class) {
			return inter( (Sphere) sh);
		}
		if(sh.getClass() == Plane.class) {
			return inter( (Plane) sh );
		}
		if(sh.getClass() == Triangle.class) {
			return inter( (Triangle) sh );
		}
		
			// else 
			return -1;
		
	}

	public Vector getP() {
		Vector ans = this.getP0().add(this.v.mult(this.t));
		return ans;
	}


} // end of ray class
