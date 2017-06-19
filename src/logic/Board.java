package logic;

import java.util.Iterator;
import java.util.Vector;

public class Board extends Tickable{

	private int _playerHealth;
	private int _wave;
	
	private int _xSize;
	private int _ySize;
	
	private Coords[][] _directionBoard;
	private Coords _inGate;
	private Coords _outGate;
	
	private Spawner _spawner;
	private Vector<Tower> _towers;
	private Vector<Creep> _creeps;
	private int _deadCreeps;
	private int _victoriousCreeps;

	public Board(Coords[][] directionBoard) {
		super(2);
		
		_directionBoard = directionBoard;
		_xSize = directionBoard.length;
		_ySize = directionBoard[0].length;
		
		_inGate = findGate(directionBoard, true);
		_outGate = findGate(directionBoard, false);
		
		if(_inGate==null || _outGate==null)
			throw new IllegalArgumentException("Could not find inGate/outGate in direction matrix");
		
		_towers = new Vector<Tower>();
		_creeps = new Vector<Creep>();
		
		_playerHealth = 20;
		_wave = 1;
		_spawner = new Spawner(_wave, _inGate, this);
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
	
	public Coords[][] getDirectionBoard(){
		return _directionBoard;
	}
	
	public int getNumOfDeadCreeps(){
		return _deadCreeps;
	}
	
	public int getNumOfVictoriousCreeps(){
		return _victoriousCreeps;
	}
	
	public int getWave(){
		return _wave;
	}
	
	public void setWave(int wave){
		if(wave<1 | wave>5)
			throw new IllegalArgumentException("wave number must be 1-5");
		
		_wave = wave;
		_spawner = new Spawner(_wave, _inGate, this);
	}
	
	public Vector<Creep> getCreepsInRange(int x, int y, int range){
		if(x<0 | x>= _xSize | y<0 | y>=_ySize)
			throw new IndexOutOfBoundsException("x/y coordinates outside of board.");
		
		//TODO: maybe delete?
		//if(x-range<0 | x+range>= _xSize | y-range<0 | y+range>=_ySize)
			//throw new IndexOutOfBoundsException("range coordinates outside of board.");
		
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
	
	public Coords getDirection(int x, int y){
		if(x<0 | x>= _xSize | y<0 | y>=_ySize)
			throw new IndexOutOfBoundsException("x/y coordinates outside of board.");
		
		return _directionBoard[y][x];
	}
	
	@Override
	protected void tickAction() {
		return;
	}

	@Override
	protected void tickPassive() {
		//Verify stop conditions
		if(playerWon() | playerLost()) return;
		
		//Spawn creeps
		_spawner.tickHappened();
		
		Vector<Creep> toRemove = new Vector<Creep>();
		
		//Remove dead/victorious creeps, advance alive creeps
		Iterator<Creep> iter = _creeps.iterator();
		while(iter.hasNext()){
			Creep creep = iter.next();
			if(!creep.isAlive()){
				toRemove.add(creep);
				_deadCreeps+=1;
			}
			else if(creep.isVictorious()){
				toRemove.add(creep);
				_victoriousCreeps+=1;
				_playerHealth-=1;
			}
			else{
				creep.tickHappened();
			}
		}

		for(Creep c : toRemove){
			_creeps.remove(c);
		}
		
		//Fire towers
		for(Tower t : _towers){
			t.tickHappened();
		}
		

	}
	
	public boolean playerWon() {
		return (_spawner.isEmpty() & _creeps.isEmpty());
	}
	
	public boolean playerLost() {
		return _playerHealth<=0;
	}

	private Coords findGate(Coords[][] directionBoard, boolean inGate){
		int x = (inGate) ? 0 : directionBoard.length-1;
		boolean foundGate = false;
		Coords gate = null;
		for(int y=0; y<directionBoard.length & !foundGate; y++){
			Coords current = directionBoard[y][x];
			if(current.getX()==1){
				foundGate = true;
				gate = new Coords(x,y);
			}
		}
		return gate;
	}
}