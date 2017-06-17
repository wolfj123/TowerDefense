package model;

import java.util.Random;

public class TowerDragon extends Tower {

	private boolean _wingsUp;
	
	public TowerDragon(int x, int y, Board board) {
		super(x, y, 2, board, 2);
		_wingsUp = true;
	}
	
	@Override
	public void visit(CreepSkull creep) {
		_isAttacking=true;
		creep.inflictDamage(15);
	}

	@Override
	public void visit(CreepAlien creep) {
		_isAttacking=true;
		creep.inflictDamage(10);
	}

	@Override
	public void visit(CreepKnight creep) {
		_isAttacking=true;
		creep.inflictDamage(8);
	}

	@Override
	public void visit(CreepNinja creep) {
		_isAttacking=true;
		creep.inflictDamage(11);
	}
	
	@Override
	protected void tickAction(){
		super.tickAction();
		move();
	}
	
	@Override
	protected void tickPassive(){
		super.tickPassive();
		_wingsUp = !_wingsUp;
	}

    public void set_wingsUp(boolean _wingsUp) {
        this._wingsUp = _wingsUp;
    }

    public boolean get_wingsUp (){
	    return _wingsUp;
    }
    
    //Random movement (can move diagonally as well)
	private void move(){
		int xMove = 0;
		int yMove = 0;
		
		if(getX()<=0) xMove = 1;
		else if(getX()>=_board.getXsize()-1) xMove = -1;
		else xMove = randomizeMove();
				
		if(getY()<=0) yMove = 1;
		else if(getY()>=_board.getYsize()-1) yMove = -1;
		else yMove = randomizeMove();
		
		_x += xMove;
		_y += yMove;
	}
	
	private int randomizeMove(){
		Random random = new Random();
		return random.nextInt(3)-1;
	}
	
}
