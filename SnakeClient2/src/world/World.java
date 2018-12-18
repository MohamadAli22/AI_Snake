package world;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Mohammad Hadi
 */
public class World implements Serializable{

    public char[][] d;
    public ArrayList<Snake> snake;
    public int tx;
    public int ty;

    public World(char[][] d, ArrayList<Snake> snake, int tx, int ty) {
        this.d = d;
        this.snake = snake;
        this.tx = tx;
        this.ty = ty;
    }

}

