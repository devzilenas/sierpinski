import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.Color;
import java.io.IOException;
import java.util.Arrays;

/**
 * Demonstrates usage of Sierpinski class.
 *
 * @author Marius Žilėnas
 * @since 2014-12-11
 */
public class SierpinskiDemo
{
	/**
	 * Number of arguments required.
	 */
	public static final int REQUIRED_ARGS = 5;

	public static void main(String[] args)
	{
		/**
		 * Sierpinski triangle we will be generating.
		 */
		Sierpinski sierpinski = null;
		/**
		 * Output file.
		 */
		File       file       = null;
		/**
		 * MIME for the image output.
		 */
		String     type       = null;

		if (REQUIRED_ARGS == args.length)
		{
			sierpinski = 
				new Sierpinski(
						Integer.valueOf(args[0]),
						Integer.valueOf(args[1]),
						Integer.valueOf(args[2]));
			type = args[3];

			file = new File(args[4]);

			sierpinski.generate();
			toFile(
				toImage(sierpinski),
				type,
				file);
		}
		else
		{
			usage();
			exit() ;
		}
	}

	/**
	 * Saves buffered image to a given file.
	 *
	 * @param img image to be save to the file
	 *
	 * @param type Type name of the MIME for image
	 *
	 * @param file file to save an image to
	 * 
	 * @see javax.imageio.ImageIO#getWriterMIMEType
	 */
	public static void toFile(BufferedImage img, String type, File file)
	{
		try
		{
			ImageIO.write(img, type, file);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Creates an image from the given Sierpinski triangle.
	 *
	 * @param sierpinski triangle to generate picture of
	 * @return image of the Sierpinski triangle
	 */
	public static BufferedImage toImage(Sierpinski sierpinski)
	{
		BufferedImage img = new BufferedImage(
				sierpinski.getWidth() ,
				sierpinski.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = img.createGraphics();

		g.setColor(Color.black);
		g.fillRect(0, 0, img.getWidth(), img.getHeight());
		g.setColor(Color.green);
		for (Line2D line : sierpinski.getLines())
		{
			g.draw(line);
		}
		g.dispose();
		return img;
	}

	/**
	 * Describes the program.
	 */
	public static void usage()
	{
		System.out.println("Required parameters <width> <height> <limit of a 3angle's side size> <output type> <output filename>" );
		System.out.println("Available output types" + Arrays.toString(ImageIO.getWriterMIMETypes()));
	}

	/**
	 * Exits the program.
	 */
	public static void exit()
	{
		System.exit(-1);
	}
}
