package model;

import java.util.Vector;

public abstract class Tower extends Tickable implements Visitor{
	private int _x;
	private int _y;
	final int _range;
	private Board _board;
	private boolean _isAttacking;
	
	public Tower(int x, int y, int range, Board board, int ticksBeforeAction) {
		super(ticksBeforeAction);
		_x = x;
		_y = y;
		_range = range;
		_board = board;
		_isAttacking = false;
	}
	
	public int getX(){
		return _x;
	}
	
	public int getY(){
		return _y;
	}
	
	public int getRange(){
		return _range;
	}
	
	public boolean isAttacking(){
		return _isAttacking;
	}
	
	@Override
	protected void tickAction(){
		Vector<Creep> creepsInRange = 
				_board.getCreepsInRange(getX(), getY(), getRange());
		
		if(creepsInRange.isEmpty()) return;	
		creepsInRange.get(0).impact(this);
	}
	
	@Override
	protected void tickPassive(){
		_isAttacking=false;
	}
	
	
}
