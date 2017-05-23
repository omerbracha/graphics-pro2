package RayTracing;

public class Triangle extends Shape{
	//TODO check xyz int or double
	public double p0x, p0y, p0z; //position of vertex 1
	public double p1x, p1y, p1z; //position of vertex 2
	public double p2x, p2y, p2z; //position of vertex 3
	//public int mat_idx; //material index
	
	public double getP0x() {
		return p0x;
	}
	public void setP0x(double p0x) {
		this.p0x = p0x;
	}
	public double getP0y() {
		return p0y;
	}
	public void setP0y(double p0y) {
		this.p0y = p0y;
	}
	public double getP0z() {
		return p0z;
	}
	public void setP0z(double p0z) {
		this.p0z = p0z;
	}
	public double getP1x() {
		return p1x;
	}
	public void setP1x(double p1x) {
		this.p1x = p1x;
	}
	public double getP1y() {
		return p1y;
	}
	public void setP1y(double p1y) {
		this.p1y = p1y;
	}
	public double getP1z() {
		return p1z;
	}
	public void setP1z(double p1z) {
		this.p1z = p1z;
	}
	public double getP2x() {
		return p2x;
	}
	public void setP2x(double p2x) {
		this.p2x = p2x;
	}
	public double getP2y() {
		return p2y;
	}
	public void setP2y(double p2y) {
		this.p2y = p2y;
	}
	public double getP2z() {
		return p2z;
	}
	public void setP2z(double p2z) {
		this.p2z = p2z;
	}
	public int getMat_idx() {
		return mat_idx;
	}
	public void setMat_idx(int mat_idx) {
		this.mat_idx = mat_idx;
	}
}
