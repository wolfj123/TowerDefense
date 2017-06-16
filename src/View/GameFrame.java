package View;

import model.Board;
import model.Coords;
import model.LevelLoader;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by ariel on 11-Jun-17.
 */
public class GameFrame extends JFrame {

    JLabel _lifeLabel;
    JLabel _waveLabel;
    JToolBar _toolBar;
    boolean _gameSpeed; // true - normal speed, false - double speed

    Board _gameBoard;
    Coords [][] _pathCoords;

    ImageIcon _pathIcon;// =
    ImageIcon _grassIcon;// =


    public GameFrame (int levelNum,LevelLoader levelLoader) {
        super ("Tower Defense");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        //Load level

        _pathCoords = levelLoader.getLevel(levelNum);
        for (int i=0;i<_pathCoords.length;i++) {
            for (int j = 0; j < _pathCoords[0].length; j++)
                System.out.print(_pathCoords[i][j]);
            System.out.println();
        }
        _gameBoard = new Board(_pathCoords);
        SetIconSize();

        //set toolbar
        CreateToolBar();
        this.add(_toolBar,BorderLayout.NORTH);
        //create game panel
        this.add(CreateBackground(),BorderLayout.SOUTH);




        this.pack();
        this.setVisible(true);
    }

    private void CreateToolBar(){
        //create labels
        _lifeLabel = new JLabel("life left: 20");
        _waveLabel =  new JLabel("wave: 1");
        //create buttons
        JButton speedButton = new JButton("Normal Speed");
        _gameSpeed = true;
        speedButton.addMouseListener(new SpeedListner());
        JButton startWave = new JButton("Start Wave");
        startWave.addMouseListener(new NewWaveListner());
        // set tool bar
        _toolBar = new JToolBar();
        _toolBar.setLayout(new FlowLayout());
        _toolBar.add(_lifeLabel);
        _toolBar.add(_waveLabel);
        _toolBar.add(speedButton);
        _toolBar.add(startWave);
    }

    private JPanel CreateBackground (){
        JPanel gameBoard = new JPanel();
        gameBoard.setSize(800,800);
        for (int i=0;i<_pathCoords.length;i++) {
            for (int j = 0; j < _pathCoords[i].length; j++) {
                if (_pathCoords[i][j].getX() != 0 | _pathCoords[i][j].getY() != 0) {
                    _pathIcon.paintIcon(gameBoard, getGraphics(), i * 32, j * 32);
                } else {
                    _grassIcon.paintIcon(gameBoard, getGraphics(), i * 32, j * 32);
                }
            }
        }

        return gameBoard;
    }

    private void SetIconSize (){
        ImageIcon tempPathIcon = new ImageIcon(this.getClass().getResource("/background/Path_Icon.jpg"));
        ImageIcon tempGrassIcon = new ImageIcon(this.getClass().getResource("/background/Grass_Icon.jpg"));

        Image sizeable;
        //Grass Icon
        sizeable = tempGrassIcon.getImage().getScaledInstance(32,32,Image.SCALE_SMOOTH);
        _grassIcon = new ImageIcon(sizeable);
        //path icon
        sizeable = tempPathIcon.getImage().getScaledInstance(32,32,Image.SCALE_SMOOTH);
        _pathIcon = new ImageIcon(sizeable);

    }
}
