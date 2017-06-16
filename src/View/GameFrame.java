package View;

import com.sun.istack.internal.Nullable;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
        _gameBoard = new Board(_pathCoords);
        //test show radius***************** //TODO delete
        TowerArrow dd = new TowerArrow(5,5,_gameBoard);
        dd.set_showRadius(true);
        _gameBoard.addTower(dd);

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

    /**
     * paints a new JPanel of the game board
     */
    private void PaintNewGamePanel (){
        GamePanel gamePainting = new GamePanel(_pathCoords,_pathIcon,_grassIcon,_gameBoard,_creepsIcons,_towerIcons);
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
        sizeable = towerArrow.getImage().getScaledInstance(32,48,Image.SCALE_SMOOTH);
        _towerIcons[EnumTowers.TowerArrow.getIndex()] = new ImageIcon(sizeable);
        //tower lava
        sizeable = towerLava.getImage().getScaledInstance(32,48,Image.SCALE_SMOOTH);
        _towerIcons[EnumTowers.TowerLava.getIndex()] = new ImageIcon(sizeable);
        //tower magic
        sizeable = towerMagic.getImage().getScaledInstance(32,48,Image.SCALE_SMOOTH);
        _towerIcons[EnumTowers.TowerMagic.getIndex()] = new ImageIcon(sizeable);
        // tower poison
        sizeable = towerPoison.getImage().getScaledInstance(32,48,Image.SCALE_SMOOTH);
        _towerIcons[EnumTowers.TowerPoison.getIndex()] = new ImageIcon(sizeable);
        //tower goku
        sizeable = towerGoku.getImage().getScaledInstance(32,48,Image.SCALE_SMOOTH);
        _towerIcons[EnumTowers.TowerGoku.getIndex()] = new ImageIcon(sizeable);
        //tower drug
        sizeable = towerDrug.getImage().getScaledInstance(32,48,Image.SCALE_SMOOTH);
        _towerIcons[EnumTowers.TowerDrug.getIndex()] = new ImageIcon(sizeable);
        //tower dino1
        sizeable = dino1.getImage().getScaledInstance(32,48,Image.SCALE_SMOOTH);
        _towerIcons[EnumTowers.TowerDragon1.getIndex()] = new ImageIcon(sizeable);
        //dino 2
        sizeable = dino2.getImage().getScaledInstance(32,48,Image.SCALE_SMOOTH);
        _towerIcons[EnumTowers.TowerDragon2.getIndex()] = new ImageIcon(sizeable);

        //create Creeps
        ImageIcon abir1 = new ImageIcon(this.getClass().getResource("/creeps/abir-1.png"));
        ImageIcon abir2 = new ImageIcon(this.getClass().getResource("/creeps/abir-2.png"));
        ImageIcon  guli1= new ImageIcon(this.getClass().getResource("/creeps/guli-1.png"));
        ImageIcon  guli2= new ImageIcon(this.getClass().getResource("/creeps/guli-2.png"));
        ImageIcon  mike1= new ImageIcon(this.getClass().getResource("/creeps/mike-1.png"));
        ImageIcon  mike2= new ImageIcon(this.getClass().getResource("/creeps/mike-2.png"));
        ImageIcon  naji1= new ImageIcon(this.getClass().getResource("/creeps/naji-1.png"));
        ImageIcon  naji2= new ImageIcon(this.getClass().getResource("/creeps/naji-2.png"));

        _creepsIcons = new ImageIcon[8];

        //creep knight
        sizeable = abir1.getImage().getScaledInstance(32,32,Image.SCALE_SMOOTH);
        _creepsIcons[EnumCreeps.CreepKnight1.get_index()] = new ImageIcon(sizeable);
        sizeable = abir2.getImage().getScaledInstance(32,32,Image.SCALE_SMOOTH);
        _creepsIcons[EnumCreeps.CreepKnight2.get_index()] = new ImageIcon(sizeable);
        //creep skull
        sizeable = guli1.getImage().getScaledInstance(32,32,Image.SCALE_SMOOTH);
        _creepsIcons[EnumCreeps.CreepSkull1.get_index()] = new ImageIcon(sizeable);
        sizeable = guli2.getImage().getScaledInstance(32,32,Image.SCALE_SMOOTH);
        _creepsIcons[EnumCreeps.CreepSkull2.get_index()] = new ImageIcon(sizeable);
        //creep alien
        sizeable = mike1.getImage().getScaledInstance(32,32,Image.SCALE_SMOOTH);
        _creepsIcons[EnumCreeps.CreepAlien1.get_index()] = new ImageIcon(sizeable);
        sizeable = mike2.getImage().getScaledInstance(32,32,Image.SCALE_SMOOTH);
        _creepsIcons[EnumCreeps.CreepAlien2.get_index()] = new ImageIcon(sizeable);
        //creep ninja
        sizeable = naji1.getImage().getScaledInstance(32,32,Image.SCALE_SMOOTH);
        _creepsIcons[EnumCreeps.CreepNinja1.get_index()] = new ImageIcon(sizeable);
        sizeable = naji2.getImage().getScaledInstance(32,32,Image.SCALE_SMOOTH);
        _creepsIcons[EnumCreeps.CreepNinja2.get_index()] = new ImageIcon(sizeable);


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("X = "+e.getX());
        System.out.println("Y = "+e.getY());
        int xSquare = (e.getX()-4)/32;
        int ySquare = (e.getY()-74)/32;
        System.out.println("X = "+xSquare);
        System.out.println("Y = "+ySquare);

        if (IsGrass(xSquare,ySquare)) {
            Tower tower = CheckForTowerInSquare(xSquare,ySquare);
            if (tower != null) {
                tower.set_showRadius(!tower.get_showRadius());
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
