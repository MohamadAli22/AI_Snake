package world;

import java.io.Serializable;

/**
 *
 * @author Mohammad Hadi
 */
public class Snake implements Serializable{

    public Snake(int x, int y, char d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }

    public int x;
    public int y;
    public char d;
}
