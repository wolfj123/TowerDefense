package model;

public class TowerMagic extends Tower {

	public TowerMagic(int x, int y, Board board) {
		super(x, y, 1, board, 2);
	}

	@Override
	public void visit(CreepSkull creep) {
		_isAttacking=true;
		creep.inflictDamage(25);
	}

	@Override
	public void visit(CreepAlien creep) {
		_isAttacking=true;
		creep.inflictDamage(10);
	}

	@Override
	public void visit(CreepKnight creep) {
		_isAttacking=true;
		creep.inflictDamage(30);
	}

	@Override
	public void visit(CreepNinja creep) {
		_isAttacking=true;
		creep.inflictDamage(10);
	}

}
