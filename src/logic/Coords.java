package logic;

public class Coords {
	private int _x;
	private int _y;
	
	public Coords(int x, int y){
		_x = x;
		_y = y;
	}
	
	public int getX(){
		return _x;		
	}
	
	public int getY(){
		return _y;		
	}

	public String toString (){
		return "["+_x+","+_y+"] ";
	}
}
