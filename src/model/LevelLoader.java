package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;


/**
 * Created by ariel on 11-Jun-17.
 */
public class LevelLoader {

    private  final char _empty = '-';
    private  final char _up = '^';
    private  final char _right = '>';
    private  final char _down = 'v';
    private  final char _left = '<';

    private Vector<Coords[][]> _levles;
    private String _path ="levels.txt";

    public LevelLoader (){
        _levles = new Vector<Coords[][]>();
    }

    /**
     * get a level
     * @param index
     * @return
     */
    public Coords[][] getLevel (int index){
        return _levles.get(index);
    }

    public boolean Load () throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(_path));

        String line;
        int w=0;
        int h=0;
        int row = 0;
        Coords [][] currLevel= null;

        // when text file ends
        while ((line=br.readLine())!= null) {

            // current level paint is done
            if (line.trim().isEmpty()) {
                // if level exists insert to levels
                if (currLevel != null) {
                    _levles.add(currLevel);
                }
                continue;
            }

            // get width
            if (line.trim().startsWith("w")) {
                w = Integer.valueOf(line.trim().substring(1));
                continue;
            }
            //get height
            if (line.trim().startsWith("h")) {
                h = Integer.valueOf(line.trim().substring(1));
                continue;
            }
            //initialize currLevel array
            if ((currLevel == null) & (h > 0) & (w > 0)) {
                currLevel = new Coords[h][w];
                row = 0;
            }

            // create a row
            for (int col = 0; col < w; col++) {
                Coords coord = parseCoord(line.charAt(col));
                if (coord != null) {
                    currLevel[row][col] = coord;
                } else { // level us written wrong
                    br.close();
                    return false;
                }
            }
            row++;
        }

        // add new loadad level to levels vector
        _levles.add(currLevel);
        br.close();
        return true;
    }

    private Coords parseCoord (char input){
        switch (input){

            case _empty:
                return new Coords(0,0);
            case _up:
                return new Coords(0,-1);
            case _right:
                return new Coords(1,0);
            case _down:
                return new Coords(0,1);
            case _left:
                return new Coords(-1,0);
        }
        return null;
    }

    public int numOfLeveles (){
        return _levles.size();
    }
}
