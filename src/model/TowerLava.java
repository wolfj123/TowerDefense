package model;

public class TowerLava extends TowerAOE{

	public TowerLava(int x, int y, Board board) {
		super(x, y, 1, board, 2);
	}

	@Override
	public void visit(CreepSkull creep) {
		_isAttacking=true;
		creep.inflictDamage(15);
	}

	@Override
	public void visit(CreepAlien creep) {
		_isAttacking=true;
		creep.inflictDamage(15);
	}

	@Override
	public void visit(CreepKnight creep) {
		_isAttacking=true;
		creep.inflictDamage(10);
	}

	@Override
	public void visit(CreepNinja creep) {
		_isAttacking=true;
		creep.inflictDamage(15);
	}
	
}
