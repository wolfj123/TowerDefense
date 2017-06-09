package model;

public class TowerArrow extends Tower {

	public TowerArrow(int x, int y, Board board) {
		super(x, y, 2, board, 2);
	}

	@Override
	public void visit(CreepSkull creep) {
		creep.inflictDamage(15);

	}

	@Override
	public void visit(CreepAlien creep) {
		creep.inflictDamage(30);

	}

	//TODO: maybe add null effect?
	@Override
	public void visit(CreepKnight creep) {
		creep.inflictDamage(0);

	}

	@Override
	public void visit(CreepNinja creep) {
		creep.inflictDamage(30);

	}

}
