package View;

import model.Board;
import model.Coords;
import model.LevelLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import com.sun.org.apache.bcel.internal.generic.NEW;

import javax.management.ObjectInstance;
import javax.management.Query;
import javax.swing.*;
import java.awt.*;


/**
 * Created by ariel on 11-Jun-17.
 */
public class GameFrame extends JFrame implements MouseListener {

    private JLabel _lifeLabel;
    private JLabel _waveLabel;
    private JToolBar _toolBar;
    private  boolean _gameSpeed; // true - normal speed, false - double speed

    private Board _gameBoard;
    private Coords [][] _pathCoords;

    private ImageIcon _pathIcon;
    private ImageIcon _grassIcon;

    boolean _gameRunnig;


    public GameFrame (int levelNum,LevelLoader levelLoader) {
        super ("Tower Defense");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        //Load level
        _pathCoords = levelLoader.getLevel(levelNum);
        SetIconSize();
        _gameRunnig = false;
        //_gameBoard = new Board(_pathCoords); //TODO



        //set toolbar
        CreateToolBar();
        this.add(_toolBar,BorderLayout.NORTH);
        this.setVisible(true);


        //create game panel
        GamePanel gamePainting = new GamePanel(_pathCoords,_pathIcon,_grassIcon,_gameBoard);
        this.add(gamePainting,BorderLayout.CENTER);


        this.addMouseListener(this);
        this.setSize(805,877);
        this.setResizable(false);


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

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("X = "+e.getX());
        System.out.println("Y = "+e.getY());
        int xBLock = (e.getX()-4)/32;
        int yBlock = (e.getY()-74)/32;
        System.out.println("X = "+xBLock);
        System.out.println("Y = "+yBlock);

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
