
import java.util.Arrays;

/**
 *
 * @author Jaskaran
 */
public class Quicksort {

    private static int[] arrayGenerator(int size, int range) {
        int ar[] = new int[size];
        for (int i = 0; i < size; i++) {
            ar[i] = (int) (Math.random() * range + 1);
        }
        return ar;
    }

    private static void swap(int a[], int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void randomQuicksort(int ar[], int left, int right) {
        if (left > right) {
            return;
        }
        int pivIndex = left + (int) (Math.random() * ((right - left) + 1));
        swap(ar, pivIndex, right);
        int piv = ar[right];
        int i = left - 1;
        for (int j = left; j <= right - 1; j++) {
            if (ar[j] <= piv) {
                i++;
                swap(ar, i, j);
            }
        }
        swap(ar, i + 1, right);
        int j = i + 1; //partition
        randomQuicksort(ar, left, j - 1);
        randomQuicksort(ar, j + 1, right);
    }

    public static void middleQuicksort(int ar[], int left, int right) {
        if (left > right) {
            return;
        }
        int pivIndex = (left + right) / 2;
        swap(ar, pivIndex, right);
        int piv = ar[right];
        int i = left - 1;
        for (int j = left; j <= right - 1; j++) {
            if (ar[j] <= piv) {
                i++;
                swap(ar, i, j);
            }
        }
        swap(ar, i + 1, right);
        int j = i + 1; //partition
        middleQuicksort(ar, left, j - 1);
        middleQuicksort(ar, j + 1, right);
    }

    public static void main(String[] args) {
        int ar[] = {7,1,32,11,20,19,12,28,29,5,18,17,6,25,13,16,21,26,27,9,14,8,15,10,22,23,30,31,24,2,3,4};
        long start = System.nanoTime();
        middleQuicksort(ar, 0, ar.length - 1);
        System.out.println((System.nanoTime() - start)/1000.0);
    }
}
