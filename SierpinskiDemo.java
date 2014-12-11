import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.Color;
import java.io.IOException;
import java.util.Arrays;

public class SierpinskiDemo
{
	public static void main(String[] args)
	{
		if (5 == args.length)
		{
			Sierpinski sierpinski = 
				new Sierpinski(
						Integer.valueOf(args[0]),
						Integer.valueOf(args[1]),
						Integer.valueOf(args[2]));
			String outputType = args[3];
			File   file       = new File(args[4]);

			sierpinski.generate();
			toFile(
				toImage(sierpinski),
				file);
		}
		else
		{
			usage();
			exit() ;
		}
	}

	public static void toFile(BufferedImage img, File file)
	{
		try
		{
			ImageIO.write(img, "png", file);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

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

	public static void usage()
	{
		System.out.println("Required parameters <width> <height> <limit of a 3angle's side size> <output type> <output filename>" );
		System.out.println("Available output types" + Arrays.toString(ImageIO.getWriterMIMETypes()));
	}

	public static void exit()
	{
		System.exit(-1);
	}

}
