package model;

import java.util.Vector;

public class Board extends Tickable{

	private int _playerHealth;
	private int _wave;
	
	private int _xSize;
	private int _ySize;
	
	private Direction[][] _directionBoard;
	private Vector<Integer> _inGate;
	private Vector<Integer> _outGate;
	
	private Spawner _spawner;
	private Vector<Tower> _towers;
	private Vector<Creep> _creeps;
	private int _deadCreeps;
	private int _victoriousCreeps;


	
	public Board(Direction[][] directionBoard) {
		super(2);
		
		_directionBoard = directionBoard;
		
		_xSize = directionBoard.length;
		_ySize = directionBoard[0].length;
		
		_towers = new Vector<Tower>();
		_creeps = new Vector<Creep>();
		
		_playerHealth = 100;
		_wave = 1;
		_spawner = new Spawner(_wave, x, y, this);
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
		boolean spotEmpty = true;
		for(Tower t : _towers){
			if(tower.getX()==t.getX() && tower.getY()==t.getY())
				spotEmpty=false;
		}
		if(spotEmpty)
			return _towers.add(tower);
		else return false;
	}
	
	public boolean addCreep(Creep creep) {
		if(_creeps.contains(creep)) return false;
		
		return _creeps.add(creep);
	}
	
	public Direction getDirection(int x, int y){
		if(x<0 | x>= _xSize | y<0 | y>=_ySize)
			throw new IndexOutOfBoundsException("x/y coordinates outside of board.");
		
		return _directionBoard[x][y];
	}
	
	@Override
	protected void tickAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void tickPassive() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean playerWon() {
		return (_spawner.isEmpty() & _creeps.isEmpty());
	}
	
	public boolean playerLost() {
		return _playerHealth>=0;
	}
	
	public int getNumOfDeadCreeps(){
		return _deadCreeps;
	}
	
	public int getNumOfVictoriousCreeps(){
		return _victoriousCreeps;
	}



}
