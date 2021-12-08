package cs146.project4.shohin;

public interface Visitor<Key extends Comparable<Key>> {
    /**
     * This method is called at each node.
     *
     * @param visitKeyNode the visited node
     */
    void visit(Node<Key> visitKeyNode);
}
