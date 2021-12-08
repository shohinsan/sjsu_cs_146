package cs146.project4.shohin;

public class Node<Key extends Comparable<Key>> {

    public static Node<String> root;

    Key key;
    Node<String> parent;
    Node<String> leftChild;
    Node<String> rightChild;
    boolean isRed;
    int color;

    public Node(Key data) {
        this.key = data;
        leftChild = null;
        rightChild = null;
    }

    public static Node<String> getRoot() {
        return root;
    }

    public static void setRoot(Node<String> root) {
        Node.root = root;
    }

    public int compareTo(Node<Key> n) {    //this < that  <0
        return key.compareTo(n.key);    //this > that  >0
    }

    public boolean isLeaf() {
        if (this.equals(getRoot()) && this.leftChild == null && this.rightChild == null) return true;
        if (this.equals(getRoot())) return false;
        return this.leftChild == null && this.rightChild == null;
    }
    public boolean isLeaf(Node<String> n) {
        if (n.equals(root) && n.leftChild == null && n.rightChild == null) return true;
        if (n.equals(root)) return false;
        return n.leftChild == null && n.rightChild == null;
    }

}