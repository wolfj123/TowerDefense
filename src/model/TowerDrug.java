package model;

public class TowerDrug extends TowerAOE{

	public TowerDrug(int x, int y, Board board) {
		super(x, y, 1, board, 2);
	}

	@Override
	public void visit(CreepSkull creep) {
		_isAttacking=true;
		creep.inflictSlow(6, 2);
	}

	@Override
	public void visit(CreepAlien creep) {
		_isAttacking=true;
		creep.inflictSlow(6, 2);
		creep.inflictDamage(10);
	}

	@Override
	public void visit(CreepKnight creep) {
		_isAttacking=true;
		creep.inflictSlow(12, 2);
	}

	@Override
	public void visit(CreepNinja creep) {
		_isAttacking=true;
		creep.inflictSlow(6, 2);
	}

}
