package model;

public class CreepSkull extends Creep {
	
	public CreepSkull(int x, int y, Board board) {
		super(x, y, 2, board);
	}
	
	@Override
	public void impact(Tower t){ 
		t.visit(this); 
	}
}
