package RayTracing;

public class Material {
	public double dr, dg, db; //diffuse color
	public double sr, sg, sb; //specular color
	public double rr, rg, rb; //reflection color
	public int phong; //phong specularity
	public double trans; //transparency value;
	
	public double getDr() {
		return dr;
	}
	public void setDr(double dr) {
		this.dr = dr;
	}
	public double getDg() {
		return dg;
	}
	public void setDg(double dg) {
		this.dg = dg;
	}
	public double getDb() {
		return db;
	}
	public void setDb(double db) {
		this.db = db;
	}
	public double getSr() {
		return sr;
	}
	public void setSr(double sr) {
		this.sr = sr;
	}
	public double getSg() {
		return sg;
	}
	public void setSg(double sg) {
		this.sg = sg;
	}
	public double getSb() {
		return sb;
	}
	public void setSb(double sb) {
		this.sb = sb;
	}
	public double getRr() {
		return rr;
	}
	public void setRr(double rr) {
		this.rr = rr;
	}
	public double getRg() {
		return rg;
	}
	public void setRg(double rg) {
		this.rg = rg;
	}
	public double getRb() {
		return rb;
	}
	public void setRb(double rb) {
		this.rb = rb;
	}
	public int getPhong() {
		return phong;
	}
	public void setPhong(int phong) {
		this.phong = phong;
	}
	public double getTrans() {
		return trans;
	}
	public void setTrans(double trans) {
		this.trans = trans;
	}
	
	
	
}
