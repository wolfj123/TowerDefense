package model;

public class TowerLava extends TowerAOE{

	public TowerLava(int x, int y, Board board) {
		super(x, y, 1, board, 2);
	}

	@Override
	public void visit(CreepSkull creep) {
		creep.inflictDamage(15);
	}

	@Override
	public void visit(CreepAlien creep) {
		creep.inflictDamage(15);
	}

	@Override
	public void visit(CreepKnight creep) {
		creep.inflictDamage(10);
	}

	@Override
	public void visit(CreepNinja creep) {
		creep.inflictDamage(15);
	}
	
}
