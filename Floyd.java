import java.util.ArrayList;

/**
 *
 * @author jaskaran
 */
public class Floyd {

    int[][] g; //adjaceny matrix containing 0s and weights
    int n;

    public Floyd(int vertices, int[][] adj) {
        n = vertices;
        g = adj;
    }

    public void print() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && g[i][j] == 0) {
                    g[i][j] = 999;
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(g[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] a) {
        int g[][] = {
            {0, 5, 0, 0},
            {50, 0, 15, 5},
            {30, 0, 0, 15},
            {15, 0, 5, 0}
        };
        Floyd f = new Floyd(4,g);
        f.print();
    }
}
