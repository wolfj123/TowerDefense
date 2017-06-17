package model;

public class TowerArrow extends Tower {

	public TowerArrow(int x, int y, Board board) {
		super(x, y, 2, board, 2);
	}

	@Override
	public void visit(CreepSkull creep) {
		_isAttacking=true;
		creep.inflictDamage(15);

	}

	@Override
	public void visit(CreepAlien creep) {
		_isAttacking=true;
		creep.inflictDamage(30);

	}

	//TODO: maybe add null effect?
	@Override
	public void visit(CreepKnight creep) {
		_isAttacking=true;
		creep.inflictDamage(0);

	}

	@Override
	public void visit(CreepNinja creep) {
		_isAttacking=true;
		creep.inflictDamage(30);

	}

}
