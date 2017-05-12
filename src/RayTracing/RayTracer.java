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
// import javafx.scene.shape.Sphere;
import javax.imageio.ImageIO;


/**
 *  Main class for ray tracing exercise.
 *//// try to push from pc2

public class RayTracer {
	public int imageWidth;
	public int imageHeight;
	public int cnt_mtl = 0, cnt_pln = 0 ,cnt_sph = 0 ,cnt_lgt = 0;
	public Scene scene = new Scene();
	private double[][][] screen; //TODO- change int? 
	
	/**
	 * Runs the ray tracer. Takes scene file, output image file and image size as input.
	 */
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////            main                 /////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args) {

		try {

			RayTracer tracer = new RayTracer();
			
			// Default values:
			tracer.imageWidth = 500;
			tracer.imageHeight = 500;
			if (args.length < 2)
				throw new RayTracerException("Not enough arguments provided. Please specify an input scene file and an output image file for rendering.");
			
			String sceneFileName = args[0];
			String outputFileName = args[1];

			
			if (args.length > 3)
			{
				tracer.imageWidth = Integer.parseInt(args[2]);
				tracer.imageHeight = Integer.parseInt(args[3]);
			}
			
			//Create screen:
			tracer.screen = new double[tracer.imageWidth][tracer.imageHeight][3];

			// Parse scene file:
			tracer.parseScene(sceneFileName);
			
			// Render scene:
			tracer.renderScene(outputFileName);

			
			//		} catch (IOException e) {
			//			System.out.println(e.getMessage());
		} catch (RayTracerException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("_goodbye_");
	} // end of main 

	/**
	 * Parses the scene file and creates the scene. Change this function so it generates the required objects.
	 */
	public void parseScene(String sceneFileName) throws IOException, RayTracerException
	{
		FileReader fr = new FileReader(sceneFileName);
		
		BufferedReader r = new BufferedReader(fr);
		String line = null;
		int lineNum = 0;
		System.out.println("Started parsing scene file " + sceneFileName);

		while ((line = r.readLine()) != null)
		{
			line = line.trim();
			++lineNum;

			if (line.isEmpty() || (line.charAt(0) == '#'))
			{  // This line in the scene file is a comment
				continue;
			}
			else
			{
				String code = line.substring(0, 3).toLowerCase();
				// Split according to white space characters:
				String[] params = line.substring(3).trim().toLowerCase().split("\\s+");

				if (code.equals("cam"))
				{
					Camera cam = new Camera();
					cam.setpX(Double.parseDouble(params[0])); 		// set position x
					cam.setpY(Double.parseDouble(params[1])); 		// set position y
					cam.setpZ(Double.parseDouble(params[2])); 		// set position z
					cam.setLaX(Double.parseDouble(params[3]));		// set look at x
					cam.setLaY(Double.parseDouble(params[4]));		// set look at y
					cam.setLaZ(Double.parseDouble(params[5]));		// set look at z
					cam.setUvX(Integer.parseInt(params[6]));		// set up vector x
					cam.setUvY(Integer.parseInt(params[7]));		// set up vector y
					cam.setUvZ(Integer.parseInt(params[8]));		// set up vector z
					cam.setSc_dist(Double.parseDouble(params[9])); 	//set up distance from view 
					cam.setSw_from_cam(Integer.parseInt(params[10]));//set up width from view
					
					scene.setCam(cam); 								// set camera to scene 
					
					System.out.println(String.format("Parsed camera parameters (line %d)", lineNum));
				}
				else if (code.equals("set"))
				{

					MySet set = new MySet();
					set.setBgr(Double.parseDouble(params[0]));		//R background color.
					set.setBgg(Double.parseDouble(params[1])); 		//G background color.
					set.setBgb(Double.parseDouble(params[2])); 		//B background color.
					set.setSh_rays(Integer.parseInt(params[3])); 	//root number of shadow rays.
					set.setRec_max(Integer.parseInt(params[4])); 	//maximum recursion.
					
					if(params.length > 5){
					set.setSS(Integer.parseInt(params[5])); 		//super sampling level.
					} else {
						set.setSS(2);
					}
					
					scene.setMySet(set); 							// set the setting
					
					System.out.println(String.format("Parsed general settings (line %d)", lineNum));
				}
				else if (code.equals("mtl"))
				{
					Material mat = new Material();
					mat.setIndex(cnt_mtl);
					cnt_mtl++;
					mat.setDr(Double.parseDouble(params[0]));                    
					mat.setDg(Double.parseDouble(params[1]));
					mat.setDb(Double.parseDouble(params[2]));
					mat.setSr(Double.parseDouble(params[3]));
					mat.setSg(Double.parseDouble(params[4]));
					mat.setSb(Double.parseDouble(params[5]));
					mat.setRr(Double.parseDouble(params[6]));
					mat.setRg(Double.parseDouble(params[7]));
					mat.setRb(Double.parseDouble(params[8]));
					mat.setPhong(Float.parseFloat(params[9]));
					mat.setTrans(Double.parseDouble(params[10]));
					
					scene.setMaterial(mat);
					
					System.out.println(String.format("Parsed material (line %d)", lineNum));
				}
				else if (code.equals("sph"))
				{
					// Add code here to parse sphere parameters
					Sphere sphere = new Sphere();
					sphere.setIndex(cnt_sph);
					cnt_sph++;
					sphere.setCx(Double.parseDouble(params[0])); 		// sphere center x
					sphere.setCy(Double.parseDouble(params[1]));		// sphere center y
					sphere.setCz(Double.parseDouble(params[2]));		// sphere center z
					sphere.setRadius(Double.parseDouble(params[3])); 	// radius
					sphere.setMat_idx(Integer.parseInt(params[4]));	// material index number
					
					scene.getSpheres().add(sphere); 				// add a sphere to the sphere list. 
					
					System.out.println(String.format("Parsed sphere (line %d)", lineNum));
				}
				else if (code.equals("pln")) {

					Plane pln = new Plane();
					pln.setIndex(cnt_pln);
					cnt_pln++;
					pln.setNx(Integer.parseInt(params[0]));                    
					pln.setNy(Integer.parseInt(params[1]));
					pln.setNz(Integer.parseInt(params[2]));
					pln.setOffset(Integer.parseInt(params[3]));
					pln.setMat_idx(Integer.parseInt(params[4]));
					
					scene.getPlanes().add(pln);						// add a plane to the pln list.
					
					System.out.println(String.format("Parsed plane (line %d)", lineNum));
				}
				else if (code.equals("trg"))
				{
					
					Triangle trg = new Triangle();
					trg.setMat_idx(cnt_mtl);
					cnt_mtl++;
					trg.setP0x(Double.parseDouble(params[0]));                
					trg.setP0y(Double.parseDouble(params[1]));
					trg.setP0z(Double.parseDouble(params[2]));
					trg.setP1x(Double.parseDouble(params[3]));
					trg.setP1y(Double.parseDouble(params[4]));
					trg.setP1z(Double.parseDouble(params[5]));
					trg.setP2x(Double.parseDouble(params[6]));
					trg.setP2y(Double.parseDouble(params[7]));
					trg.setP2z(Double.parseDouble(params[8]));
					trg.setMat_idx(Integer.parseInt(params[9]));
					
					scene.getTriangles().add(trg);
					
					System.out.println(String.format("Parsed cylinder (line %d)", lineNum)); //TODO 
				}
				else if (code.equals("lgt"))
				{
					Light lict  = new Light();
					lict.setIndex(cnt_lgt);
					cnt_lgt++;
					lict.setPx(Double.parseDouble(params[0])); 		// position x
					lict.setPy(Double.parseDouble(params[1])); 		// position y
					lict.setPz(Double.parseDouble(params[2])); 		// position z
					lict.setR(Double.parseDouble(params[3])); 		// red
					lict.setG(Double.parseDouble(params[4])); 		// green
					lict.setB(Double.parseDouble(params[5])); 		// blue
					lict.setSpec(Double.parseDouble(params[6]));		// specular
					lict.setShadow(Double.parseDouble(params[7]));	// shadow
					lict.setWidth(Integer.parseInt(params[8]));		// width
					
					scene.getLights().add(lict);
					
					System.out.println(String.format("Parsed light (line %d)", lineNum));
				}
				else
				{
					System.out.println(String.format("ERROR: Did not recognize object: %s (line %d)", code, lineNum));
				}
			}
		}
		
		// It is recommended that you check here that the scene is valid,
		// for example camera settings and all necessary materials were defined.
		
		if(scene.cam == null || scene.mySet == null){
			System.err.println("no camera or set in file");
		}
		
		System.out.println("Finished parsing scene file " + sceneFileName);
	}

	/**
	 * Renders the loaded scene and saves it to the specified file location.
	 */
	public void renderScene(String outputFileName)
	{
		long startTime = System.currentTimeMillis();

		// Create a byte array to hold the pixel data:
		byte[] rgbData = new byte[this.imageWidth * this.imageHeight * 3];

		// Put your ray tracing code here!
		//
		// Write pixel color values in RGB format to rgbData:
		// Pixel [x, y] red component is in rgbData[(y * this.imageWidth + x) * 3]
		//            green component is in rgbData[(y * this.imageWidth + x) * 3 + 1]
		//             blue component is in rgbData[(y * this.imageWidth + x) * 3 + 2]
		//
		// Each of the red, green and blue components should be a byte, i.e. 0-255
		
		for (int i = 0; i < imageWidth; i++) {				// run over x axis
			for (int j = 0; j < imageHeight; j++) {			// run over y axis
				this.screen[i][j] = getColorForPix(i,j);
			}
		}
		
		int weight = this.imageWidth;
		int height = this.imageHeight;
		
		int p = 0;
		for (int i = 0; i < weight; i++) {
			for (int j = 0; j < height; j++) {
				for (int k = 0; k < 3; k++) {
					rgbData[p] = (byte) this.screen[i][j][k];
					p++;
				}
			}
		}
		
		long endTime = System.currentTimeMillis();
		Long renderTime = endTime - startTime;

		//saveImage(this.imageWidth, rgbData, outputFileName);
		
		// The time is measured for your own conveniece, rendering speed will not affect your score
		// unless it is exceptionally slow (more than a couple of minutes)
		System.out.println("Finished rendering scene in " + renderTime.toString() + " milliseconds.");

		// This is already implemented, and should work without adding any code.
		saveImage(this.imageWidth, rgbData, outputFileName);

		System.out.println("Saved file " + outputFileName);

	}
	
	
	
	//////////////////////// Utilities functions //////////////////////////////
	private double[] getColorForPix(int XaxisNum, int YaxisNum) {
		if( (XaxisNum == 250) && (YaxisNum == 260) ){
			int yuval = 1;// braking point //TODO remove 
		}
		int red = 0, green = 0,blue = 0;
		double[] ans = new double[3]; 
		int size = scene.getMySet().getSS();
		double[][][] superSampleMatrix = new double[size][size][3];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				superSampleMatrix[i][j] =  sampleColorByRay(XaxisNum, YaxisNum,i ,j);
				red += superSampleMatrix[i][j][0];		// sum red
				green += superSampleMatrix[i][j][1];	// sum green
				blue += superSampleMatrix[i][j][2];		// sum blue
			}
		}
		ans[0] = (int) (red /(size*size));				// add average color
		ans[1] = (int) (green/(size*size));				// add average color
		ans[2] = (int) (blue/(size*size));				// add average color
				
		return ans;
	}



	private double[] sampleColorByRay(int xaxisNum, int yaxisNum, int i, int j) {
		double t = 0;
		int flag = 0;
		double red = 255,green = 255,blue = 255; // TODO - default value is white? Spouse to never happen that stays without change
		Sphere sphere = new Sphere(); //TODO
		Ray ray = new Ray();
		ray.cameraRay(this, xaxisNum, yaxisNum, i, j);
		for (Sphere sph : this.scene.getSpheres()) {
			if((t = ray.inter(sph)) > 0 ){
				if (t < ray.t ) { // no go 
					ray.t = t;
					sphere = sph;
					flag = 1;
				}
			}
		} //for
		if(flag > 0){
			red = this.scene.materials.get(sphere.getMat_idx() -1 ).getDr() * 255;
			green = this.scene.materials.get(sphere.getMat_idx() -1 ).getDg() * 255;
			blue = this.scene.materials.get(sphere.getMat_idx() -1 ).getDb() * 255;
		}
		double [] ans = {red , green, blue};
		return ans;
	}

	//////////////////////// FUNCTIONS TO SAVE IMAGES IN PNG FORMAT //////////////////////////////////////////


	/*
	 * Saves RGB data as an image in png format to the specified location.
	 */
	public static void saveImage(int width, byte[] rgbData, String fileName)
	{
		try {

			BufferedImage image = bytes2RGB(width, rgbData);
			ImageIO.write(image, "png", new File(fileName));

		} catch (IOException e) {
			System.out.println("ERROR SAVING FILE: " + e.getMessage());
		}

	}

	/*
	 * Producing a BufferedImage that can be saved as png from a byte array of RGB values.
	 */
	public static BufferedImage bytes2RGB(int width, byte[] buffer) {
		int height = buffer.length / width / 3;
		ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_sRGB);
		ColorModel cm = new ComponentColorModel(cs, false, false,
				Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
		SampleModel sm = cm.createCompatibleSampleModel(width, height);
		DataBufferByte db = new DataBufferByte(buffer, width * height);
		WritableRaster raster = Raster.createWritableRaster(sm, db, null);
		BufferedImage result = new BufferedImage(cm, raster, false, null);

		return result;
	}

	public static class RayTracerException extends Exception {
		public RayTracerException(String msg) {  super(msg); }
	}


} // end of rayTrac
