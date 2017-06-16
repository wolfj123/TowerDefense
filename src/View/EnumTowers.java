package View;

/**
 * Created by ariel on 16-Jun-17.
 */
public class EnumTowers {
    public enum Towers{
        TowerArrow (0),
        TowerLava(1),
        TowerMagic(2),
        TowerPoison(3),
        TowerGoku(4),
        TowerDrug(5),
        Dino1(6),
        Dino2(7);

        private final int _index;
        Towers(int index){
            _index=index;
        }

        public int getIndex (){
            return _index;
        }
    }
}
