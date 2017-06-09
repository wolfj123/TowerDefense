package model;

import javax.swing.ImageIcon;

public abstract class Creep implements Visited, Tickable, Drawable, Comparable<Creep>{
	private int _x;
	private int _y;
	private Board _board;
	private boolean _isUnderAttack;
	private ImageIcon _characterIcon;
	private int _ticksUntilMove;
	private int numOfStepsTaken; //to see which one to target
	
	protected int _poisonDMG;
	protected int _poisonDuration;
	protected int _poisonTime;
	
	protected int _slowDMG;
	protected int _slowDuration;
	protected int _slowTime;
	
	
	public int getX(){
		return _x;
	}
	
	public int getY(){
		return _y;
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
