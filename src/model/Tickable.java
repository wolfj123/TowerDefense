package model;

public abstract class Tickable {
	private int _ticks;
	final int _ticksBeforeAction;
	
	public Tickable(int ticksBeforeAction){
		_ticksBeforeAction = ticksBeforeAction;
	}
	
	public void tickHappened(){
		_ticks+=1;
		if(_ticks==_ticksBeforeAction){
			_ticks = 0;
			tickAction();
		}
	}
	
	protected abstract void tickAction();
}
