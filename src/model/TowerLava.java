package model;

public class TowerLava extends Tower{

	public TowerLava(int x, int y, Board board) {
		super(x, y, 1, board, 2);
		
	}

	@Override
	public void visit(CreepSkull skull) {
		skull.inflictDamage(15);
	}

	@Override
	public void visit(CreepAlien alien) {
		alien.inflictDamage(15);
	}

	@Override
	public void visit(CreepKnight knight) {
		knight.inflictDamage(10);
	}

	@Override
	public void visit(CreepNinja ninja) {
		ninja.inflictDamage(15);
	}
	
}
