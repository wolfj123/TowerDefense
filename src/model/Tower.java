package model;

import javax.swing.ImageIcon;

public abstract class Tower extends Tickable implements Visitor{
	private int _x;
	private int _y;
	private Board _board;
	private boolean _isAttacking;
	
	public Tower(int x, int y, Board board, int ticksBeforeAction) {
		super(ticksBeforeAction);
		_x = x;
		_y = y;
		_board = board;
		_isAttacking = false;
	}
	
	public int getX(){
		return _x;
	}
	
	public int getY(){
		return _y;
	}
	
	public boolean isAttacking(){
		return _isAttacking;
	}
	
	protected void tickAction(){
		
	}
	
	protected void tickPassive(){
		
	}
	
	
}
