package cs146.project4.shohin;

public class RedBlackTree<Key extends Comparable<Key>> {

    public boolean isLeaf(Node<String> node) {
        if (node.equals(Node.root) && node.leftChild == null && node.rightChild == null) return true;
        if (node.equals(Node.root)) return false;
        return node.leftChild == null && node.rightChild == null;
    }


    public void visit(Node<Key> node) {
        System.out.println(node.key);
    }

    public void printTree() {  //preorder: visit, go left, go right
        Node<String> currentNode = Node.root;
        printTree(currentNode);
    }

    public void printTree(Node<String> node) {
        System.out.print(node.key);
        if (node.isLeaf()) {
            return;
        }

        if (node.leftChild != null) {
            printTree(node.leftChild);
        }
        if (node.rightChild != null) {
            printTree(node.rightChild);
        }
    }

    // place a new node in the RB tree with data the parameter and color it red.
// place a new node in the RB tree with data the parameter and color it red.
    public void addNode(String data) {    //this < that  <0.  this > that  >0
        Node<String> rover = null; // holder
        Node<String> current = Node.root;
        Node<String> addedNode = new Node<>(data); // added node
        while (current != null) {
            rover = current;
            if (addedNode.compareTo(current) < 0) //if added node is less than current, go to current's left child
            {
                current = current.leftChild;
            } else {
                current = current.rightChild; //if added node is greater than current, go to currents right child
            }
        }
        addedNode.parent = rover;
        if (rover == null) {
            Node.root = addedNode;
        } else if (addedNode.compareTo(rover) < 0) {
            rover.leftChild = addedNode;
        } else {
            rover.rightChild = addedNode;
        }
        addedNode.leftChild = null;
        addedNode.rightChild = null;
        addedNode.color = 0;
        fixTree(addedNode);
    }


    public void insert(String data) {
        addNode(data);
    }

    //Search up a node in the RBT
    //Recursively searches for a node in RBT
    public Node<String> lookup(Node<String> current, String k) {
        Node<String> searchedNode = new Node<>(k);
        if (current == null || searchedNode.compareTo(current) == 0) {
            return current;
        }
        if (searchedNode.compareTo(current) < 0) {
            return lookup(current.leftChild, k);
        }
        return lookup(current.rightChild, k);
    }

    //Returns the sibling of a node in the RBT
    public Node<String> getSibling(Node<String> n) {
        Node<String> parentOfN = n.parent;
        if (parentOfN != null) {
            if (parentOfN.compareTo(n) < 0) {
                return parentOfN.leftChild;
            } else if (parentOfN.compareTo(n) > 0) {
                return parentOfN.rightChild;
            }
        }
        return null;
    }

    //Returns the aunt of a node in the RBT
    public Node<String> getAunt(Node<String> n) {
        Node<String> grandparent = getGrandparent(n);
        if (grandparent != null) {
            if (grandparent.compareTo(n) < 0) {
                return grandparent.leftChild;
            } else if (grandparent.compareTo(n) > 0) {
                return grandparent.rightChild;
            }
        }
        return null;
    }

    //Returns the grandparent of a node in the RBT
    public Node<String> getGrandparent(Node<String> n) {
        if (n.parent != null) {
            if (n.parent.parent != null) {
                return n.parent.parent;
            }
        }
        return null;
    }

    public void rotateLeft(Node<String> node) {

        Node<String> rightChildOfNode = node.rightChild;
        node.rightChild = rightChildOfNode.leftChild;            //turns y's left subtree into x's right subtree
        if (rightChildOfNode.leftChild != null) {
            rightChildOfNode.leftChild.parent = node;
        }
        rightChildOfNode.parent = node.parent;                    //links x's parents to y
        if (node.parent == null) {
            Node.root = rightChildOfNode;
        } else if (node == node.parent.leftChild) {
            node.parent.leftChild = rightChildOfNode;
        } else {
            node.parent.rightChild = rightChildOfNode;

        }
        rightChildOfNode.leftChild = node;                    //put x on y's left
        node.parent = rightChildOfNode;
    }

    public void rotateRight(Node<String> node) {
        //right rotation is the reverse of left rotation
        Node<String> leftChildOfNode = node.leftChild;
        node.leftChild = leftChildOfNode.rightChild;
        if (leftChildOfNode.rightChild != null) {
            leftChildOfNode.rightChild.parent = node;
        }
        leftChildOfNode.parent = node.parent;
        if (node.parent == null) {
            Node.root = leftChildOfNode;
        } else if (node == node.parent.rightChild) {
            node.parent.rightChild = leftChildOfNode;
        } else {
            node.parent.leftChild = leftChildOfNode;

        }
        leftChildOfNode.rightChild = node;
        node.parent = leftChildOfNode;
    }

    //Followed the pseudocode in the book, turned it into code
    public void fixTree(Node<String> current) {
        if (current == Node.root) {
            current.color = 1;
        } else if (current.color == 0 && current.parent.color == 0) {
            if (getAunt(current) == null || getAunt(current).color == 1) {
                if (current.parent == getGrandparent(current).leftChild && current == current.parent.rightChild) {
                    rotateLeft(current.parent);
                    current = current.parent;
                    fixTree(current);
                } else if (current.parent == getGrandparent(current).rightChild && current == current.parent.leftChild) {
                    rotateRight(current.parent);
                    current = current.parent;
                    fixTree(current);
                } else if (current.parent == getGrandparent(current).leftChild && current == current.parent.leftChild) {
                    current.parent.color = 1;
                    getGrandparent(current).color = 0;
                    rotateRight(getGrandparent(current));
                } else if (current.parent == getGrandparent(current).rightChild && current == current.parent.rightChild) {
                    current.parent.color = 1;
                    getGrandparent(current).color = 0;
                    rotateLeft(getGrandparent(current));
                }
            } else {
                current.parent.color = 1;
                getAunt(current).color = 1;
                getGrandparent(current).color = 0;
                current = getGrandparent(current);
                fixTree(current);
            }
        }
    }

    public boolean isEmpty(Node<String> node) {
        return node.key == null;
    }

    public boolean isLeftChild(Node<String> parent, Node<String> child) {
        //child is less than parent
        return child.compareTo(parent) < 0;
    }


    public void preOrderVisit(Visitor<String> traverse) {
        preOrderVisit(Node.root, traverse);
    }


    private static void preOrderVisit(Node<String> node, Visitor<String> traverse) {
        if (node != null) {
            traverse.visit(node);
            preOrderVisit(node.leftChild, traverse);
            preOrderVisit(node.rightChild, traverse);
        }
    }


    public static void main(String[] args) {

        RedBlackTree RBT;
        RBT = new RedBlackTree();

        RBT.insert("D");
        RBT.insert("B");
        RBT.insert("A");
        RBT.insert("Q");
        RBT.insert("F");
        RBT.insert("E");
        RBT.insert("C");
        RBT.insert("G");
        RBT.insert("I");
        RBT.insert("J");
        RBT.printTree();
        System.out.println(" is RBT");

        System.out.println(" ");

        RBT.insert("D");
        RBT.insert("B");
        RBT.insert("A");
        RBT.insert("C");
        RBT.insert("F");
        RBT.insert("E");
        RBT.insert("H");
        RBT.insert("G");
        RBT.insert("I");
        RBT.insert("J");
        RBT.printTree();
        System.out.println(" is Rotated Left");

        RBT.rotateRight(Node.root);

        System.out.println(" ");

        RBT.printTree();
        System.out.println(" is Rotated Right");
        System.out.println(" ");

    }


}



