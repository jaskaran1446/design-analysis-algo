/**
 *
 * @author Jaskaran
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

class DisjointSets {

    private List<Map<Integer, Set<Integer>>> disjointSet;

    public DisjointSets() {
        disjointSet = new ArrayList<Map<Integer, Set<Integer>>>();
    }

    public void create_set(int element) {
        Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
        Set<Integer> set = new HashSet<Integer>();
        set.add(element);
        map.put(element, set);
        disjointSet.add(map);
    }

    public void union(int first, int second) {
        int first_rep = find_set(first);
        int second_rep = find_set(second);

        Set<Integer> first_set = null;
        Set<Integer> second_set = null;

        for (int index = 0; index < disjointSet.size(); index++) {
            Map<Integer, Set<Integer>> map = disjointSet.get(index);
            if (map.containsKey(first_rep)) {
                first_set = map.get(first_rep);
            } else if (map.containsKey(second_rep)) {
                second_set = map.get(second_rep);
            }
        }

        if (first_set != null && second_set != null) {
            first_set.addAll(second_set);
        }

        for (int index = 0; index < disjointSet.size(); index++) {
            Map<Integer, Set<Integer>> map = disjointSet.get(index);
            if (map.containsKey(first_rep)) {
                map.put(first_rep, first_set);
            } else if (map.containsKey(second_rep)) {
                map.remove(second_rep);
                disjointSet.remove(index);
            }
        }
    }

    public int find_set(int element) {
        for (int index = 0; index < disjointSet.size(); index++) {
            Map<Integer, Set<Integer>> map = disjointSet.get(index);
            Set<Integer> keySet = map.keySet();
            for (Integer key : keySet) {
                Set<Integer> set = map.get(key);
                if (set.contains(element)) {
                    return key;
                }
            }
        }
        return -1;
    }

    public int getNumberofDisjointSets() {
        return disjointSet.size();
    }
}

class Edge {

    private Integer v1, v2; //vertices are implemeted as Integer type objects
    private int weight;

    public Edge(Integer v1, Integer v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge{" + "v1=" + v1 + ", v2=" + v2 + ", weight=" + weight + '}';
    }

    public int getWeight() {
        return weight;
    }

    public Integer getV1() {
        return v1;
    }

    public Integer getV2() {
        return v2;
    }
}

class EdgeComparator implements Comparator<Edge> {

    @Override
    public int compare(Edge o1, Edge o2) {
        if ((o1.getV1() == o2.getV1() && o1.getV2() == o2.getV2()) || (o1.getV1() == o2.getV2() && o1.getV2() == o2.getV1())) {
            //System.out.println("same");
            return 0;
        } else if (o1.getWeight() < o2.getWeight()) {
            return -1;
        } else {
            return 1;
        }
    }
}

class Graph {

    private final TreeSet<Integer> V;
    private final NavigableSet<Edge> E;

    public Graph(int vertices) {
        E = new TreeSet<Edge>(new EdgeComparator());
        V = new TreeSet<>();
        for (int i = 0; i < vertices; i++) {
            this.V.add(i);
        }
    }

    public Graph(int vertices,int edges) {
        this.V = new TreeSet<>();
        for (int i = 0; i < vertices; i++) {
            this.V.add(i);
        }
        this.E = new TreeSet<Edge>(new EdgeComparator());
        Scanner s = new Scanner(System.in);
        
        System.out.println("Enter all edges:");
        for (int x = 0; x < edges; x++) {
            int v1 = s.nextInt();
            int v2 = s.nextInt();
            int wt = s.nextInt();
            addEdge(v1, v2, wt);
        }
    }

    public NavigableSet<Edge> getE() {
        return E;
    }

    public TreeSet<Integer> getV() {
        return V;
    }

    public void addEdge(int v1, int v2, int w) {
        if (V.contains(v1) && V.contains(v2) && w >= 0 && v1 != v2) {
            Edge x = new Edge(v1, v2, w);
            E.add(x);
        }
    }

    public void print() {
        System.out.println("Size:" + this.E.size());
        for (Edge e : this.E) {
            System.out.println(e);
        }
    }
}

public class Kruskal {

    private Graph g;

    public Kruskal(int v,int e) {
        g = new Graph(v,e);
    }

    public void solve() {
        Graph mst = new Graph(g.getV().size());
        DisjointSets ds = new DisjointSets();
        TreeSet<Integer> vertices = g.getV();
        for (int i : vertices) {
            ds.create_set(i);
        }

        NavigableSet<Edge> edges = g.getE();
        for (Edge each : edges) {
            int i, j;
            if ((i = ds.find_set(each.getV1())) != (j = ds.find_set(each.getV2()))) {
                mst.addEdge(each.getV1(), each.getV2(), each.getWeight());
                ds.union(i, j);                
            }
        }
        System.out.println("MST has the following edges: ");
        mst.print();
    }

    public static void main(String args[]) {
        Kruskal k = new Kruskal(5,6);
        k.solve();
    }
}
