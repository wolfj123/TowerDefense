package model;

public class CreepKnight extends Creep {
	
	public CreepKnight(int x, int y, Board board) {
		super(x, y, 2, board);
	}
	
	@Override
	public void impact(Tower t){ 
		t.visit(this); 
	}
}
