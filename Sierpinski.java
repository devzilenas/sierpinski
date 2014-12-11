import java.util.List       ;
import java.util.LinkedList ;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D ;

public class Sierpinski
{
	public int width ;
	public int height;
	/**
	 * The smallest size of a side of the triangle.
	 */
	public int limit ;

	public List<Line2D> lines = new LinkedList<>();

	public Sierpinski(int width, int height, int limit)
	{
		this.width  = width;
		this.height = height;
		this.limit  = limit;
	}

	public int getLimit()
	{
		return limit;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public List<Line2D> getLines()
	{
		return lines;
	}

	public void addLine(Point2D p1, Point2D p2)
	{
		getLines().add(
				new Line2D.Double(p1,p2));
	} 

	public void generate()
	{
		generate(
				new Point2D.Double(getWidth() / 2, 0          ),
				new Point2D.Double(0             , getHeight() - 1 ),
				new Point2D.Double(getWidth() - 1, getHeight() - 1));
	}

	public static double triangleArea(Point2D top, Point2D left, Point2D right)
	{
		return Math.abs(top.getX()*(right.getY()-left.getY()) + right.getX()*(left.getY() - top.getY()) + left.getX()*(top.getY()-right.getY())) / 2;
	}

	public void generate(Point2D top, Point2D left, Point2D right)
	{
		if (getLimit() < triangleArea(top,left,right)) 
		{
			Point2D leftMiddle   = 
				new Point2D.Double(
						left.getX() + (top.getX()  - left.getX()) / 2,
						top.getY()  + (left.getY() - top.getY())  / 2);
			Point2D rightMiddle  = 
				new Point2D.Double(
						top.getX() + (right.getX() - top.getX()) / 2,
						leftMiddle.getY());
			Point2D bottomMiddle =
				new Point2D.Double(
						top.getX() ,
						left.getY());
			
			generate(top        , leftMiddle  , rightMiddle );
			generate(leftMiddle , left        , bottomMiddle);
			generate(rightMiddle, bottomMiddle, right       );
		}
		else
		{
			addLine(top , right);
			addLine(top , left );
			addLine(left, right);
		}
	}
}
