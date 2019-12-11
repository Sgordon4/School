package pa2;

public class Tuple {
	
	private int x, y;
	
	
	public Tuple() {
		x = 0;
		y = 0;
	}
	
	public Tuple(int a, int b) {
		x = a;
		y = b;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public String toString() {
		return  "< " + x + " " + y + " >";
	}

}

