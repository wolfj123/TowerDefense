package model;

public class TowerDragon extends Tower {

	private boolean _wingsUp;
	
	public TowerDragon(int x, int y, Board board) {
		super(x, y, 2, board, 2);
	}
	
	@Override
	public void visit(CreepSkull creep) {
		creep.inflictDamage(15);
	}

	@Override
	public void visit(CreepAlien creep) {
		creep.inflictDamage(10);
	}

	@Override
	public void visit(CreepKnight creep) {
		creep.inflictDamage(8);
	}

	@Override
	public void visit(CreepNinja creep) {
		creep.inflictDamage(11);
	}
	
	@Override
	protected void tickAction(){
		// TODO Auto-generated method stub
	}
	
	@Override
	protected void tickPassive(){
		// TODO Auto-generated method stub
	}

}
