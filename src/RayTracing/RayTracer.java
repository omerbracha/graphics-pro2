package RayTracing;

import java.awt.Transparency;
import java.awt.color.*;
import java.awt.image.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// import javafx.scene.shape.Sphere;
import javax.imageio.ImageIO;

import com.sun.org.apache.xml.internal.utils.ListingErrorHandler;

/**
 * Main class for ray tracing exercise.
 *//// try to push from pc2

public class RayTracer {
	public int imageWidth;
	public int imageHeight;
	public int cnt_mtl = 0, cnt_pln = 0, cnt_sph = 0, cnt_lgt = 0;
	public Scene scene = new Scene();
	private double[][][] screen; // TODO- change int?

	/**
	 * Runs the ray tracer. Takes scene file, output image file and image size
	 * as input.
	 */

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////// main
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////// /////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) {

		try {

			RayTracer tracer = new RayTracer();

			// Default values:
			tracer.imageWidth = 500;
			tracer.imageHeight = 500;
			if (args.length < 2)
				throw new RayTracerException(
						"Not enough arguments provided. Please specify an input scene file and an output image file for rendering.");

			String sceneFileName = args[0];
			String outputFileName = args[1];

			if (args.length > 3) {
				tracer.imageWidth = Integer.parseInt(args[2]);
				tracer.imageHeight = Integer.parseInt(args[3]);
			}

			// Create screen:
			tracer.screen = new double[tracer.imageWidth][tracer.imageHeight][3];

			// Parse scene file:
			tracer.parseScene(sceneFileName);

			// Render scene:
			tracer.renderScene(outputFileName);

			// } catch (IOException e) {
			// System.out.println(e.getMessage());
		} catch (RayTracerException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("_goodbye_");
	} // end of main

	/**
	 * Parses the scene file and creates the scene. Change this function so it
	 * generates the required objects.
	 */
	public void parseScene(String sceneFileName) throws IOException, RayTracerException {
		FileReader fr = new FileReader(sceneFileName);

		BufferedReader r = new BufferedReader(fr);
		String line = null;
		int lineNum = 0;
		System.out.println("Started parsing scene file " + sceneFileName);

		while ((line = r.readLine()) != null) {
			line = line.trim();
			++lineNum;

			if (line.isEmpty() || (line.charAt(0) == '#')) { // This line in the
				// scene file is
				// a comment
				continue;
			} else {
				String code = line.substring(0, 3).toLowerCase();
				// Split according to white space characters:
				String[] params = line.substring(3).trim().toLowerCase().split("\\s+");

				if (code.equals("cam")) { // camera parameters
					Camera cam = new Camera();
					cam.setpX(Double.parseDouble(params[0])); // set position x
					cam.setpY(Double.parseDouble(params[1])); // set position y
					cam.setpZ(Double.parseDouble(params[2])); // set position z
					cam.setLaX(Double.parseDouble(params[3])); // set look at x
					cam.setLaY(Double.parseDouble(params[4])); // set look at y
					cam.setLaZ(Double.parseDouble(params[5])); // set look at z
					cam.setUvX(Integer.parseInt(params[6])); // set up vector x
					cam.setUvY(Integer.parseInt(params[7])); // set up vector y
					cam.setUvZ(Integer.parseInt(params[8])); // set up vector z
					cam.setSc_dist(Double.parseDouble(params[9])); // set up distance
					cam.setSw_from_cam(Integer.parseInt(params[10]));// set up width

					scene.setCam(cam); // set camera to scene

					System.out.println(String.format("Parsed camera parameters (line %d)", lineNum));
				} else if (code.equals("set")) { // set parameters

					MySet set = new MySet();
					set.setBgr(Double.parseDouble(params[0])); // R background color.
					set.setBgg(Double.parseDouble(params[1])); // G background color.
					set.setBgb(Double.parseDouble(params[2])); // B background color.
					set.setSh_rays(Integer.parseInt(params[3])); // number of shadow rays.
					set.setRec_max(Integer.parseInt(params[4])); // maximum recursion.

					if (params.length > 5) {
						set.setSS(Integer.parseInt(params[5])); // super sampling level.
					} else {
						set.setSS(2);
					}

					scene.setMySet(set); // set the setting

					System.out.println(String.format("Parsed general settings (line %d)", lineNum));
				} else if (code.equals("mtl")) { // material attributes
					Material mat = new Material();
					mat.setIndex(cnt_mtl);
					cnt_mtl++;
					mat.setDr(Double.parseDouble(params[0])); // diffuse red
					mat.setDg(Double.parseDouble(params[1])); // diffuse green
					mat.setDb(Double.parseDouble(params[2])); // diffuse blue
					mat.setSr(Double.parseDouble(params[3])); // specular red
					mat.setSg(Double.parseDouble(params[4])); // specular green
					mat.setSb(Double.parseDouble(params[5])); // specular blue
					mat.setRr(Double.parseDouble(params[6])); // reflection red
					mat.setRg(Double.parseDouble(params[7])); // reflection green
					mat.setRb(Double.parseDouble(params[8])); // reflection blue
					mat.setPhong(Float.parseFloat(params[9])); // phong coefficient
					mat.setTrans(Double.parseDouble(params[10])); // transparency

					scene.setMaterial(mat);

					System.out.println(String.format("Parsed material (line %d)", lineNum));
				} else if (code.equals("sph")) { // sphere parameters
					// Add code here to parse sphere parameters
					Shape sphere = new Sphere();
					sphere.setIndex(cnt_sph);
					cnt_sph++;
					sphere.setCx(Double.parseDouble(params[0])); // center x
					sphere.setCy(Double.parseDouble(params[1])); // center y
					sphere.setCz(Double.parseDouble(params[2])); // center z
					sphere.setRadius(Double.parseDouble(params[3])); // radius
					sphere.setMat_idx(Integer.parseInt(params[4])); // material index number
					scene.getShapes().add(sphere); // add to the sphere list.

					System.out.println(String.format("Parsed sphere (line %d)", lineNum));
				} else if (code.equals("pln")) { // plane parameters
					Shape pln = new Plane();
					pln.setIndex(cnt_pln);
					cnt_pln++;
					pln.setNx(Double.parseDouble(params[0])); // normal x
					pln.setNy(Double.parseDouble(params[1])); // normal y
					pln.setNz(Double.parseDouble(params[2])); // normal z
					pln.setOffset(Double.parseDouble(params[3])); // plane offset
					pln.setMat_idx(Integer.parseInt(params[4])); // material index number
					scene.getShapes().add(pln); // add to the plane list.

					System.out.println(String.format("Parsed plane (line %d)", lineNum));
				} else if (code.equals("trg")) { // triangle parameters

					Shape trg = new Triangle();
					trg.setMat_idx(cnt_mtl);
					cnt_mtl++;
					trg.setP0x(Double.parseDouble(params[0])); // p0 x
					trg.setP0y(Double.parseDouble(params[1])); // p0 y
					trg.setP0z(Double.parseDouble(params[2])); // p0 z
					trg.setP1x(Double.parseDouble(params[3])); // p1 x
					trg.setP1y(Double.parseDouble(params[4])); // p1 y
					trg.setP1z(Double.parseDouble(params[5])); // p1 z
					trg.setP2x(Double.parseDouble(params[6])); // p2 x
					trg.setP2y(Double.parseDouble(params[7])); // p2 y
					trg.setP2z(Double.parseDouble(params[8])); // p2 z
					trg.setMat_idx(Integer.parseInt(params[9])); // material index number

					scene.getShapes().add(trg);

					System.out.println(String.format("Parsed triangle (line %d)", lineNum));
				} else if (code.equals("lgt")) { // light parameters
					Light lict = new Light();
					lict.setIndex(cnt_lgt);
					cnt_lgt++;
					lict.setPx(Double.parseDouble(params[0])); // position x
					lict.setPy(Double.parseDouble(params[1])); // position y
					lict.setPz(Double.parseDouble(params[2])); // position z
					lict.setR(Double.parseDouble(params[3])); // red
					lict.setG(Double.parseDouble(params[4])); // green
					lict.setB(Double.parseDouble(params[5])); // blue
					lict.setSpec(Double.parseDouble(params[6])); // specular
					lict.setShadow(Double.parseDouble(params[7])); // shadow
					lict.setWidth(Integer.parseInt(params[8])); // width

					scene.getLights().add(lict);

					System.out.println(String.format("Parsed light (line %d)", lineNum));
				} else {
					System.out.println(String.format("ERROR: Did not recognize object: %s (line %d)", code, lineNum));
				}
			}
		}

		// It is recommended that you check here that the scene is valid,
		// for example camera settings and all necessary materials were defined.

		if (scene.cam == null || scene.mySet == null) {
			System.err.println("no camera or set in file");
		}

		System.out.println("Finished parsing scene file " + sceneFileName);
	}

	/**
	 * Renders the loaded scene and saves it to the specified file location.
	 */
	public void renderScene(String outputFileName) {
		long startTime = System.currentTimeMillis();

		// Create a byte array to hold the pixel data:
		byte[] rgbData = new byte[this.imageWidth * this.imageHeight * 3 ];

		// Put your ray tracing code here!
		//
		// Write pixel color values in RGB format to rgbData:
		// Pixel [x, y] red component is in rgbData[(y * this.imageWidth + x) *
		// 3]
		// green component is in rgbData[(y * this.imageWidth + x) * 3 + 1]
		// blue component is in rgbData[(y * this.imageWidth + x) * 3 + 2]
		//
		// Each of the red, green and blue components should be a byte, i.e.
		// 0-255

		for (int i = 0; i < this.imageWidth; i++) { // run from top to bottom
			for (int j = 0; j < this.imageHeight; j++) { // run from left to right
				// System.out.println(Integer.toString(i) + " " +
				// Integer.toString(j));
				this.screen[i][j] = getColorForPix(j, i);
			}
		}

		int weight = this.imageWidth;
		int height = this.imageHeight;
		
		// load data into RGB array
		for (int i = 0; i < weight; i++) {
			for (int j = 0; j < height; j++) {
				for (int k = 0; k < 3; k++) {
					rgbData[(j * imageWidth + i) * 3 + k] = (byte) this.screen[i][j][k];
				}
			}
		}

		long endTime = System.currentTimeMillis();
		Long renderTime = endTime - startTime;


		// The time is measured for your own conveniece, rendering speed will
		// not affect your score
		// unless it is exceptionally slow (more than a couple of minutes)
		System.out.println("Finished rendering scene in " + renderTime.toString() + " milliseconds.");

		// This is already implemented, and should work without adding any code.
		saveImage(this.imageWidth, rgbData, outputFileName);

		System.out.println("Saved file " + outputFileName);

	}

	//////////////////////// Utilities functions //////////////////////////////
	// get color for pixel
	private double[] getColorForPix(int topBottom, int leftRight) {
		int red = 0, green = 0, blue = 0;
		double[] ans = new double[3];
		int size = scene.getMySet().getSS();
		double[][][] superSampleMatrix = new double[size][size][3];
		for (int i = 0; i < size; i++) { // iterate over super sampling matrix size
			for (int j = 0; j < size; j++) {
				superSampleMatrix[i][j] = sampleColorByRay(topBottom, leftRight, i, j,
						this.scene.getMySet().getRec_max(), null); // find pixel color with ray
				red += superSampleMatrix[i][j][0]; // sum red
				green += superSampleMatrix[i][j][1]; // sum green
				blue += superSampleMatrix[i][j][2]; // sum blue
			}
		}
		ans[0] = (int) (red / (size * size)); // add average color
		ans[1] = (int) (green / (size * size)); // add average color
		ans[2] = (int) (blue / (size * size)); // add average color

		return ans;
	}

	private double[] sampleColorByRay(int TopBottom, int leftRight, int i, int j, int rec, Shape inputShape) {
		double t = 0;
		int flag = 0;
		MySet set = this.scene.getMySet();
		double red = 0, green = 0, blue = 0;
		Ray ray = new Ray();
		ray.cameraRay(this, TopBottom, leftRight, i, j); // create ray with pixel super sampling position
		double ans[];
		ans = sampleColorByRayRec(ray, t, red, green, blue, rec, null); // recursive color function
		// multiply color coefficient by RGB range number
		red = 255 * ans[0];
		green = 255 * ans[1];
		blue = 255 * ans[2];

		if (red > 255) { // keep in bounds
			red = 255;
		}
		if (green > 255) { // keep in bounds
			green = 255;
		}
		if (blue > 255) { // keep in bounds
			blue = 255;
		}

		double[] FinalAns = { red, green, blue };
		return FinalAns;
	}

	private double[] sampleColorByRayRec(Ray inputRay, double t, double InRed, double InGreen, double InBlue, int rec,
			Shape inputShape) {
		int flag = 0;

		MySet set = this.scene.getMySet();
		double red = 0, green = 0, blue = 0;
		Material mat = new Material();

		if (rec == 0) { // halting - use background color
			red = set.getBgr();
			green = set.getBgg();
			blue = set.getBgb();
			double[] ans_stopping = { red, green, blue };
			return ans_stopping;
		}

		// not ending
		Shape shape = new Shape() {
		};
		Ray ray = inputRay;
		for (Shape sh : this.scene.getShapes()) { // check for every shape
			if (sh != inputShape) { // if not original shape
				if ((t = ray.inter(sh)) > 0) { // check intersection
					if (t < ray.t) { // if closer than previous closest shape
						ray.t = t;
						shape = sh;
						flag = 1; // hit
					}
				}
			}
		}
		if (flag > 0) { // hit something
			Vector endPoint = ray.getP();
			mat = this.scene.materials.get(shape.getMat_idx() - 1);
			set = this.scene.getMySet();
			// get background color
			Vector backColor = getBackgroundColor(red, green, blue, shape, endPoint, ray, rec);
			double Ir = backColor.x * mat.getTrans();
			double Ig = backColor.y * mat.getTrans();
			double Ib = backColor.z * mat.getTrans();
			double[] lightValues;
			for (Light licht : this.scene.getLights()) { // check for each light in scene
				lightValues = getlightValues(endPoint, licht, shape); // get light values
				Vector v = licht.getPosition().sub(endPoint);
				v = v.normalize();
				Vector normal = shape.getNormal(endPoint);
				double Dnormal = Math.abs(normal.dot(v));
				
				// sum values of diffusion and specular colors
				Ir += ((mat.getDr() * ( Dnormal)* lightValues[0]) + (mat.getSr()
						* licht.getSpec() * Math.pow(shape.getR(endPoint, licht.getPosition()).dot(ray.getV()), mat.getPhong())
						* lightValues[0])) * (1 - mat.getTrans()); // change 
				Ig += ((mat.getDg() * (Dnormal) * lightValues[1]) + (mat.getSg()
						* licht.getSpec() * Math.pow(shape.getR(endPoint, licht.getPosition()).dot(ray.getV()), mat.getPhong())
						* lightValues[1])) * (1 - mat.getTrans()); // change
				Ib += ((mat.getDb() * (Dnormal) * lightValues[2]) + (mat.getSb()
						* licht.getSpec() * Math.pow(shape.getR(endPoint, licht.getPosition()).dot(ray.getV()), mat.getPhong())
						* lightValues[2])) * (1 - mat.getTrans()); // change
			}

			// reflection colors
			Vector I = getReflectionColor(endPoint, ray, shape, rec);
			Ir += mat.getRr() * I.x;
			Ig += mat.getRg() * I.y;
			Ib += mat.getRb() * I.z;

			// get base color by ray
			red = Ir;
			green = Ig;
			blue = Ib;
		} else { // no intersection
			red = set.getBgr();
			green = set.getBgg();
			blue = set.getBgb();
			double[] ans_stopping = { red, green, blue };
			return ans_stopping;
		}

		//TODO check if relevantttttttttttttttttttttttttttttttttt
		if (red < 0) {
			red = 0;
		}
		if (green < 0) {
			green = 0;
		}
		if (blue < 0) {
			blue = 0;
		}

		double[] ans = { red, green, blue };
		return ans;
	}

	private Vector getBackgroundColor(double inputRed, double inputGreen, double inputBlue, Shape shape,
			Vector endPoint, Ray ray, int rec) {
		Ray newRay = new Ray(Double.POSITIVE_INFINITY, endPoint, ray.getV());

		MySet set = this.scene.getMySet();
		double red = 0, green = 0, blue = 0;
		double t = 0;

		if (rec == 0) { // halting - use background color
			red = set.getBgr();
			green = set.getBgg();
			blue = set.getBgb();
			Vector ans = new Vector(red, green, blue);
			return (ans);
		}
		// call color function recursively
		double[] recans = sampleColorByRayRec(newRay, t, red, green, blue, rec - 1, shape);
		Vector ans = new Vector(recans[0], recans[1], recans[2]);
		return ans;
	}

	private Vector getReflectionColor(Vector endPoint, Ray ray, Shape shape, int rec) {

		Vector r = shape.getR(endPoint, ray.getP0()); // find reflection vector
		Ray recRay = new Ray(Double.POSITIVE_INFINITY, endPoint, r); // find new ray from endpoint
		Material mat = this.scene.materials.get(shape.getMat_idx() - 1);
		MySet set = this.scene.getMySet();
		double Ir = 0;
		double Ig = 0;
		double Ib = 0;
		double t = 0;

		if (rec == 0) { // halting - use background color
			Ir = set.getBgr();
			Ig = set.getBgg();
			Ib = set.getBgb();
			Vector I = new Vector(Ir, Ig, Ib);
			return (I);
		}
		// call color function recursively
		double[] recAns = sampleColorByRayRec(recRay, t, Ir, Ib, Ig, rec - 1, shape);
		Vector I = new Vector(Ir + recAns[0], Ig + recAns[1], Ib + recAns[2]);
		return I;
	}

	private double[] getlightValues(Vector endPoint, Light licht, Shape OriginalShape) {

		double[] ans = { 0, 0, 0 };
		Vector v = licht.getPosition().sub(endPoint); // vector from hit point to light
		v = v.normalize();
		Vector norm = OriginalShape.getNormal(endPoint); // shape normal
		if(OriginalShape.getClass() == Triangle.class) { // check for correct direction
			if(norm.dot(v) < 0){
				norm = norm.mult(-1);
			}
		}
		Vector p0 = endPoint.add(norm.mult(0.001));
		double t = p0.getDistanceScalar(licht.getPosition()); // distance from light
		Ray rayOfLight = new Ray(t, p0, v); // create new ray
		ans[0] = licht.getR();
		ans[1] = licht.getG();
		ans[2] = licht.getB();
		
		// compute shadows
		double softShade = getSoftShadowValue(OriginalShape, licht, rayOfLight, endPoint);
		for (int i = 0; i < 3; i++) {
			ans[i] *= (1 - softShade);
		}
		for (int i = 0; i < 3; i++) { // check for exceptions
			if (ans[i] > 1) {
				ans[i] = 1;
			}
		}
		return ans;
	}

	private double getSoftShadowValue(Shape originalShape, Light licht, Ray rayOfLight, Vector endPoint) {
		double shRays = (double) this.scene.getMySet().getSh_rays();

		// find a plane perpendicular to the ray
		double shadowValue = licht.getShadow();
		Vector p0 = licht.getPosition();
		Vector normal = rayOfLight.getV().mult(-1);
		double d = -(p0.dot(normal));
		double x = (-d) / (normal.x);
		Vector p1 = new Vector(x, 0, 0);
		Vector right = p1.sub(p0).normalize();
		Vector up = right.cross(normal).normalize();
		Vector left = right.mult(-1);
		Vector down = up.mult(-1);

		// create upper-left point:
		double size = licht.getWidth();
		double cellSize = size / shRays;
		Vector upperLeftPoint = p0.add(up.mult(size / 2)).add(left.mult(size / 2));

		double cnt = 0;
		Random r = new Random();
		// create rectangle from light source and iterate over matrix
		for (int i = 0; i < (int) shRays; i++) {
			for (int j = 0; j < (int) shRays; j++) {
				// find new ray
				Vector p = upperLeftPoint.add(down.mult((i + r.nextDouble()) * cellSize))
						.add(right.mult((j + r.nextDouble()) * cellSize));
				Vector newV = p.sub(endPoint).normalize();
				double t = endPoint.getDistanceScalar(p);
				Vector newP0 = endPoint.add(newV.mult(0.0001));
				Ray newRay = new Ray(t, newP0, newV);

				for (Shape shape : this.scene.getShapes()) { // iterate over shapes
					double newT = newRay.inter(shape); // check for intersection
					if ((newT > 0) && (newT <= t)) { // if intersection is closer
						double trans = this.scene.getMaterials().get(shape.getMat_idx() - 1).getTrans();
						cnt += (shadowValue) * (1 - trans);
						if (trans == 0) { // not transparent - full shadow
							break;
						}
					}
				}
			}
		}
		// return ration between hit rays and total number of rays
		return cnt / (shRays * shRays);
	}


	//////////////////////// FUNCTIONS TO SAVE IMAGES IN PNG FORMAT
	//////////////////////// //////////////////////////////////////////

	/*
	 * Saves RGB data as an image in png format to the specified location.
	 */
	public static void saveImage(int width, byte[] rgbData, String fileName) {
		try {

			BufferedImage image = bytes2RGB(width, rgbData);
			ImageIO.write(image, "png", new File(fileName));

		} catch (IOException e) {
			System.out.println("ERROR SAVING FILE: " + e.getMessage());
		}

	}

	/*
	 * Producing a BufferedImage that can be saved as png from a byte array of
	 * RGB values.
	 */
	public static BufferedImage bytes2RGB(int width, byte[] buffer) {
		int height = buffer.length / width / 3;
		ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_sRGB);
		ColorModel cm = new ComponentColorModel(cs, false, false, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
		SampleModel sm = cm.createCompatibleSampleModel(width, height);
		DataBufferByte db = new DataBufferByte(buffer, width * height);
		WritableRaster raster = Raster.createWritableRaster(sm, db, null);
		BufferedImage result = new BufferedImage(cm, raster, false, null);

		return result;
	}

	public static class RayTracerException extends Exception {
		public RayTracerException(String msg) {
			super(msg);
		}
	}

} // end of rayTrac
