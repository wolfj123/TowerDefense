package gui;

import javax.swing.*;

import logic.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by ariel on 11-Jun-17.
 */
public class LevelLoadFrame extends JFrame implements MouseListener {

    JLabel _levleSelectText = new JLabel("Welcome to Tower Defese - please select level below and press start game");
    JList<String> _levelNamesJlist;
    LevelLoader _levelLoader;

    public LevelLoadFrame() throws IOException{
        super ("Tower Defense");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // get all info needed for level names
        Vector <String> levelNames = new Vector <String>();
        _levelLoader = new LevelLoader();
        _levelLoader.Load();
        int numberOfLevels = _levelLoader.numOfLeveles();

        //create level names
        for (int i=1;i<=numberOfLevels;i++){
            levelNames.add("Level " + i);
        }

        // create JList
        _levelNamesJlist = new JList<String>(levelNames);
        _levelNamesJlist.setSelectedIndex(0); // set the initial level value to 0
        //Create ScrollPane
        JScrollPane scrolList = new JScrollPane(_levelNamesJlist);
        _levelNamesJlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); /// enable selecting only one level


        this.setLayout(new BorderLayout());
        this.add(UpperPanel(),BorderLayout.NORTH);
        this.add(_levelNamesJlist,BorderLayout.SOUTH);


        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

    /***
     * create the upper part of the screen
     * @return
     */
    private JPanel UpperPanel (){

        JPanel upperPanel = new JPanel(new FlowLayout());
        upperPanel.add(_levleSelectText);
        // create start game button
        JButton startGameButton = new JButton("Start Game");
        startGameButton.addMouseListener(this);
        upperPanel.add(startGameButton);

        return upperPanel;
    }


    public static void main(String[] args) throws IOException{
        LevelLoadFrame levelFrame = new LevelLoadFrame();
    }

    /***
     * MouseListener to begin a new game
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e)  {
        GameFrame gameFrame = new GameFrame(_levelNamesJlist.getSelectedIndex(),_levelLoader);
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
