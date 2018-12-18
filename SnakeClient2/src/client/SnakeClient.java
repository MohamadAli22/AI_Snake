package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import world.World;

/**
 *
 * @author Mohammad Hadi
 */
public class SnakeClient {

    static Socket socket;
    static ObjectOutputStream out;
    static ObjectInputStream in;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        socket = new Socket(InetAddress.getLocalHost(), 5050);
        System.out.println("Server Found");
        out = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("got output");
        in = new ObjectInputStream(socket.getInputStream());
        System.out.println("got input");
        AI ai = new AI();
        while (true) {
            out.reset();
            World world = (World) in.readObject();
            if (world.snake == null) {
                break;
            }
            out.writeChar(ai.nextMove(world));
        }
    }

    static void setRandomTarger(boolean b) {
        try {
            if (b) {
                out.writeChar('a');
            } else {
                out.writeChar('b');
            }
        } catch (IOException ex) {
            Logger.getLogger(SnakeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static void setDelay(long milis) {
        try {
            out.writeChar('c');
            out.writeLong(milis);
        } catch (IOException ex) {
            Logger.getLogger(SnakeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
