import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class ClosestPoints {
	
	ArrayList<Point> byX = null;
	ArrayList<Point> byY = null;
	
	ClosestPoints()
	{
		byX = new ArrayList<Point>();
		byY = new ArrayList<Point>();
	}
	
	public void sortByX(ArrayList<Point> data)
	{
		Comparator<Point> comp = new Comparator<Point>()
		{
			public int compare(Point o1, Point o2)
			{
				return new Integer(o1.x).compareTo(o2.x);
			}
	    };
	    copy(byX, data);
	    Collections.sort(byX, comp);
	}
	
	public void sortByY(ArrayList<Point> data)
	{
		Comparator<Point> comp = new Comparator<Point>()
		{
			public int compare(Point o1, Point o2)
			{
				return new Integer(o1.y).compareTo(o2.y);
			}
	    };
	    copy(byY, data);
	    Collections.sort(byY, comp);
	}
	
	public void printData(ArrayList<Point> data) 
	{
		System.out.println("---------BY X---------");
		for(int i = 0; i < byX.size(); i++)
		{
			System.out.println("X : " + byX.get(i).getX() + " Y : " + byX.get(i).getY());
		}
		System.out.println("---------BY X---------\n");
		System.out.println("---------BY Y---------");
		for(int i = 0; i < byY.size(); i++)
		{
			System.out.println("X : " + byY.get(i).getX() + " Y : " + byY.get(i).getY());
		}
		System.out.println("---------BY Y---------\n");
	}
	
	public void copy(ArrayList<Point> copy, ArrayList<Point> data)
	{
		for(int i = 0; i < data.size(); i++)
		{
			copy.add(data.get(i));
		}
	}
}