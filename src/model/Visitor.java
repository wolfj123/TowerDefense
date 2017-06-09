package model;

public interface Visitor {
	
	void visit(CreepSkull skull);
	
	void visit(CreepAlien alien);
	
	void visit(CreepKnight knight);
	
	void visit(CreepNinja ninja);
	
}
