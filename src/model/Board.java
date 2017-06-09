package model;

import java.util.Vector;

public class Board implements Tickable{
	private Direction[][] _directionBoard;
	private Tower[][] _towers;
	private Creep[][][] _creeps;
	private int _xSize;
	private int _ySize;
	private Spawner _spawner;
	private Vector<Integer> _inGate;
	private Vector<Integer> _outGate;
	
	private static int _maxNumOfCreepsInCell = 2;  //To allow a ninja with another creep
	
	public Board(Direction[][] directionBoard){
		_directionBoard = directionBoard;
		
		_xSize = directionBoard.length;
		_ySize = directionBoard[0].length;
		
		_towers = new Tower[_xSize][_ySize];
		_creeps = new Creep[_xSize][_ySize][_maxNumOfCreepsInCell];
		
		//initialize the arrays with nulls
		for(int i=0; i<_xSize; i++){
			for(int j=0; j<_ySize; j++){
				for(int k = 0; k<_maxNumOfCreepsInCell; k++){
					_towers[i][j] = null;
					_creeps[i][j][k] = null;	
				}
			}
		}
	}
	
	public int getXsize(){
		return _xSize;
	}
	
	public int getYsize(){
		return _ySize;
	}
	
	public Vector<Creep> getCreepsInRange(int x, int y, int range){
		if(x<0 | x>= _xSize | y<0 | y>=_ySize)
			throw new IndexOutOfBoundsException("x/y coordinates outside of board.");
		if(x-range<0 | x+range>= _xSize | y-range<0 | y+range>=_ySize)
			throw new IndexOutOfBoundsException("range coordinates outside of board.");
		
		Vector<Creep> output = new Vector<Creep>();
		
		//Add all creeps in range
		for(int i=x-range; i<=x+range; i++){
			for(int j=y-range; j<=y+range; j++){
				for(int k = 0; k<_maxNumOfCreepsInCell; k++){				
					Creep current = _creeps[i][j][k];
					if(current!=null){
						output.add(current);
					}
				}
			}
		}
		
		output.sort(null);
		return output;
	}
	
	public boolean addTower(int x, int y) {
		// TODO Auto-generated method stub
	}
	
	public boolean addCreep(int x, int y) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
	}
	
	public boolean playerLost() {
		// TODO Auto-generated method stub
	}
	
}
