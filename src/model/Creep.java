package model;

import javax.swing.ImageIcon;

public abstract class Creep extends Tickable implements Visited, Drawable, Comparable<Creep>{
	private int _x;
	private int _y;
	private int _health;
	private Board _board;
	private boolean _isUnderAttack;
	private ImageIcon _characterIcon;
	private int numOfStepsTaken; //to see which one to target
	
	protected int _poisonModifier;
	protected int _poisonDuration;
	protected int _poisonTicks;
	
	protected int _slowModifier;
	protected int _slowDuration;
	protected int _slowTicks;
	
	
	public Creep(int x, int y, int ticksBeforeAction, Board board){
		super(ticksBeforeAction);
		_x = x;
		_y = y;
		_board = board;
		_isUnderAttack = false;
		
		_poisonModifier=1;
		_poisonDuration=0;
		_poisonTicks=0;
		
		_slowModifier = 1;
		_slowDuration=0;
		_slowTicks=0;
	}
	
	public int getX(){
		return _x;
	}
	
	public int getY(){
		return _y;
	}
	
	public void setX(int x){
		_x=x;
	}
	
	public void setY(int y){
		_y=y;
	}
	
	public boolean isAlive(){
		return _health>0;
	}
	
	public void inflictDamage(int dmg){
		_health -=_poisonModifier * dmg;
		_isUnderAttack = true;
	}
	
	public void inflictPoison(int poisonModifier, int poisonDuration){
		if(poisonModifier>=_poisonModifier) _poisonModifier = poisonModifier;
		if(poisonDuration>=_poisonDuration) _poisonDuration = poisonDuration;
	}
	
	private void calculatePoison(){
		if(_poisonDuration<=0) return;
		_poisonTicks+=1;
		if(_poisonTicks>=_poisonDuration){
			_poisonModifier=1;
			_poisonTicks=0;
			_poisonDuration=0;
		}
	}
	
	public void inflictSlow(int slowDuration, int slowModifier){
		if(slowModifier>=_slowModifier) _slowModifier = slowModifier;
		if(slowDuration>=_slowDuration) _slowDuration = slowDuration;
	}
	
	private void calculateSlow(){
		if(_slowDuration<=0) return;
		_slowTicks+=1;
		if(_slowTicks>=_slowDuration){
			_slowModifier=1;
			_slowTicks=0;
			_slowDuration=0;
		}
	}
	
	@Override
	public int getTicksBeforeAction(){
		return _ticksBeforeAction*_slowModifier;
	}
	
	public void impact(Tower t){ 
		t.visit(this); 
	}
	
	@Override
	public void tickAction(){
		move();
	}
	
	private void move(){
		Direction direction = _board.getDirection(getX(), getY()); 
		setX(getX() + direction.getX());
		setY(getY() + direction.getY());
	}
	
	@Override
	public void tickPassive(){
		calculatePoison();
		calculateSlow();
		_isUnderAttack = false;
	}
	
	@Override
	public int compareTo(Creep other){
		if(other==null)
			throw new IllegalArgumentException("Cannot compare creep to null");
		
		return (-1)*(getNumOfStepsTaken()-other.getNumOfStepsTaken());
	}
	
	public int getNumOfStepsTaken(){
		return numOfStepsTaken;
	}
}
