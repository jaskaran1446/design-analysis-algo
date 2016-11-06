import java.util.ArrayList;

/**
 *
 * @author jaskaran
 */
public class Prim {

    int[][] g; //adjaceny matrix containing 0s and weights
    int n;

    public Prim(int vertices, int[][] adj) {
        n = vertices;
        g = adj;
    }

    public void printMST(int source) {
        System.out.println("MST:");
        ArrayList<Integer> visited = new ArrayList<>();
        ArrayList<Integer> unvisited = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i != source) {
                unvisited.add(i);
            } else {
                visited.add(i);
            }
        }
        while (!unvisited.isEmpty()) {
            int min = Integer.MAX_VALUE;
            int chosen = -1;
            String edge = "";
            for (int u : visited) {
                for (int v : unvisited) {
                    if (g[u][v] != 0 && g[u][v] < min) {
                        min = g[u][v];
                        chosen = v;
                        edge = u + "-" + v;
                    }
                }
            }
            System.out.println(edge+"-->"+min);
            visited.add(chosen);
            unvisited.remove(new Integer(chosen));
        }
    }

    public static void main(String[] a) {
        int g[][] = {
            {0, 5, 0, 6, 0},
            {5, 0, 9, 0, 7},
            {0, 9, 0, 0, 9},
            {6, 0, 0, 0, 5},
            {0, 7, 9, 5, 0}
        };
        Prim p = new Prim(5, g);
        p.printMST(0);
    }
}
