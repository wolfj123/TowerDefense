package View;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * Created by ariel on 16-Jun-17.
 */
public class GamePanel extends JPanel {

    private ImageIcon _pathIcon;
    private ImageIcon _grassIcon;
    private ImageIcon [] _towersIcons;
    private ImageIcon [] _creepsIcons;

    private Coords [][] _pathCoords;

    private boolean _showChooseTower;
    private Board _gameBoard;


    public GamePanel (Coords [][] levelCoord,ImageIcon path,ImageIcon grass,Board gameBoard,ImageIcon [] creeps, ImageIcon [] towers){
        super();
        _pathIcon = path;
        _grassIcon = grass;
        _pathCoords = levelCoord;
        this.setSize(800,800);
        _showChooseTower=false;
        _gameBoard=gameBoard;
        _creepsIcons = creeps;
        _towersIcons = towers;
    }

    public void set_showChooseTower(boolean _showChooseTower) {
        this._showChooseTower = _showChooseTower;
    }

    public void paint (Graphics graphics){
        super.paint(graphics);
        DrawBackground(graphics);
        DrawRadius(graphics);
        DrawHitAndFire(graphics);
        DrawTowes(graphics);
        DrawCreeps(graphics);
    }

    private void DrawHitAndFire(Graphics graphics) {
        //draw firing towers
        Vector<Tower> towers =  _gameBoard.getTowers();
        for (Tower t : towers) {
            if (t.isAttacking()){
                graphics.setColor(Color.blue);
                graphics.fillRect((t.getX()*32+2),(t.getY()*32+2),28,28);
            }
        }
        //draw creeps getting hit
        Vector<Creep> creeps = _gameBoard.getCreeps();
        for (Creep c : creeps){
            if (c.isUnderAttack()){
                graphics.setColor(Color.red);
                graphics.fillRect((c.getX()*32+2),(c.getY()*32+2),28,28);
            }
        }
        //reset color
        graphics.setColor(Color.black);
    }

    private void DrawCreeps(Graphics graphics) {
        Vector<Creep> creeps = _gameBoard.getCreeps();
        for (Creep c : creeps){
            //draw creeps first icon
            if (c.get_picPos()){
                String index = c.getClass().getSimpleName()+"1";
                _creepsIcons[EnumCreeps.valueOf(index).get_index()].paintIcon(this,graphics,(c.getX()*32),(c.getY()*32));
                c.set_picPos(false);
            }
            //draw creeps second icon
            else {
                String index = c.getClass().getSimpleName()+"2";
                _creepsIcons[EnumCreeps.valueOf(index).get_index()].paintIcon(this,graphics,(c.getX()*32),(c.getY()*32));
                c.set_picPos(true);
            }
        }
    }

    private void DrawTowes(Graphics graphics) {
        Vector<Tower> towers =  _gameBoard.getTowers();
        for (Tower t : towers){
            //check if a tower is a dragon
            if (!(t instanceof TowerDragon)) {
                //paint towers that arent dragons
                int index = EnumTowers.valueOf(t.getClass().getSimpleName()).getIndex();
                _towersIcons[index].paintIcon(this, graphics, (t.getX() * 32), ((t.getY() * 32) - 16));
            }
            else {
                //paint dragon 1 or
                if (((TowerDragon) t).get_wingsUp()) {
                    String index = t.getClass().getSimpleName()+"1";
                    _towersIcons[EnumTowers.valueOf(index).getIndex()].paintIcon(this, graphics, (t.getX() * 32), ((t.getY() * 32) - 16));
                    ((TowerDragon)t).set_wingsUp(false);
                }
                else{
                    String index = t.getClass().getSimpleName()+"2";
                    _towersIcons[EnumTowers.valueOf(index).getIndex()].paintIcon(this, graphics, (t.getX() * 32), ((t.getY() * 32) - 16));
                    ((TowerDragon)t).set_wingsUp(true);
                }
            }
        }
    }


    private void DrawRadius(Graphics graphics) {
        graphics.setColor(Color.white);
        //test        graphics.fillRect(2,2,28,28);
        Vector<Tower> towers = _gameBoard.getTowers();
        for (Tower t : towers) {
            if (t.get_showRadius()) {
                for (int i = 0; i <= t.getRange()*2; i++) {
                    for (int j = 0; j <= t.getRange()*2; j++) {
                        try {
                            graphics.fillRect(((t.getX()*32) - (t.getRange() * 32) + j*32 + 2), ((t.getY()*32) - (t.getRange() * 32) + i*32 + 2), 28, 28);
                        }
                        catch (Exception e){

                        }
                    }
                }
            }
        }
        graphics.setColor(Color.black);
    }



    private void DrawBackground(Graphics graphics){
        for (int i=0;i<_pathCoords.length;i++){
            for (int j=0;j<_pathCoords[i].length;j++){
                if (_pathCoords[i][j].getY()!=0 | _pathCoords[i][j].getX()!=0){
                    _pathIcon.paintIcon(this,graphics,j*32,i*32);
                }
                else {
                    _grassIcon.paintIcon(this,graphics,j*32,i*32);
                }
            }
        }
    }
}
