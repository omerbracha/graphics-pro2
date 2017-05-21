package RayTracing;

public class Plane extends Shape{
	//TODO check xyz int or double
	public double nx, ny, nz; //normal
	public int offset; //offset
	public int mat_idx; //material index
	public int index;
	
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
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getMat_idx() {
		return mat_idx;
	}
	public void setMat_idx(int mat_idx) {
		this.mat_idx = mat_idx;
	}
	

}
