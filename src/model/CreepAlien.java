package model;

public class CreepAlien extends Creep {
	
	public CreepAlien(int x, int y, Board board) {
		super(x, y, 2, board);
	}
	
	@Override
	public void impact(Tower t){ 
		t.visit(this); 
	}
}
