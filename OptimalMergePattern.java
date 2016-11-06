
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author jaskaran
 */
class Node {

    private int weight;
    private Node left, right;

    public Node(int weight) {
        this.weight = weight;
        left = right = null;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
}

class Tree implements Comparable, Comparator {

    private Node root;

    public Node getRoot() {
        return root;
    }

    public Tree(int n) {
        root = new Node(n);
    }

    public Tree join(Tree t) {
        Tree newTree = new Tree(0);
        newTree.root.setWeight(root.getWeight() + t.root.getWeight());
        Node newLeft = root.getLeft(), newRight = root.getRight();
        if (newLeft != null && newRight != null && newLeft.getWeight() > newRight.getWeight()) {
            Node tem = newLeft;
            newLeft = newRight;
            newRight = tem;
        }
        newTree.root.setLeft(newLeft);
        newTree.root.setRight(newRight);
        return newTree;
    }

    @Override
    public int compareTo(Object o) {
        Tree t = (Tree) o;
        if (t.root.getWeight() < this.root.getWeight()) {
            return 1;
        } else if (t.root.getWeight() > this.root.getWeight()) {
            return -1;
        }
        return 0;
    }

    @Override
    public int compare(Object o1, Object o2) {
        Tree t = (Tree) o1;
        Tree u = (Tree) o2;
        if (t.root.getWeight() < u.root.getWeight()) {
            return 1;
        } else if (t.root.getWeight() > u.root.getWeight()) {
            return -1;
        }
        return 0;
    }
}

public class OptimalMergePattern {

    public static void main(String args[]) {
        int sizes[] = {10, 5, 74, 56, 96, 14, 8};
        ArrayList<Tree> set = new ArrayList<>();
        for (int i = 0; i < sizes.length; i++) {
            set.add(i, new Tree(sizes[i]));
        }
        int effort = 0;
        while (set.size() >= 2) {
            set.sort(null);
            Tree t1 = set.remove(0);
            Tree t2 = set.remove(0);
            Tree combine = t1.join(t2);
            System.out.println("Join: " + t1.getRoot().getWeight() + " and "+ t2.getRoot().getWeight());
            effort += combine.getRoot().getWeight();
            set.add(combine);
        }
        System.out.println(effort);

    }
}
