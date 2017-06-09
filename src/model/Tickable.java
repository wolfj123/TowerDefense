package model;

public abstract class Tickable {
	private int _ticks;
	final int _ticksBeforeAction;
	
	public Tickable(int ticksBeforeAction){
		_ticksBeforeAction = ticksBeforeAction;
	}
	
	public void tickHappened(){
		_ticks+=1;
		tickPassive();
		if(_ticks==getTicksBeforeAction()){
			_ticks = 0;
			tickAction();
		}
	}
	
	protected int getTicksBeforeAction(){
		return _ticksBeforeAction;
	}
	
	protected abstract void tickAction();
	
	protected abstract void tickPassive();
	
}
