package logic;

public abstract class Creep extends Tickable implements Visited, Comparable<Creep>{
	private int _x;
	private int _y;
	private int _health;
	private Board _board;
	private boolean _isUnderAttack;
	private int _numOfStepsTaken; //to see which one to target
	
	protected double _poisonModifier;
	protected int _poisonDuration;
	
	protected int _slowModifier;
	protected int _slowDuration;

	protected boolean _picPos;
	
	
	public Creep(int x, int y, int ticksBeforeAction, Board board){
		super(ticksBeforeAction);
		_x = x;
		_y = y;
		_health = 100;
		_board = board;
		_isUnderAttack = false;
		
		_poisonModifier=1;
		_poisonDuration=0;
		
		_slowModifier = 1;
		_slowDuration=0;
		_picPos = true;
	}
	
	public int getX(){
		return _x;
	}
	
	public int getY(){
		return _y;
	}
	
	public void setX(int x){
		_x=x;
	}
	
	public void setY(int y){
		_y=y;
	}
	
	public boolean isAlive(){
		return _health>0;
	}
	
	public boolean isVictorious(){
		return (getX()>=_board.getXsize());
	}
	
	public boolean isUnderAttack(){
		return _isUnderAttack;
	}
	
	public void inflictDamage(int dmg){
		_isUnderAttack = true;
		_health -=_poisonModifier * dmg;
	}
	
	public void inflictPoison(double poisonModifier, int poisonDuration){
		if(poisonModifier>=_poisonModifier) _poisonModifier = poisonModifier;
		if(poisonDuration>=_poisonDuration) _poisonDuration = poisonDuration;
	}
	
	private void calculatePoison(){
		_poisonDuration-=1;
		if(_poisonDuration==0){
			_poisonModifier=1;
		}
	}
	
	public void inflictSlow(int slowDuration, int slowModifier){
		if(slowModifier>=_slowModifier) _slowModifier = slowModifier;
		if(slowDuration>=_slowDuration) _slowDuration = slowDuration;
	}
	
	private void calculateSlow(){
		_slowDuration-=1;
		if(_slowDuration==0){
			_slowModifier=1;
		}
	}
	
	@Override
	public int getTicksBeforeAction(){
		return _ticksBeforeAction*_slowModifier;
	}
	
	
	@Override
	public void tickAction(){
		move();
	}
	
	private void move(){
		Coords direction = _board.getDirection(getX(), getY()); 
		setX(getX() + direction.getX());
		setY(getY() + direction.getY());
		_numOfStepsTaken+=1;
	}
	
	@Override
	public void tickPassive(){
		calculatePoison();
		calculateSlow();
		_isUnderAttack = false;
	}
	
	@Override
	public int compareTo(Creep other){
		if(other==null)
			throw new IllegalArgumentException("Cannot compare creep to null");
		
		return (-1)*(getNumOfStepsTaken()-other.getNumOfStepsTaken());
	}
	
	public int getNumOfStepsTaken(){
		return _numOfStepsTaken;
	}

	public void set_picPos(boolean _picPos) {
		this._picPos = _picPos;
	}

	public boolean get_picPos(){
		return _picPos;
	}
}
