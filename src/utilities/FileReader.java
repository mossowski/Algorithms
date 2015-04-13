package utilities;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import utilities.MaximaPoint;

import javafx.scene.shape.Line;

public class FileReader {

    private Scanner itsInFileData;
    public static ArrayList<Point> itsDataPoints;
    public static ArrayList<MaximaPoint> itsDataMaximaPoints;
    public static ArrayList<Line> itsDataLines;

    public FileReader(String aFileName) throws FileNotFoundException {
        itsInFileData = new Scanner(new File(aFileName));
        itsDataPoints = new ArrayList<Point>();
        itsDataMaximaPoints = new ArrayList<MaximaPoint>();
        itsDataLines = new ArrayList<Line>();
    }

    // --------------------------------------------------------------------------

    /**
     * Loads data
     * 
     */
    public void loadData(int aWhatType) {

        switch (aWhatType) {

        case 1:
            while (itsInFileData.hasNextLine()) {
                String theLine = itsInFileData.nextLine();
                Scanner s = new Scanner(theLine);
                s.useDelimiter(" ");
                int x = s.nextInt();
                int y = s.nextInt();
                Point p = new Point(x, y);
                itsDataPoints.add(p);
                s.close();
            }
            itsInFileData.close();
            break;

        case 2:
            while (itsInFileData.hasNextLine()) {
                String theLine = itsInFileData.nextLine();
                Scanner s = new Scanner(theLine);
                s.useDelimiter(" ");
                int x = s.nextInt();
                int y = s.nextInt();
                String orientation = "None";
                MaximaPoint p = new MaximaPoint(x, y, orientation);
                itsDataMaximaPoints.add(p);
                s.close();
            }
            itsInFileData.close();
            break;

        case 3:
            while (itsInFileData.hasNextLine()) {
                String theLine = itsInFileData.nextLine();
                Scanner s = new Scanner(theLine);
                s.useDelimiter(" ");
                int startX = s.nextInt();
                int startY = s.nextInt();
                int endX = s.nextInt();
                int endY = s.nextInt();
                Line l = new Line(startX, startY, endX, endY);
                itsDataLines.add(l);
                s.close();
            }
            itsInFileData.close();
            break;
        default:
            break;
        }
    }

    // --------------------------------------------------------------------------

    /**
     * Prints data points
     * 
     */
    public void printDataPoints() {
        System.out.println("----------FILE DATA----------");
        for (int i = 0; i < itsDataPoints.size(); i++) {
            System.out.println("X : " + itsDataPoints.get(i).getX() + " Y : " + itsDataPoints.get(i).getY());
        }
        System.out.println("----------FILE DATA----------\n");
    }

    // --------------------------------------------------------------------------

    /**
     * Prints data lines
     * 
     */
    public void printDataLines() {
        System.out.println("----------FILE DATA----------");
        for (int i = 0; i < itsDataLines.size(); i++) {
            System.out.println("Start : " + itsDataLines.get(i).getStartX() + "," + itsDataLines.get(i).getStartY()
                    + " End : " + itsDataLines.get(i).getEndX() + "," + itsDataLines.get(i).getEndY());
        }
        System.out.println("----------FILE DATA----------\n");
    }
}