package model;

public class TowerDrug extends TowerAOE{

	public TowerDrug(int x, int y, Board board) {
		super(x, y, 1, board, 2);
	}

	@Override
	public void visit(CreepSkull creep) {
		creep.inflictSlow(6, 2);
	}

	@Override
	public void visit(CreepAlien creep) {
		creep.inflictSlow(6, 2);
		creep.inflictDamage(10);
	}

	@Override
	public void visit(CreepKnight creep) {
		creep.inflictSlow(12, 2);
	}

	@Override
	public void visit(CreepNinja creep) {
		creep.inflictSlow(6, 2);
	}

}
