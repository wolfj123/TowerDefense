package model;

public class CreepNinja extends Creep {
	
	public CreepNinja(int x, int y, Board board) {
		super(x, y, 1, board);
	}
	
	@Override
	public void impact(Tower t){ 
		t.visit(this); 
	}
}
