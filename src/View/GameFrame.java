package View;

import model.Board;
import model.Coords;
import model.LevelLoader;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import com.sun.org.apache.bcel.internal.generic.NEW;

import javax.management.ObjectInstance;
import javax.management.Query;
import javax.swing.*;
import java.awt.*;


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

    ImageIcon _pathIcon;
    ImageIcon _grassIcon;


    public GameFrame (int levelNum,LevelLoader levelLoader) {
        super ("Tower Defense");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        //Load level
        _pathCoords = levelLoader.getLevel(levelNum);
        SetIconSize();
        //_gameBoard = new Board(_pathCoords); //TODO



        //set toolbar
        CreateToolBar();
        this.add(_toolBar,BorderLayout.NORTH);
        this.setVisible(true);


        //create game panel
        GamePanel gamePainting = new GamePanel(_pathCoords,_pathIcon,_grassIcon);
        this.add(gamePainting,BorderLayout.CENTER);
        //gamePainting.paint(getGraphics());
        this.setSize(830,_toolBar.getHeight()+850);

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

    /***
     * scale icons to correct size
     */
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
