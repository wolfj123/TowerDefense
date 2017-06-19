package logic;

import java.util.Vector;

public abstract class Tower extends Tickable implements Visitor{
	protected int _x;
	protected int _y;
	final int _range;
	protected Board _board;
	protected boolean _isAttacking;
	private boolean _showRadius;
	
	public Tower(int x, int y, int range, Board board, int ticksBeforeAction) {
		super(ticksBeforeAction);
		_x = x;
		_y = y;
		_range = range;
		_board = board;
		_isAttacking = false;
		_showRadius = false;
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

	public void set_showRadius(boolean _showRadius) {
		this._showRadius = _showRadius;
	}

	public boolean get_showRadius (){
		return _showRadius;
	}
}
