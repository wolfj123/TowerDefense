package model;

import java.util.Queue;

public class Spawner implements Tickable{
	private Queue<Creep> _queue;
	
	
	public Spawner(int wave){
		switch(wave){
		// TODO Auto-generated method stub
		case 1:
			
		
		
		
		
		
		
		
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
