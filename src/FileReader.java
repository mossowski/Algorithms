import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class FileReader {

	private Scanner inFileData;
	public ArrayList<Point> data;
	
	FileReader(String fileName) throws FileNotFoundException
	{	
		inFileData = new Scanner(new File(fileName));
		data = new ArrayList<Point>();

		while(inFileData.hasNextLine())
		{
			String line = inFileData.nextLine();
			Scanner s = new Scanner(line);
			s.useDelimiter(" ");
			int x = s.nextInt();
			int y = s.nextInt();
			Point p = new Point(x,y);					
			data.add(p);
			s.close();
		}
		inFileData.close();
	}
	
	public void printData() 
	{
		System.out.println("----------FILE DATA----------");
		for(int i = 0; i < data.size(); i++)
		{
			System.out.println("X : " + data.get(i).getX() + " Y : " + data.get(i).getY());
		}
		System.out.println("----------FILE DATA----------\n");
	}
}