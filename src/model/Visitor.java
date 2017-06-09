package model;

public interface Visitor {
	
	void visit(CreepSkull creep);
	
	void visit(CreepAlien creep);
	
	void visit(CreepKnight creep);
	
	void visit(CreepNinja creep);
}
