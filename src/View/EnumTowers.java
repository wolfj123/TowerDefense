package View;

/**
 * Created by ariel on 16-Jun-17.
 */
public enum  EnumTowers {
        TowerArrow (0),
        TowerLava(1),
        TowerMagic(2),
        TowerPoison(3),
        TowerGoku(4),
        TowerDrug(5),
        TowerDragon1(6),
        TowerDragon2(7);

        private final int _index;
        EnumTowers(int index){
            _index=index;
        }

        public int getIndex (){
            return _index;
        }
}
