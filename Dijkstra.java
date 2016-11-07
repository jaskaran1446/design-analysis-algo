import static java.lang.System.out;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Jaskaran
 */
public class Dijkstra {

    int graph[][];
    int numberOfVertices;

    public Dijkstra(int n) {
        this.numberOfVertices = n;
        graph = new int[n][n];
    }

    public void input() {
        out.println("Enter adjaceny matrix:(-1 for no edge)");
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < numberOfVertices; i++) {
            for (int j = 0; j < numberOfVertices; j++) {
                graph[i][j] = sc.nextInt();
            }
        }
    }

    public void solve(int source) {
        int distance[] = new int[numberOfVertices];

        for (int i = 0; i < distance.length; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        boolean[] visited = new boolean[numberOfVertices];
        distance[source] = 0;

        for (int x = 0; x < numberOfVertices; ++x) {
            int u = -1, minDist = Integer.MAX_VALUE;
            for (int y = 0; y < numberOfVertices; y++) {
                if (!visited[y] && minDist > distance[y]) {
                    u = y;
                    minDist = distance[y];
                }
            }
            visited[u] = true;
            for (int z = 0; z < numberOfVertices; z++) {
                if (graph[u][z] != -1 && !visited[z]) {
                    distance[z] = Math.min(distance[z], distance[u] + graph[u][z]);
                }
            }
        }
        out.println("Minimum distance to each vertex is :" + Arrays.toString(distance));
    }
    
    public static void main(String[] args){
        Dijkstra d = new Dijkstra(4);
        d.input();
        d.solve(0);
    }
}

