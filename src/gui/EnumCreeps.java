package gui;

/**
 * Created by ariel on 12-Jun-17.
 */
public enum EnumCreeps {
    CreepKnight1 (0),
    CreepKnight2(1),
    CreepSkull1(2),
    CreepSkull2(3),
    CreepAlien1(4),
    CreepAlien2(5),
    CreepNinja1(6),
    CreepNinja2(7);

    private int _index;

    EnumCreeps (int index){
        _index=index;
    }

    public int get_index (){
        return _index;
    }

}
