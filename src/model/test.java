package model;

import java.io.IOException;

/**
 * Created by ariel on 11-Jun-17.
 */
public class test {

    public static void main (String [] args) throws IOException{
        Coords[][] level= null;
        LevelLoader ll = new LevelLoader();
        ll.Load();
        level = ll.getLevel(0);

        for (int i=0;i<level.length;i++){
            for (int j=0;j<level[i].length;j++){
                System.out.print(level[i][j]);
            }
            System.out.println();
        }
    }
}
