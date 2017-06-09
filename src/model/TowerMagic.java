package model;

public class TowerMagic extends Tower {

	public TowerMagic(int x, int y, Board board) {
		super(x, y, 1, board, 2);
	}

	@Override
	public void visit(CreepSkull creep) {
		creep.inflictDamage(25);
	}

	@Override
	public void visit(CreepAlien creep) {
		creep.inflictDamage(10);
	}

	@Override
	public void visit(CreepKnight creep) {
		creep.inflictDamage(30);
	}

	@Override
	public void visit(CreepNinja creep) {
		creep.inflictDamage(10);
	}

}
