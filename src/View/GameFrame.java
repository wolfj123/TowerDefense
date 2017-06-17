package View;

//import com.sun.istack.internal.Nullable;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.util.Vector;


/**
 * Created by ariel on 11-Jun-17.
 */
public class GameFrame extends JFrame implements MouseListener, ActionListener {

    private JLabel _lifeLabel;
    private JLabel _waveLabel;
    private JToolBar _toolBar;
    private  boolean _gameSpeed; // true - normal speed, false - double speed

    private JButton _startWave;
    private JButton _speedButton;

    private Board _gameBoard;
    private Coords [][] _pathCoords;
    private int [] _towerToAdd;

    private ImageIcon _pathIcon;
    private ImageIcon _grassIcon;
    private ImageIcon [] _towerIcons;
    private ImageIcon [] _creepsIcons;

    private boolean _gameRunnig;
    private boolean _canContinuePlaying;

    private Timer _timer;
    private int _normalSpeed;
    private int _fastSpeed;



    public GameFrame (int levelNum,LevelLoader levelLoader) {
        super ("Tower Defense");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        //Load level
        _pathCoords = levelLoader.getLevel(levelNum);
        SetIconSize();
        _gameRunnig = false;
        _canContinuePlaying=true;
        _gameBoard = new Board(_pathCoords);

        _towerToAdd = new int [7];
        for (int i = 0; i < _towerToAdd.length; i++) {
            _towerToAdd[i]=3;
        }

        //set timer
        _normalSpeed=500;
        _fastSpeed=250;
        _timer = new Timer(_normalSpeed,this);
        _timer.setRepeats(true);


        //test show radius***************** //TODO delete
        TowerDragon dd = new TowerDragon(10,10,_gameBoard);
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
        //create _labels
        _lifeLabel = new JLabel("life left: 20");
        _waveLabel =  new JLabel("wave: 1");
        //create buttons
        _speedButton = new JButton("Normal Speed");
        _gameSpeed = true;
        _speedButton.addMouseListener(this);
        _startWave = new JButton("Start Wave");
        _startWave.addMouseListener(this);
        // set tool bar
        _toolBar = new JToolBar();
        _toolBar.setLayout(new FlowLayout());
        _toolBar.add(_lifeLabel);
        _toolBar.add(_waveLabel);
        _toolBar.add(_speedButton);
        _toolBar.add(_startWave);
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

    public void setLifeLeft(int life){
        _lifeLabel.setText("life left: "+life);
    }

    public void setWaveNumber (int waveNum){
        _waveLabel.setText("wave: "+waveNum);
    }

    public void CheckForGameEnding () throws IOException {
        //in case of a win
        if (_gameBoard.playerWon()){
            _timer.stop();
            _gameRunnig=false;
            //prepare check if game ended
            if (_gameBoard.getWave()<5) {
                _gameBoard.setWave(_gameBoard.getWave() + 1);
            }
            else {
                _canContinuePlaying=false;
                JOptionPane.showMessageDialog(null,
                        "Congratulations you have won!!!!!!!!!!!!!");
            }
        }
        // in case of a lose
        if (_gameBoard.playerLost()){
            _canContinuePlaying=false;
            _timer.stop();
            _gameRunnig=false;
            JOptionPane.showMessageDialog(null,
                    "You have lost - try again");
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (_canContinuePlaying) {
            //start game button
            if (e.getSource() == _startWave) {
                //start game if needed
                if (!_gameRunnig) {
                    _gameRunnig = true;
                    _gameBoard.setWave(_gameBoard.getWave());
                    _timer.start();
                }
            }
            //change speed button
            else if (e.getSource() == _speedButton) {
                //change speed if game is runnig
                if (_gameRunnig) {
                    //speed up game
                    if (_gameSpeed) {
                        _gameSpeed = false;
                        _timer.stop();
                        _timer.setDelay(_fastSpeed);
                        _timer.start();
                        _speedButton.setText("Double Speed");
                    }
                    //slow down game
                    else {
                        _gameSpeed = true;
                        _timer.stop();
                        _timer.setDelay(_normalSpeed);
                        _timer.start();
                        _speedButton.setText("Normal Speed");
                    }
                }
            }
            //game board ws clicked
            else {
                int xSquare = (e.getX() - 4) / 32;
                int ySquare = (e.getY() - 74) / 32;
                if (IsGrass(xSquare, ySquare)) {
                    Tower tower = CheckForTowerInSquare(xSquare, ySquare);
                    //check if an existing tower was clicked
                    if (tower != null) {
                        tower.set_showRadius(!tower.get_showRadius());
                        PaintNewGamePanel();
                    }
                    //check if the game isn't running to enable adding more towers
                    else if (!_gameRunnig) {
                        ChooseTowerFrame chooseTowerFrame = new ChooseTowerFrame(_towerToAdd, _towerIcons, this, xSquare, ySquare);
                        this.disable();
                    }
                }
            }
        }
    }

    /***
     * add new tower to the board
     * @param index index of tower
     * @param x
     * @param y
     */
    public void AddTower(int index,int x,int y){
        // check if its possible to add tower
        if ((_towerToAdd[index]>0)){
            _towerToAdd[index]--;
            Tower addedTower = null;
            //find what type of tower to add
            switch (index){
                case 0:
                     addedTower = new TowerArrow(x,y,_gameBoard);
                     break;
                case 1:
                    addedTower = new TowerLava(x,y,_gameBoard);
                    break;
                case 2:
                    addedTower = new TowerMagic(x,y,_gameBoard);
                    break;
                case 3:
                    addedTower = new TowerPoison(x,y,_gameBoard);
                    break;
                case 4:
                    addedTower = new TowerGoku(x,y,_gameBoard);
                    break;
                case 5:
                    addedTower = new TowerDrug(x,y,_gameBoard);
                    break;
                case 6:
                    addedTower = new TowerDragon(x,y,_gameBoard);
                    break;
            }
            _gameBoard.addTower(addedTower);
        }
        PaintNewGamePanel();
    }

    /***
     * check if a tower exsits in a crurent square
     * @param x
     * @param y
     * @return the tower if exsits or null if doesn't
     */
    //@Nullable
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
        if (_pathCoords[y][x].getX()!=0 | _pathCoords[y][x].getY()!=0){
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

    /**
     * _timer tick
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        _gameBoard.tickHappened(); // update board logic
        this.PaintNewGamePanel(); // paint new board
        setLifeLeft(_gameBoard.getPlayerHealth());
        try {
            CheckForGameEnding();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    //TODO - wave ended
}
