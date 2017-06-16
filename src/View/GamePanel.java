package View;

import model.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ariel on 16-Jun-17.
 */
public class GamePanel extends JPanel {

    ImageIcon _pathIcon;
    ImageIcon _grassIcon;

    Coords [][] _pathCoords;

    public GamePanel (Coords [][] levelCoord,ImageIcon path,ImageIcon grass){
        super();
        _pathIcon = path;
        _grassIcon = grass;
        _pathCoords = levelCoord;
        this.setSize(800,800);
    }


    public void paint (Graphics graphics){
        super.paint(graphics);
        DrawBackground(graphics);
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
