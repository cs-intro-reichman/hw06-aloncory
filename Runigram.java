// This class uses the Color class, which is part of a package called awt,
// which is part of Java's standard class library.
import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

	public static void main(String[] args) {
	    
		//// Hide / change / add to the testing code below, as needed.
		
		// Tests the reading and printing of an image:	
		Color[][] tinypic = read("tinypic.ppm");
		print(tinypic);

		// Creates an image which will be the result of various 
		// image processing operations:
		//Color[][] imageOut;

		// Tests the horizontal flipping of an image:
		//imageOut = flippedHorizontally(tinypic);
		//System.out.println();
		//print(imageOut);

		
		
		//// Write here whatever code you need in order to test your work.
		//// You can reuse / overide the contents of the imageOut array.

		//imageOut = scaled(tinypic, 5, 3);
		//System.out.println();
		//print(imageOut);
	}

	/** Returns a 2D array of Color values, representing the image data
	 * stored in the given PPM file. */
	public static Color[][] read(String fileName) {
		In in = new In(fileName);
		// Reads the file header, ignoring the first and the third lines.
		in.readString();
		int numCols = in.readInt();
		int numRows = in.readInt();
		in.readInt();
		// Creates the image array
		Color[][] image = new Color[numRows][numCols];
		// Reads the RGB values from the file, into the image array. 
		// For each pixel (i,j), reads 3 values from the file,
		// creates from the 3 colors a new Color object, and 
		// makes pixel (i,j) refer to that object.
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				int r = in.readInt(); // Reads the "red" value of the color
				int g = in.readInt(); // Reads the "green" value of the color
				int b = in.readInt(); // Reads the "blue" value of the color
				image[i][j] = new Color(r, g, b); // Creates a new Color object, and makes pixel (i,j) refer to that object.
			}
		}
		return image; // Returns the image array
	}

    // Prints the RGB values of a given color.
	public static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed());   // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s",  c.getBlue());  // Prints the blue component
        System.out.print(")  ");
	}

	// Prints the pixels of the given image.
	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting image.
	public static void print(Color[][] image) {
		for (Color[] rowOfColors: image) {
			for (Color color: rowOfColors) {
				print(color); // Prints the color
			}
			System.out.println(); // Skips to a new line
		}
		System.out.println(); // Skips to a new line
	}
	
	/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
		int numRows = image.length;
		int numCols = image[0].length;
		Color[][] flippedImage = new Color[numRows][numCols];
		for (int i = 0; i < numRows; i++) { 
			for (int j = 0; j < numCols; j++) {
				flippedImage[i][j] = image[i][numCols - j - 1];
			}
		}
		return flippedImage;
	}
	
	/**
	 * Returns an image which is the vertically flipped version of the given image. 
	 */
	public static Color[][] flippedVertically(Color[][] image){
		int numRows = image.length;
		int numCols = image[0].length;
		Color[][] flippedImage = new Color[numRows][numCols];
		for (int i = 0; i < numRows; i++) { 
			for (int j = 0; j < numCols; j++) {
				flippedImage[i][j] = image[numRows - i - 1][j];
			}
		}
		return flippedImage;
	}
	
	// Computes the luminance of the RGB values of the given pixel, using the formula 
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.
	public static Color luminance(Color pixel) {
		int r = pixel.getRed(); // Gets the red value of the pixel
		int g = pixel.getGreen(); // Gets the green value of the pixel
		int b = pixel.getBlue(); // Gets the blue value of the pixel
		int lum = (int)(0.299 * r + 0.587 * g + 0.114 * b); // Computes the luminance of the pixel.
		return new Color(lum,lum,lum); // Returns the "greyed" pixel
	}
	
	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */
	public static Color[][] grayScaled(Color[][] image) {
		int numRows = image.length;
		int numCols = image[0].length;
		Color[][] greyedImage = new Color[numRows][numCols];
		for (int i = 0; i < numRows; i++) { 
			for (int j = 0; j < numCols; j++) {
				greyedImage[i][j] = luminance(image[i][j]); // Grayscales the (i,j) pixel in the original image 
			}
		}
		return greyedImage; // Returns the grayscaled version of the given image.
	}	
	
	/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		Color[][] scaledImage = new Color[width][height];
		int w0 = image.length; // Gets the width of the original image
		int h0 = image[0].length; // Gets the height of the original image
		double widthScaleFactor = (double) w0 / width; 
		for (int i = 1; i <= width; i++) {
			double heightScaleFactor = (double) h0 / height;
			for (int j = 1; j <= height; j++) {
				scaledImage[i - 1][j - 1] = image[(int)((i - 1) * widthScaleFactor)][(int)((j - 1) * heightScaleFactor)];
			}
		}
		return scaledImage;
	}
	
	/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
		int blendedRed = (int) (alpha * c1.getRed() + (1 - alpha) * c2.getRed()); // Computes the red value of the blended color
		int blendedGreen = (int) (alpha * c1.getGreen() + (1 - alpha) * c2.getGreen()); // Computes the green value of the blended color
		int blendedBlue = (int) (alpha * c1.getBlue() + (1 - alpha) * c2.getBlue()); // Computes the blue value of the blended color
		//Color blendedColor = new Color(blendedRed, blendedGreen, blendedBlue); // Constructs the blended color
		return new Color(blendedRed, blendedGreen, blendedBlue); // Returns the blended color
	}
	
	/**
	 * Cosntructs and returns an image which is the blending of the two given images.
	 * The blended image is the linear combination of (alpha) part of the first image
	 * and (1 - alpha) part the second image.
	 * Assume that the two images have the same dimensions.
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
		// Gets image dimensions
		int numRows = image1.length;
		int numCols = image1[0].length;
		Color[][] blendedImage = new Color[numRows][numCols]; 
		for (int i = 0; i < numRows; i++) { 
			for (int j = 0; j < numCols; j++) {
				Color c1 = image1[i][j]; // Gets the color of (i,j) pixel in image1
				Color c2 = image2[i][j]; // Gets the color of (i,j) pixel in image2
				blendedImage[i][j] = blend(c1, c2, alpha); // Blendes the colors
			}
		}
		return blendedImage; // Returns the blended image
	}

	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		Color[][] scaledTarget = scaled(target, source.length, source[0].length); // Scales the target image to the dimensions of the source image.
		for (int i = 0; i <= n; i++) {
			double alpha = (n - i) / (double) n; // Computes alpha by the formula : alpha = (n - i) / n
			Color[][] currnetImage = blend(source, scaledTarget, alpha); // Creates the image in the i-th step
			display(currnetImage); // Displays the blended image in the i-th step
			StdDraw.pause(500); 
		}
	}
	
	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(height, width);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}

