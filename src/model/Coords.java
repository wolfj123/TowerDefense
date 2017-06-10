package model;

public class Coords {
	private int _x;
	private int _y;
	
	public Coords(int x, int y){
		if(x<-1 | x>1)
			throw new IllegalArgumentException("x must be -1,0,1");
		if(y<-1 | y>1)
			throw new IllegalArgumentException("y must be -1,0,1");
		
		_x = x;
		_y = y;
	}
	
	public int getX(){
		return _x;		
	}
	
	public int getY(){
		return _y;		
	}
	
	//TODO: Maybe add icon depending whether is grass or path?
}
