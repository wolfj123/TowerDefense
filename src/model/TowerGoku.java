package model;

public class TowerGoku extends Tower {

	private int _h;
	private int _numOfHits;
	
	
	public TowerGoku(int x, int y, Board board) {
		super(x, y, 2, board, 2);
		_numOfHits=0;
		_h=1;
	}

	@Override
	public void visit(CreepSkull creep) {
		creep.inflictDamage(10*_h);
		calculateH();
	}

	@Override
	public void visit(CreepAlien creep) {
		creep.inflictDamage(5*_h);
		calculateH();
	}

	@Override
	public void visit(CreepKnight creep) {
		creep.inflictDamage(7*_h);
		calculateH();
	}

	@Override
	public void visit(CreepNinja creep) {
		creep.inflictDamage(5*_h);
		calculateH();
	}

	private void calculateH(){
		_numOfHits+=1;
		if(_numOfHits==0){
			_numOfHits=0;
			_h=_h*2;
		}
	}
}
