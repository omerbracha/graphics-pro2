package RayTracing;

public class MySet {
	public int bgr, bgg, bgb; //background colors
	public int sh_rays; //shadow rays
	public int rec_max; //maximum recursion
	public int SS; //super sampling
	
	public int getBgr() {
		return bgr;
	}
	public void setBgr(int bgr) {
		this.bgr = bgr;
	}
	public int getBgg() {
		return bgg;
	}
	public void setBgg(int bgg) {
		this.bgg = bgg;
	}
	public int getBgb() {
		return bgb;
	}
	public void setBgb(int bgb) {
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
