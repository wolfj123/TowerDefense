package logic;

import java.util.Vector;

public abstract class TowerAOE extends Tower {

	public TowerAOE(int x, int y, int range, Board board, int ticksBeforeAction) {
		super(x, y, range, board, ticksBeforeAction);
	}

	@Override
	protected void tickAction(){
		Vector<Creep> creepsInRange = 
				_board.getCreepsInRange(getX(), getY(), getRange());
		
		if(creepsInRange.isEmpty()) return;			
		for(Creep creep : creepsInRange){
			creep.impact(this);
		}
	}

}
