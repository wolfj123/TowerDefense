package model;

import java.util.Vector;

public class Cell implements Drawable{
	private Vector<Integer> _direction;
	private Tower _tower;
	private Vector<Creep> _creeps;
	

	//Create path
	public Cell(int[] direction){
		_direction = direction;
		_tower = null;
		_creeps = null;
	}
	
	//Create grass
	public Cell(){
		_direction = null;
		_tower = null;
		_creeps = null;
	}
	
	
	public boolean isPath(){
		return !(_direction==null);
	}
	
	
	public boolean addCreep(){
		
	}
	
}
