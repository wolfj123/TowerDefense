package logic;

import java.util.Collections;
import java.util.Vector;

public class Spawner extends Tickable{
	private Board _board;
	private Vector<Creep> _list;
	
	public Spawner(int wave, Coords position, Board board) {
		super(2);
		_board = board;	
		_list = new Vector<Creep>();
		
		int x = position.getX();
		int y = position.getY();
		
		int n = 0;
		switch(wave){
		
		case 1:
			n=1;
			break;
		case 2:
			n=2;
			break;
		case 3: 
			n=4;
			break;
		case 4:
			n=8;
			break;
		case 5:
			n=16;
			break;
		default: 
			throw new IllegalArgumentException("Spawner wave number must be 1-5");
		}
		
		for(int i=0; i<n; i++){
			_list.add(new CreepSkull(x, y, board));
			_list.add(new CreepAlien(x, y, board));
			_list.add(new CreepKnight(x, y, board));
			_list.add(new CreepNinja(x, y, board));
		}
		
		Collections.shuffle(_list);
		
	}
	
	public boolean isEmpty() {
		return _list.isEmpty();
	}

	@Override
	protected void tickAction() {
		if(isEmpty()) return;
		
		Creep creep = _list.lastElement();
		_list.remove(_list.size()-1);
		
		_board.addCreep(creep);	
	}

	@Override
	protected void tickPassive() {
		return;
	}
	

}
