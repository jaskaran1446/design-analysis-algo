import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author jaskaran
 */
class Item implements Comparable {

    private int profit;
    private int weight;
    private float perUnit;

    public Item(int profit, int weight) {
        this.profit = profit;
        this.weight = weight;
        this.perUnit = profit * 1.0f / weight;
    }

    public int getProfit() {
        return profit;
    }

    public int getWeight() {
        return weight;
    }

    public int compare(Object o1, Object o2) {
        Item a = (Item) o1;
        Item b = (Item) o2;
        if (a.perUnit > b.perUnit) {
            return 1;
        }
        if (a.perUnit < b.perUnit) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Item{" + "profit=" + profit + ", weight=" + weight + '}';
    }

    @Override
    public int compareTo(Object o) {
        Item a = (Item) o;
        if (a.perUnit > perUnit) {
            return 1;
        }
        if (a.perUnit < perUnit) {
            return -1;
        }
        return 0;
    }
}

public class Knapsack {

    int wmax;
    ArrayList<Item> items;

    public Knapsack(int wmax, int[] profit, int[] weight) {
        this.wmax = wmax;
        this.items = new ArrayList<>();
        for (int i = 0; i < profit.length; i++) {
            items.add(new Item(profit[i], weight[i]));
        }
    }

    public void fractional() {
        System.out.println("Fractional Knapsack");
        items.sort(null);
        Collections.reverse(items);
        float weight = 0;
        float value = 0;
        while (!items.isEmpty() && weight < wmax) {
            Item current = items.remove(0);
            if (current.getWeight() + weight <= wmax) {
                weight += current.getWeight();
                System.out.println("Added " + current);
                value += current.getProfit();
            } else {
                float fract = 1.0f * (wmax - weight) / current.getWeight();
                System.out.println("Added " + fract * 100 + "% of " + current);
                value += current.getProfit() * fract;
                weight += fract * current.getWeight();
            }
        }
        System.out.println("Value of knapsack:" + value);
    }

    public void zeroOne() {
        System.out.println("Binary Knapsack");
        int[][] a = new int[1 + items.size()][1 + wmax];
        for (int i = 1; i <= items.size(); i++) {
            Item curr = items.get(i - 1);
            for (int j = 1; j <= wmax; j++) {
                if (curr.getWeight() <= j) {
                    a[i][j] = Math.max(curr.getProfit() + a[i - 1][j - curr.getWeight()], a[i - 1][j]);
                }else{
                    a[i][j] = a[i-1][j];
                }
            }
        }
        for (int i = 0; i <= items.size(); i++) {
            for (int j = 0; j <= wmax; j++) {
                System.out.print(a[i][j] + "\t");
            }
            System.out.println();
        }
        //traceback
        int p=items.size(),q=wmax;
        System.out.println("Value of knapsack: " + a[p][q]);
        while(p>0 && q>0){
            if(a[p][q]!=a[p-1][q]){
                System.out.println("Added " + items.get(p-1));
                p--;
                q -= items.get(p).getWeight();
            }else{
                p--;
            }
        }
    }

    public static void main(String args[]) {
        int w[] = {5, 4, 6, 3};
        int p[] = {10, 40, 30, 50};
        Knapsack k = new Knapsack(10, p, w);
        k.zeroOne();
        k.fractional();
    }
}
