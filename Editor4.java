import java.awt.Color;

/**
 * Demonstrates a morphing operation that morphs an image into its greyscaled
 * version featured by Runigram.java. 
 * The program recieves two command-line arguments: a string representing the name
 * of the PPM file of a source image, and the number of morphing steps (an int). 
 * For example, to greyscale the cake in 50 steps, use:
 * java Editor4 cake.ppm 50
 * Note: There is no need to scale the target image to the size of the source
 * image, since Runigram.morph performs this action.
 */
public class Editor4 {
    public static void main (String[] args) {
		String source = args[0]; // Gets the PPM file of a source image
		int n = Integer.parseInt(args[1]); // Gets the number of morphing steps
		Color[][] sourceImage = Runigram.read(source);
		Color[][] targetImage = Runigram.grayScaled(sourceImage); // grayscales the source image , and saves it as the target image
		Runigram.setCanvas(sourceImage); // Creates a canvas for displaying the images
		Runigram.morph(sourceImage, targetImage, n); // Morphs the source image into its greyscaled version, gradually, in n steps.
	}
}

