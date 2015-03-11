import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class ClosestPoints {
	
	ArrayList<Point> byX = null;
	ArrayList<Point> byY = null;
	ArrayList<Point> byX1 = null;
	ArrayList<Point> byX2 = null;
	ArrayList<Point> byY1 = null;
	ArrayList<Point> byY2 = null;
	ArrayList<Point> sd1 = null;
	
	double delta;
	int medianax;
	
	static Point firstPoint = new Point();
	static Point secondPoint = new Point();
	
	ClosestPoints()
	{
		byX = new ArrayList<Point>();
		byY = new ArrayList<Point>();
		byX1 = new ArrayList<Point>();
		byX2 = new ArrayList<Point>();
		byY1 = new ArrayList<Point>();
		byY2 = new ArrayList<Point>();
		sd1 = new ArrayList<Point>();
		
		delta = 0;
		medianax = 0;
		
		firstPoint.setLocation(0, 0);
		secondPoint.setLocation(0, 0);
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
	
	public void partXY()
	{
		medianax = (int) byX.get((byX.size()/2)-1).getX();
		
		for(int i=0; i < byX.size(); i++)
		{
			if(byX.get(i).getX() <= medianax)
				byX1.add(byX.get(i));
			else
				byX2.add(byX.get(i));
		}		
		
		for(int i=0; i < byY.size(); i++)
		{
			if(byY.get(i).getX() <= medianax)
				byY1.add(byY.get(i));
			else
				byY2.add(byY.get(i));
		}	
	}
	
	public void deltaHalf(ArrayList<Point> data)
	{
		double sqrtMain = 0;
		double distance = 0;
		
		for(int i=0; i < data.size()-1; i++)
		{
			for(int j=i; j < data.size()-1; j++)
			{
				sqrtMain = Math.pow(data.get(j+1).getX() - data.get(i).getX(),2) + Math.pow(data.get(j+1).getY() - data.get(i).getY(),2);
				distance = Math.sqrt(sqrtMain);
				
				if(delta == 0 || delta > distance)
				{
					delta = distance;
					firstPoint.setLocation(data.get(i).getX(), data.get(i).getY());
					secondPoint.setLocation(data.get(j+1).getX(), data.get(j+1).getY());
				}
			}
		}
	}
	
	public void deltaHalfLast(ArrayList<Point> data)
	{
		/*
		 * 
		 * 	Need to change this method
		 * 
		 */
	}
	
	public void makeDeltaPart(ArrayList<Point> data1, ArrayList<Point> data2)
	{
		for(int i = data1.size()-1; i >= 0; i--)
		{
			if(data1.get(i).getX() > medianax-delta)
				sd1.add(data1.get(i));
			else
				i = -1;
		}
		
		for(int i=0; i <= data2.size(); i++)
		{
			if(data2.get(i).getX() < medianax+delta)
			{
				sd1.add(data2.get(i));
			}
			else
				i = data2.size();
		}
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
	
	public void printParts()
	{
		System.out.println("---------X1---------");
		for(int i = 0; i < byX1.size(); i++)
		{
			System.out.println("X : " + byX1.get(i).getX() + " Y : " + byX1.get(i).getY());
		}
		System.out.println("---------X1---------\n");
		System.out.println("---------X2---------");
		for(int i = 0; i < byX2.size(); i++)
		{
			System.out.println("X : " + byX2.get(i).getX() + " Y : " + byX2.get(i).getY());
		}
		System.out.println("---------X2---------\n");
		System.out.println("---------Y1---------");
		for(int i = 0; i < byY1.size(); i++)
		{
			System.out.println("X : " + byY1.get(i).getX() + " Y : " + byY1.get(i).getY());
		}
		System.out.println("---------Y1---------\n");
		System.out.println("---------Y2---------");
		for(int i = 0; i < byY2.size(); i++)
		{
			System.out.println("X : " + byY2.get(i).getX() + " Y : " + byY2.get(i).getY());
		}
		System.out.println("---------Y2---------\n");
	}
	
	public void printDeltaSpace()
	{
		System.out.println("---------SD1---------");
		for(int i = 0; i < sd1.size(); i++)
		{
			System.out.println("X : " + sd1.get(i).getX() + " Y : " + sd1.get(i).getY());
		}
		System.out.println("---------SD1---------\n");
	}
	
	public void printResults()
	{
		System.out.println("---------Points---------");
		System.out.println("X : " + firstPoint.getX() + " Y : " + firstPoint.getY());
		System.out.println("X : " + secondPoint.getX() + " Y : " + secondPoint.getY());
		System.out.println("---------Points---------\n");
		
		System.out.println("\nDISTANCE\n" + delta);
	}
	
	public void copy(ArrayList<Point> copy, ArrayList<Point> data)
	{
		for(int i = 0; i < data.size(); i++)
		{
			copy.add(data.get(i));
		}
	}
}