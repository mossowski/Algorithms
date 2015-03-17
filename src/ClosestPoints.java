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
	ArrayList<Point> sd2 = null;
	
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
		sd2 = new ArrayList<Point>();
		
		delta = 0;
		medianax = 0;
		
		firstPoint.setLocation(0, 0);
		secondPoint.setLocation(0, 0);
	}
	
	// SORTING DATA BY X
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
	
	// SORTING DATA BY Y
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
	
	//DIVIDE SORTED ARRAYS TO TWO PARTS (x1,x2/y1,y2) 
	public void partXY()
	{
		double x = 0;
		medianax = (int) byX.get((byX.size()/2)-1).getX();
		
		for(int i=0; i < byX.size(); i++)
		{
			x = byX.get(i).getX();
			
			if(x <= medianax)
				byX1.add(byX.get(i));
			else
				byX2.add(byX.get(i));
		}		
		
		for(int i=0; i < byY.size(); i++)
		{
			x = byY.get(i).getX();
			
			if(x <= medianax)
				byY1.add(byY.get(i));
			else
				byY2.add(byY.get(i));
		}	
	}
	
	// COUNT DELTA FROM TWO PARTS
	public void deltaHalf()
	{
		double distance = 0;
		
		for(int i=0; i < byX1.size(); i++)
		{
			for(int j=i; j < byX1.size()-1; j++)
			{
				distance = Math.pow(byX1.get(j+1).getX() - byX1.get(i).getX(),2) + Math.pow(byX1.get(j+1).getY() - byX1.get(i).getY(),2);
				
				if(delta == 0 || delta > distance)
				{
					delta = distance;
					firstPoint.setLocation(byX1.get(i).getX(), byX1.get(i).getY());
					secondPoint.setLocation(byX1.get(j+1).getX(), byX1.get(j+1).getY());
				}
			}
		}
		
		for(int i=0; i < byX2.size(); i++)
		{
			for(int j=i; j < byX2.size()-1; j++)
			{
				distance = Math.pow(byX2.get(j+1).getX() - byX2.get(i).getX(),2) + Math.pow(byX2.get(j+1).getY() - byX2.get(i).getY(),2);

				if(delta == 0 || delta > distance)
				{
					delta = distance;
					firstPoint.setLocation(byX2.get(i).getX(), byX2.get(i).getY());
					secondPoint.setLocation(byX2.get(j+1).getX(), byX2.get(j+1).getY());
				}
			}
		}
	}
	
	// MAKE DELTA SPACE
	public void makeDeltaPart()
	{
		double x = 0;
		
		delta = Math.sqrt(delta);
		
		for(int i = 0; i < byY1.size(); i++)
		{
			x = byY1.get(i).getX();
			
			if(x > medianax-delta)
				sd1.add(byY1.get(i));
		}
		
		for(int i=0; i < byY2.size(); i++)
		{
			x = byY2.get(i).getX();
			
			if(x <= medianax + delta)
				sd2.add(byY2.get(i));
		}
		
	}
	
	//SEARCH FOR CLOSEST POINTS IN DELTA SPACE
	public void deltaHalfLast()
	{
		int blueindex = 0;
		double distance = 0;
		double y1,y2 = 0;
		double x1,x2 = 0;
		
		for(int i=0; i < sd1.size(); i++)
		{
			for(int j=blueindex; j < sd2.size(); j++)
			{
				y1 = sd1.get(i).getY();
				y2 = sd2.get(j).getY();
				x1 = sd1.get(i).getX();
				x2 = sd2.get(j).getX();
				
				if(y2 < y1 - delta)
					blueindex ++;
				
				else if(y2 > y1 - delta && y2 <  delta + y1)
				{
					distance = Math.pow(x2 - x1,2) + Math.pow(y2 - y1,2); 
	
					if(delta == 0 || delta > distance)
					{
						delta = Math.sqrt(distance);
						firstPoint.setLocation(x1, y1);
						secondPoint.setLocation(x2, y2);
					}
				}
				
				else if(y2 > delta + y1)
					break;
			}
		}
	}
	

// PRINT METHODS
	
	public void printData()
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
		System.out.println("---------SD2---------");
		for(int i = 0; i < sd2.size(); i++)
		{
			System.out.println("X : " + sd2.get(i).getX() + " Y : " + sd2.get(i).getY());
		}
		System.out.println("---------SD2---------\n");
	}
	
	public void printResults()
	{
		System.out.println("---------Points---------");
		System.out.println("X : " + firstPoint.getX() + " Y : " + firstPoint.getY());
		System.out.println("X : " + secondPoint.getX() + " Y : " + secondPoint.getY());
		System.out.println("---------Points---------\n");
		
		System.out.println("\nDISTANCE\n" + delta);
		System.out.println("\nMediana\n" + medianax);
	}
	
	public void copy(ArrayList<Point> copy, ArrayList<Point> data)
	{
		for(int i = 0; i < data.size(); i++)
		{
			copy.add(data.get(i));
		}
	}
}