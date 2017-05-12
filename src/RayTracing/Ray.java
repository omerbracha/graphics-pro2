package RayTracing;

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

	public void cameraRay(RayTracer tracer, int UPaxisNum, int RIGHTaxisNum, int i, int j) {
		// p0 = position 
		// t = inf
		
		int n = tracer.scene.mySet.getSS(); // super sampling number
		Camera cam = tracer.scene.cam;
		int screenHeight = ( (cam.getSw_from_cam()*tracer.imageHeight) / (tracer.imageWidth) );
		double sizeRIGHTcell = cam.getSw_from_cam()/(double)tracer.imageWidth;
		double sizeUPcell = screenHeight/(double)tracer.imageHeight;
		double sizeRIGHTsmallCell = sizeRIGHTcell/n;
		double sizeUPsmallCell = sizeUPcell/n;
		
		double Vright =  RIGHTaxisNum*sizeRIGHTcell + j*sizeRIGHTsmallCell - cam.getSw_from_cam()/2 ;//X bar
		double Vup = UPaxisNum*sizeUPcell + i*sizeUPsmallCell - screenHeight/2 ;			//Y bar  
		double Vin = cam.getSc_dist(); 									//screen distance TODO 
		
		double vSize = Math.sqrt(Vright*Vright + Vup*Vup + Vin*Vin);
		
		this.setT(Double.POSITIVE_INFINITY);
		this.setP0(cam.getPosition());
		this.setV(new Vector(Vright/vSize, Vup/vSize, Vin/vSize));
		
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
		double t_hc = Math.sqrt(sph.getRadius()*sph.getRadius() - d_pow);
		double dist = interDist(t_hc, t_ca);
		return dist;
	}
	
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


} // end of ray class
