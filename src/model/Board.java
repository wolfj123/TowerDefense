package model;

import java.util.Vector;

public class Board implements Tickable{
	
	private int _xSize;
	private int _ySize;
	
	private Direction[][] _directionBoard;
	private Vector<Integer> _inGate;
	private Vector<Integer> _outGate;
	
	private Spawner _spawner;
	private Vector<Tower> _towers;
	private Vector<Creep> _creeps;
	private Vector<Creep> _deadCreeps;
	private Vector<Creep> _victoriousCreeps;

	private int _playerHealth;
	private int wave;
	
	public Board(Direction[][] directionBoard){
		_directionBoard = directionBoard;
		
		_xSize = directionBoard.length;
		_ySize = directionBoard[0].length;
		
		_towers = new Vector<Tower>();
		_creeps = new Vector<Creep>();
	}
	
	public int getXsize(){
		return _xSize;
	}
	
	public int getYsize(){
		return _ySize;
	}
	
	public int getPlayerHealth(){
		return _playerHealth;
	}
	
	public Vector<Tower> getTowers(){
		return _towers;
	}
	
	public Vector<Creep> getCreeps(){
		_creeps.sort(null);
		return _creeps;
	}
	
	public Vector<Creep> getCreepsInRange(int x, int y, int range){
		if(x<0 | x>= _xSize | y<0 | y>=_ySize)
			throw new IndexOutOfBoundsException("x/y coordinates outside of board.");
		if(x-range<0 | x+range>= _xSize | y-range<0 | y+range>=_ySize)
			throw new IndexOutOfBoundsException("range coordinates outside of board.");
		
		Vector<Creep> output = new Vector<Creep>();
		
		for(Creep c : _creeps){
			if(c.getX()>=x-range & c.getX()<=x+range & c.getY()>=y-range & c.getY()<=y+range)
				output.add(c);
		}
		output.sort(null); //sort by who is the most advanced
		return output;
	}
	
	public boolean addTower(Tower tower) {
		// TODO Auto-generated method stub
	}
	
	public boolean addCreep(Creep creep) {
		if(_creeps.contains(creep)) return false;
		
		
	}
	
	public Direction getDirection(int x, int y){
		if(x<0 | x>= _xSize | y<0 | y>=_ySize)
			throw new IndexOutOfBoundsException("x/y coordinates outside of board.");
		
		return _directionBoard[x][y];
	}

	
	@Override
	public void tickHappend() {
		// TODO Auto-generated method stub
		//Make spawner insert after moving all creeps
		//Sort all creeps and move them by their leader
	}
	
	public boolean playerWon() {
		return (_spawner.isEmpty() & _creeps.isEmpty());
	}
	
	public boolean playerLost() {
		return _playerHealth>=0;
	}
	
}
