package model;

import java.util.ArrayList;
import java.util.Vector;

public class Board implements Tickable{
	private Vector<Integer>[][] _directionBoard;
	private Tower[][] _towers;
	private Creep[][][] _creeps;
	private int _xSize;
	private int _ySize;
	private Spawner _spawner;
	
	
	private static int _maxNumOfCreepsInCell = 2;  //To allow a ninja with another creep
	
	public Board(Vector<Integer>[][] directionBoard){
		_directionBoard = directionBoard;
		
		_xSize = directionBoard.length;
		_ySize = directionBoard[0].length;
		
		_towers = new Tower[_xSize][_ySize];
		_creeps = new Creep[_xSize][_ySize][_maxNumOfCreepsInCell];
	}
	
	public Vector<Creep> getCreepsInRange(int x, int y, int range){
		
	}
	
}
