package client;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import world.Snake;
import world.World;

/**
 *
 * @author Mohammad Hadi
 */
public class AI {

    Snake tail = null;
    int cc = 0;

    public char nextMove(World w) {
        /* your code must be added here
         return u for up
         return r for right
         return d for down
         return l for left
         */
        setDelay(1);
        setRandomTarget(true);
        Snake head = w.snake.get(w.snake.size() - 1);
        tail = w.snake.get(0);
        cc++;
        //char des = whereToGo(w, head, w.tx, w.ty);
        char des = 'f';
        for (int i = 2; i >= 0; i--) {
            for (int k = 3; k >= 1; k--) {
                for (int j = 2; j >= 0; j--) {
                    for (int l = 3; l >= 1; l--) {
                        des = whereToGo(w, head, w.tx, w.ty, i, w.d.length - k, w.d.length - l, j);
                        if (des != 'f') {
                            System.out.println("mahdoodiyate emal shode: " + i + j + k + l);
                            break;
                        }
                    }
                }
            }
        }

        char ans = 'd';
        boolean haveBetterIdea = false;
        Snake neck = null;
        if (w.snake.size() > 1) {
            neck = w.snake.get(w.snake.size() - 2);
        }

        if (des == 'f') {
            char[][] newWorld = new char[w.d.length][w.d.length];
            for (int i = 0; i < w.d.length; i++) {
                for (int j = 0; j < w.d[i].length; j++) {
                    newWorld[i][j] = w.d[i][j];
                }
            }

            boolean[] b = new boolean[4];

            int up = 120000;
            int right = 120000;
            int left = 120000;
            int down = 120000;
            int min = 120000;

            int upp = 1;
            int rightp = 1;
            int leftp = 1;
            int downp = 1;
            int minp = 1;
            ans = 'd';

            if (head.x > 1 && w.d[head.x - 1][head.y] != 's') {
                newWorld[head.x - 1][head.y] = 's';
                newWorld[tail.x][tail.y] = 'e';
                up = freeZoneCounter(newWorld);
                upp = badeshCheghadrMisheRaf(newWorld, head.x - 1, head.y);
                if (up < min && upp != 0) {
                    min = up;
                    ans = 'u';
                }
                if (upp > minp) {
                    minp = upp;
                }

                newWorld[head.x - 1][head.y] = 'e';
                newWorld[tail.x][tail.y] = 's';
            }
            if (head.x < w.d.length - 2 && w.d[head.x + 1][head.y] != 's') {
                newWorld[head.x + 1][head.y] = 's';
                newWorld[tail.x][tail.y] = 'e';
                down = freeZoneCounter(newWorld);
                downp = badeshCheghadrMisheRaf(newWorld, head.x + 1, head.y);
                if (down < min && downp != 0) {
                    min = down;
                    ans = 'd';
                }
                if (downp > minp) {
                    minp = downp;
                }

                newWorld[head.x + 1][head.y] = 'e';
                newWorld[tail.x][tail.y] = 's';
            }
            if (head.y > 0 && w.d[head.x][head.y - 1] != 's') {
                newWorld[head.x][head.y - 1] = 's';
                newWorld[tail.x][tail.y] = 'e';
                left = freeZoneCounter(newWorld);
                leftp = badeshCheghadrMisheRaf(newWorld, head.x, head.y - 1);
                if (left < min && leftp != 0) {
                    min = left;
                    ans = 'l';
                }
                if (leftp > minp) {
                    minp = leftp;
                }
                newWorld[head.x][head.y - 1] = 'e';
                newWorld[tail.x][tail.y] = 's';
            }
            if (head.y < w.d.length - 1 && w.d[head.x][head.y + 1] != 's') {
                newWorld[head.x][head.y + 1] = 's';
                newWorld[tail.x][tail.y] = 'e';
                right = freeZoneCounter(newWorld);
                rightp = badeshCheghadrMisheRaf(newWorld, head.x, head.y + 1);
                if (right < min && rightp != 0) {
                    min = right;
                    ans = 'r';
                }
                if (rightp > minp) {
                    minp = rightp;
                }

                newWorld[head.x][head.y + 1] = 'e';
                newWorld[tail.x][tail.y] = 's';
            }

            System.out.println("ba mahdoodiyat");
            System.out.println("u d r l  " + up + " " + down + " " + right + " " + left);
            System.out.println("p: u d r l  " + upp + " " + downp + " " + rightp + " " + leftp);

            if ( head.x!=2 && head.x!=w.d.length-3 && head.y!=2 && head.y!=w.d.length-3) {

                if (head.x > 0 && w.d[head.x - 1][head.y] != 's') {
                    newWorld[head.x - 1][head.y] = 's';
                    newWorld[tail.x][tail.y] = 'e';
                    up = freeZoneCounter(newWorld);
                    upp = badeshCheghadrMisheRaf(newWorld, head.x - 1, head.y);
                    if (up < min && upp != 0) {
                        min = up;
                        ans = 'u';
                    }
                    if (upp > minp) {
                        minp = upp;
                    }

                    newWorld[head.x - 1][head.y] = 'e';
                    newWorld[tail.x][tail.y] = 's';
                }
                if (head.x < w.d.length - 1 && w.d[head.x + 1][head.y] != 's') {
                    newWorld[head.x + 1][head.y] = 's';
                    newWorld[tail.x][tail.y] = 'e';
                    down = freeZoneCounter(newWorld);
                    downp = badeshCheghadrMisheRaf(newWorld, head.x + 1, head.y);
                    if (down < min && downp != 0) {
                        min = down;
                        ans = 'd';
                    }
                    if (downp > minp) {
                        minp = downp;
                    }

                    newWorld[head.x + 1][head.y] = 'e';
                    newWorld[tail.x][tail.y] = 's';
                }
                if (head.y > 0 && w.d[head.x][head.y - 1] != 's') {
                    newWorld[head.x][head.y - 1] = 's';
                    newWorld[tail.x][tail.y] = 'e';
                    left = freeZoneCounter(newWorld);
                    leftp = badeshCheghadrMisheRaf(newWorld, head.x, head.y - 1);
                    if (left < min && leftp != 0) {
                        min = left;
                        ans = 'l';
                    }
                    if (leftp > minp) {
                        minp = leftp;
                    }
                    newWorld[head.x][head.y - 1] = 'e';
                    newWorld[tail.x][tail.y] = 's';
                }
                if (head.y < w.d.length - 1 && w.d[head.x][head.y + 1] != 's') {
                    newWorld[head.x][head.y + 1] = 's';
                    newWorld[tail.x][tail.y] = 'e';
                    right = freeZoneCounter(newWorld);
                    rightp = badeshCheghadrMisheRaf(newWorld, head.x, head.y + 1);
                    if (right < min && rightp != 0) {
                        min = right;
                        ans = 'r';
                    }
                    if (rightp > minp) {
                        minp = rightp;
                    }

                    newWorld[head.x][head.y + 1] = 'e';
                    newWorld[tail.x][tail.y] = 's';
                }

                System.out.println("FREEE");
                System.out.println("u d r l  " + up + " " + down + " " + right + " " + left);
                System.out.println("p: u d r l  " + upp + " " + downp + " " + rightp + " " + leftp);

            }

            if (min == down) {
                b[1] = true;
                ans = 'd';
                System.out.println("down :" + min);
            }
            if (min == left) {
                b[2] = true;
                ans = 'l';
                System.out.println("left :" + min);
            }
            if (min == up) {
                b[0] = true;
                ans = 'u';
                System.out.println("up :" + up);
            }
            if (min == right) {
                b[3] = true;
                ans = 'r';
                System.out.println("right :" + min);
            }

            if (min >= 2) {
                boolean hale = false;

                
                if (b[2] && w.snake.size() <= leftp) {
                    ans = 'l';
                    System.out.println("hale leftp :" + minp);
                    hale = true;
                }
                if (b[1] && w.snake.size() <= downp) {
                    ans = 'd';
                    System.out.println("hale downp :" + minp);
                    hale = true;
                }
                if (b[0] && w.snake.size() <= upp) {
                    ans = 'u';
                    System.out.println("hale upp :" + minp);
                    hale = true;
                }
                if (b[3] && w.snake.size() <= rightp) {
                    ans = 'r';
                    System.out.println("hale rightp :" + minp);
                    hale = true;
                }

                //mirim jaii ke ja bashe mohem nis zone ha chand ta beshe
                if (!hale) {
                    System.out.println("mirim jaii ke ja bashe mohem nis zone ha chand ta beshe");
                    
                    if (minp == upp) {
                        ans = 'u';
                        System.out.println("upp :" + minp);
                    }
                    if (minp == leftp) {
                        ans = 'l';
                        System.out.println("leftp :" + minp);
                    }
                    
                    if (minp == downp) {
                        ans = 'd';
                        System.out.println("downp :" + minp);
                    }
                    if (minp == rightp) {
                        ans = 'r';
                        System.out.println("rightp :" + minp);
                    }
                }
            }

            if (ans == 'r' && min == 120000) {

                ans = 'f';

                if (head.y < w.d.length - 1 && w.d[head.x][head.y + 1] != 's') {

                    w.d[head.x][head.y + 1] = 's';
                    w.d[tail.x][tail.y] = 'e';
                    int zone = freeZoneCounter(w.d);
                    w.d[head.x][head.y + 1] = 'e';
                    w.d[tail.x][tail.y] = 's';

                    if (zone == 1) {
                        ans = 'r';
                    }
                } else if (head.x < w.d.length - 1 && w.d[head.x + 1][head.y] != 's') {

                    w.d[head.x + 1][head.y] = 's';
                    w.d[tail.x][tail.y] = 'e';
                    int zone = freeZoneCounter(w.d);
                    w.d[head.x + 1][head.y] = 'e';
                    w.d[tail.x][tail.y] = 's';

                    if (zone == 1) {
                        ans = 'd';
                    }

                } else if (head.x > 0 && w.d[head.x - 1][head.y] != 's') {
                    w.d[head.x - 1][head.y] = 's';
                    w.d[tail.x][tail.y] = 'e';
                    int zone = freeZoneCounter(w.d);
                    w.d[head.x - 1][head.y] = 'e';
                    w.d[tail.x][tail.y] = 's';

                    if (zone == 1) {
                        ans = 'u';
                    }
                } else if (head.y > 0 && w.d[head.x][head.y - 1] != 's') {
                    w.d[head.x][head.y - 1] = 's';
                    w.d[tail.x][tail.y] = 'e';
                    int zone = freeZoneCounter(w.d);
                    w.d[head.x][head.y - 1] = 'e';
                    w.d[tail.x][tail.y] = 's';

                    if (zone == 1) {
                        ans = 'l';
                    }
                }
                System.out.println("haminjoori mirim: " + ans);
            }

            System.out.println("tasmim:" + ans);
            des = ans;

        }

        for (int i = 0; i < w.d.length; i++) {
            for (int j = 0; j < w.d.length; j++) {
                if (i == head.x && j == head.y) {
                    System.out.print(" O");
                } else if (i == tail.x && j == tail.y) {
                    System.out.print(" X");
                } else {
                    System.out.print(" " + w.d[i][j]);
                }
            }
            System.out.println("");
        }
        System.out.println("------------------------");
        System.out.println("headX:" + head.x + "  headY:" + head.y);

        return des;
    }

    public void setRandomTarget(boolean b) {
        SnakeClient.setRandomTarger(b);
    }

    public void setDelay(long milis) {
        SnakeClient.setDelay(milis);
    }

    public char whereToGo(World w, Snake head, int targetX, int targetY, int ub, int db, int rb, int lb) {

        Queue queue = new LinkedList();
        Nodee n = new Nodee(head.x, head.y, new ArrayList<>());
        queue.add(n);
        boolean[][] visited = new boolean[w.d.length][w.d.length];
        visited[head.x][head.y] = true;
        Nodee ans = null;

        while (!queue.isEmpty()) {
            Nodee cur = (Nodee) queue.poll();
            int x = cur.x;
            int y = cur.y;
            ArrayList ar = cur.where;

            if (x == targetX && y == targetY) {
                int zone = 0;
                if (cur.where.get(0) == 'u') {
                    w.d[head.x - 1][head.y] = 's';
                    w.d[tail.x][tail.y] = 'e';
                    zone = freeZoneCounter(w.d);
                    w.d[head.x - 1][head.y] = 'e';
                    w.d[tail.x][tail.y] = 's';
                } else if (cur.where.get(0) == 'd') {
                    w.d[head.x + 1][head.y] = 's';
                    w.d[tail.x][tail.y] = 'e';
                    zone = freeZoneCounter(w.d);
                    w.d[head.x + 1][head.y] = 'e';
                    w.d[tail.x][tail.y] = 's';
                } else if (cur.where.get(0) == 'r') {
                    w.d[head.x][head.y + 1] = 's';
                    w.d[tail.x][tail.y] = 'e';
                    zone = freeZoneCounter(w.d);
                    w.d[head.x][head.y + 1] = 'e';
                    w.d[tail.x][tail.y] = 's';
                } else {
                    w.d[head.x][head.y - 1] = 's';
                    w.d[tail.x][tail.y] = 'e';
                    zone = freeZoneCounter(w.d);
                    w.d[head.x][head.y - 1] = 'e';
                    w.d[tail.x][tail.y] = 's';
                }
                if (zone == 1) {
                    ans = cur;
                    break;
                }
            }

            if (x > ub && !visited[x - 1][y] && w.d[x - 1][y] != 's') {
                ArrayList<Character> arr = (ArrayList<Character>) ar.clone();
                arr.add('u');
                Nodee node = new Nodee(x - 1, y, arr);
                queue.add(node);
                visited[x - 1][y] = true;
            }
            if (x < db && !visited[x + 1][y] && w.d[x + 1][y] != 's') {
                ArrayList<Character> arr = (ArrayList<Character>) ar.clone();
                arr.add('d');
                Nodee node = new Nodee(x + 1, y, arr);
                queue.add(node);
                visited[x + 1][y] = true;
            }
            if (y < rb && !visited[x][y + 1] && w.d[x][y + 1] != 's') {
                ArrayList<Character> arr = (ArrayList<Character>) ar.clone();
                arr.add('r');
                Nodee node = new Nodee(x, y + 1, arr);
                queue.add(node);
                visited[x][y + 1] = true;
            }
            if (y > lb && !visited[x][y - 1] && w.d[x][y - 1] != 's') {
                ArrayList<Character> arr = (ArrayList<Character>) ar.clone();
                arr.add('l');
                Nodee node = new Nodee(x, y - 1, arr);
                queue.add(node);
                visited[x][y - 1] = true;
            }

        }

        if (ans != null) {
            //answer founded
            return ans.where.get(0);
        } else {
            //bon bastes
            return 'f';

        }

    }

    private static int freeZoneCounter(char[][] w) {
        ArrayList<ChosNodee> ar = new ArrayList<>();
        for (int i = 0; i < w.length; i++) {
            for (int j = 0; j < w[i].length; j++) {
                if (w[i][j] != 's') {
                    ar.add(new ChosNodee(i, j));
                }
            }
        }

        boolean[][] visited = new boolean[w.length][w.length];
        int count = 0;
        while (!ar.isEmpty()) {
            ChosNodee tmp = ar.remove(0);
            int xx = tmp.x;
            int yy = tmp.y;
            count++;
            Queue<ChosNodee> queue = new LinkedList();
            queue.add(tmp);
            visited[xx][yy] = true;
            while (!queue.isEmpty()) {
                ChosNodee cur = queue.poll();
                int x = cur.x;
                int y = cur.y;
                removeIt(ar, cur);
                if (x > 0 && !visited[x - 1][y] && w[x - 1][y] != 's') {
                    ChosNodee node = new ChosNodee(x - 1, y);
                    queue.add(node);
                    visited[x - 1][y] = true;
                }
                if (x < w.length - 1 && !visited[x + 1][y] && w[x + 1][y] != 's') {
                    ChosNodee node = new ChosNodee(x + 1, y);
                    queue.add(node);
                    visited[x + 1][y] = true;
                }
                if (y < w.length - 1 && !visited[x][y + 1] && w[x][y + 1] != 's') {
                    ChosNodee node = new ChosNodee(x, y + 1);
                    queue.add(node);
                    visited[x][y + 1] = true;
                }
                if (y > 0 && !visited[x][y - 1] && w[x][y - 1] != 's') {
                    ChosNodee node = new ChosNodee(x, y - 1);
                    queue.add(node);
                    visited[x][y - 1] = true;
                }
            }
        }
        return count;
    }

    private static int badeshCheghadrMisheRaf(char[][] newWorld, int headx, int heady) {//test nashode

        boolean[][] visited = new boolean[newWorld.length][newWorld.length];
        Queue<Nodee> queue = new LinkedList<>();
        queue.add(new Nodee(headx, heady, new ArrayList<>()));
        int counter = -1;

        while (!queue.isEmpty()) {
            Nodee cur = queue.poll();
            int x = cur.x;
            int y = cur.y;
            ArrayList<Character> ar = cur.where;
            counter++;

            if (x > 0 && !visited[x - 1][y] && newWorld[x - 1][y] != 's') {
                ArrayList<Character> arr = (ArrayList<Character>) ar.clone();
                arr.add('u');
                Nodee node = new Nodee(x - 1, y, arr);
                queue.add(node);
                visited[x - 1][y] = true;
            }
            if (x < newWorld.length - 1 && !visited[x + 1][y] && newWorld[x + 1][y] != 's') {
                ArrayList<Character> arr = (ArrayList<Character>) ar.clone();
                arr.add('d');
                Nodee node = new Nodee(x + 1, y, arr);
                queue.add(node);
                visited[x + 1][y] = true;
            }
            if (y < newWorld.length - 1 && !visited[x][y + 1] && newWorld[x][y + 1] != 's') {
                ArrayList<Character> arr = (ArrayList<Character>) ar.clone();
                arr.add('r');
                Nodee node = new Nodee(x, y + 1, arr);
                queue.add(node);
                visited[x][y + 1] = true;
            }
            if (y > 0 && !visited[x][y - 1] && newWorld[x][y - 1] != 's') {
                ArrayList<Character> arr = (ArrayList<Character>) ar.clone();
                arr.add('l');
                Nodee node = new Nodee(x, y - 1, arr);
                queue.add(node);
                visited[x][y - 1] = true;
            }

        }

        return counter;
    }

    private static ArrayList<ChosNodee> removeIt(ArrayList<ChosNodee> ar, ChosNodee rem) {
        if (ar.size() == 0) {
            return ar;
        }
        int index = -1;
        for (int i = 0; i < ar.size(); i++) {
            ChosNodee ch = ar.get(i);
            if (ch.x == rem.x && ch.y == rem.y) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            ChosNodee n = ar.remove(index);
        }
        return ar;
    }

    static class ChosNodee {

        int x = 0;
        int y = 0;

        public ChosNodee(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "x" + x + "y" + y;
        }

        @Override
        public boolean equals(Object obj) {
            return this.toString().equals(obj.toString());
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(x) + Integer.hashCode(y); //To change body of generated methods, choose Tools | Templates.
        }

    }

    static class Nodee {

        int x = 0;
        int y = 0;
        ArrayList<Character> where;

        public Nodee(int x, int y, ArrayList<Character> wh) {
            this.x = x;
            this.y = y;
            where = wh;
        }

        @Override
        public String toString() {
            return "x:" + x + " y:" + y;
        }

    }

}
