package logic;

public class TowerPoison extends Tower {

	public TowerPoison(int x, int y, Board board) {
		super(x, y, 1, board, 2);
	}

	@Override
	public void visit(CreepSkull creep) {
		_isAttacking=true;
		creep.inflictDamage(20);
	}

	//TODO: maybe add Null effect?
	@Override
	public void visit(CreepAlien creep) {
		_isAttacking=true;
		creep.inflictDamage(0);
	}

	@Override
	public void visit(CreepKnight creep) {
		_isAttacking=true;
		creep.inflictPoison(2,10);
	}

	@Override
	public void visit(CreepNinja creep) {
		_isAttacking=true;
		creep.inflictPoison(1.5,10);
	}

}
