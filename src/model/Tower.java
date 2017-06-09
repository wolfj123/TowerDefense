package model;

import javax.swing.ImageIcon;

public abstract class Tower extends Tickable implements Visitor, Drawable{
	private int _x;
	private int _y;
	private Board _board;
	private boolean _isAttacking;
	private ImageIcon _characterIcon;
	
	public int getX(){
		return _x;
	}
	
	public int getY(){
		return _y;
	}
}
