package View;

import com.sun.istack.internal.Nullable;
import model.Board;
import model.Coords;
import model.LevelLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import com.sun.org.apache.bcel.internal.generic.NEW;
import model.Tower;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.management.ObjectInstance;
import javax.management.Query;
import javax.swing.*;
import java.awt.*;
import java.util.Vector;


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
    private ImageIcon [] _towerIcons;
    private ImageIcon [] _creepsIcons;

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
        PaintNewGamePanel();


        this.addMouseListener(this);
        this.setSize(805,877);
        this.setResizable(false);


    }

    private void PaintNewGamePanel (){
        GamePanel gamePainting = new GamePanel(_pathCoords,_pathIcon,_grassIcon,_gameBoard);
        this.add(gamePainting,BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
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

        //createTowers
        ImageIcon towerArrow = new ImageIcon(this.getClass().getResource("/towers/2.png"));
        ImageIcon towerLava = new ImageIcon(this.getClass().getResource("/towers/1.png"));
        ImageIcon towerMagic = new ImageIcon(this.getClass().getResource("/towers/4.png"));
        ImageIcon towerPoison = new ImageIcon(this.getClass().getResource("/towers/3.png"));
        ImageIcon towerGoku = new ImageIcon(this.getClass().getResource("/towers/6.png"));
        ImageIcon towerDrug = new ImageIcon(this.getClass().getResource("/towers/5.png"));
        ImageIcon dino1 =  new ImageIcon(this.getClass().getResource("/towers/dino-1.png"));
        ImageIcon dino2 = new ImageIcon(this.getClass().getResource("/towers/dino-2.png"));

        _towerIcons = new ImageIcon[8];
        //tower arrow
        sizeable = towerArrow.getImage().getScaledInstance(32,32,Image.SCALE_SMOOTH);
        _towerIcons[EnumTowers.Towers.TowerArrow.getIndex()] = new ImageIcon(sizeable);
        //tower lava
        sizeable = towerLava.getImage().getScaledInstance(32,32,Image.SCALE_SMOOTH);
        _towerIcons[EnumTowers.Towers.TowerLava.getIndex()] = new ImageIcon(sizeable);
        //tower magic
        sizeable = towerMagic.getImage().getScaledInstance(32,32,Image.SCALE_SMOOTH);
        _towerIcons[EnumTowers.Towers.TowerMagic.getIndex()] = new ImageIcon(sizeable);
        // tower poison
        sizeable = towerPoison.getImage().getScaledInstance(32,32,Image.SCALE_SMOOTH);
        _towerIcons[EnumTowers.Towers.TowerPoison.getIndex()] = new ImageIcon(sizeable);
        //tower goku
        sizeable = towerGoku.getImage().getScaledInstance(32,32,Image.SCALE_SMOOTH);
        _towerIcons[EnumTowers.Towers.TowerGoku.getIndex()] = new ImageIcon(sizeable);
        //tower drug
        sizeable = towerDrug.getImage().getScaledInstance(32,32,Image.SCALE_SMOOTH);
        _towerIcons[EnumTowers.Towers.TowerDrug.getIndex()] = new ImageIcon(sizeable);
        //tower dino1
        sizeable = dino1.getImage().getScaledInstance(32,32,Image.SCALE_SMOOTH);
        _towerIcons[EnumTowers.Towers.Dino1.getIndex()] = new ImageIcon(sizeable);
        //dino 2
        sizeable = dino2.getImage().getScaledInstance(32,32,Image.SCALE_SMOOTH);
        _towerIcons[EnumTowers.Towers.Dino2.getIndex()] = new ImageIcon(sizeable);

        //create Creeps


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("X = "+e.getX());
        System.out.println("Y = "+e.getY());
        int xSquare = (e.getX()-4)/32;
        int ySquare = (e.getY()-74)/32;
        System.out.println("X = "+xSquare);
        System.out.println("Y = "+ySquare);

        if (!IsGrass(xSquare,ySquare)) {
            Tower tower = CheckForTowerInSquare(xSquare,ySquare);
            if (tower != null) {
                /*tower.set_showRadius(!tower.get_showRadius());*///TODO - remove /**/
                PaintNewGamePanel();
            } else if (!_gameRunnig) {
                //TODO show choose tower
                throw new NotImplementedException();
            }
        }
    }

    /***
     * check if a tower exsits in a crurent square
     * @param x
     * @param y
     * @return the tower if exsits or null if doesn't
     */
    @Nullable
    private Tower CheckForTowerInSquare (int x, int y){
        Vector<Tower> towers = _gameBoard.getTowers();
        for (Tower t : towers){
            if (t.getX()==x & t.getY()==y){
                return t;
            }
        }
        return null;
    }

    /**
     * check if a square is a grass square or a path square
     * @param x
     * @param y
     * @return true - if a grass square
     */
    private boolean IsGrass (int x,int y) {
        if (_pathCoords[x][y].getY()!=0 | _pathCoords[x][y].getY()!=0){
            return false;
        }
        return true;
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
