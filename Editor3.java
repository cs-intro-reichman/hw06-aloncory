import java.awt.Color;

/**
 * Demonstrates the morphing operation featured by Runigram.java. 
 * The program recieves three command-line arguments: a string representing the name
 * of the PPM file of a source image, a string representing the name of the PPM file
 * of a target image, and the number of morphing steps (an int). 
 * For example, to morph the cake into ironman in 50 steps, use:
 * java Editor3 cake.ppm ironman.ppm 50
 * Note: There is no need to scale the target image to the size of the source
 * image, since Runigram.morph performs this action.
 */
public class Editor3 {

	public static void main (String[] args) {
		String source = args[0]; // Gets the PPM file of a source image
		String target = args[1]; // Gets the PPM file of a target image
		int n = Integer.parseInt(args[2]); // Gets the number of morphing steps
		Color[][] sourceImage = Runigram.read(source);
		Color[][] targetImage = Runigram.read(target);
		Runigram.setCanvas(sourceImage); // Creates a canvas for displaying the images
		Runigram.morph(sourceImage, targetImage, n); // Morphs the source image into the target image, gradually, in n steps.
	}
}
