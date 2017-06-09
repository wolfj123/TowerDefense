package model;

import javax.swing.ImageIcon;

public abstract class Creep implements Visited, Tickable, Drawable{
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
	
}
