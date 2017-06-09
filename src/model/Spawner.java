package model;

import java.util.Queue;
import java.util.Vector;

public class Spawner implements Tickable{
	private Queue<Creep> _queue;
	private Vector<Creep> _list;
	
	public Spawner(int wave){
		// TODO Auto-generated method stub
		
		_list = new Vector<Creep>();
		
		switch(wave){
		
		case 1:
			_list.add(new CreepAlien());
		
		
		
		
		
		
		
		}
		
		
		
		
		
		
		
	}
	
	@Override
	public void tickHappened() {
		if(isEmpty()) return;
		// TODO Auto-generated method stub
		
	}
	
	public boolean isEmpty() {
		return _queue.isEmpty();
	}
	

}
