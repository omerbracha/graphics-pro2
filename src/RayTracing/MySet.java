package RayTracing;

public class MySet {
	public double bgr, bgg, bgb; //background colors
	public int sh_rays; //shadow rays
	public int rec_max; //maximum recursion
	public int SS = 2 ; //super sampling
	
	public double getBgr() {
		return bgr;
	}
	public void setBgr(double bgr) {
		this.bgr = bgr;
	}
	public double getBgg() {
		return bgg;
	}
	public void setBgg(double bgg) {
		this.bgg = bgg;
	}
	public double getBgb() {
		return bgb;
	}
	public void setBgb(double bgb) {
		this.bgb = bgb;
	}
	public int getSh_rays() {
		return sh_rays;
	}
	public void setSh_rays(int sh_rays) {
		this.sh_rays = sh_rays;
	}
	public int getRec_max() {
		return rec_max;
	}
	public void setRec_max(int rec_max) {
		this.rec_max = rec_max;
	}
	public int getSS() {
		return SS;
	}
	public void setSS(int sS) {
		SS = sS;
	}
	
	
}
