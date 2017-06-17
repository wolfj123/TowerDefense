package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by ariel on 17-Jun-17.
 */
public class ChooseTowerFrame extends JFrame implements MouseListener {


    GameFrame _gameFrame;

    public ChooseTowerFrame(int [] towersLeft , ImageIcon [] icons,GameFrame gameFrame) {
        super("Tower Defense - Select Tower");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        GridLayout gridLayout = new GridLayout(2,7);
        gridLayout.setHgap(30);
        this.setLayout(gridLayout);

        _gameFrame = gameFrame;

        JLabel[][] labels = new JLabel[2][7];
        //create icons labels
        for (int i = 0; i < labels[0].length; i++) {
            Image image = icons[i].getImage();
            labels[0][i]= new JLabel(new ImageIcon(image));
            labels[0][i].addMouseListener(this);
        }
        //crate how many left
        for (int i = 0; i < labels[0].length; i++) {
            String text = "    " + towersLeft[i];
            labels[1][i]= new JLabel(text);
        }


        for (int i = 0; i < labels.length; i++) {
            for (int j = 0; j < labels[0].length; j++) {
                this.add(labels[i][j]);
            }
        }

        this.pack();
        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //TODO
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
