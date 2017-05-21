package RayTracing;

public class Plane extends Shape{
	//TODO check xyz int or double
	public double nx, ny, nz; //normal
	public double offset; //offset
	public int mat_idx; //material index
	public int index;
	
	public Plane(double x, double y, double z, Vector position, int mat_idx, double nx, double ny, double nz,
			double offset, int mat_idx2, int index) {
		super(x, y, z, position, mat_idx);
		this.nx = nx;
		this.ny = ny;
		this.nz = nz;
		this.offset = offset;
		mat_idx = mat_idx2;
		this.index = index;
	}
	
	public Plane() {
		this.nx = 0;
		this.ny = 0;
		this.nz = 0;
		this.offset = 0;
		mat_idx = 0;
		this.index = 0;
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
	public double getNx() {
		return nx;
	}
	public void setNx(double nx) {
		this.nx = nx;
	}
	public double getNy() {
		return ny;
	}
	public void setNy(double ny) {
		this.ny = ny;
	}
	public double getNz() {
		return nz;
	}
	public void setNz(double nz) {
		this.nz = nz;
	}
	public double getOffset() {
		return offset;
	}
	public void setOffset(double offset) {
		this.offset = offset;
	}
	public int getMat_idx() {
		return mat_idx;
	}
	public void setMat_idx(int mat_idx) {
		this.mat_idx = mat_idx;
	}
	

}
