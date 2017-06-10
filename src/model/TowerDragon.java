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
		super.tickAction();
		move();
	}
	
	@Override
	protected void tickPassive(){
		_wingsUp = !_wingsUp;
	}

	
	//Random movement (can move diagonally as well)
	private void move(){
		int xMove = 0;
		int yMove = 0;
		
		if(getX()<=0) xMove = 1;
		else if(getX()>=_board.getXsize()-1) xMove = -1;
		else xMove = 
				(int) ( Math.floor(Math.random()*3)-3 );
	
		if(getY()<=0) yMove = 1;
		else if(getY()>=_board.getYsize()-1) yMove = -1;
		else yMove = 
				(int) ( Math.floor(Math.random()*3)-3 );
		
		_x += xMove;
		_y += yMove;
	}
	
	
}
