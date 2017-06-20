package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

/**
 * Created by ariel on 17-Jun-17.
 */
public class ChooseTowerFrame extends JFrame implements MouseListener {

    private GameFrame _gameFrame;
    private int _x;
    private int _y;
    private JLabel[][] _labels;
    
    public ChooseTowerFrame(int [] towersLeft , ImageIcon [] icons,GameFrame gameFrame,int x,int y) {
        super("Tower Defense - Select Tower");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //set layout
        GridLayout gridLayout = new GridLayout(2,7);
        gridLayout.setHgap(30);
        this.setLayout(gridLayout);
        //set fileds
        _gameFrame = gameFrame;
        _x=x;
        _y=y;
        //create label array
         _labels = new JLabel[2][7];
        //create icons _labels
        for (int i = 0; i < _labels[0].length; i++) {
            Image image = icons[i].getImage();
            _labels[0][i]= new JLabel(new ImageIcon(image));
            _labels[0][i].addMouseListener(this);
        }
        //crate how many left
        for (int i = 0; i < _labels[0].length; i++) {
            String text = " towers left: "+ towersLeft[i];
            _labels[1][i]= new JLabel(text);
        }

        //add label array to frame
        for (int i = 0; i < _labels.length; i++) {
            for (int j = 0; j < _labels[0].length; j++) {
                this.add(_labels[i][j]);
            }
        }

        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        _gameFrame.enable();
        int index=-1;
        //find tower index
        for (int i=0;i<_labels[0].length;i++){
            if (e.getSource().equals(_labels[0][i])){
                index = i;
                break;
            }
        }
        if (index!=-1) {
            _gameFrame.AddTower(index, _x, _y);
        }
        this.dispose();
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

    @Override
    public void dispose (){
        _gameFrame.enable();
        super.dispose();
    }
}
