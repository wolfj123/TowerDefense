package View;

import model.*;

import javax.swing.*;
import java.awt.*;

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

    public GamePanel (Coords [][] levelCoord,ImageIcon path,ImageIcon grass,Board gameBoard){
        super();
        _pathIcon = path;
        _grassIcon = grass;
        _pathCoords = levelCoord;
        this.setSize(800,800);
        _showChooseTower=false;
        _gameBoard=gameBoard;
    }

    public void set_showChooseTower(boolean _showChooseTower) {
        this._showChooseTower = _showChooseTower;
    }

    public void paint (Graphics graphics){
        super.paint(graphics);
        DrawBackground(graphics);
        DrawRadius(graphics);
        DrawHitAndFire(graphics);
        getDrawTowes(graphics);
        DrawCreeps(graphics);
    }

    private void DrawHitAndFire(Graphics graphics) {
        //TODO
    }

    private void DrawCreeps(Graphics graphics) {
        //TODO
    }

    private void getDrawTowes(Graphics graphics) {
        //TODO
    }


    private void DrawRadius(Graphics graphics) {
        //TODO
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
