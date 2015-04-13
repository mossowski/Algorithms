package utilities;

public class MaximaPoint {

	private int x;
	private int y;
	private String orientation;
	
	public MaximaPoint(int ax, int ay, String aorient){
		
		x = ax;
		y = ay;
		orientation = aorient;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getOrientation() {
		return orientation;
	}
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	
	
}
